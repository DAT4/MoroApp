package dtu.android.moroapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.models.Event
import dtu.android.moroapp.utils.EventFilters
import dtu.android.moroapp.utils.EventFilters.*
import dtu.android.moroapp.utils.EventFiltersListStuff
import dtu.android.moroapp.utils.EventFiltersListStuff.*
import dtu.android.moroapp.utils.GraphQL.Filter
import kotlinx.coroutines.launch

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {
    private val _events: MutableLiveData<Resource<List<Event>>> by lazy {
        MutableLiveData<Resource<List<Event>>>().also {
            loadEvents()
        }
    }

    val events get() = _events as LiveData<Resource<List<Event>>>


    fun filterEvents(filter: Filter) {
        events.value?.data?.let {
            var tmpList = it
            filter.filters.forEach { filter ->
                if (filter.key is EventFiltersListStuff){
                    tmpList = when (filter.key as EventFiltersListStuff) {
                        PLACE -> tmpList.filter { event -> (filter.value as List<*>).contains(event.location.place) }
                        AREA -> tmpList.filter { event -> (filter.value as List<*>).contains(event.location.area) }
                        CATEGORY -> tmpList.filter { event -> (filter.value as List<*>).any{it in event.category} }
                        GENRE -> tmpList.filter { event -> (filter.value as List<*>).contains(event.genre) }
                    }
                } else {
                    tmpList = when (filter.key as EventFilters) {
                        TITLE -> tmpList.filter { event -> event.title.contains(filter.value as String, true) }
                        PRICELT -> tmpList.filter { event -> event.price <= filter.value as Int }
                        PRICEGT -> tmpList.filter { event -> event.price >= filter.value as Int }
                        TIMELT -> tmpList.filter { event -> event.time <= filter.value as Int }
                        TIMEGT -> tmpList.filter { event -> event.time >= filter.value as Int }
                    }
                }
            }
            _events.postValue(Resource.Success(tmpList))
        }
    }


    private fun loadEvents() = viewModelScope.launch {
        _events.postValue(Resource.Loading())
        val response = eventRepository.getEvents(load())
        _events.postValue(response)
    }


    private fun load(): Filter {
        val t = System.currentTimeMillis() / 1000
        return Filter.Builder()
                .filters(TIMEGT, t)
                .build()
    }
}
package dtu.android.moroapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.models.Event
import kotlinx.coroutines.launch

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {
    private val _events: MutableLiveData<Resource<List<Event>>> by lazy {
        MutableLiveData<Resource<List<Event>>>().also {
            loadEvents()
        }
    }

    val events get() = _events as LiveData<Resource<List<Event>>>


    private fun loadEvents() = viewModelScope.launch {
        _events.postValue(Resource.Loading())
        val response = eventRepository.getEvents(loadFilter())
        _events.postValue(response)
    }


    private fun loadFilter(): List<Filter> {
        val t = System.currentTimeMillis() / 1000
        return listOf(Filter.ExclusiveFilter.TimeGTFilter(t))
    }
}
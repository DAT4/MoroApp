package dtu.android.moroapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.models.FindEventModel
import dtu.android.moroapp.models.event.Event
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {
    private val _events: MutableLiveData<Resource<List<Event>>> by lazy {
        MutableLiveData<Resource<List<Event>>>()
    }

    val events get() = _events as LiveData<Resource<List<Event>>>

    fun loadEvents(filters: List<Filter> = listOf()) = viewModelScope.launch {
        _events.postValue(Resource.Loading())
        val response = eventRepository.getEvents(filters)
        _events.postValue(response)
    }

    fun filterExistingEvents(filters: List<Filter>) : List<Event>{
        return (events.value?.data?.applyFilters(filters) ?: emptyList()).also {
            FindEventModel.instance.clear()
        }
    }

}
package dtu.android.moroapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dtu.android.moroapp.api.GQLQuery
import dtu.android.moroapp.api.GQLResponse
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.models.Event
import dtu.android.moroapp.utils.EventFilters
import dtu.android.moroapp.utils.GraphQL.Filter
import dtu.android.moroapp.utils.events
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {
    val events: MutableLiveData<Resource<List<Event>>> = MutableLiveData()

    init {
        Log.i("EventViewModel", "Created")
    }

    fun loadEvents() = viewModelScope.launch {
        events.postValue(Resource.Loading())
        val response = eventRepository.getEvents(load())
        events.postValue(handleGetEvents(response))
    }

    fun getAllEvents() : LiveData<Resource<List<Event>>> {
        return events
    }

    fun getFilteredEvents(filter: Filter) = viewModelScope.launch {
        val query = makeQuery(filter)
        events.postValue(Resource.Loading())
        val response = eventRepository.getEvents(query)
        events.postValue(handleGetEvents(response))
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("EventViewModel", "ViewModel Destroyed")
    }

    private fun handleGetEvents(response: Response<GQLResponse>): Resource<List<Event>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                println("HER ER LISTEN ${resultResponse.data.events}")
                return Resource.Success(resultResponse.data.events)
            }
        }
        return Resource.Error(response.message())
    }

    private fun makeQuery(filter: Filter): GQLQuery {
        return GQLQuery(
                "${
                    events(filter) {
                        title
                        genre
                        image
                        category
                        link
                        tickets
                        other
                        price
                        text
                        time
                        location {
                            area
                            place
                            address {
                                city
                                street
                                no
                                state
                                zip
                            }
                            coordinates {
                                longitude
                                latitude
                            }
                        }
                    }
                }"
        )
    }

    private fun load(): GQLQuery {
        val t = System.currentTimeMillis() / 1000
        val filter = Filter.Builder()
                .filters(EventFilters.TIMEGT, 1601146800)
                .build()

        return makeQuery(filter)
    }
}
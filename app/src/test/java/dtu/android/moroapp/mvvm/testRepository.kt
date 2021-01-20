package dtu.android.moroapp.mvvm

import androidx.lifecycle.MutableLiveData
import dtu.android.moroapp.api.GQLResponse
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.models.event.Address
import dtu.android.moroapp.models.event.Coordinates
import dtu.android.moroapp.models.event.Event
import dtu.android.moroapp.models.event.Location
import retrofit2.Response

class testRepository : IEventRepository {

    private val events = mutableListOf<Event>()
    private val observableEvents = MutableLiveData<List<Event>>(events)
    private val cacheList = mutableListOf<Event>()
    private val testEvent = Event("John Test spiller", "Musik", "image/src",
            "someurl.com", listOf("Gratis", "Koncert"), 0,
            "John test tester sit udstyr", "", 6987262, Location("Indre By", "", Address("", "", "", "", 2300), Coordinates(2f,2f)))
    private val cacheEvent = Event("Johny Cache spiller", "Musik", "image/src",
            "someurl.com", listOf("Gratis", "Koncert"), 0,
            "John test tester sit udstyr", "", 6987262, Location("Indre By", "", Address("", "", "", "", 2300), Coordinates(2f,2f)))
    private var shouldReturnNetworkError = false

    fun makeCache() {
        cacheList.add(cacheEvent)
    }

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }


    override suspend fun getEvents(filters: List<Filter>): Resource<List<Event>> {
        return if (shouldReturnNetworkError) {
            Resource.Error("Error", null)
        } else {
            return if (cacheList.isEmpty()) {
                events.add(testEvent)
                Resource.Success(events)
            } else {
                Resource.Success(cacheList)
            }
        }
    }

    override fun handleGetEvents(response: Response<GQLResponse>): Resource<List<Event>> {
        TODO("Not yet implemented")
    }
}
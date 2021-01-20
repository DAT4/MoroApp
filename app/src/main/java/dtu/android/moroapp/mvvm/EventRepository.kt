package dtu.android.moroapp.mvvm

import api.gql.events
import dtu.android.moroapp.api.GQLQuery
import dtu.android.moroapp.api.GQLResponse
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.api.RetrofitInstance
import dtu.android.moroapp.models.event.Event
import dtu.android.moroapp.mvvm.EventsCache.cacheList
import retrofit2.Response

class EventRepository(private val db: EventDatabase) {

    suspend fun getEvents(filters: List<Filter> = listOf()): Resource<List<Event>> {
        return if (cacheList.isEmpty()) {
            handleGetEvents(RetrofitInstance.api.getEvents(makeQuery(filters)))
        } else {
            Resource.Success(cacheList.applyFilters(filters))
        }
    }

    private fun makeQuery(filters: List<Filter>) = GQLQuery(
            "${
                events(filters) {
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

    private fun handleGetEvents(response: Response<GQLResponse>): Resource<List<Event>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                EventsCache.cacheList = resultResponse.data.events
                return Resource.Success(resultResponse.data.events)
            }
        }
        return Resource.Error(response.message())
    }
}
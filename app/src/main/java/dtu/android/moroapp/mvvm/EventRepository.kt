package dtu.android.moroapp.mvvm

import dtu.android.moroapp.api.GQLQuery
import dtu.android.moroapp.api.GQLResponse
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.api.RetrofitInstance
import dtu.android.moroapp.models.Event
import dtu.android.moroapp.mvvm.EventsCache.cacheList
import dtu.android.moroapp.utils.EventFilters
import dtu.android.moroapp.utils.EventFilters.*
import dtu.android.moroapp.utils.EventFiltersListStuff
import dtu.android.moroapp.utils.EventFiltersListStuff.*
import dtu.android.moroapp.utils.GraphQL.Filter
import dtu.android.moroapp.utils.GraphQL.Query
import dtu.android.moroapp.utils.events
import retrofit2.Response

class EventRepository(private val db: EventDatabase) {

    suspend fun getEvents(filter: Filter): Resource<List<Event>> {
        return if (cacheList.isEmpty()) {
            handleGetEvents(RetrofitInstance.api.getEvents(makeQuery(filter)))
        } else {
            Resource.Success(cacheList)
        }
    }

    private fun makeQuery(filter: Filter) = GQLQuery(
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

    fun filter(query: Filter, eventsIn: List<Event>): List<Event> {
        var events = eventsIn
        query.filters.forEach { filter ->
            if (filter.key is EventFiltersListStuff){
                events = when (filter.key as EventFiltersListStuff) {
                    PLACE -> events.filter { event -> (filter.value as List<*>).contains(event.location.place) }
                    AREA -> events.filter { event -> (filter.value as List<*>).contains(event.location.area) }
                    CATEGORY -> events.filter { event -> (filter.value as List<*>).any{it in event.category} }
                    GENRE -> events.filter { event -> (filter.value as List<*>).contains(event.genre) }
                }
            } else {
                events = when (filter.key as EventFilters) {
                    TITLE -> events.filter { event -> event.title.contains(filter.value as String, true) }
                    PRICELT -> events.filter { event -> event.price <= filter.value as Int }
                    PRICEGT -> events.filter { event -> event.price >= filter.value as Int }
                    TIMELT -> events.filter { event -> event.time <= filter.value as Int }
                    TIMEGT -> events.filter { event -> event.time >= filter.value as Int }
                }
            }
        }
        return events
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
}
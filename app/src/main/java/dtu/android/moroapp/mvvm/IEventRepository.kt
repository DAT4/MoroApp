package dtu.android.moroapp.mvvm

import dtu.android.moroapp.api.GQLQuery
import dtu.android.moroapp.api.GQLResponse
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.models.event.Event
import retrofit2.Response

interface IEventRepository {
    suspend fun getEvents(filters: List<Filter> = listOf()) :Resource<List<Event>>

    fun handleGetEvents(response: Response<GQLResponse>) : Resource<List<Event>>
}
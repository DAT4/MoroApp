package dtu.android.moroapp.mvvm

import dtu.android.moroapp.api.GQLQuery
import dtu.android.moroapp.api.RetrofitInstance

class EventRepository(private val db: EventDatabase) {

    suspend fun getEvents(query: GQLQuery) = RetrofitInstance.api.getEvents(query)
}
package dtu.android.moroapp.api

import dtu.android.moroapp.models.Event
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface API {
    @POST("/moro/api")
    suspend fun getEvents(@Body query: GQLQuery) : Response<GQLResponse>


}

data class GQLQuery(val query: String)
data class GQLData(val events: List<Event>, val errors: MutableList<GQLError>)
data class GQLResponse(val data: GQLData)
data class GQLError(val message: String)
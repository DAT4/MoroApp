package dtu.android.moroapp.utils.graphQL

data class GQL(val query: String)
data class Response<T>(val data: T, val errors: MutableList<GQLError>)
data class GQLError(val message: String)

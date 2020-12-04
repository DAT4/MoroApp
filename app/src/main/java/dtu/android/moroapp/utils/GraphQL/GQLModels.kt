package dtu.android.moroapp.utils.GraphQL

data class GQL(val query: String)
data class Response<T>(val data: T, val errors: MutableList<GQLError>)
data class GQLError(val message: String)

package api.gql.models

import api.gql.EdgeCase
import api.gql.Query

class GQLLocation : Query("location") {
    val area = object : EdgeCase(this, "area") {}
    val place = object : EdgeCase(this, "place") {}
    fun address(visit: GQLAddress.() -> Unit) = visitEntity(GQLAddress(), visit)
    fun coordinates(visit: GQLCoordinates.() -> Unit) = visitEntity(GQLCoordinates(), visit)
}
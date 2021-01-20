package api.gql.models

import api.gql.EdgeCase
import api.gql.Query

class GQLCoordinates : Query("coordinates") {
    val longitude = object : EdgeCase(this, "longitude") {}
    val latitude = object : EdgeCase(this, "latitude") {}
}
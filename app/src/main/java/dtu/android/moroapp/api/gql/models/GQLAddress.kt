package api.gql.models

import api.gql.EdgeCase
import api.gql.Query

class GQLAddress : Query("address") {
    val city = object : EdgeCase(this, "city") {}
    val street = object : EdgeCase(this, "street") {}
    val no = object : EdgeCase(this, "no") {}
    val state = object : EdgeCase(this, "state") {}
    val zip = object : EdgeCase(this, "zip") {}
}
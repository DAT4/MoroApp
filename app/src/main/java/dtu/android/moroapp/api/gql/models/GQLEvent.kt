package api.gql.models

import api.gql.EdgeCase
import api.gql.MotherCase
import dtu.android.moroapp.mvvm.Filter

class GQLEvent(filters: List<Filter>) : MotherCase(filters, "events") {

    val title = object : EdgeCase(this, "title") {}
    val genre = object : EdgeCase(this, "genre") {}
    val image = object : EdgeCase(this, "image") {}
    val category = object : EdgeCase(this, "category") {}
    val link = object : EdgeCase(this, "link") {}
    val other = object : EdgeCase(this, "other") {}
    val price = object : EdgeCase(this, "price") {}
    val text = object : EdgeCase(this, "text") {}
    val tickets = object : EdgeCase(this, "tickets") {}
    val time = object : EdgeCase(this, "time") {}

    fun location(visit: GQLLocation.() -> Unit) = visitEntity(GQLLocation(), visit)
}
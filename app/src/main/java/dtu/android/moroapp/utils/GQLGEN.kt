package dtu.android.moroapp.utils

import dtu.android.moroapp.utils.graphQL.*


fun events(filter: Filter, visit: EVENTS.() -> Unit): EVENTS {
    val events = EVENTS(filter)
    events.visit()
    return events
}

class EVENTS(private val filter: Filter) : MotherCase(filter, "events") {

    val title = object : EdgeCase(this,"title") {}
    val genre = object : EdgeCase(this,"genre") {}
    val image = object : EdgeCase(this, "image") {}
    val link = object : EdgeCase(this, "link") {}
    val other = object : EdgeCase(this, "other") {}
    val price = object : EdgeCase(this, "price") {}
    val text = object : EdgeCase(this, "text") {}
    val tickets = object : EdgeCase(this, "tickets") {}
    val time = object : EdgeCase(this, "time") {}

    fun location(visit: LOCATION.() -> Unit) = visitEntity(LOCATION(), visit)
}

class LOCATION : Query("location") {
    val area  = object: EdgeCase(this,"area"){}
    val place = object: EdgeCase(this,"place"){}
    fun address(visit: ADDRESS.() -> Unit) = visitEntity(ADDRESS(), visit)
    fun coordinates(visit: COORDINATES.() -> Unit) = visitEntity(COORDINATES(), visit)
}

class ADDRESS : Query("address") {
    val city = object : EdgeCase(this,"city"){}
    val street = object : EdgeCase(this,"street"){}
    val no = object : EdgeCase(this,"no"){}
    val state = object : EdgeCase(this,"state"){}
    val zip = object : EdgeCase(this,"zip"){}
}

class COORDINATES : Query("coordinates") {
    val longitude = object : EdgeCase(this,"longitude"){}
    val latitude = object : EdgeCase(this,"latitude"){}
}

enum class EventFilters(val str: String): FilterType {
    PLACE("place"),
    PRICELT("priceLT"),
    PRICEGT("priceGT"),
    TIMELT("timestampLT"),
    TIMEGT("timestampGT"),
    AREA("area"),
    TITLE("title"),
    GENRE("genre");
    override fun str(): String = str
}



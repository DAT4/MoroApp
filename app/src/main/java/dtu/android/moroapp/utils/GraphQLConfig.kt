package dtu.android.moroapp.utils

import com.google.gson.reflect.TypeToken
import dtu.android.moroapp.utils.GraphQL.*
import java.lang.reflect.Type

/**
 * Klasserne i denne configuration bliver brugt til at gennerere
 * en implementation af det sprog som man skal bruge til at lave
 * de graphql queries som passer til modellerne i systemet.
 *
 * NOTE: Det ville være mega smart hvis man kan gennerere de her
 * classer automatisk ud fra modellerne i systemet.
 *
 */

data class EventList(val events: MutableList<dtu.android.moroapp.models.Event>)

fun getEventListResponseType(): Type = object : TypeToken<Response<EventList>>() {}.type

fun events(filter: Filter, visit: Event.() -> Unit): Event {
    val events = Event(filter)
    events.visit()
    return events
}


class Event(filter: Filter) : MotherCase(filter, "events") {

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

    fun location(visit: Location.() -> Unit) = visitEntity(Location(), visit)
}

class Location : Query("location") {
    val area = object : EdgeCase(this, "area") {}
    val place = object : EdgeCase(this, "place") {}
    fun address(visit: Address.() -> Unit) = visitEntity(Address(), visit)
    fun coordinates(visit: Coordinates.() -> Unit) = visitEntity(Coordinates(), visit)
}

class Address : Query("address") {
    val city = object : EdgeCase(this, "city") {}
    val street = object : EdgeCase(this, "street") {}
    val no = object : EdgeCase(this, "no") {}
    val state = object : EdgeCase(this, "state") {}
    val zip = object : EdgeCase(this, "zip") {}
}

class Coordinates : Query("coordinates") {
    val longitude = object : EdgeCase(this, "longitude") {}
    val latitude = object : EdgeCase(this, "latitude") {}
}

enum class EventFilters(val str: String) : FilterType {
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



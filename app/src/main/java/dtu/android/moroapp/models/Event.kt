package dtu.android.moroapp.models

import java.io.Serializable

data class data(val data: allEvents)

data class allEvents(val allEvents: List<Event>)

data class Event(
        val title :String,
        val genre : String,
        val image : String,
        val link :String,
        val other : MutableList<String>,
        val price : Int,
        val text : String,
        val tickets : String,
        val location: Location,
        val time : Long,
): Serializable

data class Address(
        val city : String,
        val street : String,
        val no : String,
        val state : String,
        val zip : Int,
)

data class Coordinates(
        val longitude : Float,
        val latitude : Float,
)

data class Location(
        val area: String,
        val address: Address,
        val place : String,
        val coordinates: Coordinates,
)

data class Query(
        val query: String,
)

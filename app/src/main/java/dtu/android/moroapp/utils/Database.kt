package dtu.android.moroapp.utils

import com.google.gson.Gson
import dtu.android.moroapp.models.Event
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

inline fun <reified T> postStuff(query: Any, url: String): T{
    val req = URL(url)
    val con = req.openConnection() as HttpURLConnection
    con.requestMethod = "POST"
    con.connectTimeout = 30000
    con.doOutput = true
    val json = Gson().toJson(query)
    val data = (json).toByteArray()
    con.setRequestProperty("Content-Type", "application/json")
    val request = DataOutputStream(con.outputStream)
    request.write(data)
    request.flush()
    con.inputStream.bufferedReader().use {
        val response = StringBuffer()
        var inputLine = it.readLine()
        while (inputLine != null) {
            response.append(inputLine)
            inputLine = it.readLine()
        }
        return Gson().fromJson(response.toString(), T::class.java)
    }
}

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

@DslMarker //Domain Specific Language
annotation class GraphQLMarker


@GraphQLMarker
abstract class Query(val name: String) : Element {
    val children = arrayListOf<Element>()
    protected fun <T : Element> visitEntity(entity: T, visit: T.() -> Unit): T {
        entity.visit()
        children.add(entity)
        return entity
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$name")
        if (children.isNotEmpty()) {
            builder.append("{\n")
            for (c in children) {
                c.render(builder, "$indent  ")
            }
            builder.append("$indent}")
        }
        builder.append("\n")
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

class EVENTS(private val filter: Filter) : Query("events") {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("{$name")
        if (filter.filters.isNotEmpty()) {
            builder.append("(" + filter.filters.map {
                if (it.value is Int || it.value is Long) {
                    it.key.str + ":" + it.value + ","
                } else {
                    it.key.str + ":\"" + it.value + "\","
                }
            }.joinToString(" ").dropLast(1) + ")")
        }
        if (children.isNotEmpty()) {
            builder.append("{\n")
            for (c in children) {
                c.render(builder, "$indent  ")
            }
            builder.append("$indent}")
        }
        builder.append("\n}")
    }

    fun title(visit: TITLE.() -> Unit) = visitEntity(TITLE(), visit)
    fun genre(visit: GENRE.() -> Unit) = visitEntity(GENRE(), visit)
    fun image(visit: IMAGE.() -> Unit) = visitEntity(IMAGE(), visit)
    fun link(visit: LINK.() -> Unit) = visitEntity(LINK(), visit)
    fun other(visit: OTHER.() -> Unit) = visitEntity(OTHER(), visit)
    fun price(visit: PRICE.() -> Unit) = visitEntity(PRICE(), visit)
    fun text(visit: TEXT.() -> Unit) = visitEntity(TEXT(), visit)
    fun tickets(visit: TICKETS.() -> Unit) = visitEntity(TICKETS(), visit)
    fun time(visit: TIME.() -> Unit) = visitEntity(TIME(), visit)
    fun location(visit: LOCATION.() -> Unit) = visitEntity(LOCATION(), visit)
}

class Filter private constructor(val filters: MutableMap<FilterType, Any>) {
    class Builder {
        private val filters = mutableMapOf<FilterType, Any>()
        fun filters(key: FilterType, value: Any) = apply {
            this.filters[key] = value
        }

        fun build(): Filter {
            return Filter(filters)
        }
    }
}

fun events(filter: Filter, visit: EVENTS.() -> Unit): EVENTS {
    val events = EVENTS(filter)
    events.visit()
    return events
}

class TITLE : Query("title")
class GENRE : Query("genre")
class IMAGE : Query("image")
class LINK : Query("link")
class OTHER : Query("other")
class PRICE : Query("price")
class TEXT : Query("text")
class TICKETS : Query("tickets")
class TIME : Query("time")
class LOCATION : Query("location") {
    fun area(visit: AREA.() -> Unit) = visitEntity(AREA(), visit)
    fun place(visit: PLACE.() -> Unit) = visitEntity(PLACE(), visit)
    fun address(visit: ADDRESS.() -> Unit) = visitEntity(ADDRESS(), visit)
    fun coordinates(visit: COORDINATES.() -> Unit) = visitEntity(COORDINATES(), visit)
}

class AREA : Query("area")
class PLACE : Query("place")
class ADDRESS : Query("address") {
    fun city(visit: CITY.() -> Unit) = visitEntity(CITY(), visit)
    fun street(visit: STREET.() -> Unit) = visitEntity(STREET(), visit)
    fun no(visit: NO.() -> Unit) = visitEntity(NO(), visit)
    fun state(visit: STATE.() -> Unit) = visitEntity(STATE(), visit)
    fun zip(visit: ZIP.() -> Unit) = visitEntity(ZIP(), visit)
}

class CITY : Query("city")
class STREET : Query("street")
class NO : Query("no")
class STATE : Query("state")
class ZIP : Query("zip")
class COORDINATES : Query("coordinates") {
    fun longitude(visit: LONGITUDE.() -> Unit) = visitEntity(LONGITUDE(), visit)
    fun latitude(visit: LATITUDE.() -> Unit) = visitEntity(LATITUDE(), visit)
}

class LONGITUDE : Query("longitude")
class LATITUDE : Query("latitude")

enum class FilterType(val str: String) {
    PLACE("place"),
    PRICELT("priceLT"),
    PRICEGT("priceGT"),
    TIMELT("timestampLT"),
    TIMEGT("timestampGT"),
    AREA("area"),
    TITLE("title"),
    GENRE("genre")
}


data class GraphQL(val query: String)
data class Response(val data: Data, val errors: MutableList<GQLError>)
data class Data(val events: MutableList<Event>)
data class GQLError(val message: String)

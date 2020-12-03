package dtu.android.moroapp.utils

import com.google.gson.Gson
import dtu.android.moroapp.models.Event
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

inline fun <reified T> postStuff(query: Any, url: String): T {
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

abstract class EdgeCase(parent: Query, name: String) :Query(name) {
    init {
        parent.children.add(this)
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

class LOCATION : Query("location") {
    val area  = object:EdgeCase(this,"area"){}
    val place = object:EdgeCase(this,"area"){}
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

data class GQL(val query: String)
data class Response(val data: Data, val errors: MutableList<GQLError>)
data class Data(val events: MutableList<Event>)
data class GQLError(val message: String)

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


class Query private constructor(val query: String) {
    class Builder {
        private var query = "{events"
        private var filters = mutableMapOf<Filter, Any>()
        private val elements = """
            title
            genre
            image
            link
            other
            price
            text
            tickets
            time
            location{
              area
              place
              address{
                city
                street
                no
                state
                street
                zip
              }
              coordinates{
                longitude
                latitude
              }
            }
        """.trimIndent()
        fun filter(key: Filter, value: Any) = apply {
            this.filters[key] = value
        }

        fun build() = Query(construct())

        private fun construct(): String {
            this.query = "{events"
            if (!filters.isEmpty()) {
                this.query += "("
                filters.forEach {
                    this.query += if (it.value is Int || it.value is Long) it.key.str+ ":" + it.value + "," else {
                        it.key.str+ ":\"" + it.value + "\","
                    }
                }
                this.query = this.query.dropLast(1) + ")"
            }
            this.query += "{$elements}}"
            println(this.query)
            return this.query
        }
    }
    enum class Filter(val str: String){
        PLACE("place"),
        PRICELT("priceLT"),
        PRICEGT("priceGT"),
        TIMELT("timestampLT"),
        TIMEGT("timestampGT"),
        AREA("area"),
        TITLE("title")
    }
}

data class Response(val data: Data, val errors: List<GQLError>)
data class Data(val events: List<Event>)
data class GQLError(val message: String)
package dtu.android.moroapp.utils

import com.google.gson.Gson
import dtu.android.moroapp.models.Event
import java.io.DataOutputStream
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.URL

inline fun <reified T> postStuff(query: Any, url: String): T? {
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
    try {
        con.inputStream.bufferedReader().use {
            val response = StringBuffer()
            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }
            return Gson().fromJson(response.toString(), T::class.java)
        }
    } catch (e: FileNotFoundException) {
        return null
    }
}


data class GQL(val query: String)
data class Response(val data: Data, val errors: MutableList<GQLError>)
data class Data(val events: MutableList<Event>)
data class GQLError(val message: String)

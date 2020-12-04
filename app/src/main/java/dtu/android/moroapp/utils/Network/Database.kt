package dtu.android.moroapp.utils.Network

import com.google.gson.Gson
import java.io.DataOutputStream
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL

inline fun <reified T> postStuff(query: Any, url: String, type: Type): T{
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
        return Gson().fromJson(response.toString(), type)
    }
}

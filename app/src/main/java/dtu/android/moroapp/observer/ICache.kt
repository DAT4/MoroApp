package dtu.android.moroapp.observer

import dtu.android.moroapp.models.Event
import dtu.android.moroapp.utils.Network.postStuff
import dtu.android.moroapp.utils.graphQL.GQL
import dtu.android.moroapp.utils.graphQL.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Type

interface ICache<T> : IObservable {
    val url: String

    var content: T

    fun cache(query: GQL, type: Type) {
        println(query)
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<T> = postStuff(query, url, type)
            launch(Dispatchers.Main) {
                this@ICache.content = response.data
                sendUpdateEvent()
            }
        }
    }
}
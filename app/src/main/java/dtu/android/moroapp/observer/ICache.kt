package dtu.android.moroapp.observer

import android.widget.Toast
import dtu.android.moroapp.utils.GQL
import dtu.android.moroapp.utils.Response
import dtu.android.moroapp.utils.postStuff
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NullPointerException

interface ICache : IObservable {
    val url: String

    var content: MutableList<*>

    fun cache(query: GQL) {
        GlobalScope.launch(Dispatchers.IO) {
            val data: Response? = postStuff(query, url)
            launch(Dispatchers.Main) {
                this@ICache.content = data?.data?.events ?: ArrayList<Any>()
                sendUpdateEvent()
            }
        }
    }
}
package dtu.android.moroapp.observer

import dtu.android.moroapp.utils.Query
import dtu.android.moroapp.utils.Response
import dtu.android.moroapp.utils.postStuff
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface ICache : IObservable {
    val url: String

    var content: MutableList<*>

    fun cache(query: Query) {
        GlobalScope.launch(Dispatchers.IO) {
            val data: Response = postStuff(query, url)
            launch(Dispatchers.Main) {
                this@ICache.content = data.data.events as MutableList<*>
                sendUpdateEvent()
            }
        }
    }
}
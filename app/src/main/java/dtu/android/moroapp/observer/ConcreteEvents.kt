package dtu.android.moroapp.observer

import dtu.android.moroapp.models.Event

object ConcreteEvents : ICache {
    override val url: String = "https://mama.sh/moro/api"

    override var content: MutableList<*> = ArrayList<Event>()
    override val observers: ArrayList<IObserver> = ArrayList()

    fun getAllEvents(): MutableList<*> {
        return this.content
    }

    fun setEvents(events: MutableList<Event>) {
        this.content = events
        sendUpdateEvent()
    }
}
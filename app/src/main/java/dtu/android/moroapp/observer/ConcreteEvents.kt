package dtu.android.moroapp.observer

import dtu.android.moroapp.models.Event
import dtu.android.moroapp.utils.*

object ConcreteEvents : ICache {
    override val url: String = "https://mama.sh/moro/api"

    override var content: MutableList<*> = ArrayList<Event>()
    override val observers: ArrayList<IObserver> = ArrayList()

    fun getAllEvents(): MutableList<*> {
        return order(this.content as ArrayList<Event>)
    }

    fun setEvents(events: MutableList<Event>) {
        this.content = events
        sendUpdateEvent()
    }

    private fun order(scores: ArrayList<Event>): MutableList<Event> {
        scores.sortWith(kotlin.Comparator { lhs, rhs ->
            when {
                lhs.time > rhs.time -> 1
                lhs.time < rhs.time -> -1
                else -> 0
            }
        })
        return scores
    }

    fun load() {
        val t = System.currentTimeMillis() / 1000
        val filter = Filter.Builder()
                .filters(FilterType.TIMEGT, t)
                .build()
        cache(
                GQL(
                        "${
                            events(filter) {
                                title
                                genre
                                image
                                link
                                tickets
                                other
                                price
                                text
                                time
                                location {
                                    area
                                    place
                                    address {
                                        city
                                        street
                                        no
                                        state
                                        zip
                                    }
                                    coordinates {
                                        longitude
                                        latitude
                                    }
                                }
                            }
                        }"
                )
        )
    }
}


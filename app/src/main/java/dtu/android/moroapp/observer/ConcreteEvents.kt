package dtu.android.moroapp.observer

import dtu.android.moroapp.models.Event
import dtu.android.moroapp.utils.*
import dtu.android.moroapp.utils.graphQL.Filter
import dtu.android.moroapp.utils.graphQL.GQL

object ConcreteEvents : ICache<EventList> {
    override val url: String = "https://mama.sh/moro/api"

    override var content = EventList(ArrayList())

    override val observers: ArrayList<IObserver> = ArrayList()

    fun getAllEvents(): MutableList<Event> {
        return order(this.content.events)
    }

    private fun order(scores: MutableList<Event>): MutableList<Event> {
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
                .filters(EventFilters.TIMEGT, t)
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
        , getEventListResponseType())
    }

}


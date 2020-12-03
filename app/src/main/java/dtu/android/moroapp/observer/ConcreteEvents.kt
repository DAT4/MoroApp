package dtu.android.moroapp.observer

import dtu.android.moroapp.models.Event
import dtu.android.moroapp.utils.*
import dtu.android.moroapp.utils.graphQL.Filter

object ConcreteEvents : ICache {
    override val url: String = "https://mama.sh/moro/api"

    override var content: MutableList<*> = ArrayList<Event>()
    override val observers: ArrayList<IObserver> = ArrayList()

    fun getAllEvents(): MutableList<*> {
        return order(this.content as ArrayList<Event>)
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
        )
    }
}


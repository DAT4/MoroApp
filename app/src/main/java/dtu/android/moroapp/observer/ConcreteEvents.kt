package dtu.android.moroapp.observer

import dtu.android.moroapp.models.Event
import dtu.android.moroapp.utils.*
import dtu.android.moroapp.utils.GraphQL.Filter
import dtu.android.moroapp.utils.GraphQL.GQL

object ConcreteEvents : ICache<EventList> {
    override val url: String = "https://mama.sh/moro/api"

    override var content = EventList(ArrayList())

    override val observers: ArrayList<IObserver> = ArrayList()

    fun getAllEvents(): List<Event> {
        return order(this.content.events)
    }

    private fun order(events: MutableList<Event>): List<Event> {
        return events.sortedByDescending{ it.time  }
    }

    fun load(filter : Filter) {
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


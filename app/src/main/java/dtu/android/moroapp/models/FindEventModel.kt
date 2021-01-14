package dtu.android.moroapp.models

import dtu.android.moroapp.utils.EventFilters
import dtu.android.moroapp.utils.GraphQL.Filter
import java.io.Serializable

object FindEventModel {
    var filters : ArrayList<Pair<EventFilters, String>> = ArrayList()
    val filter = Filter.Builder()
}
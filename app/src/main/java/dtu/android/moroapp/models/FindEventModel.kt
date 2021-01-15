package dtu.android.moroapp.models

import dtu.android.moroapp.utils.EventFilters
import java.io.Serializable

object FindEventModel {
    var filters : ArrayList<Pair<EventFilters, String>> = ArrayList()

}
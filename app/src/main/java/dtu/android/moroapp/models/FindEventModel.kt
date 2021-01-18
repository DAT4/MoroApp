package dtu.android.moroapp.models

import dtu.android.moroapp.mvvm.Filter

object FindEventModel {
    var filter : ArrayList<Filter> = ArrayList()

    fun clear() {
        filter = ArrayList()
    }
}
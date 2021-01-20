package dtu.android.moroapp.models

import dtu.android.moroapp.mvvm.Filter

class FindEventModel {
    companion object Filters{
        val instance: ArrayList<Filter> by lazy {
            ArrayList()
        }
    }
}
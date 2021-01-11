package dtu.android.moroapp.ui.FindEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import dtu.android.moroapp.R
import dtu.android.moroapp.models.FindEventModel.filters
import dtu.android.moroapp.utils.EventFilters

class findEvent_what_fragment : Fragment() {
    private var root: View? = null
    private var grid: GridLayout? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_find_event_what_fragment, container, false)
        grid = root.findViewById(R.id.grid_what)
        setupGrid()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupGrid() {
        for (i in 0 until grid!!.childCount) {
            val button = grid!!.getChildAt(i) as ToggleButton
            button.setOnClickListener { view: View? ->
                if (button.isChecked) {
                    filters.add(Pair(EventFilters.GENRE, button.textOff.toString()))
                    for (p in filters) {
                        println(p)
                    }
                }
                if (!button.isChecked) {
                    filters.remove(Pair(EventFilters.GENRE, button.textOff.toString()))
                    for (p in filters) {
                        println(p)
                    }
                }
            }
        }
    }
}
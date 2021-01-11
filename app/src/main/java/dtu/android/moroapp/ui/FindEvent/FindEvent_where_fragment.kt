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

class FindEvent_where_fragment : Fragment() {
    private var grid: GridLayout? = null
    private var root: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_find_event_where_fragment, container, false)
        grid = root.findViewById(R.id.grid_where)
        setupGrid()
        return root
    }

    private fun setupGrid() {
        for (i in 0 until grid!!.childCount) {
            val button = grid!!.getChildAt(i) as ToggleButton
            button.setOnClickListener { view: View? ->
                if (button.isChecked) {
                    filters.add(Pair(EventFilters.AREA, button.textOff.toString()))
                }
                if (!button.isChecked) {
                    filters.remove(Pair(EventFilters.AREA, button.textOff.toString()))
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
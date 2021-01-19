package dtu.android.moroapp.ui.fragments.findEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dtu.android.moroapp.databinding.FragmentFindEventWhenBinding
import dtu.android.moroapp.models.FindEventModel
import dtu.android.moroapp.models.event.TimeDate
import dtu.android.moroapp.mvvm.Filter
import java.util.*

class FindEvent_when_fragment : Fragment() {

    private lateinit var _binding: FragmentFindEventWhenBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFindEventWhenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            calendarView.setOnDateChangeListener { _, y, m, d ->
                val (from, to) = getTimeIntervalForOneDay(y,m,d)
                updateTimeFilter(from,to)
            }
        }
    }

    private fun updateTimeFilter(from:TimeDate, to:TimeDate){
        FindEventModel.instance.removeIf { it is Filter.Exclusive.Time }
        FindEventModel.instance.add(Filter.Exclusive.Time.GT(from))
        FindEventModel.instance.add(Filter.Exclusive.Time.LT(to))
    }

    private fun getTimeIntervalForOneDay(year: Int, month: Int, day: Int): Pair<TimeDate,TimeDate> {
        val c = Calendar.getInstance()
        c.set(year, month, day)
        val from = c.timeInMillis / 1000
        c.add(Calendar.DAY_OF_YEAR, 1)
        val to = c.timeInMillis / 1000
        return Pair(from, to)
    }

}
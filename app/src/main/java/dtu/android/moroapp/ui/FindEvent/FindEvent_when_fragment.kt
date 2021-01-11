package dtu.android.moroapp.ui.FindEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dtu.android.moroapp.R

class FindEvent_when_fragment : Fragment() {
    private var root: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_find_event_when, container, false)
        return root
    }
}
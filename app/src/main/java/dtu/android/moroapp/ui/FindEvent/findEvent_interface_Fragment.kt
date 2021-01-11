package dtu.android.moroapp.ui.FindEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import dtu.android.moroapp.R
import dtu.android.moroapp.adapters.TabAdapter

class findEvent_interface_Fragment : Fragment() {
    private var viewPager: ViewPager2? = null
    private val tabWhen: TabItem? = null
    private val tabWhere: TabItem? = null
    private val tabWhat: TabItem? = null
    private var root: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_find_event_interface, container, false)
        viewPager = root.findViewById(R.id.find_event_viewPager)
        viewPager.setAdapter(TabAdapter(activity!!))
        val tabLayout: TabLayout = root.findViewById(R.id.find_event_tabLayout)
        val tabLayoutMediator = TabLayoutMediator(
                tabLayout, viewPager, TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Hvornår"
                1 -> tab.text = "Hvor"
                2 -> tab.text = "hvad"
            }
        }
        )
        tabLayoutMediator.attach()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
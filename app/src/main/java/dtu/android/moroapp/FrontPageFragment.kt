package dtu.android.moroapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dtu.android.moroapp.adapters.Frontpage_Adapter
import dtu.android.moroapp.models.Event
import dtu.android.moroapp.observer.ConcreteEvents
import dtu.android.moroapp.observer.IObserver
import kotlinx.android.synthetic.main.fragment_front_page.*
import kotlinx.android.synthetic.main.fragment_view_pager.*
import sh.mama.hangman.adapters.EventAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [FrontPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FrontPageFragment : Fragment(), IObserver {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        ConcreteEvents.add(this)
    }


    private fun printEvents() {
        val events = ConcreteEvents.getAllEvents()
        try {
            val adapter = EventAdapter(events as List<Event>)
            front_page_list.adapter = adapter
            front_page_list.layoutManager = LinearLayoutManager(activity)
        } catch (e: Exception) {
            print("Fejlet")
        }
    }

    /*private fun printBanner() {
        val events = ConcreteEvents.getAllEvents()
        try {
            val adapter = Frontpage_Adapter(this.context, events as ArrayList<Event>)
            viewPager.adapter = adapter

        } catch (e: Exception) {
            print("Fejlet")
        }
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_front_page, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            find_event_button.setOnClickListener {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, findEvent_interface_Fragment())
                        .addToBackStack(null)
                        .commit()
            }

            lige_nu.setOnClickListener {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, RightNowFragment())
                        .addToBackStack(null)
                        .commit()
            }

            printEvents();
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        ConcreteEvents.remove(this)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FrontPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FrontPageFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun update() {
        printEvents()
    }
}
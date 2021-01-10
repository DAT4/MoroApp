package dtu.android.moroapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dtu.android.moroapp.adapters.PremiumAdapter
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.databinding.FragmentFrontPageBinding
import dtu.android.moroapp.models.Event
import dtu.android.moroapp.mvvm.EventViewModel
import dtu.android.moroapp.observer.ConcreteEvents
import dtu.android.moroapp.observer.IObserver
import kotlinx.android.synthetic.main.fragment_front_page.*
import sh.mama.hangman.adapters.EventAdapter

class FrontPageFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: EventViewModel
    private lateinit var _binding: FragmentFrontPageBinding
    private val binding get() = _binding

    private fun initViewPager(events: List<Event>) {
        try {
            val adapter = PremiumAdapter(events as ArrayList<Event>)
            binding.viewPager.adapter = adapter
        } catch (e: java.lang.Exception) {
            println(e)
        }
    }

    private fun initRecyclerView(events: List<Event>) {
        try {
            val adapter = EventAdapter(events)
            binding.frontPageList.adapter = adapter
            binding.frontPageList.layoutManager = LinearLayoutManager(activity)
        } catch (e: Exception) {
            print("Fejlet")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentFrontPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        navController = Navigation.findNavController(view);

        viewModel.events.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let {
                        initRecyclerView(it)
                        initViewPager(it)
                    }
                }
                is Resource.Error -> {
                    response.message?.let {
                        println("Error occured: $it")
                    }
                }
            }

        })

        view.apply {
            binding.findEventButton.setOnClickListener {
                navController.navigate(R.id.action_frontPageFragment_to_findEvent_interface_Fragment)
            }
            binding.ligeNu.setOnClickListener {
                navController.navigate(R.id.action_frontPageFragment_to_rightNowFragment)
            }

        }
    }
}
package dtu.android.moroapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dtu.android.moroapp.ui.MainActivity
import dtu.android.moroapp.R
import dtu.android.moroapp.adapters.PremiumAdapter
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.databinding.FragmentFrontPageBinding
import dtu.android.moroapp.models.event.Event
import dtu.android.moroapp.mvvm.EventViewModel
import sh.mama.hangman.adapters.EventAdapter

class FrontPageFragment : Fragment() {

    lateinit var viewModel: EventViewModel
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
            val adapter = EventAdapter(
                    events.sortedByDescending { it.time }.reversed().take(5)
            )
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
        //viewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewModel.events.observe(viewLifecycleOwner, { response ->
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FrontPageFragment", "Getting viewModel from main")

        /*viewModel = (activity as MainActivity).viewModel

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

        }) */

        view.apply {
            binding.findEventButton.setOnClickListener {
                findNavController().navigate(R.id.action_frontPageFragment_to_findEvent_interface_Fragment)
            }
            binding.ligeNu.setOnClickListener {
                findNavController().navigate(R.id.action_frontPageFragment_to_rightNowFragment)
            }

        }
    }
}
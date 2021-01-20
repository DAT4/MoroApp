package dtu.android.moroapp.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dtu.android.moroapp.ui.MainActivity
import dtu.android.moroapp.R
import dtu.android.moroapp.adapters.IViewPagerClickInterface
import dtu.android.moroapp.adapters.PremiumAdapter
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.databinding.FragmentFrontPageBinding
import dtu.android.moroapp.models.event.Event
import dtu.android.moroapp.mvvm.EventViewModel
import dtu.android.moroapp.utils.Constants.REQUEST_CODE_LOCATION_PERMISSION
import dtu.android.moroapp.utils.TrackingUtility
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import dtu.android.moroapp.adapters.EventAdapter

class FrontPageFragment : Fragment(), IViewPagerClickInterface, EasyPermissions.PermissionCallbacks {


    lateinit var viewModel: EventViewModel
    private lateinit var _binding: FragmentFrontPageBinding
    private val binding get() = _binding
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentFrontPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewModel.events.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { events ->
                        val events = events.sortedBy { it.time }.take(10)
                        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                            initRecyclerView(events, location)
                        }
                        initViewPager(events)
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
        requestPermissions()
        Log.i("FrontPageFragment", "Getting viewModel from main")

        view.apply {
            binding.findEventButton.setOnClickListener {
                findNavController().navigate(R.id.action_frontPageFragment_to_findEvent_interface_Fragment)
            }
            binding.ligeNu.setOnClickListener {
                findNavController().navigate(R.id.action_frontPageFragment_to_rightNowFragment)
            }

        }
    }

    private fun initRecyclerView(events: List<Event>, location: Location?) {
        val adapter =
                EventAdapter(
                        events,
                        location
                )
        binding.frontPageList.adapter = adapter
        binding.frontPageList.layoutManager = LinearLayoutManager(activity)
    }

    private fun initViewPager(events: List<Event>) {
        val adapter = PremiumAdapter(events, this)
        binding.viewPager.adapter = adapter
    }

    override fun onItemClick(isRight: Boolean?) {
        val direction: Int

        if (isRight == true) {
            direction = 1
        } else {
            direction = -1
        }

        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + direction, true)
    }

    private fun requestPermissions() {
        if (TrackingUtility.hasPermissions(requireContext())){
            return
        }
        Log.d("LOCATIONCHECKER", (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q).toString())
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                    this,
                    "Accepter location permissions tak...",
                    REQUEST_CODE_LOCATION_PERMISSION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "Accepter location permissions tak...",
                    REQUEST_CODE_LOCATION_PERMISSION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

}
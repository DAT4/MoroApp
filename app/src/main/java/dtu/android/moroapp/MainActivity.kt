package dtu.android.moroapp

import android.content.Context
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.NavController
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dtu.android.moroapp.databinding.ActivityMainBinding
import dtu.android.moroapp.mvvm.*

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private lateinit var bottomBar: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: EventViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind binding to xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()
        setupNav()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    private fun initializeUI() {
        val eventRepository = EventRepository(EventDatabase(this))
        val eventViewModelProviderFactory = EventViewModelProviderFactory(eventRepository)
        viewModel = ViewModelProvider(this, eventViewModelProviderFactory).get(EventViewModel::class.java)
    }

    private fun setupNav() {
        bottomBar = binding.bottomNavigationView
        navController = findNavController( R.id.mainFragment)
        bottomBar.setupWithNavController(navController)
    }
}
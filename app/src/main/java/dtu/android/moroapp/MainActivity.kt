package dtu.android.moroapp

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.NavController
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dtu.android.moroapp.databinding.ActivityMainBinding
import dtu.android.moroapp.mvvm.EventDatabase
import dtu.android.moroapp.mvvm.EventRepository
import dtu.android.moroapp.mvvm.EventViewModel
import dtu.android.moroapp.mvvm.EventViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var bottomBar: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: EventViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind binding to xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup viewModel dependency injection
        val eventRepository = EventRepository(EventDatabase(this))
        val eventViewModelProviderFactory = EventViewModelProviderFactory(eventRepository)
        viewModel = ViewModelProvider(this, eventViewModelProviderFactory).get(EventViewModel::class.java)
        Log.d("VISNINGSMODEL", viewModel.toString())

        // Setup nav
        bottomBar = binding.bottomNavigationView
        navController = findNavController( R.id.fragment)
        bottomBar.setupWithNavController(navController)
    }
}
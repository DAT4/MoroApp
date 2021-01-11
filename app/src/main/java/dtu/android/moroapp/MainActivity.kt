package dtu.android.moroapp

import android.content.Context
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.NavController
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dtu.android.moroapp.databinding.ActivityMainBinding
import dtu.android.moroapp.mvvm.EventDatabase
import dtu.android.moroapp.mvvm.EventRepository
import dtu.android.moroapp.mvvm.EventViewModel
import dtu.android.moroapp.mvvm.EventViewModelProviderFactory
import dtu.android.moroapp.observer.ConcreteEvents
import dtu.android.moroapp.observer.IObserver
import dtu.android.moroapp.utils.EventFilters
import dtu.android.moroapp.utils.GraphQL.Filter

class MainActivity : AppCompatActivity(), LifecycleOwner, IObserver {
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
        ConcreteEvents.add(this)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topbar_menu,menu)
        val search = menu?.findItem(R.id.search_icon)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val t = System.currentTimeMillis() / 1000
                    val filter = Filter.Builder()
                            .filters(EventFilters.TIMEGT, t)
                            .build()
                    ConcreteEvents.load(filter)
                    Toast.makeText(this@MainActivity,query,Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun update() {
        Toast.makeText(this,"Succes",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        ConcreteEvents.remove(this)
    }
}
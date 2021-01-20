package dtu.android.moroapp.ui

import android.content.Context
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.NavController
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dtu.android.moroapp.R
import dtu.android.moroapp.databinding.ActivityMainBinding
import dtu.android.moroapp.models.FindEventModel
import dtu.android.moroapp.mvvm.*
import io.sentry.Sentry
import io.sentry.SentryOptions
import io.sentry.android.core.SentryAndroid

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
        //supportActionBar?.setDisplayUseLogoEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)
        //supportActionBar?.setIcon(R.drawable.ic_moro_logo)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.top_bar)
        supportActionBar?.setDisplayShowHomeEnabled(true)


    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

    private fun initializeUI() {
        val eventRepository = EventRepository(EventDatabase(this))
        val eventViewModelProviderFactory = EventViewModelProviderFactory(eventRepository)
        viewModel = ViewModelProvider(this, eventViewModelProviderFactory).get(EventViewModel::class.java)
        viewModel.loadEvents()
    }

    private fun setupNav() {
        bottomBar = binding.bottomNavigationView
        navController = findNavController(R.id.mainFragment)
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
                    FindEventModel
                            .instance
                            .clear()
                    FindEventModel
                            .instance
                            .add(Filter.Exclusive.Title(query))
                    navController.navigate(R.id.moveToSearchListFragment)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}
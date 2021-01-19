package dtu.android.moroapp.mvvm

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class EventViewModelProviderFactoryTest {
    private lateinit var viewModelProviderFactory: EventViewModelProviderFactory

    @Before
    fun setup() {
        viewModelProviderFactory = EventViewModelProviderFactory(testRepository())
    }
    @Test
    fun `the viewModel provider factory should return a correct type of viewmodel` () {
        var testModel = viewModelProviderFactory.create(EventViewModel::class.java)

        assert(testModel is EventViewModel)
    }
}
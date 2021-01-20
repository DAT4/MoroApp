package dtu.android.moroapp.mvvm


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import dtu.android.moroapp.api.Resource
import dtu.android.moroapp.models.event.Event
import org.junit.Rule


class EventViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EventViewModel
    private lateinit var repository: testRepository
    //val dispatcher = TestCoroutineDispatcher()
    //val testScope = TestCoroutineScope(dispatcher)

    @Before
    fun setup() {
        repository = testRepository()
        viewModel = EventViewModel(repository)
        //Dispatchers.setMain(dispatcher)
    }
    /*@After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()

    } */

    @Test
    fun `making a call to get events should return a succes`() {
        viewModel.loadEvents()
        val events = viewModel.events
        assert(events.value is Resource.Success)
    }


    @Test
    fun `making a call to repo should return a list of events`() {
        viewModel.loadEvents()
        val events = viewModel.events
        assert(events.value?.data is List<Event>)

    }

    @Test
    fun `making a call to repo should return cache if cache exists`() {
        repository.makeCache()
        viewModel.loadEvents()

        val events = viewModel.events

        assertThat(events.value?.data?.get(0)?.title).isEqualTo("Johny Cache spiller")
    }

    @Test
    fun `making a call to repo with no network should give the correct error message`() {
        repository.setShouldReturnNetworkError(true)
        viewModel.loadEvents()

        val events = viewModel.events
        assert(events.value is Resource.Error)
        assertThat(events.value?.message).isEqualTo("Error")


    }

}
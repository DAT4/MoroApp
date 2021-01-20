package dtu.android.moroapp.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import dtu.android.moroapp.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setup() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    }

    @Test
    fun test_isMainActivityInView() {

        onView(withId(R.id.MainActivity)).check(matches(isDisplayed()))
    }


    @Test
    fun test_isMainFragmentDisplayed() {
        onView(withId(R.id.mainFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isButtomNavDisplayed() {
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()))
    }
}
package dtu.android.moroapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dtu.android.moroapp.models.Event
import dtu.android.moroapp.models.Query
import dtu.android.moroapp.models.data
import kotlinx.android.synthetic.main.fragment_front_page.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sh.mama.hangman.adapters.EventAdapter
import java.io.DataOutputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [FrontPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FrontPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun printEvents(){
        GlobalScope.launch(Dispatchers.IO) {
            val events = getEvents()
            for (event in events) {
                println(event.title)
                println(event.time)
                println(event.link)
                println(event.tickets)
                println(event.image)
                println(event.genre)
                println(event.location.address.city)
                println(event.location.place)
            }
            launch(Dispatchers.Main) {
                try {
                    val adapter = EventAdapter(events)
                    front_page_list.adapter = adapter
                    front_page_list.layoutManager = LinearLayoutManager(activity)
                } catch (e: Exception){
                    print("Fejlet")
                }
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_front_page, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            find_event_button.setOnClickListener {
                val newFragment = findEvent_interface_Fragment()
                val transaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.mainFragment, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            lige_nu.setOnClickListener{
                printEvents();
            }
        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FrontPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FrontPageFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    private fun getEvents(): List<Event>{
        val url = "https://mama.sh/moro/api"
        val req = URL(url)
        val con = req.openConnection() as HttpURLConnection
        con.requestMethod = "POST"
        con.connectTimeout = 300000
        con.doOutput = true
        val gson = Gson()
        val json = gson.toJson(Query("""
{
  allEvents{
    title
    genre
    image
    link
    other
    price
    text
    tickets
    time
    location{
      area
      place
      address{
        city
        street
        no
        state
        street
        zip
      }
      coordinates{
        longitude
        latitude
      }
    }
  }
} 
        """.trimIndent()))
        println(json)
        val data = (json).toByteArray()
        con.setRequestProperty("User-Agent", "Your-Mom")
        con.setRequestProperty("Content-Type", "application/json")

        val request = DataOutputStream(con.outputStream)
        request.write(data)
        request.flush()
        con.inputStream.bufferedReader().use {
            val response = StringBuffer()
            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }
            println("DATA=$response.toString()")
            val gson = Gson()
            val eventsType = object : TypeToken<data>() {}.type
            val out = gson.fromJson(response.toString(),eventsType ) as data
            return out.data.allEvents
        }
    }
}
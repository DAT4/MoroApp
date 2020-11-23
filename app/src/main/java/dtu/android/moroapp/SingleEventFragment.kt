package dtu.android.moroapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import dtu.android.moroapp.models.Event
import kotlinx.android.synthetic.main.fragment_single_event.view.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SingleEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingleEventFragment : Fragment() {
    private lateinit var event : Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments!!.let {
            event = it.get("event") as Event
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {

            val timeStamp = Date(event.time * 1000)

            val timeFormat = SimpleDateFormat("HH:mm")
            val dateFormat = SimpleDateFormat("dd/mm/yy")

            val time = timeFormat.format(timeStamp)
            val date = dateFormat.format(timeStamp)
            event_single_title.text = event.title
            event_single_description.text = event.text
            event_single_date.text = date
            event_single_time.text = time
            event_single_price.text = "${event.price} DKK"
            event_single_place.text = event.location.place
            event_single_address.text = "${event.location.address.street} ${event.location.address.no}"
            event_single_city.text = "${event.location.address.zip} ${event.location.address.city}"
            event_single_link.setOnClickListener{
                Toast.makeText(context,"link",Toast.LENGTH_SHORT).show()
            }
            event_single_ticket.setOnClickListener{
                Toast.makeText(context,"ticket",Toast.LENGTH_SHORT).show()
            }
            println(event.image)
            Picasso.get()
                    .load(event.image)
                    .fit()
                    .centerCrop()
                    .into(image)
        }
    }

    companion object {
        //Factory stuff
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SingleEventFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
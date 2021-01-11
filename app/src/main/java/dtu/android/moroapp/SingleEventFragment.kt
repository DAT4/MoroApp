package dtu.android.moroapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dtu.android.moroapp.models.Event
import kotlinx.android.synthetic.main.fragment_single_event.view.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SingleEventFragment : Fragment() {
    private lateinit var event : Event
    private val args: SingleEventFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = args.event
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

            val time = SimpleDateFormat("HH:mm").format(timeStamp)
            val date = SimpleDateFormat("dd/MM/yyyy").format(timeStamp)

            event_single_title.text = event.title
            event_single_description.text = event.text
            event_single_date.text = date
            event_single_time.text = time
            event_single_price.text = "${event.price} DKK"
            event_single_place.text = event.location.place
            event_single_address.text = "${event.location.address.street} ${event.location.address.no}"
            event_single_city.text = "${event.location.address.zip} ${event.location.address.city}"
            event_single_link.setOnClickListener{
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(event.link)))
            }
            event_single_ticket.setOnClickListener{
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(event.tickets)))
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
package sh.mama.hangman.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dtu.android.moroapp.FrontPageFragmentDirections
import dtu.android.moroapp.R
import dtu.android.moroapp.databinding.EventCardFragmentBinding
import dtu.android.moroapp.models.Event
import kotlinx.android.synthetic.main.event_card_fragment.view.*

class EventAdapter(
        private var events: List<Event>,
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    inner class EventViewHolder(itemView: EventCardFragmentBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventCardFragmentBinding
                .inflate(LayoutInflater
                        .from(parent.context), parent, false)

        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.itemView.apply {
            event_card_title.text = event.title
            event_card_long_time.text = event.getTime()
            event_card_long_date.text = event.getDate()
            event_card_long_place.text = event.location.place
            Picasso.get().load(events[position].image).fit().centerCrop().into(image)
            image.setColorFilter(Color.GRAY, PorterDuff.Mode.DARKEN)
            GridLayout1.setBackgroundColor(resources.getColor(R.color.colorFindEventWhere))

            event_card_long.setOnClickListener {
                val action = FrontPageFragmentDirections
                        .actionFrontPageFragmentToSingleEventFragment(event)
                findNavController().navigate(action);
            }
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}
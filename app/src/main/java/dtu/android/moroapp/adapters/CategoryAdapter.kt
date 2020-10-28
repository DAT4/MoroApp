package sh.mama.hangman.adapters

import android.content.Intent
import kotlinx.android.synthetic.main.event_card_fragment.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dtu.android.moroapp.R
import dtu.android.moroapp.models.Event
import java.time.LocalDate
import java.util.*

class EventAdapter(
        private var events: List<Event>,
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_card_fragment, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.itemView.apply {

            val time = Date(events[position].time)
            println(time)
            event_card_title.text = events[position].title
            event_card_long_place.text = events[position].location.place
            event_card_long.setOnClickListener {
                Toast.makeText(event_card_long.context,"HEJSA",Toast.LENGTH_SHORT)
            }
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}
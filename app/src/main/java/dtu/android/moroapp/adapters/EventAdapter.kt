package sh.mama.hangman.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.location.Location
import android.location.LocationManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import dtu.android.moroapp.R
import dtu.android.moroapp.databinding.EventCardFragmentBinding
import dtu.android.moroapp.models.event.Event
import dtu.android.moroapp.ui.fragments.FrontPageFragmentDirections
import java.text.DecimalFormat

class EventAdapter(
        private var events: List<Event>,
        private var locaion : Location,
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private lateinit var binding: EventCardFragmentBinding
    inner class EventViewHolder(itemView: EventCardFragmentBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventCardFragmentBinding
                .inflate(LayoutInflater
                        .from(parent.context), parent, false)
        this.binding = binding

        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        val event = events[position]
        binding.apply {
            eventCardLong.animation = (AnimationUtils.loadAnimation(root.context,R.anim.fade_transition))
            eventCardTitle.text = event.title
            eventCardLongTime.text = event.getTimeToString()
            eventCardLongDate.text = event.getDate()
            //eventCardLongPlace.text = event.location.place
            val dist = FloatArray(1)
            eventCardLongPlace.text = event.location.place
            locaion?.let {
                Location.distanceBetween(
                        locaion.latitude,
                        locaion.longitude,
                        (event.location.coordinates.latitude.toDouble()),
                        (event.location.coordinates.longitude.toDouble()),
                        dist)

                eventCardLongPlace.text = DecimalFormat("#.#").format(dist[0]/1000) + " km"
            }

            Picasso.get().load(events[position].image).fit().centerCrop().into(image)
            image.setColorFilter(Color.GRAY, PorterDuff.Mode.DARKEN)

            GridLayout1.setBackgroundColor(ContextCompat.getColor(root.context, R.color.colorFindEventWhere))

            root.setOnClickListener {
                val action = FrontPageFragmentDirections
                        .actionFrontPageFragmentToSingleEventFragment(event)
                root.findNavController().navigate(action);
            }
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}
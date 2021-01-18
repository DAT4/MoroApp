package dtu.android.moroapp.adapters

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import dtu.android.moroapp.databinding.CardItemViewPagerBinding
import dtu.android.moroapp.models.event.Event
import dtu.android.moroapp.ui.fragments.FrontPageFragmentDirections
import java.lang.Exception
import java.util.logging.Handler

class PremiumAdapter(
        val events: List<Event>,
        val click: IViewPagerClickInterface
) : RecyclerView.Adapter<PremiumAdapter.EventViewHolder>() {
    private lateinit var binding: CardItemViewPagerBinding

    inner class EventViewHolder(itemView: CardItemViewPagerBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = CardItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.binding = binding
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        binding.apply {
            cardItemTitel.text = event.title
            cardItemTime.text = event.getTimeToString()
            Picasso.get().load(event.image).fit().centerCrop().into(bannerID)

            btnLeft.setOnClickListener {
                click.onItemClick(false)
            }

            btnRight.setOnClickListener { click.onItemClick(true) }

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


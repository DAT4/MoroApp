package dtu.android.moroapp.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dtu.android.moroapp.R
import dtu.android.moroapp.SingleEventFragment
import dtu.android.moroapp.models.Event
import kotlinx.android.synthetic.main.card_item_view_pager.view.*
import kotlinx.android.synthetic.main.event_card_fragment.view.*
import kotlinx.android.synthetic.main.event_viewpager.view.*

class PremiumAdapter(
        val events: List<Event>
): RecyclerView.Adapter<PremiumAdapter.EventViewHolder>(){
    inner class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_view_pager, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.itemView.apply {
            card_item_titel.text = event.title
            card_item_time.text = event.getTime()
            Picasso.get().load(event.image).fit().centerCrop().into(bannerID)
            bannerID.setColorFilter(Color.GRAY, PorterDuff.Mode.DARKEN)
            /*
            title.text = event.title
            description.text = event.genre
            //date.text = event.getDate()
            //time.text = event.getTime()
            Picasso.get().load(event.image).fit().centerCrop().into(banner)
            banner.setColorFilter(Color.GRAY, PorterDuff.Mode.DARKEN)
            this.setOnClickListener{
                val f = SingleEventFragment()
                val fm = (context as AppCompatActivity).supportFragmentManager
                val args = Bundle()
                args.putSerializable("event",events[position])
                f.arguments = args
                fm.beginTransaction()
                        .replace(R.id.mainFragment, f)
                        .addToBackStack(null)
                        .commit()
            */}
    }

    override fun getItemCount(): Int {
        return events.size
    }
}


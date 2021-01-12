package dtu.android.moroapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import dtu.android.moroapp.R;
import dtu.android.moroapp.RightNowFragmentDirections;
import dtu.android.moroapp.models.Event;

public class EventItemViewHolder extends RecyclerView.ViewHolder {
    protected final TextView eventTitle;
    protected final TextView eventDistance;
    protected final TextView eventDate;
    protected final TextView eventTime;
    protected final Context context;
    protected final View eventLink;
    protected final ImageView eventimage;
    NavController navController;

    public EventItemViewHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View
        context = view.getContext();
        eventTitle = (TextView) view.findViewById(R.id.event_card_title);
        eventDistance = (TextView) view.findViewById(R.id.event_card_long_place);
        eventDate = (TextView) view.findViewById(R.id.event_card_long_date);
        eventTime = (TextView) view.findViewById(R.id.event_card_long_time);
        eventLink = (View) view.findViewById(R.id.event_card_long);
        eventimage = (ImageView) view.findViewById(R.id.image);
        
    }

    public TextView getEventTitle() {
        return eventTitle;
    }

    public TextView getEventDistance() {
        return eventDistance;
    }

    public TextView getEventDate() {
        return eventDate;
    }

    public TextView getEventTime() {
        return eventTime;
    }

    public void setEventimage(String url) {
        Picasso.get().load(url).fit().centerCrop().into(eventimage);
        eventimage.setColorFilter(Color.GRAY, PorterDuff.Mode.DARKEN);
    }

    public void setEventLink(Event event) {
        eventLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = RightNowFragmentDirections.Companion.actionRightNowFragmentToSingleEventFragment(event);
                Navigation.findNavController(itemView).navigate(action);

            }
        });
    }
}

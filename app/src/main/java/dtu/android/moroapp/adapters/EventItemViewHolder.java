package dtu.android.moroapp.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dtu.android.moroapp.R;

public class EventItemViewHolder extends RecyclerView.ViewHolder {
    protected final TextView eventTitle;
    protected final TextView eventDistance;
    protected final TextView eventDate;
    protected final TextView eventTime;

    public EventItemViewHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View

        eventTitle = (TextView) view.findViewById(R.id.event_card_title);
        eventDistance = (TextView) view.findViewById(R.id.event_card_long_place);
        eventDate = (TextView) view.findViewById(R.id.event_card_long_date);
        eventTime = (TextView) view.findViewById(R.id.event_card_long_time);
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
}

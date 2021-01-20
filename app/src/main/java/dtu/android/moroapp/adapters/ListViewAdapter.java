package dtu.android.moroapp.adapters;

import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.event.Event;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private List<Event> localDataSet;
    IRecyclerViewClickListener customOnClick;
    ColorThemeManager colorThemeManager;
    View listView;
    Location location;

    public ListViewAdapter(List<Event> localDataSet, ColorThemeManager colorThemeManager, IRecyclerViewClickListener customOnClick, Location location) {
        this.localDataSet = localDataSet;
        this.customOnClick = customOnClick;
        this.colorThemeManager = colorThemeManager;
        this.location = location;
    }

    public void setLocalDataSet(List<Event> localDataSet) {
        this.localDataSet = localDataSet;
    }



    public static class ViewHolder extends dtu.android.moroapp.adapters.EventItemViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate( R.layout.event_card_fragment, viewGroup, false);

        listView = view.findViewById(R.id.GridLayout1);

        return new ListViewAdapter.ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ListViewAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.eventLink.setAnimation( AnimationUtils.loadAnimation(viewHolder.context,R.anim.fade_transition));

        if (this.localDataSet.get(position).isSaved()) {
            viewHolder.getEventToSaveBTN().setBackgroundResource(R.drawable.ic_minus);
        }

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getEventTitle().setText(this.localDataSet.get(position).getTitle());
        //viewHolder.getEventDistance().setText(this.localDataSet.get(position).getLocation().getPlace());
        // Location stuff
        float[] dist = new float[1];
        if (this.location != null) {
            Location.distanceBetween(
                    this.location.getLatitude(),
                    this.location.getLongitude(),
                    this.localDataSet.get(position).getLocation().getCoordinates().getLatitude(),
                    this.localDataSet.get(position).getLocation().getCoordinates().getLongitude(),
                    dist);

            viewHolder.getEventDistance().setText(new DecimalFormat("#.#").format(dist[0]/1000) + " km");
        } else {
            viewHolder.getEventDistance().setText(this.localDataSet.get(position).getLocation().getPlace());
        }



        viewHolder.getEventDate().setText(this.localDataSet.get(position).getDate());
        viewHolder.getEventTime().setText(this.localDataSet.get(position).getTimeToString());
        viewHolder.setEventLink(this.localDataSet.get(position));
        viewHolder.setEventimage(this.localDataSet.get(position).getImage());
        listView.setBackgroundResource(colorThemeManager.getIcon());


        viewHolder.getEventToSaveBTN().setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                customOnClick.onItemClick(localDataSet.get(position));
                localDataSet.get(position).setSaved(true);
                viewHolder.getEventToSaveBTN().setBackgroundResource(R.drawable.ic_minus);
                Toast.makeText(v.getContext(), "Event tilføjet til mine events", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

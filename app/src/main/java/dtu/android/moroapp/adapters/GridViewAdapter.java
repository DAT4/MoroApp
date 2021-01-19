package dtu.android.moroapp.adapters;

import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.event.Event;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder>{



    private List<Event> localDataSet;
    IRecyclerViewClickListener customOnClick;
    ColorThemeManager colorThemeManager;
    View gridView;
    Location location;


    public GridViewAdapter(List<Event> localDataSet, ColorThemeManager colorThemeManager,  IRecyclerViewClickListener customOnClick, Location location) {
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

    @NonNull
    @Override
    public GridViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.fragment_event_card_block, parent, false);

        //ImageView imageListView = view.findViewById(R.id.savedEvents_list_view);
        //imageListView.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIconOrange));

        gridView = view.findViewById(R.id.blockView);

        return new GridViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewAdapter.ViewHolder holder, int position) {
        /*SimpleDateFormat date = new SimpleDateFormat("dd-MM-yy");
        String dateStr = date.format(new Date(this.localDataSet.get(position).getTime() * 1000)); */

        holder.eventLink.setAnimation( AnimationUtils.loadAnimation(holder.context,R.anim.fade_transition));

        holder.getEventTitle().setText(this.localDataSet.get(position).getTitle());
        //holder.getEventDistance().setText(this.localDataSet.get(position).getLocation().getPlace());
        // location stuff
        float[] dist = new float[1];
        if (this.location != null) {
            Location.distanceBetween(
                    this.location.getLatitude(),
                    this.location.getLongitude(),
                    this.localDataSet.get(position).getLocation().getCoordinates().getLatitude(),
                    this.localDataSet.get(position).getLocation().getCoordinates().getLongitude(),
                    dist);

            holder.getEventDistance().setText(new DecimalFormat("#.#").format(dist[0]/1000) + " km");
        } else {
            holder.getEventDistance().setText(this.localDataSet.get(position).getLocation().getPlace());
        }

        holder.getEventDate().setText(this.localDataSet.get(position).getDate());
        holder.getEventTime().setText(this.localDataSet.get(position).getTimeToString());
        holder.setEventLink(this.localDataSet.get(position));
        holder.setEventimage(this.localDataSet.get(position).getImage());
        holder.getEventToSaveBTN().setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                customOnClick.onItemClick(localDataSet.get(position));
                Toast.makeText(v.getContext(), "Event tilføjet til mine events", Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setBackgroundResource(colorThemeManager.getIcon());

    }

    @Override
    public int getItemCount() {
        return this.localDataSet.size();
    }
}

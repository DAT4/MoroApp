package dtu.android.moroapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.event.Event;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder>{



    private List<Event> localDataSet;
    IRecyclerViewClickListener customOnClick;
    ColorThemeManager colorThemeManager;
    View gridView;

    public GridViewAdapter(List<Event> localDataSet, ColorThemeManager colorThemeManager,  IRecyclerViewClickListener customOnClick) {
        this.localDataSet = localDataSet;
        this.customOnClick = customOnClick;
        this.colorThemeManager = colorThemeManager;
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

        holder.getEventTitle().setText(this.localDataSet.get(position).getTitle());
        holder.getEventDistance().setText("00 km");
        holder.getEventDate().setText("00-00");
        holder.getEventTime().setText("00:00");
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

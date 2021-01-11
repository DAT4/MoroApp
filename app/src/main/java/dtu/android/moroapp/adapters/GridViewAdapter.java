package dtu.android.moroapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.Event;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder>{

    private List<Event> localDataSet;
    ColorThemeManager colorThemeManager;

    public GridViewAdapter(List<Event> localDataSet, ColorThemeManager colorThemeManager) {
        this.localDataSet = localDataSet;
        this.colorThemeManager = colorThemeManager;
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

        View gridView = view.findViewById(R.id.blockView);
        gridView.setBackgroundColor(colorThemeManager.getIcon());



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
    }

    @Override
    public int getItemCount() {
        return this.localDataSet.size();
    }
}

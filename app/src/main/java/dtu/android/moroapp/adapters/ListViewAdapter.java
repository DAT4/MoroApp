package dtu.android.moroapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.Event;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private List<Event> localDataSet;
    ColorThemeManager colorThemeManager;
    View listView;

    public ListViewAdapter(List<Event> localDataSet, ColorThemeManager colorThemeManager) {
        this.localDataSet = localDataSet;
        this.colorThemeManager = colorThemeManager;

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

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getEventTitle().setText(this.localDataSet.get(position).getTitle());
        viewHolder.getEventDistance().setText( "0 km");
        viewHolder.getEventDate().setText("00-00");
        viewHolder.getEventTime().setText("00:00");
        viewHolder.setEventLink(this.localDataSet.get(position));
        viewHolder.setEventimage(this.localDataSet.get(position).getImage());
        listView.setBackgroundResource(colorThemeManager.getIcon());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

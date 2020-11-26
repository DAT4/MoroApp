package dtu.android.moroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.SingleEventFragment;
import dtu.android.moroapp.models.Event;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> implements IListState {

    private List<Event> localDataSet;
    private EventsViewManager manager;


    public GridViewAdapter(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
        this.localDataSet = this.manager.dataToView;
    }

    @Override
    public void viewGrid() {

    }

    @Override
    public void viewList() {
        this.manager.changeState(new ListViewAdapter(this.manager));
    }

    @Override
    public void viewMap() {
        this.manager.changeState(new MapViewAdapter(this.manager));
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends dtu.android.moroapp.adapters.EventItemViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_event_card_block, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        /*SimpleDateFormat date = new SimpleDateFormat("dd-MM-yy");
        String dateStr = date.format(new Date(this.localDataSet.get(position).getTime() * 1000)); */

        viewHolder.getEventTitle().setText(this.localDataSet.get(position).getTitle());
        viewHolder.getEventDistance().setText("00 km");
        viewHolder.getEventDate().setText("00-00");
        viewHolder.getEventTime().setText("00:00");
        viewHolder.setEventLink(this.localDataSet.get(position));
        viewHolder.setEventimage(this.localDataSet.get(position).getImage());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.localDataSet.size();
    }
}

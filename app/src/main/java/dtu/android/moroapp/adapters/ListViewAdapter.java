package dtu.android.moroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.Event;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> implements IListState{

    private List<Event> localDataSet;
    EventsViewManager manager;

    public ListViewAdapter(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
        this.localDataSet = this.manager.dataToView;
    }

    @Override
    public void viewGrid() {
        this.manager.changeState(new GridViewAdapter(this.manager));
    }

    @Override
    public void viewList() {

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
        return new LinearLayoutManager(context);
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
    public ListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_card_fragment, viewGroup, false);

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

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
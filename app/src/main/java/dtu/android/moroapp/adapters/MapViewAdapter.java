package dtu.android.moroapp.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public class MapViewAdapter implements IListState {


    private EventsViewManager manager;

    MapViewAdapter(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
    }

    @Override
    public void viewGrid() {
        this.manager.changeState(new GridViewAdapter(this.manager));
    }

    @Override
    public void viewList() {
        this.manager.changeState(new ListViewAdapter(this.manager));
    }

    @Override
    public void viewMap() {
        // Do nothing
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return null;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return null;
    }
}

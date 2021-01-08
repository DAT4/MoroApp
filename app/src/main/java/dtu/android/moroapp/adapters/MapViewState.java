package dtu.android.moroapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.models.Event;

public class MapViewState implements IListState {

    private EventsViewManager manager;
    Event_Map_Fragment myFragment;
    List<Event> events;

    MapViewState(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
        this.events = this.manager.dataToView;
        this.myFragment = new Event_Map_Fragment(events);
    }

    @Override
    public Fragment viewGrid(Fragment view, Context context) {
        return this.manager.changeState(new GridViewState(this.manager));
    }

    @Override
    public Fragment viewList(Fragment view, Context context) {
        return this.manager.changeState(new ListViewState(this.manager));
    }

    @Override
    public Fragment viewMap(Fragment view, Context context) {
        return myFragment;
    }

    @Override
    public Fragment getFragment() {
        return myFragment;
    }

    @Override
    public void updateFragment() {

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
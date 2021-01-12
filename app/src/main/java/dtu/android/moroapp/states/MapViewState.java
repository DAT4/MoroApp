package dtu.android.moroapp.states;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.MapView;

import java.util.List;

import dtu.android.moroapp.Event_Map_Fragment;
import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.models.Event;

public class MapViewState implements IListState {

    private EventsViewManager manager;
    Event_Map_Fragment myFragment;
    List<Event> events;
    MapView mapView;

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

    @Override
    public void updateEvents(List<Event> events) {

    }

    @Override
    public View getView() {
        return null;
    }
}
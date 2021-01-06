package dtu.android.moroapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import dtu.android.moroapp.Event_Recycler_Fragment;

public class MapViewAdapter implements IListState {


    private EventsViewManager manager;
    Fragment myFragment;

    MapViewAdapter(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
    }

    @Override
    public Fragment viewGrid(Fragment view, Context context) {
        return this.manager.changeState(new GridViewAdapter(this.manager));
    }

    @Override
    public Fragment viewList(Fragment view, Context context) {
        return this.manager.changeState(new ListViewAdapter(this.manager));
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
    public RecyclerView.Adapter getAdapter() {
        return null;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return null;
    }
}
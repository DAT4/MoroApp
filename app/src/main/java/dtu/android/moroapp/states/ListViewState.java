package dtu.android.moroapp.states;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dtu.android.moroapp.Event_Recycler_Fragment;
import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.ListViewAdapter;

public class ListViewState implements IListState {

    EventsViewManager manager;
    Event_Recycler_Fragment myFragment;
    ListViewAdapter adapter;

    public ListViewState(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
        this.adapter = new ListViewAdapter(this.manager.dataToView);
        this.myFragment = new Event_Recycler_Fragment(this.adapter,getLayoutManager(this.manager.context));
    }

    @Override
    public Fragment viewGrid(Fragment view, Context context) {
         return this.manager.changeState(new GridViewState(this.manager));
    }

    @Override
    public Fragment viewList(Fragment view, Context context) {
        return this.myFragment;
    }

    @Override
    public Fragment viewMap(Fragment view, Context context) {
        return this.manager.changeState(new MapViewState(this.manager));
    }

    @Override
    public Fragment getFragment() {
        return this.myFragment;
    }

    public void updateFragment(){
        this.myFragment.setAdapter(getAdapter());
        this.myFragment.setLayoutManager(getLayoutManager(myFragment.getContext()));
    }

    public RecyclerView.Adapter getAdapter() {
        return this.adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}

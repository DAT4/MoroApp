package dtu.android.moroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.Event_Recycler_Fragment;
import dtu.android.moroapp.R;
import dtu.android.moroapp.models.Event;

public class GridViewState implements IListState {

    private EventsViewManager manager;
    Event_Recycler_Fragment myFragment;
    GridViewAdapter adapter;


    public GridViewState(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
        this.adapter = new GridViewAdapter(this.manager.dataToView);
        this.myFragment = new Event_Recycler_Fragment(this.adapter,getLayoutManager(this.manager.context));
    }

    @Override
    public Fragment viewGrid(Fragment view, Context context) {
        return this.myFragment;
    }

    @Override
    public Fragment viewList(Fragment view, Context context) {
        return this.manager.changeState(new ListViewState(this.manager));
    }

    @Override
    public Fragment viewMap(Fragment view, Context context) {
        return this.manager.changeState(new MapViewState(this.manager));
    }

    @Override
    public Fragment getFragment() {
        return myFragment;
    }

    public void updateFragment(){
        this.myFragment.setAdapter(getAdapter());
        this.myFragment.setLayoutManager(getLayoutManager(myFragment.getContext()));
    }

    public RecyclerView.Adapter getAdapter() {
        return this.adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }




}
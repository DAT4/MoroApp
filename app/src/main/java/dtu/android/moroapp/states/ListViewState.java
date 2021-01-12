package dtu.android.moroapp.states;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dtu.android.moroapp.Event_Recycler_Fragment;
import dtu.android.moroapp.adapters.ColorThemeManager;
import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.ListViewAdapter;
import dtu.android.moroapp.states.GridViewState;
import dtu.android.moroapp.states.IListState;
import dtu.android.moroapp.states.MapViewState;

public class ListViewState implements IListState {

    EventsViewManager manager;
    Event_Recycler_Fragment myFragment;
    Event_Recycler_Fragment mySavedEvents;
    ListViewAdapter adapter;
    ColorThemeManager colorThemeManager;

    public ListViewState(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
        this.colorThemeManager = this.manager.colorThemeManager;
        this.adapter = new ListViewAdapter(this.manager.dataToView,colorThemeManager);
        this.myFragment = new Event_Recycler_Fragment(this.adapter,getLayoutManager(this.manager.context));
        this.mySavedEvents = new Event_Recycler_Fragment(this.adapter,getLayoutManager(this.manager.context));
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

    @Override
    public View getView() {
        RecyclerView recyclerView = new RecyclerView(this.manager.context);
        recyclerView.setLayoutManager(getLayoutManager(this.manager.context));
        recyclerView.setAdapter(getAdapter());
        return recyclerView;
    }
}

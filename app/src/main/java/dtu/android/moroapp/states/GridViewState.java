package dtu.android.moroapp.states;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.ui.cards.Event_Recycler_Fragment;
import dtu.android.moroapp.adapters.ColorThemeManager;
import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.GridViewAdapter;
import dtu.android.moroapp.models.event.Event;

public class GridViewState implements IListState {

    private EventsViewManager manager;
    Event_Recycler_Fragment myFragment;
    GridViewAdapter adapter;
    ColorThemeManager colorThemeManager;


    public GridViewState(EventsViewManager eventsViewManager) {
        this.manager = eventsViewManager;
        this.colorThemeManager = this.manager.colorThemeManager;
        this.adapter = new GridViewAdapter(this.manager.dataToView,colorThemeManager, this.manager.customClick, this.manager.location);
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

    @Override
    public void updateEvents(List<Event> events) {
        this.adapter.setLocalDataSet(events);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public View getView() {
        RecyclerView recyclerView = new RecyclerView(this.manager.context);
        recyclerView.setLayoutManager(getLayoutManager(this.manager.context));
        recyclerView.setAdapter(getAdapter());
        return recyclerView;
    }


}

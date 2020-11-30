package dtu.android.moroapp.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.models.Event;

public class EventsViewManager {

    IListState state;
    List<Event> dataToView;

    // Get the data
    public EventsViewManager(List<Event> titles) {
        this.dataToView = titles;
        this.state = new ListViewAdapter(this);
    }

    public void changeState(IListState listState) {
        this.state = listState;
    }

    public void viewGrid() {
        this.state.viewGrid();
    }

    public void viewList() {
        this.state.viewList();
    }
    public void viewMap() {
        this.state.viewMap();
    }

    public RecyclerView.Adapter getAdapter() {
        return this.state.getAdapter();
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return this.state.getLayoutManager(context);
    }




}
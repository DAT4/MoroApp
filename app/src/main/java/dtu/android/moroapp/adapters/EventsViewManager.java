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

    public void viewGrid(RecyclerView view, Context context) {
        this.state.viewGrid(view, context);
        updateView(view, context);
    }



    public void viewList(RecyclerView view, Context context) {
        this.state.viewList(view, context);
        updateView(view, context);
    }
    public void viewMap(RecyclerView view, Context context) {
        this.state.viewMap(view, context);
        updateView(view, context);
    }


    public void updateView(RecyclerView view, Context context) {
        view.setAdapter(getAdapter());
        view.setLayoutManager(getLayoutManager(context));
    }

    public RecyclerView.Adapter getAdapter() {
        return this.state.getAdapter();
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return this.state.getLayoutManager(context);
    }




}

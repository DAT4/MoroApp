package dtu.android.moroapp.adapters;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.models.Event;

public class EventsViewManager {

    IListState state;
    List<Event> dataToView;
    Context context;
    IRecyclerViewClickListener customClick;

    // Get the data
    public EventsViewManager(List<Event> titles, Context context, IRecyclerViewClickListener customOnClick) {
        this.dataToView = titles;
        this.state = new ListViewState(this);
        this.context = context;
        this.customClick = customOnClick;
    }

    public Fragment changeState(IListState listState) {
        this.state = listState;
        return getFragment();
    }

    public Fragment viewGrid(Fragment view, Context context) {
        return this.state.viewGrid(view, context);
    }

    public Fragment viewList(Fragment view, Context context) {
        return this.state.viewList(view, context);
    }

    public Fragment viewMap(Fragment view, Context context) {
        return this.state.viewMap(view, context);
    }

    public RecyclerView.Adapter getAdapter() {
        return this.state.getAdapter();
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return this.state.getLayoutManager(context);
    }

    public Fragment getFragment(){
        return this.state.getFragment();
    }
    public void updateEventsList( List<Event> list) {
        this.dataToView = list;
    }

    public void updateFragment() {
        this.state.updateFragment();
    }

    public void updateEvents( List<Event> events){
        this.dataToView = events;
        this.state.updateEvents(events);
        //this.state.updateFragment();
    }


    public View getView() {
        return this.state.getView();
    }
}

package dtu.android.moroapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.states.IListState;
import dtu.android.moroapp.states.ListViewState;

public class EventsViewManager {

    IListState state;
    public List<Event> dataToView;
    public Context context;

    // Get the data
    public EventsViewManager(List<Event> titles, Context context) {
        this.dataToView = titles;
        this.state = new ListViewState(this);
        this.context = context;
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

    public void updateFragment(){
        this.state.updateFragment();
    }


}

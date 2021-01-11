package dtu.android.moroapp.states;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public interface IListState {
    Fragment viewGrid(Fragment view, Context context);
    Fragment viewList(Fragment view, Context context);
    Fragment viewMap(Fragment view, Context context);
    Fragment getFragment();
    void updateFragment();
    RecyclerView.Adapter getAdapter();
    RecyclerView.LayoutManager getLayoutManager(Context context);
}

package dtu.android.moroapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public interface IListState {
    Fragment viewGrid(Fragment view, Context context);
    Fragment viewList(Fragment view, Context context);
    Fragment viewMap(Fragment view, Context context);
    Fragment getFragment();
    RecyclerView.Adapter getAdapter();
    RecyclerView.LayoutManager getLayoutManager(Context context);
}

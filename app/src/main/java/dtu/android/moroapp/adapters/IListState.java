package dtu.android.moroapp.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface IListState {
    void viewGrid();
    void viewList();
    void viewMap();
    RecyclerView.Adapter getAdapter();
    RecyclerView.LayoutManager getLayoutManager(Context context);
}

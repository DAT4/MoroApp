package dtu.android.moroapp.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface IListState {
    void viewGrid(RecyclerView view, Context context);
    void viewList(RecyclerView view, Context context);
    void viewMap(RecyclerView view, Context context);
    RecyclerView.Adapter getAdapter();
    RecyclerView.LayoutManager getLayoutManager(Context context);
}

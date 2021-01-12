package dtu.android.moroapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Event_Recycler_Fragment extends Fragment {

    RecyclerView recyclerView;
    View root;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;

    public Event_Recycler_Fragment(RecyclerView.Adapter adapter, RecyclerView.LayoutManager manager){
        this.adapter = adapter;
        this.manager = manager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate( R.layout.event_recycler_fragment, container, false );
        //recyclerView = root.findViewById(R.id.recyclerView);

       // Fragment fragment = new Fragment();

        recyclerView = new RecyclerView(getContext());
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(manager);

        LinearLayout layout = root.findViewById(R.id.eventRecyclerViewContainer);
        layout.addView(recyclerView);


        return root;
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        this.recyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        this.recyclerView.setLayoutManager(layoutManager);
    }
}

package dtu.android.moroapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Event_Recycler_Fragment extends Fragment {

    RecyclerView recyclerView;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate( R.layout.event_recycler_fragment, container, false );

        recyclerView = root.findViewById(R.id.recyclerView);

        return root;
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        this.recyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        this.recyclerView.setLayoutManager(layoutManager);
    }
}

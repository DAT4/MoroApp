package dtu.android.moroapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.GridViewAdapter;
import dtu.android.moroapp.adapters.ListViewAdapter;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.Location;
import dtu.android.moroapp.observer.ConcreteEvents;

public class RightNowFragment extends Fragment implements View.OnClickListener {

    private Button back;
    Button btnList, btnGrid, btnMap;
    private View root;
    RecyclerView listview;
    EventsViewManager viewManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.right_now_fragment, container, false);

        back = root.findViewById(R.id.right_now_back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class );
                startActivity(intent);
                }
            }
        );

        // BTN setup
        btnList = root.findViewById(R.id.viewList);
        btnList.setOnClickListener(this);

        btnGrid = root.findViewById(R.id.viewGrid);
        btnGrid.setOnClickListener(this);

        btnMap = root.findViewById(R.id.viewMap);
        btnMap.setOnClickListener(this);

        // Test values
        List<Event> events = (List<Event>) ConcreteEvents.INSTANCE.getAllEvents();

        // Manger setup
        viewManager = new EventsViewManager(events);

        // recycler view setup
        listview = root.findViewById(R.id.recyclerView);
        updateView();

        return root;
    }

    public void viewList(View view) {
        // Change View
        viewManager.viewList();

        // Update to screen
        updateView();
    }

    public void viewGrid(View view) {
        viewManager.viewGrid();
        updateView();
    }

    public void viewMap(View view) {
        viewManager.viewMap();
        updateView();
    }

    void updateView () {
        listview.setAdapter(viewManager.getAdapter());
        listview.setLayoutManager(viewManager.getLayoutManager(this.getContext()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewList:
                viewList(v);
                break;
            case R.id.viewGrid:
                viewGrid(v);
                break;
            case R.id.viewMap:
                viewMap(v);
                break;
            default:
                break;
        }
    }
}

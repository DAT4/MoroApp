package dtu.android.moroapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.observer.ConcreteEvents;


public class AnbefaledeFragment extends Fragment implements View.OnClickListener {

    private View root;
    RecyclerView recyclerView;
    EventsViewManager eventsViewManager;
    Button btnList, btnGrid, btnMap;
    Fragment myFragment;
    FragmentManager fragmentManager;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_anbefalede, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        // Events
        //List<Event> events = (List<Event>) ConcreteEvents.INSTANCE.getAllEvents();

        List<Event> events = new ArrayList<>();
        events.add(ConcreteEvents.INSTANCE.getAllEvents().get(2));

        // Manager setup
        eventsViewManager = new EventsViewManager(events,getContext());

        // BTN setup
        btnList = root.findViewById(R.id.recommendEvents_list_view);
        btnList.setOnClickListener(this);

        btnGrid = root.findViewById(R.id.recommendEvents_grid_view);
        btnGrid.setOnClickListener(this);

        btnMap = root.findViewById(R.id.recommendEvents_map_view);
        btnMap.setOnClickListener(this);

        //recyclerView = root.findViewById(R.id.recommendRecyclerView);

        myFragment = eventsViewManager.getFragment();

        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();

        //updateView();

        return root;
    }

    void viewList(View v) {
        myFragment = eventsViewManager.viewList(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();
    }

    void viewGrid(View v) {
        myFragment = eventsViewManager.viewList(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();
    }

    void viewMap(View v) {
        myFragment = eventsViewManager.viewList(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();
    }

    void updateView() {
        recyclerView.setAdapter(eventsViewManager.getAdapter());
        recyclerView.setLayoutManager(eventsViewManager.getLayoutManager(this.getContext()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recommendEvents_list_view:
                viewList(v);
                break;
            case R.id.recommendEvents_grid_view:
                viewGrid(v);
                break;
            case R.id.recommendEvents_map_view:
                viewMap(v);
                break;
            default:
                break;
        }
    }
}
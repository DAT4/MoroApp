package dtu.android.moroapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.observer.ConcreteEvents;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AnbefaledeFragment extends Fragment implements View.OnClickListener {

    private View root;
    RecyclerView recyclerView;
    EventsViewManager eventsViewManager;
    Button btnList, btnGrid, btnMap;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_anbefalede, container, false);

        // Events
        List<Event> events = (List<Event>) ConcreteEvents.INSTANCE.getAllEvents();

        // Manager setup
        eventsViewManager = new EventsViewManager(events);

        // BTN setup
        btnList = root.findViewById(R.id.recommendEvents_list_view);
        btnList.setOnClickListener(this);

        btnGrid = root.findViewById(R.id.recommendEvents_grid_view);
        btnGrid.setOnClickListener(this);

        btnMap = root.findViewById(R.id.recommendEvents_map_view);
        btnMap.setOnClickListener(this);

        recyclerView = root.findViewById(R.id.recommendRecyclerView);
        updateView();

        return root;
    }

    void viewList(View v) {
        eventsViewManager.viewList(recyclerView, this.getContext());
    }

    void viewGrid(View v) {

        eventsViewManager.viewGrid(recyclerView, this.getContext());
    }

    void viewMap(View v) {
        eventsViewManager.viewMap(recyclerView, this.getContext());
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
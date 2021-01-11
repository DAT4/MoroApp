package dtu.android.moroapp;

import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.EventDao;
import dtu.android.moroapp.models.EventRoomDatabase;
import dtu.android.moroapp.models.EventViewModel;
import dtu.android.moroapp.observer.ConcreteEvents;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedEventsFragment extends Fragment implements View.OnClickListener {

    private View view;
    Button btnList, btnGrid, btnMap;
    RecyclerView recyclerView;
    EventsViewManager eventsViewManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_saved_events, container, false);

  /*      ImageView imageListView = view.findViewById(R.id.savedEvents_list_view);
        ImageView imageGridView = view.findViewById(R.id.savedEvents_grid_view);
        ImageView imageMapView = view.findViewById(R.id.savedEvents_map_view);

        imageListView.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIconOrange));
        imageGridView.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIconOrange));
        imageMapView.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIconOrange)); */

        // BTN setup
        btnList = view.findViewById(R.id.savedEvents_list_view);
        btnList.setOnClickListener(this);

        btnGrid = view.findViewById(R.id.savedEvents_grid_view);
        btnGrid.setOnClickListener(this);

        btnMap = view.findViewById(R.id.savedEvents_map_view);
        btnMap.setOnClickListener(this);

        // load saved events events
        List<Event> saveEvents = new ArrayList<>();
        saveEvents.add(ConcreteEvents.INSTANCE.getAllEvents().get(2));

        //Database and saved events;
        EventViewModel eventViewModel = new EventViewModel(getActivity().getApplication());

        // Inserting data and saving data
        eventViewModel.insert(saveEvents.get(0));

        // Get data and Async tast
        List<Event> events = new ArrayList<>();
        // Manger setup
        eventsViewManager = new EventsViewManager(events);

        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventsList) {
                eventsViewManager.updateEventsList(eventsList);
            }
        });


        //events.add(ConcreteEvents.INSTANCE.getAllEvents().get(3));



        // recycler view setup
        recyclerView = view.findViewById(R.id.savedEventsRecyclerView);
        updateView();

        return view;
    }

    private void updateView() {
        recyclerView.setAdapter(eventsViewManager.getAdapter());
        recyclerView.setLayoutManager(eventsViewManager.getLayoutManager(this.getContext()));
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savedEvents_list_view:
                viewList(v);
                break;
            case R.id.savedEvents_grid_view:
                viewGrid(v);
                break;
            case R.id.savedEvents_map_view:
                viewMap(v);
                break;
            default:
                break;
        }
    }
}
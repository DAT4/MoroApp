package dtu.android.moroapp.ui.fragments.minProfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.Theme;
import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.IRecyclerViewClickListener;
import dtu.android.moroapp.models.event.Event;
import dtu.android.moroapp.mvvm.EventRoomViewModel;
//import dtu.android.moroapp.mvvm.EventViewModel;


public class SavedEventsFragment extends Fragment implements View.OnClickListener, IRecyclerViewClickListener {

    private View view;
    Button btnList, btnGrid, btnMap;
    RecyclerView recyclerView;
    EventsViewManager eventsViewManager;
    Fragment myFragment;
    FragmentManager fragmentManager;
    List<Event> events;
    EventRoomViewModel localEventViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_saved_events, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

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
        events = new ArrayList<>();
        //saveEvents.add(ConcreteEvents.INSTANCE.getAllEvents().get(2));
        localEventViewModel = new ViewModelProvider(requireActivity()).get(EventRoomViewModel.class);

       /* onlineEventViewModel.getEvents().observe(getViewLifecycleOwner(), modelEvents -> {
            events = onlineEventViewModel.getEvents().getValue().getData();
        }); */

        // Manger setup
        eventsViewManager = new EventsViewManager(events,getContext(), Theme.ORANGE, this);

        updateEvents();


        //Database and saved events;
        //EventViewModel eventViewModel = new EventViewModel(getActivity().getApplication());

        // Inserting data and saving data
        //eventViewModel.insert(saveEvents.get(0));

        // Get data and Async tast
       /* List<Event> events = new ArrayList<>();
        events.add(ConcreteEvents.INSTANCE.getAllEvents().get(2));
        events.add(ConcreteEvents.INSTANCE.getAllEvents().get(3)); */

        /*eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventsList) {
                eventsViewManager.updateEventsList(eventsList);
            }
        }); */


        //events.add(ConcreteEvents.INSTANCE.getAllEvents().get(3));

        // Manger setup
        //eventsViewManager = new EventsViewManager(events,getContext(),Theme.ORANGE);

        // recycler view setup
        //recyclerView = view.findViewById(R.id.savedEventsRecyclerView);

        myFragment = eventsViewManager.getFragment();

        fragmentManager.beginTransaction().replace(R.id.savedEvents_container,myFragment).commit();

        //updateView();

        return view;
    }

    private void updateEvents() {
        localEventViewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                //events = events;
                for (Event event: events) {
                    event.setSaved(true);
                }

                eventsViewManager.updateEvents(events);
            }
        });
    }

    private void updateView() {
        recyclerView.setAdapter(eventsViewManager.getAdapter());
        recyclerView.setLayoutManager(eventsViewManager.getLayoutManager(this.getContext()));
    }

    void viewList(View v) {
        myFragment = eventsViewManager.viewList(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.savedEvents_container,myFragment).commit();
    }

    void viewGrid(View v) {
        myFragment = eventsViewManager.viewGrid(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.savedEvents_container,myFragment).commit();
    }

    void viewMap(View v) {
        myFragment = eventsViewManager.viewMap(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.savedEvents_container,myFragment).commit();
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

    @Override
    public void onItemClick(Event event) {
        localEventViewModel.removeFromSavedEvents(event.getTitle());
        updateEvents();

    }
}
package dtu.android.moroapp;

import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.observer.ConcreteEvents;


public class SavedEventsFragment extends Fragment implements View.OnClickListener {

    private View view;
    Button btnList, btnGrid, btnMap;
    RecyclerView recyclerView;
    EventsViewManager eventsViewManager;
    Fragment myFragment;
    FragmentManager fragmentManager;

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
        List<Event> events = new ArrayList<>();
        events.add(ConcreteEvents.INSTANCE.getAllEvents().get(2));


        // Manger setup
        eventsViewManager = new EventsViewManager(events,getContext());

        // recycler view setup
        //recyclerView = view.findViewById(R.id.savedEventsRecyclerView);

        myFragment = eventsViewManager.getFragment();

        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();

        //updateView();

        return view;
    }

    private void updateView() {
        recyclerView.setAdapter(eventsViewManager.getAdapter());
        recyclerView.setLayoutManager(eventsViewManager.getLayoutManager(this.getContext()));
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
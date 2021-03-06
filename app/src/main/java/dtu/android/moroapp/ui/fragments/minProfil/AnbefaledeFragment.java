package dtu.android.moroapp.ui.fragments.minProfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
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

public class AnbefaledeFragment extends Fragment implements View.OnClickListener, IRecyclerViewClickListener {

    private View root;
    RecyclerView recyclerView;
    EventsViewManager eventsViewManager;
    Button btnList, btnGrid, btnMap;
    Fragment myFragment;
    FragmentManager fragmentManager;
    EventRoomViewModel localEventViewModel;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_anbefalede, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        // BTN setup
        btnList = root.findViewById(R.id.recommendEvents_list_view);
        btnList.setOnClickListener(this);

        btnGrid = root.findViewById(R.id.recommendEvents_grid_view);
        btnGrid.setOnClickListener(this);

        btnMap = root.findViewById(R.id.recommendEvents_map_view);
        btnMap.setOnClickListener(this);

        // Events
        List<Event> events = new ArrayList<>();
        //List<Event> events = new ArrayList<>();
        //events.add(ConcreteEvents.INSTANCE.getAllEvents().get(2));

        // View model setup
        localEventViewModel = new ViewModelProvider(requireActivity()).get(EventRoomViewModel.class);

        // Manager setup
        eventsViewManager = new EventsViewManager(events,getContext(), Theme.ORANGE, this);



        //recyclerView = root.findViewById(R.id.recommendRecyclerView);

        myFragment = eventsViewManager.getFragment();

        fragmentManager.beginTransaction().replace(R.id.recommendEvents_container,myFragment).commit();

        //updateView();

        return root;
    }

    void viewList(View v) {
        myFragment = eventsViewManager.viewList(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.recommendEvents_container,myFragment).commit();
    }

    void viewGrid(View v) {
        myFragment = eventsViewManager.viewGrid(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.recommendEvents_container,myFragment).commit();
    }

    void viewMap(View v) {
        myFragment = eventsViewManager.viewMap(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.recommendEvents_container,myFragment).commit();
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

    @Override
    public void onItemClick(Event event) {
        localEventViewModel.insert(event);
    }
}
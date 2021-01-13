package dtu.android.moroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.IRecyclerViewClickListener;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.FindEventModel;
import dtu.android.moroapp.mvvm.EventDatabase;
import dtu.android.moroapp.mvvm.EventRepository;
import dtu.android.moroapp.mvvm.EventViewModel;
import dtu.android.moroapp.mvvm.EventRoomViewModel;
import dtu.android.moroapp.mvvm.EventViewModelProviderFactory;
import dtu.android.moroapp.utils.EventFilters;
import kotlin.Pair;

public class Search_results extends Fragment implements View.OnClickListener, IRecyclerViewClickListener {

    private Button back;
    Button btnList, btnGrid, btnMap;
    private View root;
    RecyclerView listview;
    EventsViewManager viewManager;
    Fragment myFragment;
    FragmentManager fragmentManager;
    EventViewModel viewModel;
    EventRoomViewModel localEventViewModel;
    NavController navController;
    List<Event> events;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate( R.layout.fragment_search_results, container, false );

        back = root.findViewById(R.id.right_now_back);

        fragmentManager = getActivity().getSupportFragmentManager();

        back.setOnClickListener(view -> {
                navController = Navigation.findNavController(view);
                navController.navigate(Search_resultsDirections.Companion.actionSearchResultsToFrontPageFragment());
            }
        );

        btnList = root.findViewById(R.id.viewList);
        btnList.setOnClickListener(this);

        btnGrid = root.findViewById(R.id.viewGrid);
        btnGrid.setOnClickListener(this);

        btnMap = root.findViewById(R.id.viewMap);
        btnMap.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        //Skal bruges til ViewModel
        /*EventRepository eventRepository = new EventRepository(new EventDatabase( requireContext()));
        EventViewModelProviderFactory factory = new EventViewModelProviderFactory(eventRepository);
        EventViewModel viewModel = new ViewModelProvider(this,factory).get(EventViewModel.class);*/

        //List<Event> events = Arrays.asList(Search_resultsArgs.fromBundle(getArguments()).getEvents());

        viewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        ArrayList<Pair<EventFilters, String>> filter = FindEventModel.INSTANCE.getFilters();
        events = viewModel.getFilteredEvents(filter).getValue().getData();

        viewManager = new EventsViewManager(events,getContext(),Theme.GREEN, this);

        myFragment = viewManager.getFragment();

        localEventViewModel = new ViewModelProvider(requireActivity()).get(EventRoomViewModel.class);

        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();
    }

    public void viewList(){
        myFragment = viewManager.viewList(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();
    }

    public void viewGrid(){
        myFragment = viewManager.viewGrid(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();
    }

    public void viewMap(){
        myFragment = viewManager.viewMap(null,this.getContext());
        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewList:
                viewList();
                break;
            case R.id.viewGrid:
                viewGrid();
                break;
            case R.id.viewMap:
                viewMap();
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
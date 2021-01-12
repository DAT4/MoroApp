package dtu.android.moroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.FindEventModel;
import dtu.android.moroapp.mvvm.EventViewModel;
import dtu.android.moroapp.utils.EventFilters;
import kotlin.Pair;

public class Search_results extends Fragment implements View.OnClickListener {

    private Button back;
    Button btnList, btnGrid, btnMap;
    private View root;
    RecyclerView listview;
    EventsViewManager viewManager;
    Fragment myFragment;
    FragmentManager fragmentManager;
    EventViewModel viewModel;
    NavController navController;


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


        viewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        ArrayList<Pair<EventFilters, String>> filter = FindEventModel.INSTANCE.getFilters();
        List<Event> events = viewModel.getFilteredEvents(filter).getValue().getData();

        viewManager = new EventsViewManager(events,getContext(),Theme.GREEN);

        myFragment = viewManager.getFragment();

        fragmentManager.beginTransaction().replace(R.id.container_fragment,myFragment).commit();

        return root;
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
}
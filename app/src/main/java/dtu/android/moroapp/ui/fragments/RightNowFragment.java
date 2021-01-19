package dtu.android.moroapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.ui.MainActivity;
import dtu.android.moroapp.R;
import dtu.android.moroapp.Theme;
import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.IRecyclerViewClickListener;
import dtu.android.moroapp.models.event.Event;
import dtu.android.moroapp.mvvm.EventViewModel;
import dtu.android.moroapp.mvvm.EventRoomViewModel;

public class RightNowFragment extends Fragment implements View.OnClickListener, IRecyclerViewClickListener {

    private Button back;
    Button btnList, btnGrid, btnMap;
    private View root;
    RecyclerView listview;
    EventsViewManager viewManager;
    Fragment myFragment;
    FragmentManager fragmentManager;
    NavController navController;
    EventViewModel viewModel;
    EventRoomViewModel localEventViewModel;
    List<Event> events;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate( R.layout.right_now_fragment, container, false );

        back = root.findViewById( R.id.right_now_back );

        fragmentManager = getActivity().getSupportFragmentManager();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();


        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 navController = Navigation.findNavController(view);
                 navController.popBackStack();
            }
        });

        // BTN setup
        btnList = root.findViewById( R.id.viewList );
        btnList.setOnClickListener( this );

        btnGrid = root.findViewById( R.id.viewGrid );
        btnGrid.setOnClickListener( this );

        btnMap = root.findViewById( R.id.viewMap );
        btnMap.setOnClickListener( this );

        // saved events load
        localEventViewModel = new ViewModelProvider( requireActivity() ).get( EventRoomViewModel.class );
        List<Event> savedEvents = localEventViewModel.getEvents().getValue();

        // Instantiate viewModel
        viewModel = new ViewModelProvider( requireActivity() ).get( EventViewModel.class );

        // Online events
        //viewModel.getEvents().observe( getViewLifecycleOwner(), listResource -> events = viewModel.getEvents().getValue().getData() );
        events = new ArrayList<>();


        updateEvents();



        // Manger setup
        viewManager = new EventsViewManager( events, getContext(), Theme.BLUE, this );

        // recycler view setup
        //listview = root.findViewById(R.id.recyclerView);
        //updateView();

        myFragment = viewManager.getFragment();

        fragmentManager.beginTransaction().replace( R.id.container_fragment, myFragment ).commit();

        //viewManager.updateFragment();

        /*FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFragment, myFragment);
        transaction.addToBackStack(null);
        transaction.commit();*/

        return root;
    }

    private void updateEvents() {
        localEventViewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> savedEvents) {
                events = viewModel.getEvents().getValue().getData();
                //events = events;
                // setup if saved
                for (Event event : events) {
                    for (int i = 0; i < savedEvents.size(); i++) {
                        Event test = savedEvents.get(i);

                        if (event.getTitle().equals(test.getTitle())) {
                            event.setSaved(true);
                        }

                    }
                }

                viewManager.updateEvents(events);
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        navController = Navigation.findNavController( view );

        /*
        back.setOnClickListener( view1 -> navController.navigate( RightNowFragmentDirections
                .Companion.actionRightNowFragmentToFrontPageFragment() )
        );

         */

    }

    public void viewList(View view) {
        // Change View
        myFragment = viewManager.viewList( null, this.getContext() );
        fragmentManager.beginTransaction().replace( R.id.container_fragment, myFragment ).commit();
    }

    public void viewGrid(View view) {
        myFragment = viewManager.viewGrid( null, this.getContext() );
        fragmentManager.beginTransaction().replace( R.id.container_fragment, myFragment ).commit();
    }

    public void viewMap(View view) {
        myFragment = viewManager.viewMap( null, this.getContext() );
        fragmentManager.beginTransaction().replace( R.id.container_fragment, myFragment ).commit();
    }

    void updateView() {
        listview.setAdapter( viewManager.getAdapter() );
        listview.setLayoutManager( viewManager.getLayoutManager( this.getContext() ) );
    }

    @Override
    public void onStart() {
        super.onStart();

        //viewManager.updateFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewList:
                viewList( v );
                break;
            case R.id.viewGrid:
                viewGrid( v );
                break;
            case R.id.viewMap:
                viewMap( v );
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(Event event) {
        localEventViewModel.insert( event );
    }
}

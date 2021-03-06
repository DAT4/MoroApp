package dtu.android.moroapp.ui.fragments.findEvent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import dtu.android.moroapp.R;
import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.TabAdapter;
import dtu.android.moroapp.ui.fragments.findEvent.findEvent_interface_FragmentDirections;
import dtu.android.moroapp.models.FindEventModel;
import kotlin.Pair;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class findEvent_interface_Fragment extends Fragment {

    private ViewPager2 viewPager;
    private TabItem tabWhen, tabWhere, tabWhat;
    EventsViewManager viewManager;
    Fragment myFragment;
    FragmentManager fragmentManager;
    FloatingActionButton search;

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        root = inflater.inflate(R.layout.fragment_find_event_interface, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        search = root.findViewById(R.id.search_button_find_event);


        viewPager = root.findViewById(R.id.find_event_viewPager);
        viewPager.setAdapter(new TabAdapter(getActivity()));

        TabLayout tabLayout = root.findViewById(R.id.find_event_tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager, (tab, position) -> {
                            switch (position) {
                                case 0:
                                    tab.setText("Hvornår");
                                    break;
                                case 1:
                                    tab.setText("Hvor");
                                    break;
                                case 2:
                                    tab.setText("hvad");
                                    break;
                            }
                }
        );
        tabLayoutMediator.attach();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //NavDirections action = findEvent_interface_FragmentDirections.Companion.actionFindEventInterfaceFragmentToSearchResults(filter);

                Navigation.findNavController(view).navigate(findEvent_interface_FragmentDirections.Companion.actionFindEventInterfaceFragmentToSearchResults());
            }
        } );

    }

}


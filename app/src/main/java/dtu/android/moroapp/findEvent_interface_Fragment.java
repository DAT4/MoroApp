package dtu.android.moroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.TabAdapter;
import dtu.android.moroapp.models.FindEventModel;
import dtu.android.moroapp.utils.EventFilters;
import dtu.android.moroapp.utils.GraphQL.Filter;
import dtu.android.moroapp.utils.GraphQL.FilterType;
import kotlin.Pair;


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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Pair<EventFilters, String>> choice = FindEventModel.INSTANCE.getFilters();
                Filter filter = new Filter.Builder().filters(EventFilters.CATEGORY,;);

                //NavDirections action = findEvent_interface_FragmentDirections.Companion.actionFindEventInterfaceFragmentToSearchResults(filter);

                Navigation.findNavController(view).navigate(findEvent_interface_FragmentDirections.Companion.actionFindEventInterfaceFragmentToSearchResults());
            }
        });

    }

}


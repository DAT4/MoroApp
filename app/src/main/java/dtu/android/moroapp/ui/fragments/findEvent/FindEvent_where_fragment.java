package dtu.android.moroapp.ui.fragments.findEvent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.FindEventModel;
import dtu.android.moroapp.mvvm.EventViewModel;
import dtu.android.moroapp.mvvm.Filter;

public class FindEvent_where_fragment extends Fragment{

    private GridLayout grid;
    private View root;
    private EventViewModel viewModel;

    public FindEvent_where_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_find_event_where_fragment, container, false);

        grid = root.findViewById(R.id.grid_where);

        setupGrid();

        viewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        return root;
    }

    private void setupGrid() {
        for (int i = 0; i < grid.getChildCount(); i++) {
            ToggleButton button = (ToggleButton) grid.getChildAt(i);
            button.setOnClickListener(view -> {
                if(button.isChecked()) {
                    Log.i("FindEvenWhere",button.getTextOff().toString());
                    add(button.getTextOff().toString());
                }
                if (!button.isChecked()) {
                    remove(button.getTextOff().toString());
                }
            });

        }
    }

    private void add(String area){
        FindEventModel
                .Filters
                .getInstance()
                .add(new Filter.Inclusive.Area(area));
    }
    private void remove(String area){
        FindEventModel
                .Filters
                .getInstance()
                .remove(new Filter.Inclusive.Area(area));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
package dtu.android.moroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dtu.android.moroapp.models.FindEventModel;
import dtu.android.moroapp.mvvm.Filter;
import kotlin.Pair;

public class findEvent_what_fragment extends Fragment {

    private View root;
    private GridLayout grid;



    public findEvent_what_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_find_event_what_fragment, container, false);
        grid = root.findViewById(R.id.grid_what);

        setupGrid();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setupGrid() {
        for (int i = 0; i < grid.getChildCount(); i++) {
            ToggleButton button = (ToggleButton) grid.getChildAt(i);

            button.setOnClickListener(view -> {
                if(button.isChecked()) {
                    FindEventModel.INSTANCE.getFilter().add(new Filter.InclusiveFilter.CategoryFilter(button.getTextOff().toString()));
                }
                if (!button.isChecked()) {
                    FindEventModel.INSTANCE.getFilter().remove(new Filter.InclusiveFilter.CategoryFilter(button.getTextOff().toString()));
                }
            });

        }
    }

}
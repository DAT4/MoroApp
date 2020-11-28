package dtu.android.moroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import dtu.android.moroapp.models.FindEventModel;
import dtu.android.moroapp.utils.Query;
import kotlin.Pair;

public class FindEvent_where_fragment extends Fragment{

    private GridLayout grid;
    private View root;

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


        return root;
    }

    private void setupGrid() {
        for (int i = 0; i < grid.getChildCount(); i++) {
            ToggleButton button = (ToggleButton) grid.getChildAt(i);
            button.setOnClickListener(view -> {
                if(button.isChecked()) {
                    FindEventModel.INSTANCE.getFilters().add(new Pair<>(Query.Filter.AREA, button.getTextOff().toString()));
                }
                if (!button.isChecked()) {
                    FindEventModel.INSTANCE.getFilters().remove(new Pair<>(Query.Filter.AREA, button.getTextOff().toString()));
                }
            });

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
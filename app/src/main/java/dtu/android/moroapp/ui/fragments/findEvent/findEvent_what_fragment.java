package dtu.android.moroapp.ui.fragments.findEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.FindEventModel;
import dtu.android.moroapp.mvvm.Filter;

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
                    add(button.getTextOff().toString().toLowerCase());
                }
                if (!button.isChecked()) {
                    remove(button.getTextOff().toString().toLowerCase());
                }
            });

        }
    }
    private void add(String category){
        FindEventModel
                .Filters
                .getInstance()
                .add(new Filter.Inclusive.Category(category));
    }
    private void remove(String category){
        FindEventModel
                .Filters
                .getInstance()
                .remove(new Filter.Inclusive.Category(category));
    }

}
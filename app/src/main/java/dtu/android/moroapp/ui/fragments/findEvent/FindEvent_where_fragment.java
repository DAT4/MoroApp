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

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.FindEventModel;
import dtu.android.moroapp.mvvm.Filter;
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
                    Log.i("FindEvenWhere",button.getTextOff().toString());
                    FindEventModel.INSTANCE.getFilter().add(new Filter.InclusiveFilter.AreaFilter(button.getTextOff().toString()));
                }
                if (!button.isChecked()) {
                    FindEventModel.INSTANCE.getFilter().remove(new Filter.InclusiveFilter.AreaFilter(button.getTextOff().toString()));
                }
            });

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
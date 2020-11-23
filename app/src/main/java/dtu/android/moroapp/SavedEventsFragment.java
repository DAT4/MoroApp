package dtu.android.moroapp;

import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedEventsFragment extends Fragment {

    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_saved_events, container, false);

  /*      ImageView imageListView = view.findViewById(R.id.savedEvents_list_view);
        ImageView imageGridView = view.findViewById(R.id.savedEvents_grid_view);
        ImageView imageMapView = view.findViewById(R.id.savedEvents_map_view);

        imageListView.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIconOrange));
        imageGridView.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIconOrange));
        imageMapView.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIconOrange)); */


        // Inflate the layout for this fragment
        return view;
    }
    }
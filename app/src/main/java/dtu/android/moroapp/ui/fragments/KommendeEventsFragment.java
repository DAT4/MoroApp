package dtu.android.moroapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dtu.android.moroapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KommendeEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KommendeEventsFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kommende_events, container, false);
    }
}
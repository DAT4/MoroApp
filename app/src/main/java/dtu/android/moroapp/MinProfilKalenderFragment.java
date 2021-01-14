package dtu.android.moroapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class MinProfilKalenderFragment extends Fragment {

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_min_profil_kalender, container, false);

        CalendarView calender = root.findViewById(R.id.profileCalender);
        calender.setFirstDayOfWeek(2);

        return root;
    }
}
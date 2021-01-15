package dtu.android.moroapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.riontech.calendar.CustomCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class MinProfilKalenderFragment extends Fragment {

    View root;
    Button next;
    Button prev;
    MaterialCalendarView calender;
    CalendarDay calendarDay;
    EventDecorator decorator;
    HashSet<CalendarDay> dates;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_min_profil_kalender, container, false);

        calender = root.findViewById(R.id.calendarView);

        calendarDay = CalendarDay.from(2021,1,20);

        dates = new HashSet<>();

        dates.add(calendarDay);

        decorator = new EventDecorator(R.color.colorIconOrange,dates);

        calender.addDecorator(decorator);

        //CalendarView calender = root.findViewById(R.id.profileCalender);
        //calender.setFirstDayOfWeek(2);

        return root;
    }
}
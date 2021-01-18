package dtu.android.moroapp.ui.fragments.minProfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import dtu.android.moroapp.EventDecorator;
import dtu.android.moroapp.R;
import dtu.android.moroapp.models.event.Event;
import dtu.android.moroapp.mvvm.EventRoomViewModel;

public class MinProfilKalenderFragment extends Fragment {

    View root;
    MaterialCalendarView calender;
    CalendarDay calendarDay;
    EventDecorator decorator;
    HashSet<CalendarDay> dates = new HashSet<>();
    EventRoomViewModel savedEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_min_profil_kalender, container, false);

        calender = root.findViewById(R.id.calendarView);

        savedEvents = new ViewModelProvider(requireActivity()).get(EventRoomViewModel.class);

        //updateEvents();
        List<Event> events;
        events = savedEvents.getEvents().getValue();


        for (int i = 0; i < events.size(); i++) {
            LocalDateTime temp = events.get(i).getLocalDate();
            calendarDay = CalendarDay.from(temp.getYear(),temp.getMonthValue(),temp.getDayOfMonth());

            dates.add(calendarDay);
        }

        decorator = new EventDecorator(R.color.colorIconOrange,dates);
        calender.addDecorator(decorator);

        return root;
    }

    private void updateEvents() {
        savedEvents.getEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {

                for (int i = 0; i < events.size(); i++) {
                    LocalDate temp = LocalDate.ofEpochDay(events.get(i).getTime());
                    calendarDay = CalendarDay.from(temp.getYear(),temp.getMonthValue(),temp.getDayOfMonth());

                    dates.add(calendarDay);
                }

                decorator = new EventDecorator(R.color.colorIconOrange,dates);
                calender.addDecorator(decorator);

                synchronized(calender){
                    calender.notifyAll();
                }
            }
        });
    }
}
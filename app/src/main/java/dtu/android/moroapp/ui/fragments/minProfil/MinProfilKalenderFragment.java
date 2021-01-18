package dtu.android.moroapp.ui.fragments.minProfil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dtu.android.moroapp.EventDecorator;
import dtu.android.moroapp.R;
import dtu.android.moroapp.Theme;
import dtu.android.moroapp.adapters.EventsViewManager;
import dtu.android.moroapp.adapters.IRecyclerViewClickListener;
import dtu.android.moroapp.models.event.Event;
import dtu.android.moroapp.mvvm.EventRoomViewModel;
import dtu.android.moroapp.states.MapViewState;

public class MinProfilKalenderFragment extends Fragment implements IRecyclerViewClickListener {

    View root;
    MaterialCalendarView calender;
    CalendarDay calendarDay;
    EventDecorator decorator;
    HashSet<CalendarDay> dates = new HashSet<>();
    EventRoomViewModel savedEvents;
    FragmentManager fragmentManager;
    Fragment myFragment;
    List<Event> events;
    EventsViewManager eventsViewManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_min_profil_kalender, container, false);

       calender = root.findViewById(R.id.calendarView);



        // view list
        fragmentManager = getActivity().getSupportFragmentManager();

        /// local data
        savedEvents = new ViewModelProvider(requireActivity()).get(EventRoomViewModel.class);

        //updateEvents();
        events = savedEvents.getEvents().getValue();


        // setup dates
        for (int i = 0; i < events.size(); i++) {
            LocalDateTime temp = events.get(i).getLocalDate();
            calendarDay = CalendarDay.from(temp.getYear(),temp.getMonthValue(),temp.getDayOfMonth());

            dates.add(calendarDay);
        }

        // Update calender view with events
        decorator = new EventDecorator(R.color.colorIconOrange ,dates);
        calender.addDecorator(decorator);
        //calender.setSelectionColor(R.color.colorIconOrange);

        // setup event lisenter for calender
        calender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                updateEventList(date);
            }
        });

        // List view stuff
        eventsViewManager = new EventsViewManager(events,getContext(), Theme.ORANGE, this);;
        myFragment = eventsViewManager.getFragment();
        fragmentManager.beginTransaction().replace(R.id.calenderEvents,myFragment).commit();

        return root;
    }

    void updateEventList(CalendarDay date) {
        ArrayList<Event> tempList = new ArrayList<>();

        for (int i = 0; i < events.size(); i++) {
            // check for the selected date
            LocalDateTime temp = events.get(i).getLocalDate();
            if (date.getYear() == temp.getYear() && date.getMonth() == temp.getMonthValue() && date.getDay() == temp.getDayOfMonth()) {
                tempList.add(events.get(i));
            }
        }

        eventsViewManager.updateEvents(tempList);
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

    @Override
    public void onItemClick(Event event) {
        savedEvents.removeFromSavedEvents(event.getTitle());
        updateEvents();
    }
}
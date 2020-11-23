package dtu.android.moroapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;

import dtu.android.moroapp.adapters.Frontpage_Adapter;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.observer.ConcreteEvents;

public class ViewPager extends Fragment {

    private View myFragment;
    private ViewPager2 viewPager;
    private ArrayList<Event> events;

    public ViewPager() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myFragment = inflater.inflate( R.layout.fragment_view_pager, container, false );

        viewPager = (ViewPager2) myFragment.findViewById(R.id.viewPager);

        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        setUpViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager2 viewPager) {

        events = (ArrayList<Event>) ConcreteEvents.INSTANCE.getAllEvents();

        Frontpage_Adapter frontpage_adapter = new Frontpage_Adapter(this.getContext(),events);

        Adapter adapter = (Adapter) frontpage_adapter;

        viewPager.setAdapter(adapter);
    }
}
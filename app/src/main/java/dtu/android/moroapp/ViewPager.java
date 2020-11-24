package dtu.android.moroapp;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dtu.android.moroapp.adapters.Frontpage_Adapter;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.observer.ConcreteEvents;

public class ViewPager extends Fragment {

    private View myFragment;
    private ViewPager viewPager;
    private ArrayList<Event> events;
    private TextView title;
    private TextView desc;
    private ImageView image;
    private Event event;

    public ViewPager() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myFragment = inflater.inflate( R.layout.fragment_view_pager, container, false );

        /*title = myFragment.findViewById(R.id.card_item_titel);
        desc = myFragment.findViewById(R.id.card_item_desc);
        image = myFragment.findViewById(R.id.bannerID);

        title.setText(getArguments().getString("Titel"));
        desc.setText(getArguments().getString("Desc"));
        */

        viewPager = myFragment.findViewById(R.id.viewPager);

        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        setUpViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {

        events = (ArrayList<Event>) ConcreteEvents.INSTANCE.getAllEvents();

        Frontpage_Adapter frontpage_adapter = new Frontpage_Adapter(this.getContext(),events);

        Adapter adapter = (Adapter) frontpage_adapter;


    }


}
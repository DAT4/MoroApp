package dtu.android.moroapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dtu.android.moroapp.R;
import dtu.android.moroapp.ViewPager;
import dtu.android.moroapp.models.Event;

public class FrontPageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Event> modelArrayList;
    private Context context;

    public FrontPageAdapter(FragmentManager fm, ArrayList<Event> modelArrayList, Context context) {
        super(fm);
        this.modelArrayList = modelArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        ViewPager viewPager = new ViewPager();

        View view = LayoutInflater.from(context).inflate( R.layout.card_item_view_pager, container, false);

        //Making the view ready
        ImageView bannerID = view.findViewById( R.id.bannerID);
        TextView card_item_titel = view.findViewById(R.id.card_item_titel);
        TextView card_item_desc = view.findViewById(R.id.card_item_desc);

        Event myModel = modelArrayList.get(position);
        String titel = myModel.getTitle();
        String desc = myModel.getGenre();

        Picasso.get().load(myModel.getImage()).into(bannerID);

        return viewPager;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

}
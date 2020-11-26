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

    private ArrayList<Fragment> mFragments;

    public FrontPageAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
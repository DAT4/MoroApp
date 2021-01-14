package dtu.android.moroapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dtu.android.moroapp.adapters.ProfileOverviewTabAdapter;
import dtu.android.moroapp.databinding.FragmentOverViewMyProfileBinding;

public class OverViewMyProfile extends Fragment {

    Button myProfile;
    Button calender;
    ViewPager2 viewPager;
    View root;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //binding = FragmentOverViewMyProfileBinding.inflate( inflater, container, false );
        root = inflater.inflate(R.layout.fragment_over_view_my_profile,container,false);

        //fragmentManager = getActivity().getSupportFragmentManager();

        //myCalenderFragment = new MinProfilKalenderFragment();
        //myProfileFragment = new MinProfilFragment();

        /*myProfile = binding.getRoot().findViewById( R.id.myProfileButton );
        myProfile.setOnClickListener( this );

        calender = binding.getRoot().findViewById( R.id.myKalenderButton );
        calender.setOnClickListener( this );*/

        viewPager = root.findViewById(R.id.myProfileOverviewViewPager);
        viewPager.setAdapter( new ProfileOverviewTabAdapter(getActivity()));

        TabLayout tabLayout = root.findViewById(R.id.myProfileOverviewTab);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText( "MIN PROFIL" );
                    break;
                case 1:
                    tab.setText( "KALENDER" );
                    break;
            }
        }
        );
        tabLayoutMediator.attach();

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

}
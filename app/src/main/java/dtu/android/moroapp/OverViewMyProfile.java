package dtu.android.moroapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dtu.android.moroapp.databinding.FragmentOverViewMyProfileBinding;

public class OverViewMyProfile extends Fragment {

    Button myProfile;
    Button calender;
    private FragmentOverViewMyProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOverViewMyProfileBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


    }

}
package dtu.android.moroapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FindEvent_when_fragment extends Fragment implements View.OnClickListener {
    private Button buttonDown_when, back;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_find_event_when, container, false);

        buttonDown_when = root.findViewById(R.id.button_Down_when);
        back = root.findViewById(R.id.when_back);

        buttonDown_when.setOnClickListener(this);
        back.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        if(view == buttonDown_when) {
            findEvent_what_fragment newFragment = new findEvent_what_fragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(view == back) {
            findEvent_interface_Fragment newFragment = new findEvent_interface_Fragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
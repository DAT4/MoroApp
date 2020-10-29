package dtu.android.moroapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class findEvent_interface_Fragment extends Fragment implements View.OnClickListener {

    private Button buttonWhen, buttonWhere, buttonWhat;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        root = inflater.inflate(R.layout.fragment_find_event_interface, container, false);

        buttonWhen = root.findViewById(R.id.button_when);
        buttonWhere = root.findViewById(R.id.button_where);
        buttonWhat = root.findViewById(R.id.button_what);

        buttonWhat.setOnClickListener(this);
        buttonWhere.setOnClickListener(this);
        buttonWhen.setOnClickListener(this);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonWhat) {
            findEvent_what_fragment newFragment = new findEvent_what_fragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (view == buttonWhen) {
            FindEvent_when_fragment newFragment = new FindEvent_when_fragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (view == buttonWhere) {
            FindEvent_where_fragment newFragment = new FindEvent_where_fragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}


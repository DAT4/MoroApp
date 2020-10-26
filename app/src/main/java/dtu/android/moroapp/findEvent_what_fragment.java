package dtu.android.moroapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class findEvent_what_fragment extends Fragment implements View.OnClickListener {

    private Button ButtonOp_what, ButtonDown_what;
    private View root;

    public findEvent_what_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_find_event_what_fragment, container, false);

        ButtonOp_what = root.findViewById(R.id.button_Op_what);
        ButtonDown_what = root.findViewById(R.id.button_Down_what);

        ButtonOp_what.setOnClickListener(this);
        ButtonDown_what.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View view){
        if(view == ButtonOp_what){
            FindEvent_when_fragment newFragment = new FindEvent_when_fragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if(view == ButtonDown_what){
            FindEvent_where_fragment newFragment = new FindEvent_where_fragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
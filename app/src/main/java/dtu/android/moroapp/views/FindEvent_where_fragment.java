package dtu.android.moroapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import dtu.android.moroapp.R;

public class FindEvent_where_fragment extends Fragment implements View.OnClickListener{

    private Button ButtonOp_where, ButtonDown_where, back;
    private View root;

    public FindEvent_where_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_find_event_where_fragment, container, false);

        ButtonOp_where = root.findViewById(R.id.button_Op_where);
        ButtonDown_where = root.findViewById(R.id.button_Down_where);
        back = root.findViewById(R.id.where_back);

        ButtonOp_where.setOnClickListener(this);
        ButtonDown_where.setOnClickListener(this);
        back.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View view){
         if(view == ButtonOp_where) {
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
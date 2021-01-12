package dtu.android.moroapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
public class MinProfilFragment extends Fragment implements View.OnClickListener {

    View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_min_profil, container, false);

        FragmentTransaction transaction;

//        getFragmentManager().beginTransaction()
//                .add(R.id.fragment1 ,new SavedEventsFragment(),"saved")
//                .add(,new AnbefaledeFragment(),"recommend").commit();

        //getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment1 ,new AnbefaledeFragment(),"recommend").commit();

        return root;
     }

    @Override
    public void onClick(View view) {
        //TO-DO
    }
}
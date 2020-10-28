package dtu.android.moroapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Burger_fragment extends Fragment {

    private Button bTipOs, bOmOs, bKontakt, bClose;
    private View root;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_burger_fragment, container, false);

        bTipOs = root.findViewById(R.id.burger_tip_os);
        bOmOs = root.findViewById(R.id.burger_om_os);
        bKontakt = root.findViewById(R.id.burger_kontakt_os);
        bClose = root.findViewById(R.id.burger_close);

        bTipOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new Tip_Os_Fragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFragment, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        bOmOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new Om_Os_Fragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFragment, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        bKontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new Kontakt_Os_Fragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFragment, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        bClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new FrontPageFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFragment, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return root;
    }
}
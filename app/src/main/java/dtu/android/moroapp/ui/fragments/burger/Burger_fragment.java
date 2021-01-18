package dtu.android.moroapp.ui.fragments.burger;

import android.net.CaptivePortal;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import dtu.android.moroapp.R;

public class Burger_fragment extends Fragment {

    private Button bTipOs, bOmOs, bKontakt, bClose;
    private View root;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_burger_fragment, container, false);

        bTipOs = root.findViewById(R.id.burger_tip_os);
        bOmOs = root.findViewById(R.id.burger_om_os);
        bKontakt = root.findViewById(R.id.burger_kontakt_os);
        bClose = root.findViewById(R.id.burger_close);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        bTipOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_burger_fragment_to_tip_Os_Fragment);
            }
        });

        bKontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_burger_fragment_to_kontakt_Os_Fragment);
            }
        });

        bClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(Burger_fragmentDirections.Companion.actionBurgerFragmentPop());
            }
        });


        bOmOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_burger_fragment_to_om_Os_Fragment);
            }
        });
    }
}
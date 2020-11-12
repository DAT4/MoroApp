package dtu.android.moroapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import dtu.android.moroapp.R;

public class RightNowFragment extends Fragment {

    private Button back;
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.right_now_fragment, container, false);

        back = root.findViewById(R.id.right_now_back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class );
                startActivity(intent);
                }
            }
        );

        return root;
    }
}

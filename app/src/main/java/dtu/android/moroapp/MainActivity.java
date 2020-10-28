package dtu.android.moroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bRightNow, bHome;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bRightNow = (Button) findViewById(R.id.nav_now);
        bHome = (Button) findViewById(R.id.nav_home);


        bRightNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightNow();
            }
        });

        bHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FrontPageFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });




        if (savedInstanceState == null) {
            Fragment fragment = new FrontPageFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.mainFragment, fragment).commit();

        }

    }

    private void rightNow() {
        Fragment fragment = new RightNowFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
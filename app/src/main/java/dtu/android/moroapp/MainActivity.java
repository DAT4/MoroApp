package dtu.android.moroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import dtu.android.moroapp.observer.ConcreteEvents;
import dtu.android.moroapp.utils.Query;

import dtu.android.moroapp.utils.Query.Filter;

public class MainActivity extends AppCompatActivity {

    private Button bRightNow, bHome, bMyProfile, bBurger;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConcreteEvents c = ConcreteEvents.INSTANCE;
        int t = (int) System.currentTimeMillis();
        Query q = new Query.Builder()
                .filter(Filter.TIMEGT,t)
                .build();
        c.cache(q);

        bRightNow = (Button) findViewById(R.id.nav_now);
        bHome = (Button) findViewById(R.id.nav_home);
        bMyProfile = (Button) findViewById(R.id.nav_profile);
        bBurger = (Button) findViewById(R.id.nav_burger);


        bRightNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightNow();
            }
        });

        bBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Burger_fragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        bMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Fragment fragment = new MinProfilFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFragment, fragment)
                    .addToBackStack(null)
                    .commit();
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
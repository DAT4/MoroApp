package dtu.android.moroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.Event;

public class Event_Map_Fragment extends Fragment implements OnMapReadyCallback {

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    GoogleMap map;
    View root;
    MapView mapView;
    List<Event> events;

    public Event_Map_Fragment(List<Event> events){
        this.events = events;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate( R.layout.event_map_fragment, container, false );

        mapView = root.findViewById(R.id.viewMap);

        initGoogleMap(savedInstanceState);

        return root;
    }

    public void initGoogleMap(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if(savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStart();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<String> addresses = getAddressFromList(events);
        map = googleMap;

        double v1;
        double v2;
        String title;

        String[] temp = addresses.get(0).split(" ,");

        v1 = Double.parseDouble(temp[0]);
        v2 = Double.parseDouble(temp[1]);
        title = temp[2];

        LatLng address = new LatLng(v1, v2);
        map.addMarker(new MarkerOptions().position(address).title(title));

        for (int i = 1; i < addresses.size() - 1; i++) {
            String[] temp2 = addresses.get(i).split(" ,");

            if(!(temp2[0].equals(temp[0])) && !(temp2[1].equals(temp[1]))){

                v1 = Double.parseDouble(temp2[0]);
                v2 = Double.parseDouble(temp2[1]);
                title = temp2[2];

                LatLng address2 = new LatLng(v1, v2);
                map.addMarker(new MarkerOptions().position(address2).title(title));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public ArrayList<String> getAddressFromList(List<Event> events){
        ArrayList<String> addresses = new ArrayList<>();


        for(int i = 0; i < events.size(); i++){
            String temp = "";

            temp += events.get(i).getLocation().getCoordinates().getLatitude() +" ,";
            temp += events.get(i).getLocation().getCoordinates().getLongitude() + " ,";
            temp += events.get(i).getTitle();

            addresses.add(temp);
        }
        return addresses;
    }
}
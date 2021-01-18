package dtu.android.moroapp.ui.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.event.Event;

public class Event_Map_Fragment extends Fragment implements OnMapReadyCallback {

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    GoogleMap map;
    View root;
    MapView mapView;
    List<Event> events;
    //List<String> titles;

    public Event_Map_Fragment(List<Event> events){
        this.events = events;
        //mapView = new MapView(getContext());
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
        mapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<String> addresses = getAddressFromList(events);
        map = googleMap;
        LatLng address = null;

        for (int i = 0; i < addresses.size(); i++) {

            double v1;
            double v2;

            String[] temp = addresses.get(i).split(" ,");

            v1 = Double.parseDouble(temp[0]);
            v2 = Double.parseDouble(temp[1]);

            address = new LatLng(v1, v2);
            map.addMarker(new MarkerOptions().position(address).title("Marker"));
        }
        map.moveCamera(CameraUpdateFactory.newLatLng(address));
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
        ArrayList<String> newList = new ArrayList<>();

        //FindEventModel.INSTANCE.getFilters().add(new Pair<>( EventFilters.) )

        for(int i = 0; i < events.size(); i++){
            String temp = "";
            //String title;

            temp += events.get(i).getLocation().getCoordinates().getLatitude() +" ,";
            temp += events.get(i).getLocation().getCoordinates().getLongitude() + " ,";
            //title = events.get(i).getTitle();

            //titles.add(title);
            newList.add(temp);
        }

        ArrayList<String> addresses = removeDuplicates(newList);

        return addresses;
    }

    public ArrayList<String> removeDuplicates(ArrayList<String> list){
        ArrayList<String> newList = new ArrayList<>();

        for (String str : list) {
            if(!newList.contains(str)){
                newList.add(str);
            }
        }

        return newList;
    }

}
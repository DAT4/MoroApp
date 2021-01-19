package dtu.android.moroapp.adapters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dtu.android.moroapp.Theme;
import dtu.android.moroapp.models.event.Event;
import dtu.android.moroapp.states.IListState;
import dtu.android.moroapp.states.ListViewState;

public class EventsViewManager {

    public IListState state;
    public List<Event> dataToView;
    public Context context;
    public IRecyclerViewClickListener customClick;
    public Theme theme;
    public ColorThemeManager colorThemeManager;
    public LocationManager locationManager;
    public Location location;

    // Get the data
    public EventsViewManager(List<Event> titles, Context context, Theme theme, IRecyclerViewClickListener customOnClick) {
        this.dataToView = titles;
        this.context = context;
        this.customClick = customOnClick;
        this.theme = theme;
        this.colorThemeManager = new ColorThemeManager(theme);
        setupLocaiton();
        this.state = new ListViewState(this);

    }

    private void setupLocaiton() {
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        this.location = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 20, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                location = location;
                state.updateEvents(dataToView);
            }
        });
    }

    public Fragment changeState(IListState listState) {
        this.state = listState;
        return getFragment();
    }

    public Fragment viewGrid(Fragment view, Context context) {
        return this.state.viewGrid(view, context);
    }

    public Fragment viewList(Fragment view, Context context) {
        return this.state.viewList(view, context);
    }

    public Fragment viewMap(Fragment view, Context context) {
        return this.state.viewMap(view, context);
    }

    public RecyclerView.Adapter getAdapter() {
        return this.state.getAdapter();
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return this.state.getLayoutManager(context);
    }

    public Fragment getFragment(){
        return this.state.getFragment();
    }
    public void updateEventsList( List<Event> list) {
        this.dataToView = list;
    }

    public void updateFragment() {
        this.state.updateFragment();
    }

    public void updateEvents( List<Event> events){
        this.dataToView = events;
        this.state.updateEvents(events);
        //this.state.updateFragment();
    }


    public View getView() {
        return this.state.getView();
    }
}

package dtu.android.moroapp.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dtu.android.moroapp.models.Event;

public class eventRepository {

    private static eventRepository instance;
    private ArrayList<Event> dataSet = new ArrayList<>();

    public static eventRepository getInstance() {
        if (instance == null) {
            instance = new eventRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Event>> getEvents() {

        //TODO: Implement get online data

        MutableLiveData<List<Event>> data = new MutableLiveData<>();
        data.setValue(dataSet);

        return data;

    }
}

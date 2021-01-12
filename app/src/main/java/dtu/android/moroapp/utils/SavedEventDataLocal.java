package dtu.android.moroapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class SavedEventDataLocal {
    Context context;

    public SavedEventDataLocal(Context context) {
        this.context = context;
    }

    public void addToSavedEvents(Event event) {

    }

    public void saveEventList (List<Event> events) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SavedEvents", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }

    public List<Event> getSavedEventsList() {
        return null;
    }

}

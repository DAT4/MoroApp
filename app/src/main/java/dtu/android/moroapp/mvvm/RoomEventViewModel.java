package dtu.android.moroapp.mvvm;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.EventDao;
import dtu.android.moroapp.mvvm.EventRoomDatabase;

public class RoomEventViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private EventDao eventDao;
    private EventRoomDatabase eventRoomDatabase;
    private LiveData<List<Event>> allEvents;

    public RoomEventViewModel(Application application) {
        super(application);

        eventRoomDatabase = EventRoomDatabase.getDatabase(application);
        eventDao = eventRoomDatabase.eventDao();
        allEvents = eventDao.getAll();
    }

    public void insert(Event event) {
        new InsertAsyncTask(eventDao).execute(event);
    }

    public LiveData<List<Event>> getEvents() {

        return allEvents;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<Event, Void, Void> {
        EventDao eventDao;

        public InsertAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            try {
                eventDao.insert(events[0]);
            } catch (Exception e) {

            }
            return null;
        }
    }
}

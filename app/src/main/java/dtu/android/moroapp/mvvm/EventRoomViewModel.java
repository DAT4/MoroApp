package dtu.android.moroapp.mvvm;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dtu.android.moroapp.models.event.Event;
import dtu.android.moroapp.models.EventDao;

public class EventRoomViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private EventDao eventDao;
    private EventRoomDatabase eventRoomDatabase;
    private LiveData<List<Event>> allEvents;

    public EventRoomViewModel(Application application) {
        super(application);

        eventRoomDatabase = EventRoomDatabase.getDatabase(application);
        eventDao = eventRoomDatabase.eventDao();
        allEvents = eventDao.getAll();
    }

    public LiveData<List<Event>> getEvents() {
        return allEvents;
    }

    // Insert events
    public void insert(Event event) {
        new InsertAsyncTask(eventDao).execute(event);
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

    // Delete events
    public void removeFromSavedEvents(String title){
        new DeleteAsyncTask(eventDao).execute(title);
    }

    private class DeleteAsyncTask extends AsyncTask<String, Void, Void> {
        EventDao eventDao;

        public DeleteAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(String... titles) {
            try {
                eventDao.removeFromSavedEvents(titles[0]);
            } catch (Exception e) {

            }
            return null;
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

}

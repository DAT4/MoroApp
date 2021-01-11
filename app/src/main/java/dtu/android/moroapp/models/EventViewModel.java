package dtu.android.moroapp.models;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.loader.content.AsyncTaskLoader;

import java.sql.SQLClientInfoException;
import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private EventDao eventDao;
    private EventRoomDatabase eventRoomDatabase;
    private LiveData<List<Event>> allEvents;

    public EventViewModel(Application application) {
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

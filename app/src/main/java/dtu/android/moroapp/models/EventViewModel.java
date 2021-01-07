package dtu.android.moroapp.models;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.loader.content.AsyncTaskLoader;

public class EventViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private EventDao eventDao;
    private EventRoomDatabase eventRoomDatabase;

    public EventViewModel(Application application) {
        super(application);

        eventRoomDatabase = EventRoomDatabase.getDatabase(application);
        eventDao = eventRoomDatabase.eventDao();
    }

    public void insert(Event event) {
        new InsertAsyncTask(eventDao).execute(event);
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
            eventDao.insert(events[0]);
            return null;
        }
    }
}

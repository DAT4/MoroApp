package dtu.android.moroapp.mvvm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.EventDao;

@Database(entities = Event.class, version = 1, exportSchema = false)
public abstract class EventRoomDatabase extends RoomDatabase {

    public abstract EventDao eventDao();

    // singleton object
    private static volatile EventRoomDatabase instance;

    // singleton init
    static EventRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (EventRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), EventRoomDatabase.class, "event_database").build();
                }
            }
        }

        return instance;
    }


}

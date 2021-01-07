package dtu.android.moroapp.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Event.class, version = 1)
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
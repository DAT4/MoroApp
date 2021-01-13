package dtu.android.moroapp.mvvm;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import dtu.android.moroapp.models.Converters;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.EventDao;

@Database(entities = Event.class, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class EventRoomDatabase extends RoomDatabase {

    public abstract EventDao eventDao();

    // singleton object
    private static volatile EventRoomDatabase instance;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE events ADD COLUMN category TEXT NOT NULL DEFAULT ''");
        }
    };


    // singleton init
    static EventRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (EventRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),  EventRoomDatabase.class, "event_database").fallbackToDestructiveMigration().build();
                    //instance = Room.databaseBuilder(context.getApplicationContext(), EventRoomDatabase.class, "event_database").build();
                }
            }
        }

        return instance;
    }


}

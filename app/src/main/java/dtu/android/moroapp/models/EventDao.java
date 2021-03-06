package dtu.android.moroapp.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dtu.android.moroapp.models.event.Event;

@Dao
public interface EventDao {

    @Insert
    void insert(Event event);

    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAll();

    @Query("DELETE FROM events WHERE title = :title")
    void removeFromSavedEvents(String title);

}

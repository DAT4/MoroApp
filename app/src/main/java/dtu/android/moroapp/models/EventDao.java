package dtu.android.moroapp.models;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface EventDao {

    @Insert
    void insert(Event event);
}

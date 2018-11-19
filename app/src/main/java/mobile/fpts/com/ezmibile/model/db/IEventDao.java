package mobile.fpts.com.ezmibile.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.events.EventsDB;

@Dao
public interface IEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EventsDB events);

    @Query("SELECT * FROM EventsDB")
    List<EventsDB> getEventsList();

    @Query("DELETE FROM EventsDB")
    void deleteEventsDB();

    @Query("SELECT *FROM EventsDB")
    List<EventsDB> getAllEventsDB();

    @Query("SELECT *FROM EventsDB WHERE id =:id")
    List<EventsDB> getAllByEventsDBId(int id);

    @Insert
    void addDataEvents(EventsDB dataEvents);

}

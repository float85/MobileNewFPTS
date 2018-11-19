package mobile.fpts.com.ezmibile.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndicesDB;

@Dao
public interface IWorldIndicesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorldIndicesDB... worldIndices);

    @Query("SELECT * FROM WorldIndicesDB")
    List<WorldIndicesDB> getWorldIndicesList();

    @Query("DELETE FROM WorldIndicesDB")
    void deleteWorldIndicesDB();

}

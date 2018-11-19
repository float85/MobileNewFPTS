package mobile.fpts.com.ezmibile.view.marketOverview.market.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


/**
 * Created by HoaDT  on 4/17/2018.
 */
@Dao
public interface IMarketOverviewMarketDao {
    @Query("SELECT * FROM MarketData")
    List<MarketData> getAll();

    @Query("SELECT * FROM MarketData WHERE isSave = 1")
    List<MarketData> getAllCheck();

    @Query("UPDATE MarketData SET isSave = :isSave  WHERE marketName = :marketName")
    void update(String marketName, boolean isSave);

    @Query("SELECT isSave FROM MarketData WHERE marketName =:marketName")
    int getVnIndicesCheck(String marketName);

    @Query("SELECT * FROM MarketData WHERE marketName = :marketName LIMIT 1")
    MarketData getMarketData(String marketName);

//     TODO:HoaDT 6/20/2018 Tên thị trường là duy nhất trong bảng
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MarketData marketData);

    @Query("DELETE FROM MARKETDATA")
    void deleteAll();
}

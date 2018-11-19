package mobile.fpts.com.ezmibile.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.stock.StockInfoDB;

@Dao
public interface IStockInfoDao {
    @Query("DELETE FROM StockInfoDB")
    void deleteSearchStockData();

    @Query("SELECT *FROM StockInfoDB")
    List<StockInfoDB> getSearchStockData();

    @Query("SELECT *FROM StockInfoDB WHERE id =:id")
    List<StockInfoDB> getSearchStockDataByID(int id);

    @Query("SELECT *FROM StockInfoDB WHERE stock_code = :symbol LIMIT 1")
    StockInfoDB getSearchStockDataBySymbol(String symbol);

    @Insert
    void addMaCk(StockInfoDB searchStockData);


}

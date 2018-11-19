package mobile.fpts.com.ezmibile.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.news.NewsArticleDB;

@Dao
public interface INewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewsArticleDB news);

    @Query("SELECT * FROM NewsArticleDB")
    List<NewsArticleDB> getNewsList();

    @Query("DELETE FROM NewsArticleDB")
    void deleteNewsArticleDB();

    // TODO: TamHV 6/28/2018 xóa thư mục news cũ để cập nhập dữ liệu mới
    @Query("DELETE FROM NewsArticleDB WHERE news_id = :news_id")
    void deleteNewsArticleDB_ID(String news_id);

    // TODO: TamHV 6/28/2018 lấy dữ liệu theo stype
    @Query("SELECT * FROM NewsArticleDB WHERE stype = :stype")
    List<NewsArticleDB> getNewsListFormStype(String stype);

}

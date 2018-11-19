package mobile.fpts.com.ezmibile.model.entity.news;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

/**
 * Created by FIT-thuctap22 on 3/23/2018.
 */
@Entity
public class NewsArticleDB {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "news_id")
    private String newsId;
    @ColumnInfo(name = "news_title")
    private String newsTitle;
    @ColumnInfo(name = "news_date")
    private String newsDate;
    @ColumnInfo(name = "news_size_in_byte")
    private String newsSizeInByte;
    @ColumnInfo(name = "news_size_in_kb")
    private String newsSizeInKB;
    @ColumnInfo(name = "news_date2")
    private String newsDate2;
    @ColumnInfo(name = "news_f_title")
    private String newsFTitle;
    @ColumnInfo(name = "news_content")
    private String newsContent;
    @ColumnInfo(name = "news_img")
    private String newsImg;
    @ColumnInfo(name = "stype")
    private String newStype;

    @Ignore
    public NewsArticleDB() {
    }

    public NewsArticleDB(String newsId, String newsTitle, String newsDate, String newsSizeInByte,
                         String newsSizeInKB, String newsDate2, String newsFTitle, String newsContent,
                         String newsImg, String newStype) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsDate = newsDate;
        this.newsSizeInByte = newsSizeInByte;
        this.newsSizeInKB = newsSizeInKB;
        this.newsDate2 = newsDate2;
        this.newsFTitle = newsFTitle;
        this.newsContent = newsContent;
        this.newsImg = newsImg;
        this.newStype = newStype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsSizeInByte() {
        return newsSizeInByte;
    }

    public void setNewsSizeInByte(String newsSizeInByte) {
        this.newsSizeInByte = newsSizeInByte;
    }

    public String getNewsSizeInKB() {
        return newsSizeInKB;
    }

    public void setNewsSizeInKB(String newsSizeInKB) {
        this.newsSizeInKB = newsSizeInKB;
    }

    public String getNewsDate2() {
        return newsDate2;
    }

    public void setNewsDate2(String newsDate2) {
        this.newsDate2 = newsDate2;
    }

    public String getNewsFTitle() {
        return newsFTitle;
    }

    public void setNewsFTitle(String newsFTitle) {
        this.newsFTitle = newsFTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    public String getNewStype() {
        return newStype;
    }

    public void setNewStype(String newStype) {
        this.newStype = newStype;
    }
}

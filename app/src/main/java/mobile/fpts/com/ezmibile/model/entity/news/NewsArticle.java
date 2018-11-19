package mobile.fpts.com.ezmibile.model.entity.news;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FIT-thuctap22 on 3/23/2018.
 */
@Entity
public class NewsArticle {
    @SerializedName("ID")
    @Expose
    private String NewsId;
    @SerializedName("Title")
    @Expose
    private String NewsTitle;
    @SerializedName("Date")
    @Expose
    private String NewsDate;
    @SerializedName("SizeInByte")
    @Expose
    private String NewsSizeInByte;
    @SerializedName("SizeInKB")
    @Expose
    private String NewsSizeInKB;
    @SerializedName("Date2")
    @Expose
    private String NewsDate2;
    @SerializedName("FTitle")
    @Expose
    private String NewsFTitle;
    @SerializedName("Content")
    @Expose
    private String NewsContent;
    @SerializedName("Img")
    @Expose
    private String NewsImg;

    private String newStype;

    @Ignore
    public NewsArticle() {
    }

    public NewsArticle(String newsId, String newsTitle, String newsDate, String newsSizeInByte,
                       String newsSizeInKB, String newsDate2, String newsFTitle, String newsContent,
                       String newsImg) {
        NewsId = newsId;
        NewsTitle = newsTitle;
        NewsDate = newsDate;
        NewsSizeInByte = newsSizeInByte;
        NewsSizeInKB = newsSizeInKB;
        NewsDate2 = newsDate2;
        NewsFTitle = newsFTitle;
        NewsContent = newsContent;
        NewsImg = newsImg;
    }

    public NewsArticle(String newsId, String newsTitle, String newsDate, String newsSizeInByte,
                       String newsSizeInKB, String newsDate2, String newsFTitle, String newsContent,
                       String newsImg, String newStype) {
        NewsId = newsId;
        NewsTitle = newsTitle;
        NewsDate = newsDate;
        NewsSizeInByte = newsSizeInByte;
        NewsSizeInKB = newsSizeInKB;
        NewsDate2 = newsDate2;
        NewsFTitle = newsFTitle;
        NewsContent = newsContent;
        NewsImg = newsImg;
        this.newStype = newStype;
    }

    public String getNewsId() {
        return NewsId;
    }

    public void setNewsId(String newsId) {
        NewsId = newsId;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }

    public String getNewsDate() {
        return NewsDate;
    }

    public void setNewsDate(String newsDate) {
        NewsDate = newsDate;
    }

    public String getNewsSizeInByte() {
        return NewsSizeInByte;
    }

    public void setNewsSizeInByte(String newsSizeInByte) {
        NewsSizeInByte = newsSizeInByte;
    }

    public String getNewsSizeInKB() {
        return NewsSizeInKB;
    }

    public void setNewsSizeInKB(String newsSizeInKB) {
        NewsSizeInKB = newsSizeInKB;
    }

    public String getNewsDate2() {
        return NewsDate2;
    }

    public void setNewsDate2(String newsDate2) {
        NewsDate2 = newsDate2;
    }

    public String getNewsFTitle() {
        return NewsFTitle;
    }

    public void setNewsFTitle(String newsFTitle) {
        NewsFTitle = newsFTitle;
    }

    public String getNewsContent() {
        return NewsContent;
    }

    public void setNewsContent(String newsContent) {
        NewsContent = newsContent;
    }

    public String getNewsImg() {
        return NewsImg;
    }

    public void setNewsImg(String newsImg) {
        NewsImg = newsImg;
    }

    public String getNewStype() {
        return newStype;
    }

    public void setNewStype(String newStype) {
        this.newStype = newStype;
    }
}

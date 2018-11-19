//package mobile.fpts.com.ezmibile.model.entity.events;
//
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.Ignore;
//import android.arch.persistence.room.PrimaryKey;
//import android.support.annotation.NonNull;
//
//import com.google.gson.annotations.Expose;
//
//@Entity
//public class EventsApp {
//    @ColumnInfo(name = "IDX")
//    @Expose
//    private String IDX;
//
//    @ColumnInfo(name = "GroupNm")
//    @Expose
//    private String GroupNm;
//
//    @ColumnInfo(name = "ID")
//    @Expose
//    private String ID;
//
//    @ColumnInfo(name = "stock_code")
//    @Expose
//    private String StockCode;
//
//    @ColumnInfo(name = "Content")
//    @Expose
//    private String Content;
//
//    @ColumnInfo(name = "url")
//    @Expose
//    private String url;
//
//    @ColumnInfo(name = "Date1")
//    @Expose
//    private String Date1;
//
//    @Ignore
//    public EventsApp() {
//    }
//
//    public EventsApp(String IDX, String groupNm, String ID, String stockCode,
//                  String content, String url, String date1) {
//        this.IDX = IDX;
//        GroupNm = groupNm;
//        this.ID = ID;
//        StockCode = stockCode;
//        Content = content;
//        this.url = url;
//        Date1 = date1;
//    }
//
//    public String getIDX() {
//        return IDX;
//    }
//
//    public void setIDX(String IDX) {
//        this.IDX = IDX;
//    }
//
//    public String getGroupNm() {
//        return GroupNm;
//    }
//
//    public void setGroupNm(String groupNm) {
//        GroupNm = groupNm;
//    }
//
//    public String getID() {
//        return ID;
//    }
//
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//
//    public String getStockCode() {
//        return StockCode;
//    }
//
//    public void setStockCode(String stockCode) {
//        this.StockCode = stockCode;
//    }
//
//    public String getContent() {
//        return Content;
//    }
//
//    public void setContent(String content) {
//        Content = content;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getDate1() {
//        return Date1;
//    }
//
//    public void setDate1(String date1) {
//        Date1 = date1;
//    }
//}

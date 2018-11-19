package mobile.fpts.com.ezmibile.view.watchlist.detail.financeOverview;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phamduchuytb on 6/11/2015.
 */
public class EzsReportData {
    @SerializedName("ORDERBY")
    @Expose
    private String ORDERBY;
    @SerializedName("TITLE")
    @Expose
    private String TITLE;
    @SerializedName("TITLE_SHORT")
    @Expose
    private String TITLESHORT;
    @SerializedName("CpnyID")
    @Expose
    private String CpnyID;
    @SerializedName("stock_code")
    @Expose
    private String StockCode;
    @SerializedName("BALANCEY1")
    @Expose
    private String BALANCEY1;
    @SerializedName("CpnyType")
    @Expose
    private String CpnyType;

    @Ignore
    public EzsReportData() {
    }

    public EzsReportData(String ORDERBY, String TITLE, String TITLESHORT, String cpnyID, String stockCode,
                         String BALANCEY1, String cpnyType) {
        this.ORDERBY = ORDERBY;
        this.TITLE = TITLE;
        this.TITLESHORT = TITLESHORT;
        CpnyID = cpnyID;
        StockCode = stockCode;
        this.BALANCEY1 = BALANCEY1;
        CpnyType = cpnyType;
    }

    public String getORDERBY() {
        return ORDERBY;
    }

    public void setORDERBY(String ORDERBY) {
        this.ORDERBY = ORDERBY;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTITLESHORT() {
        return TITLESHORT;
    }

    public void setTITLESHORT(String TITLESHORT) {
        this.TITLESHORT = TITLESHORT;
    }

    public String getCpnyID() {
        return CpnyID;
    }

    public void setCpnyID(String cpnyID) {
        CpnyID = cpnyID;
    }

    public String getStockCode() {
        return StockCode;
    }

    public void setStockCode(String stockCode) {
        StockCode = stockCode;
    }

    public String getBALANCEY1() {
        return BALANCEY1;
    }

    public void setBALANCEY1(String BALANCEY1) {
        this.BALANCEY1 = BALANCEY1;
    }

    public String getCpnyType() {
        return CpnyType;
    }

    public void setCpnyType(String cpnyType) {
        CpnyType = cpnyType;
    }
}
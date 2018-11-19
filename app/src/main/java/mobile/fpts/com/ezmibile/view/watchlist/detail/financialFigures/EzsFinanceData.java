package mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EzsFinanceData {
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
    @SerializedName("RATIO1")
    @Expose
    private String RATIO1;
    @SerializedName("Period")
    @Expose
    private String Period;

    @Ignore
    public EzsFinanceData() {
    }

    public EzsFinanceData(String ORDERBY, String TITLE, String TITLESHORT, String cpnyID,
                          String stockCode, String RATIO1, String period) {
        this.ORDERBY = ORDERBY;
        this.TITLE = TITLE;
        this.TITLESHORT = TITLESHORT;
        CpnyID = cpnyID;
        StockCode = stockCode;
        this.RATIO1 = RATIO1;
        Period = period;
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

    public String getRATIO1() {
        return RATIO1;
    }

    public void setRATIO1(String RATIO1) {
        this.RATIO1 = RATIO1;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }
}

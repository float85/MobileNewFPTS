package mobile.fpts.com.ezmibile.model.entity.chart;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phamduchuytb on 7/23/2015.
 */
@Entity
public class HistoryChartOtherIndex {

    @SerializedName("Open")
    @Expose
    private String chartO;//open
    @SerializedName("High")
    @Expose
    private String chartH;//high
    @SerializedName("Low")
    @Expose
    private String chartL;//low
    @SerializedName("Close")
    @Expose
    private String chartC;//close
    @SerializedName("Vol")
    @Expose
    private String charV;//volumn
    @SerializedName("Date")
    @Expose
    private String charTime;

    @Ignore
    public HistoryChartOtherIndex() {
    }

    public HistoryChartOtherIndex(String chartO, String chartH, String chartL, String chartC, String charV, String charTime) {
        this.chartO = chartO;
        this.chartH = chartH;
        this.chartL = chartL;
        this.chartC = chartC;
        this.charV = charV;
        this.charTime = charTime;
    }

    protected HistoryChartOtherIndex(Parcel in) {
        chartO = in.readString();
        chartH = in.readString();
        chartL = in.readString();
        chartC = in.readString();
        charV = in.readString();
        charTime = in.readString();
    }

    public String getChartO() {
        return chartO;
    }

    public void setChartO(String chartO) {
        this.chartO = chartO;
    }

    public String getChartH() {
        return chartH;
    }

    public void setChartH(String chartH) {
        this.chartH = chartH;
    }

    public String getChartL() {
        return chartL;
    }

    public void setChartL(String chartL) {
        this.chartL = chartL;
    }

    public String getChartC() {
        return chartC;
    }

    public void setChartC(String chartC) {
        this.chartC = chartC;
    }

    public String getCharV() {
        return charV;
    }

    public void setCharV(String charV) {
        this.charV = charV;
    }

    public String getCharTime() {
        return charTime;
    }

    public void setCharTime(String charTime) {
        this.charTime = charTime;
    }
}


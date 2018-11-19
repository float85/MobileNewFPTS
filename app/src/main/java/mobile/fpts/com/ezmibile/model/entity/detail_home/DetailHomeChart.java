package mobile.fpts.com.ezmibile.model.entity.detail_home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailHomeChart {

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Open")
    @Expose
    private String open;
    @SerializedName("High")
    @Expose
    private String high;
    @SerializedName("Low")
    @Expose
    private String low;
    @SerializedName("Close")
    @Expose
    private String close;
    @SerializedName("Vol")
    @Expose
    private String vol;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

}

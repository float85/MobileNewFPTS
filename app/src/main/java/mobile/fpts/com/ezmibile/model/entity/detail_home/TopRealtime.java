package mobile.fpts.com.ezmibile.model.entity.detail_home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopRealtime {

    @SerializedName("sCode")
    @Expose
    private String sCode;
    @SerializedName("sCeiling")
    @Expose
    private String sCeiling;
    @SerializedName("sFloor")
    @Expose
    private String sFloor;
    @SerializedName("sRefercence")
    @Expose
    private String sRefercence;
    @SerializedName("sOpen")
    @Expose
    private String sOpen;
    @SerializedName("sClose")
    @Expose
    private String sClose;
    @SerializedName("sAverage")
    @Expose
    private String sAverage;
    @SerializedName("sHighest")
    @Expose
    private String sHighest;
    @SerializedName("sLowest")
    @Expose
    private String sLowest;
    @SerializedName("sChange")
    @Expose
    private String sChange;
    @SerializedName("sChangePercent")
    @Expose
    private String sChangePercent;
    @SerializedName("sTotalShares")
    @Expose
    private String sTotalShares;
    @SerializedName("sTotalValue")
    @Expose
    private String sTotalValue;

    public String getSCode() {
        return sCode;
    }

    public void setSCode(String sCode) {
        this.sCode = sCode;
    }

    public String getSCeiling() {
        return sCeiling;
    }

    public void setSCeiling(String sCeiling) {
        this.sCeiling = sCeiling;
    }

    public String getSFloor() {
        return sFloor;
    }

    public void setSFloor(String sFloor) {
        this.sFloor = sFloor;
    }

    public String getSRefercence() {
        return sRefercence;
    }

    public void setSRefercence(String sRefercence) {
        this.sRefercence = sRefercence;
    }

    public String getSOpen() {
        return sOpen;
    }

    public void setSOpen(String sOpen) {
        this.sOpen = sOpen;
    }

    public String getSClose() {
        return sClose;
    }

    public void setSClose(String sClose) {
        this.sClose = sClose;
    }

    public String getSAverage() {
        return sAverage;
    }

    public void setSAverage(String sAverage) {
        this.sAverage = sAverage;
    }

    public String getSHighest() {
        return sHighest;
    }

    public void setSHighest(String sHighest) {
        this.sHighest = sHighest;
    }

    public String getSLowest() {
        return sLowest;
    }

    public void setSLowest(String sLowest) {
        this.sLowest = sLowest;
    }

    public String getSChange() {
        return sChange;
    }

    public void setSChange(String sChange) {
        this.sChange = sChange;
    }

    public String getSChangePercent() {
        return sChangePercent;
    }

    public void setSChangePercent(String sChangePercent) {
        this.sChangePercent = sChangePercent;
    }

    public String getSTotalShares() {
        return sTotalShares;
    }

    public void setSTotalShares(String sTotalShares) {
        this.sTotalShares = sTotalShares;
    }

    public String getSTotalValue() {
        return sTotalValue;
    }

    public void setSTotalValue(String sTotalValue) {
        this.sTotalValue = sTotalValue;
    }

}
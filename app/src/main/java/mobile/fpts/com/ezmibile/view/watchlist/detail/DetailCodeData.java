package mobile.fpts.com.ezmibile.view.watchlist.detail;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class DetailCodeData implements Serializable {
    @SerializedName("TIME")
    @Expose
    private String Time;
    //khối lượng niêm yết hiện tại
    @SerializedName("qty")
    @Expose
    private String Qty;
    //KHối lượng DLH hiện tại
    @SerializedName("KLDLHHT")
    @Expose
    private String Kldlhht;
    @SerializedName("DATE")
    @Expose
    private String Date;
    @SerializedName("CURRENT_PRICE")
    @Expose
    private String CurrentPrice;
    @SerializedName("CHANGES")
    @Expose
    private String Changes;
    @SerializedName("PerCHANGE")
    @Expose
    private String PerChange;
    //Giá trị vốn hóa thị trường
    @SerializedName("MktCap")
    @Expose
    private String MktCap;
    @SerializedName("BASIC_PRICE")
    @Expose
    private String BasicPrice;
    @SerializedName("OPEN_PRICE")
    @Expose
    //Tổng khối lượng giao dịch
    private String OpenPrice;
    @SerializedName("TOTAL_TRADING_QTTY")
    @Expose
    private String TotalTradingQtty;

    @SerializedName("FLOOR")
    @Expose
    private String Floor;
    @SerializedName("CEILING")
    @Expose
    private String Ceiling;
    @SerializedName("DIVIDENT_RATE")
    @Expose
    private String DividentRate;
    @SerializedName("HIGHEST_PRICE")
    @Expose
    private String HighestPrice;
    @SerializedName("LOWEST_PRICE")
    @Expose
    private String LowestPrice;
    @SerializedName("PRIOR_CLOSE_PRICE")
    @Expose
    private String PriorClosePrice;
    @SerializedName("RepEPS")
    @Expose
    private String ResEPS;
    @SerializedName("Dividend")
    @Expose
    private String Dividend;
    @SerializedName("P_E")
    @Expose
    private String PE;
    @SerializedName("EPSAdjustedSTC")
    @Expose
    private String EPSAdjustedSTC;
    @SerializedName("EPSbasicFPTS")
    @Expose
    private String EPSbasicFPTS;
    @SerializedName("EPSadjusted4QFPTS")
    @Expose
    private String EPSadjusted4QFPTS;
    @SerializedName("PE4QFPTS")
    @Expose
    private String PE4QFPTS;
    @SerializedName("TOTAL_TRADING_VALUE")
    @Expose
    private String TotalTradingValue;
    @SerializedName("wk52Low")
    @Expose
    private String Wk52Low;
    @SerializedName("wk52High")
    @Expose
    private String Wk52High;
    //NN Mua
    @SerializedName("Dumua")
    @Expose
    private String Dumua;
    //NN Bán
    @SerializedName("Duban")
    @Expose
    private String Duban;
    @SerializedName("EpsFpts")
    @Expose
    private String EpsFpts;
    @SerializedName("CTMG")
    @Expose
    private String CTMG;

    @SerializedName("KLGD_30_Days")
    @Expose
    private String KLGD30Days;
    //tỷ  lệ sở hữu nước ngoài
    @SerializedName("TLSHNN")
    @Expose
    private String TLSHNN;
    @SerializedName("PreCt")
    @Expose
    private String PreCt;
    @SerializedName("NNMUA_YTD")
    @Expose
    private String NNMUA_YTD;
    @SerializedName("NNMUA_YTD30")
    @Expose
    private String NNMUA_YTD30;
    @SerializedName("PB")
    @Expose
    private String PB;

    public DetailCodeData() {
    }

    public DetailCodeData(String time, String qty, String kldlhht, String date, String currentPrice,
                          String changes, String perChange, String mktCap, String basicPrice,
                          String openPrice, String totalTradingQtty, String floor, String ceiling,
                          String dividentRate, String highestPrice, String lowestPrice, String priorClosePrice,
                          String resEPS, String dividend, String PE, String EPSAdjustedSTC, String EPSbasicFPTS,
                          String EPSadjusted4QFPTS, String PE4QFPTS, String totalTradingValue, String wk52Low,
                          String wk52High, String dumua, String duban, String epsFpts, String CTMG,
                          String KLGD30Days, String TLSHNN, String preCt, String NNMUA_YTD, String NNMUA_YTD30, String PB) {
        Time = time;
        Qty = qty;
        Kldlhht = kldlhht;
        Date = date;
        CurrentPrice = currentPrice;
        Changes = changes;
        PerChange = perChange;
        MktCap = mktCap;
        BasicPrice = basicPrice;
        OpenPrice = openPrice;
        TotalTradingQtty = totalTradingQtty;
        Floor = floor;
        Ceiling = ceiling;
        DividentRate = dividentRate;
        HighestPrice = highestPrice;
        LowestPrice = lowestPrice;
        PriorClosePrice = priorClosePrice;
        ResEPS = resEPS;
        Dividend = dividend;
        this.PE = PE;
        this.EPSAdjustedSTC = EPSAdjustedSTC;
        this.EPSbasicFPTS = EPSbasicFPTS;
        this.EPSadjusted4QFPTS = EPSadjusted4QFPTS;
        this.PE4QFPTS = PE4QFPTS;
        TotalTradingValue = totalTradingValue;
        Wk52Low = wk52Low;
        Wk52High = wk52High;
        Dumua = dumua;
        Duban = duban;
        EpsFpts = epsFpts;
        this.CTMG = CTMG;
        this.KLGD30Days = KLGD30Days;
        this.TLSHNN = TLSHNN;
        PreCt = preCt;
        this.NNMUA_YTD = NNMUA_YTD;
        this.NNMUA_YTD30 = NNMUA_YTD30;
        this.PB = PB;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getKldlhht() {
        return Kldlhht;
    }

    public void setKldlhht(String kldlhht) {
        Kldlhht = kldlhht;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getChanges() {
        return Changes;
    }

    public void setChanges(String changes) {
        Changes = changes;
    }

    public String getPerChange() {
        return PerChange;
    }

    public void setPerChange(String perChange) {
        PerChange = perChange;
    }

    public String getMktCap() {
        return MktCap;
    }

    public void setMktCap(String mktCap) {
        MktCap = mktCap;
    }

    public String getBasicPrice() {
        return BasicPrice;
    }

    public void setBasicPrice(String basicPrice) {
        BasicPrice = basicPrice;
    }

    public String getOpenPrice() {
        return OpenPrice;
    }

    public void setOpenPrice(String openPrice) {
        OpenPrice = openPrice;
    }

    public String getTotalTradingQtty() {
        return TotalTradingQtty;
    }

    public void setTotalTradingQtty(String totalTradingQtty) {
        TotalTradingQtty = totalTradingQtty;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getCeiling() {
        return Ceiling;
    }

    public void setCeiling(String ceiling) {
        Ceiling = ceiling;
    }

    public String getDividentRate() {
        return DividentRate;
    }

    public void setDividentRate(String dividentRate) {
        DividentRate = dividentRate;
    }

    public String getHighestPrice() {
        return HighestPrice;
    }

    public void setHighestPrice(String highestPrice) {
        HighestPrice = highestPrice;
    }

    public String getLowestPrice() {
        return LowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        LowestPrice = lowestPrice;
    }

    public String getPriorClosePrice() {
        return PriorClosePrice;
    }

    public void setPriorClosePrice(String priorClosePrice) {
        PriorClosePrice = priorClosePrice;
    }

    public String getResEPS() {
        return ResEPS;
    }

    public void setResEPS(String resEPS) {
        ResEPS = resEPS;
    }

    public String getDividend() {
        return Dividend;
    }

    public void setDividend(String dividend) {
        Dividend = dividend;
    }

    public String getPE() {
        return PE;
    }

    public void setPE(String PE) {
        this.PE = PE;
    }

    public String getEPSAdjustedSTC() {
        return EPSAdjustedSTC;
    }

    public void setEPSAdjustedSTC(String EPSAdjustedSTC) {
        this.EPSAdjustedSTC = EPSAdjustedSTC;
    }

    public String getEPSbasicFPTS() {
        return EPSbasicFPTS;
    }

    public void setEPSbasicFPTS(String EPSbasicFPTS) {
        this.EPSbasicFPTS = EPSbasicFPTS;
    }

    public String getEPSadjusted4QFPTS() {
        return EPSadjusted4QFPTS;
    }

    public void setEPSadjusted4QFPTS(String EPSadjusted4QFPTS) {
        this.EPSadjusted4QFPTS = EPSadjusted4QFPTS;
    }

    public String getPE4QFPTS() {
        return PE4QFPTS;
    }

    public void setPE4QFPTS(String PE4QFPTS) {
        this.PE4QFPTS = PE4QFPTS;
    }

    public String getTotalTradingValue() {
        return TotalTradingValue;
    }

    public void setTotalTradingValue(String totalTradingValue) {
        TotalTradingValue = totalTradingValue;
    }

    public String getWk52Low() {
        return Wk52Low;
    }

    public void setWk52Low(String wk52Low) {
        Wk52Low = wk52Low;
    }

    public String getWk52High() {
        return Wk52High;
    }

    public void setWk52High(String wk52High) {
        Wk52High = wk52High;
    }

    public String getDumua() {
        return Dumua;
    }

    public void setDumua(String dumua) {
        Dumua = dumua;
    }

    public String getDuban() {
        return Duban;
    }

    public void setDuban(String duban) {
        Duban = duban;
    }

    public String getEpsFpts() {
        return EpsFpts;
    }

    public void setEpsFpts(String epsFpts) {
        EpsFpts = epsFpts;
    }

    public String getCTMG() {
        return CTMG;
    }

    public void setCTMG(String CTMG) {
        this.CTMG = CTMG;
    }

    public String getKLGD30Days() {
        return KLGD30Days;
    }

    public void setKLGD30Days(String KLGD30Days) {
        this.KLGD30Days = KLGD30Days;
    }

    public String getTLSHNN() {
        return TLSHNN;
    }

    public void setTLSHNN(String TLSHNN) {
        this.TLSHNN = TLSHNN;
    }

    public String getPreCt() {
        return PreCt;
    }

    public void setPreCt(String preCt) {
        PreCt = preCt;
    }

    public String getNNMUA_YTD() {
        return NNMUA_YTD;
    }

    public void setNNMUA_YTD(String NNMUA_YTD) {
        this.NNMUA_YTD = NNMUA_YTD;
    }

    public String getNNMUA_YTD30() {
        return NNMUA_YTD30;
    }

    public void setNNMUA_YTD30(String NNMUA_YTD30) {
        this.NNMUA_YTD30 = NNMUA_YTD30;
    }

    public String getPB() {
        return PB;
    }

    public void setPB(String PB) {
        this.PB = PB;
    }
}

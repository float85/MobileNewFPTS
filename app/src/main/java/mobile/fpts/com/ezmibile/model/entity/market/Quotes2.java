package mobile.fpts.com.ezmibile.model.entity.market;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quotes2 {
    // TODO: hoadt 6/20/2018 lấy data theo link bảng giá
    // TODO: https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=quotes2&symbol=fpt,fts
    @SerializedName("Code")
    @Expose
    private String Code;
    @SerializedName("UpDown")
    @Expose
    private String UpDown;
    @SerializedName("MatchPrice")
    @Expose
    private String MatchPrice;
    @SerializedName("ChangePrice")
    @Expose
    private String ChangePrice;
    @SerializedName("TotalQtty")
    @Expose
    private String TotalQtty;
    @SerializedName("CenterNo")
    @Expose
    private String CenterNo;
    @SerializedName("Ceiling")
    @Expose
    private String Ceiling;
    @SerializedName("Floor")
    @Expose
    private String Floor;
    @SerializedName("RefPrice")
    @Expose
    private String RefPrice;
    @SerializedName("BuyPrice3")
    @Expose
    private String BuyPrice3;
    @SerializedName("BuyQtty3")
    @Expose
    private String BuyQtty3;
    @SerializedName("BuyPrice2")
    @Expose
    private String BuyPrice2;
    @SerializedName("BuyQtty2")
    @Expose
    private String BuyQtty2;
    @SerializedName("BuyQtty1")
    @Expose
    private String BuyQtty1;
    @SerializedName("BuyPrice1")
    @Expose
    private String BuyPrice1;
    @SerializedName("MatchQtty")
    @Expose
    private String MatchQtty;
    @SerializedName("SellPrice1")
    @Expose
    private String SellPrice1;
    @SerializedName("SellQtty1")
    @Expose
    private String SellQtty1;
    @SerializedName("SellPrice2")
    @Expose
    private String SellPrice2;
    @SerializedName("SellQtty2")
    @Expose
    private String SellQtty2;
    @SerializedName("SellPrice3")
    @Expose
    private String SellPrice3;
    @SerializedName("SellQtty3")
    @Expose
    private String SellQtty3;
    @SerializedName("OpenPrice")
    @Expose
    private String OpenPrice;
    @SerializedName("HighestPrice")
    @Expose
    private String HighestPrice;
    @SerializedName("LowestPrice")
    @Expose
    private String LowestPrice;

    @SerializedName("ForeignBuyQtty")
    @Expose
    private String ForeignBuyQtty;

    @SerializedName("ForeignSellQtty")
    @Expose
    private String ForeignSellQtty;

    @Ignore
    public Quotes2() {
    }

    public Quotes2(String code, String upDown, String matchPrice, String changePrice, String totalQtty,
                   String centerNo, String ceiling, String floor, String refPrice, String buyPrice3,
                   String buyQtty3, String buyPrice2, String buyQtty2, String buyQtty1, String buyPrice1,
                   String matchQtty, String sellPrice1, String sellQtty1, String sellPrice2,
                   String sellQtty2, String sellPrice3, String sellQtty3, String openPrice,
                   String highestPrice, String lowestPrice, String foreignBuyQtty, String foreignSellQtty) {
        Code = code;
        UpDown = upDown;
        MatchPrice = matchPrice;
        ChangePrice = changePrice;
        TotalQtty = totalQtty;
        CenterNo = centerNo;
        Ceiling = ceiling;
        Floor = floor;
        RefPrice = refPrice;
        BuyPrice3 = buyPrice3;
        BuyQtty3 = buyQtty3;
        BuyPrice2 = buyPrice2;
        BuyQtty2 = buyQtty2;
        BuyQtty1 = buyQtty1;
        BuyPrice1 = buyPrice1;
        MatchQtty = matchQtty;
        SellPrice1 = sellPrice1;
        SellQtty1 = sellQtty1;
        SellPrice2 = sellPrice2;
        SellQtty2 = sellQtty2;
        SellPrice3 = sellPrice3;
        SellQtty3 = sellQtty3;
        OpenPrice = openPrice;
        HighestPrice = highestPrice;
        LowestPrice = lowestPrice;
        ForeignBuyQtty = foreignBuyQtty;
        ForeignSellQtty = foreignSellQtty;
    }

    public String getBuyPrice1() {
        return BuyPrice1;
    }

    public void setBuyPrice1(String buyPrice1) {
        BuyPrice1 = buyPrice1;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getUpDown() {
        return UpDown;
    }

    public void setUpDown(String upDown) {
        UpDown = upDown;
    }

    public String getMatchPrice() {
        return MatchPrice;
    }

    public void setMatchPrice(String matchPrice) {
        MatchPrice = matchPrice;
    }

    public String getChangePrice() {
        return ChangePrice;
    }

    public void setChangePrice(String changePrice) {
        ChangePrice = changePrice;
    }

    public String getTotalQtty() {
        return TotalQtty;
    }

    public void setTotalQtty(String totalQtty) {
        TotalQtty = totalQtty;
    }

    public String getCenterNo() {
        return CenterNo;
    }

    public void setCenterNo(String centerNo) {
        CenterNo = centerNo;
    }

    public String getCeiling() {
        return Ceiling;
    }

    public void setCeiling(String ceiling) {
        Ceiling = ceiling;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getRefPrice() {
        return RefPrice;
    }

    public void setRefPrice(String refPrice) {
        RefPrice = refPrice;
    }

    public String getBuyPrice3() {
        return BuyPrice3;
    }

    public void setBuyPrice3(String buyPrice3) {
        BuyPrice3 = buyPrice3;
    }

    public String getBuyQtty3() {
        return BuyQtty3;
    }

    public void setBuyQtty3(String buyQtty3) {
        BuyQtty3 = buyQtty3;
    }

    public String getBuyPrice2() {
        return BuyPrice2;
    }

    public void setBuyPrice2(String buyPrice2) {
        BuyPrice2 = buyPrice2;
    }

    public String getBuyQtty2() {
        return BuyQtty2;
    }

    public void setBuyQtty2(String buyQtty2) {
        BuyQtty2 = buyQtty2;
    }

    public String getBuyQtty1() {
        return BuyQtty1;
    }

    public void setBuyQtty1(String buyQtty1) {
        BuyQtty1 = buyQtty1;
    }

    public String getMatchQtty() {
        return MatchQtty;
    }

    public void setMatchQtty(String matchQtty) {
        MatchQtty = matchQtty;
    }

    public String getSellPrice1() {
        return SellPrice1;
    }

    public void setSellPrice1(String sellPrice1) {
        SellPrice1 = sellPrice1;
    }

    public String getSellQtty1() {
        return SellQtty1;
    }

    public void setSellQtty1(String sellQtty1) {
        SellQtty1 = sellQtty1;
    }

    public String getSellPrice2() {
        return SellPrice2;
    }

    public void setSellPrice2(String sellPrice2) {
        SellPrice2 = sellPrice2;
    }

    public String getSellQtty2() {
        return SellQtty2;
    }

    public void setSellQtty2(String sellQtty2) {
        SellQtty2 = sellQtty2;
    }

    public String getSellPrice3() {
        return SellPrice3;
    }

    public void setSellPrice3(String sellPrice3) {
        SellPrice3 = sellPrice3;
    }

    public String getSellQtty3() {
        return SellQtty3;
    }

    public void setSellQtty3(String sellQtty3) {
        SellQtty3 = sellQtty3;
    }

    public String getOpenPrice() {
        return OpenPrice;
    }

    public void setOpenPrice(String openPrice) {
        OpenPrice = openPrice;
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

    public String getForeignBuyQtty() {
        return ForeignBuyQtty;
    }

    public void setForeignBuyQtty(String foreignBuyQtty) {
        ForeignBuyQtty = foreignBuyQtty;
    }

    public String getForeignSellQtty() {
        return ForeignSellQtty;
    }

    public void setForeignSellQtty(String foreignSellQtty) {
        ForeignSellQtty = foreignSellQtty;
    }
}

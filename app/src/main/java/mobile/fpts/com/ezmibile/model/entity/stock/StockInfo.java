package mobile.fpts.com.ezmibile.model.entity.stock;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class StockInfo {
    @SerializedName("stock_code")
    @Expose
    private String StockCode;

    @SerializedName("name_vn")
    @Expose
    private String NameVN;

    @SerializedName("name_en")
    @Expose
    private String NameEN;

    @SerializedName("post_to")
    @Expose
    private String PostTO;

    @SerializedName("name_short")
    @Expose
    private String NameShort;

    @SerializedName("C")
    @Expose
    private String C;

    public StockInfo() {
    }

    public StockInfo(String stockCode, String nameVN, String nameEN, String postTO, String nameShort, String c) {
        StockCode = stockCode;
        NameVN = nameVN;
        NameEN = nameEN;
        PostTO = postTO;
        NameShort = nameShort;
        C = c;
    }

    public String getStockCode() {
        return StockCode;
    }

    public void setStockCode(String stockCode) {
        StockCode = stockCode;
    }

    public String getNameVN() {
        return NameVN;
    }

    public void setNameVN(String nameVN) {
        NameVN = nameVN;
    }

    public String getNameEN() {
        return NameEN;
    }

    public void setNameEN(String nameEN) {
        NameEN = nameEN;
    }

    public String getPostTO() {
        return PostTO;
    }

    public void setPostTO(String postTO) {
        PostTO = postTO;
    }

    public String getNameShort() {
        return NameShort;
    }

    public void setNameShort(String nameShort) {
        NameShort = nameShort;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }
}


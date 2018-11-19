package mobile.fpts.com.ezmibile.model.entity.detail_home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailHomeItem_VNI {
    @SerializedName("MARKET_INDEX")
    @Expose
    private String mARKETINDEX;
    @SerializedName("strArrow0")
    @Expose
    private String strArrow0;
    @SerializedName("CHG_INDEX")
    @Expose
    private String cHGINDEX;
    @SerializedName("PCT_INDEX")
    @Expose
    private String pCTINDEX;
    @SerializedName("TOTAL_QTTY")
    @Expose
    private String tOTALQTTY;
    @SerializedName("TOTAL_VALUE")
    @Expose
    private String tOTALVALUE;
    @SerializedName("TOTAL_TRADE")
    @Expose
    private String tOTALTRADE;

    public DetailHomeItem_VNI(String mARKETINDEX, String strArrow0, String cHGINDEX, String pCTINDEX, String tOTALQTTY, String tOTALVALUE, String tOTALTRADE) {
        this.mARKETINDEX = mARKETINDEX;
        this.strArrow0 = strArrow0;
        this.cHGINDEX = cHGINDEX;
        this.pCTINDEX = pCTINDEX;
        this.tOTALQTTY = tOTALQTTY;
        this.tOTALVALUE = tOTALVALUE;
        this.tOTALTRADE = tOTALTRADE;
    }

    public String getMARKETINDEX() {
        return mARKETINDEX;
    }

    public void setMARKETINDEX(String mARKETINDEX) {
        this.mARKETINDEX = mARKETINDEX;
    }

    public String getStrArrow0() {
        return strArrow0;
    }

    public void setStrArrow0(String strArrow0) {
        this.strArrow0 = strArrow0;
    }

    public String getCHGINDEX() {
        return cHGINDEX;
    }

    public void setCHGINDEX(String cHGINDEX) {
        this.cHGINDEX = cHGINDEX;
    }

    public String getPCTINDEX() {
        return pCTINDEX;
    }

    public void setPCTINDEX(String pCTINDEX) {
        this.pCTINDEX = pCTINDEX;
    }

    public String getTOTALQTTY() {
        return tOTALQTTY;
    }

    public void setTOTALQTTY(String tOTALQTTY) {
        this.tOTALQTTY = tOTALQTTY;
    }

    public String getTOTALVALUE() {
        return tOTALVALUE;
    }

    public void setTOTALVALUE(String tOTALVALUE) {
        this.tOTALVALUE = tOTALVALUE;
    }

    public String getTOTALTRADE() {
        return tOTALTRADE;
    }

    public void setTOTALTRADE(String tOTALTRADE) {
        this.tOTALTRADE = tOTALTRADE;
    }
}

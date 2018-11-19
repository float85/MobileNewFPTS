package mobile.fpts.com.ezmibile.view.watchlist.detail.statistics;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatisticData {
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("CODE")
    @Expose
    private String cODE;
    @SerializedName("CENTER")
    @Expose
    private String cENTER;
    @SerializedName("TRADING_DATE")
    @Expose
    private String tRADINGDATE;
    @SerializedName("BASIC_PRICE")
    @Expose
    private String bASICPRICE;
    @SerializedName("CEILING_PRICE")
    @Expose
    private String cEILINGPRICE;
    @SerializedName("FLOOR_PRICE")
    @Expose
    private String fLOORPRICE;
    @SerializedName("OPEN_PRICE")
    @Expose
    private String oPENPRICE;
    @SerializedName("CLOSE_PRICE")
    @Expose
    private String cLOSEPRICE;
    @SerializedName("HIGHEST_PRICE")
    @Expose
    private String hIGHESTPRICE;
    @SerializedName("LOWEST_PRICE")
    @Expose
    private String lOWESTPRICE;
    @SerializedName("AVERAGE_PRICE")
    @Expose
    private String aVERAGEPRICE;
    @SerializedName("TOTAL_OFFER_QTTY")
    @Expose
    private String tOTALOFFERQTTY;
    @SerializedName("TOTAL_BID_QTTY")
    @Expose
    private String tOTALBIDQTTY;
    @SerializedName("OFFER_COUNT")
    @Expose
    private String oFFERCOUNT;
    @SerializedName("BID_COUNT")
    @Expose
    private String bIDCOUNT;
    @SerializedName("NM_TOTAL_TRADED_QTTY")
    @Expose
    private String nMTOTALTRADEDQTTY;
    @SerializedName("NM_TOTAL_TRADED_VALUE")
    @Expose
    private String nMTOTALTRADEDVALUE;
    @SerializedName("PT_TOTAL_TRADED_QTTY")
    @Expose
    private String pTTOTALTRADEDQTTY;
    @SerializedName("PT_TOTAL_TRADED_VALUE")
    @Expose
    private String pTTOTALTRADEDVALUE;
    @SerializedName("TOTAL_TRADING_QTTY")
    @Expose
    private String tOTALTRADINGQTTY;
    @SerializedName("TOTAL_TRADING_VALUE")
    @Expose
    private String tOTALTRADINGVALUE;
    @SerializedName("CHANGE_VALUE")
    @Expose
    private String cHANGEVALUE;
    @SerializedName("CHANGE_PERCENT")
    @Expose
    private String cHANGEPERCENT;

    //Khối lượng được mua
    @SerializedName("ROOM_TOTAL")
    @Expose
    private String rOOMTOTAL;

    //Khối lượng hiện tại
    @SerializedName("ROOM_CURRENT")
    @Expose
    private String rOOMCURRENT;

    //Khối lượng còn lại
    @SerializedName("ROOM_REMAIN")
    @Expose
    private String rOOMREMAIN;

    //Tỷ lệ sở hữu
    @SerializedName("ROOM_RATIO")
    @Expose
    private String rOOMRATIO;
    @SerializedName("FOREIGN_BUY_QTTY")
    @Expose
    private String fOREIGNBUYQTTY;
    @SerializedName("FOREIGN_BUY_VALUE")
    @Expose
    private String fOREIGNBUYVALUE;
    @SerializedName("FOREIGN_SELL_QTTY")
    @Expose
    private String fOREIGNSELLQTTY;
    @SerializedName("FOREIGN_SELL_VALUE")
    @Expose
    private String fOREIGNSELLVALUE;
    @SerializedName("UpdateDate")
    @Expose
    private String updateDate;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("color_low")
    @Expose
    private String colorLow;
    @SerializedName("color_high")
    @Expose
    private String colorHigh;
    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("vol")
    @Expose
    private String vol;
    @SerializedName("NEW_FOREIGN_SELL_VALUE")
    @Expose
    private String nEWFOREIGNSELLVALUE;
    @SerializedName("NEW_FOREIGN_SELL_QTTY")
    @Expose
    private String nEWFOREIGNSELLQTTY;
    @SerializedName("NEW_CHANGE_PERCENT")
    @Expose
    private String nEWCHANGEPERCENT;


    @Ignore
    public StatisticData() {
    }

    public StatisticData(String iD, String cODE, String cENTER, String tRADINGDATE, String bASICPRICE,
                         String cEILINGPRICE, String fLOORPRICE, String oPENPRICE, String cLOSEPRICE,
                         String hIGHESTPRICE, String lOWESTPRICE, String aVERAGEPRICE, String tOTALOFFERQTTY,
                         String tOTALBIDQTTY, String oFFERCOUNT, String bIDCOUNT, String nMTOTALTRADEDQTTY,
                         String nMTOTALTRADEDVALUE, String pTTOTALTRADEDQTTY, String pTTOTALTRADEDVALUE,
                         String tOTALTRADINGQTTY, String tOTALTRADINGVALUE, String cHANGEVALUE,
                         String cHANGEPERCENT, String rOOMTOTAL, String rOOMCURRENT, String rOOMREMAIN,
                         String rOOMRATIO, String fOREIGNBUYQTTY, String fOREIGNBUYVALUE, String fOREIGNSELLQTTY,
                         String fOREIGNSELLVALUE, String updateDate, String color, String colorLow,
                         String colorHigh, String last, String label, String vol, String nEWFOREIGNSELLVALUE,
                         String nEWFOREIGNSELLQTTY, String nEWCHANGEPERCENT) {
        this.iD = iD;
        this.cODE = cODE;
        this.cENTER = cENTER;
        this.tRADINGDATE = tRADINGDATE;
        this.bASICPRICE = bASICPRICE;
        this.cEILINGPRICE = cEILINGPRICE;
        this.fLOORPRICE = fLOORPRICE;
        this.oPENPRICE = oPENPRICE;
        this.cLOSEPRICE = cLOSEPRICE;
        this.hIGHESTPRICE = hIGHESTPRICE;
        this.lOWESTPRICE = lOWESTPRICE;
        this.aVERAGEPRICE = aVERAGEPRICE;
        this.tOTALOFFERQTTY = tOTALOFFERQTTY;
        this.tOTALBIDQTTY = tOTALBIDQTTY;
        this.oFFERCOUNT = oFFERCOUNT;
        this.bIDCOUNT = bIDCOUNT;
        this.nMTOTALTRADEDQTTY = nMTOTALTRADEDQTTY;
        this.nMTOTALTRADEDVALUE = nMTOTALTRADEDVALUE;
        this.pTTOTALTRADEDQTTY = pTTOTALTRADEDQTTY;
        this.pTTOTALTRADEDVALUE = pTTOTALTRADEDVALUE;
        this.tOTALTRADINGQTTY = tOTALTRADINGQTTY;
        this.tOTALTRADINGVALUE = tOTALTRADINGVALUE;
        this.cHANGEVALUE = cHANGEVALUE;
        this.cHANGEPERCENT = cHANGEPERCENT;
        this.rOOMTOTAL = rOOMTOTAL;
        this.rOOMCURRENT = rOOMCURRENT;
        this.rOOMREMAIN = rOOMREMAIN;
        this.rOOMRATIO = rOOMRATIO;
        this.fOREIGNBUYQTTY = fOREIGNBUYQTTY;
        this.fOREIGNBUYVALUE = fOREIGNBUYVALUE;
        this.fOREIGNSELLQTTY = fOREIGNSELLQTTY;
        this.fOREIGNSELLVALUE = fOREIGNSELLVALUE;
        this.updateDate = updateDate;
        this.color = color;
        this.colorLow = colorLow;
        this.colorHigh = colorHigh;
        this.last = last;
        this.label = label;
        this.vol = vol;
        this.nEWFOREIGNSELLVALUE = nEWFOREIGNSELLVALUE;
        this.nEWFOREIGNSELLQTTY = nEWFOREIGNSELLQTTY;
        this.nEWCHANGEPERCENT = nEWCHANGEPERCENT;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getcODE() {
        return cODE;
    }

    public void setcODE(String cODE) {
        this.cODE = cODE;
    }

    public String getcENTER() {
        return cENTER;
    }

    public void setcENTER(String cENTER) {
        this.cENTER = cENTER;
    }

    public String gettRADINGDATE() {
        return tRADINGDATE;
    }

    public void settRADINGDATE(String tRADINGDATE) {
        this.tRADINGDATE = tRADINGDATE;
    }

    public String getbASICPRICE() {
        return bASICPRICE;
    }

    public void setbASICPRICE(String bASICPRICE) {
        this.bASICPRICE = bASICPRICE;
    }

    public String getcEILINGPRICE() {
        return cEILINGPRICE;
    }

    public void setcEILINGPRICE(String cEILINGPRICE) {
        this.cEILINGPRICE = cEILINGPRICE;
    }

    public String getfLOORPRICE() {
        return fLOORPRICE;
    }

    public void setfLOORPRICE(String fLOORPRICE) {
        this.fLOORPRICE = fLOORPRICE;
    }

    public String getoPENPRICE() {
        return oPENPRICE;
    }

    public void setoPENPRICE(String oPENPRICE) {
        this.oPENPRICE = oPENPRICE;
    }

    public String getcLOSEPRICE() {
        return cLOSEPRICE;
    }

    public void setcLOSEPRICE(String cLOSEPRICE) {
        this.cLOSEPRICE = cLOSEPRICE;
    }

    public String gethIGHESTPRICE() {
        return hIGHESTPRICE;
    }

    public void sethIGHESTPRICE(String hIGHESTPRICE) {
        this.hIGHESTPRICE = hIGHESTPRICE;
    }

    public String getlOWESTPRICE() {
        return lOWESTPRICE;
    }

    public void setlOWESTPRICE(String lOWESTPRICE) {
        this.lOWESTPRICE = lOWESTPRICE;
    }

    public String getaVERAGEPRICE() {
        return aVERAGEPRICE;
    }

    public void setaVERAGEPRICE(String aVERAGEPRICE) {
        this.aVERAGEPRICE = aVERAGEPRICE;
    }

    public String gettOTALOFFERQTTY() {
        return tOTALOFFERQTTY;
    }

    public void settOTALOFFERQTTY(String tOTALOFFERQTTY) {
        this.tOTALOFFERQTTY = tOTALOFFERQTTY;
    }

    public String gettOTALBIDQTTY() {
        return tOTALBIDQTTY;
    }

    public void settOTALBIDQTTY(String tOTALBIDQTTY) {
        this.tOTALBIDQTTY = tOTALBIDQTTY;
    }

    public String getoFFERCOUNT() {
        return oFFERCOUNT;
    }

    public void setoFFERCOUNT(String oFFERCOUNT) {
        this.oFFERCOUNT = oFFERCOUNT;
    }

    public String getbIDCOUNT() {
        return bIDCOUNT;
    }

    public void setbIDCOUNT(String bIDCOUNT) {
        this.bIDCOUNT = bIDCOUNT;
    }

    public String getnMTOTALTRADEDQTTY() {
        return nMTOTALTRADEDQTTY;
    }

    public void setnMTOTALTRADEDQTTY(String nMTOTALTRADEDQTTY) {
        this.nMTOTALTRADEDQTTY = nMTOTALTRADEDQTTY;
    }

    public String getnMTOTALTRADEDVALUE() {
        return nMTOTALTRADEDVALUE;
    }

    public void setnMTOTALTRADEDVALUE(String nMTOTALTRADEDVALUE) {
        this.nMTOTALTRADEDVALUE = nMTOTALTRADEDVALUE;
    }

    public String getpTTOTALTRADEDQTTY() {
        return pTTOTALTRADEDQTTY;
    }

    public void setpTTOTALTRADEDQTTY(String pTTOTALTRADEDQTTY) {
        this.pTTOTALTRADEDQTTY = pTTOTALTRADEDQTTY;
    }

    public String getpTTOTALTRADEDVALUE() {
        return pTTOTALTRADEDVALUE;
    }

    public void setpTTOTALTRADEDVALUE(String pTTOTALTRADEDVALUE) {
        this.pTTOTALTRADEDVALUE = pTTOTALTRADEDVALUE;
    }

    public String gettOTALTRADINGQTTY() {
        return tOTALTRADINGQTTY;
    }

    public void settOTALTRADINGQTTY(String tOTALTRADINGQTTY) {
        this.tOTALTRADINGQTTY = tOTALTRADINGQTTY;
    }

    public String gettOTALTRADINGVALUE() {
        return tOTALTRADINGVALUE;
    }

    public void settOTALTRADINGVALUE(String tOTALTRADINGVALUE) {
        this.tOTALTRADINGVALUE = tOTALTRADINGVALUE;
    }

    public String getcHANGEVALUE() {
        return cHANGEVALUE;
    }

    public void setcHANGEVALUE(String cHANGEVALUE) {
        this.cHANGEVALUE = cHANGEVALUE;
    }

    public String getcHANGEPERCENT() {
        return cHANGEPERCENT;
    }

    public void setcHANGEPERCENT(String cHANGEPERCENT) {
        this.cHANGEPERCENT = cHANGEPERCENT;
    }

    public String getrOOMTOTAL() {
        return rOOMTOTAL;
    }

    public void setrOOMTOTAL(String rOOMTOTAL) {
        this.rOOMTOTAL = rOOMTOTAL;
    }

    public String getrOOMCURRENT() {
        return rOOMCURRENT;
    }

    public void setrOOMCURRENT(String rOOMCURRENT) {
        this.rOOMCURRENT = rOOMCURRENT;
    }

    public String getrOOMREMAIN() {
        return rOOMREMAIN;
    }

    public void setrOOMREMAIN(String rOOMREMAIN) {
        this.rOOMREMAIN = rOOMREMAIN;
    }

    public String getrOOMRATIO() {
        return rOOMRATIO;
    }

    public void setrOOMRATIO(String rOOMRATIO) {
        this.rOOMRATIO = rOOMRATIO;
    }

    public String getfOREIGNBUYQTTY() {
        return fOREIGNBUYQTTY;
    }

    public void setfOREIGNBUYQTTY(String fOREIGNBUYQTTY) {
        this.fOREIGNBUYQTTY = fOREIGNBUYQTTY;
    }

    public String getfOREIGNBUYVALUE() {
        return fOREIGNBUYVALUE;
    }

    public void setfOREIGNBUYVALUE(String fOREIGNBUYVALUE) {
        this.fOREIGNBUYVALUE = fOREIGNBUYVALUE;
    }

    public String getfOREIGNSELLQTTY() {
        return fOREIGNSELLQTTY;
    }

    public void setfOREIGNSELLQTTY(String fOREIGNSELLQTTY) {
        this.fOREIGNSELLQTTY = fOREIGNSELLQTTY;
    }

    public String getfOREIGNSELLVALUE() {
        return fOREIGNSELLVALUE;
    }

    public void setfOREIGNSELLVALUE(String fOREIGNSELLVALUE) {
        this.fOREIGNSELLVALUE = fOREIGNSELLVALUE;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorLow() {
        return colorLow;
    }

    public void setColorLow(String colorLow) {
        this.colorLow = colorLow;
    }

    public String getColorHigh() {
        return colorHigh;
    }

    public void setColorHigh(String colorHigh) {
        this.colorHigh = colorHigh;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getnEWFOREIGNSELLVALUE() {
        return nEWFOREIGNSELLVALUE;
    }

    public void setnEWFOREIGNSELLVALUE(String nEWFOREIGNSELLVALUE) {
        this.nEWFOREIGNSELLVALUE = nEWFOREIGNSELLVALUE;
    }

    public String getnEWFOREIGNSELLQTTY() {
        return nEWFOREIGNSELLQTTY;
    }

    public void setnEWFOREIGNSELLQTTY(String nEWFOREIGNSELLQTTY) {
        this.nEWFOREIGNSELLQTTY = nEWFOREIGNSELLQTTY;
    }

    public String getnEWCHANGEPERCENT() {
        return nEWCHANGEPERCENT;
    }

    public void setnEWCHANGEPERCENT(String nEWCHANGEPERCENT) {
        this.nEWCHANGEPERCENT = nEWCHANGEPERCENT;
    }
}

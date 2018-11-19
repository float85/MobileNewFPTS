package mobile.fpts.com.ezmibile.model.entity.market;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// TODO:HoaDT 6/19/2018 class for link: https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=vn_indices
// TODO:HoaDT 6/22/2018 hợp link lấy data từ link https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=others_index&c=0&language=1
public class VnIndices {
    @SerializedName("IndexValue")
    @Expose
    private String IndexValue;
    @SerializedName("UpDown")
    @Expose
    private String UpDown;
    @SerializedName("TotalValue")
    @Expose
    private String TotalValue;
    @SerializedName("INDEX")
    @Expose
    private String INDEX;
    @SerializedName("TotalQtty")
    @Expose
    private String TotalQtty;
    @SerializedName("Change")
    @Expose
    private String Change;

    @SerializedName("ChangePercent")
    @Expose
    private String ChangePercent;
    @SerializedName("ChangePercent ")
    @Expose
    private String ChangePercent_;
    // TODO:HoaDT 6/22/2018 type = 1 VnIndice, type = 2 OtherIndex
    private boolean isChecked;

    private int isTypeVnIndices;

    //    @SerializedName("CenterName")
//    @Expose
//    private String CenterName;
    //    @SerializedName("IndexChange")
//    @Expose
//    private String IndexChange;
//    @SerializedName("IndexChangePercent")
//    @Expose
//    private String IndexChangePercent;
//    @SerializedName("TotalQuantity")
//    @Expose
//    private String TotalQuantity;
    @Ignore
    public VnIndices() {
    }

    public VnIndices(String indexValue, String upDown, String totalValue, String INDEX, String totalQtty,
                     String change, String changePercent, String changePercent_, boolean isChecked,
                     int isTypeVnIndices) {
        IndexValue = indexValue;
        UpDown = upDown;
        TotalValue = totalValue;
        this.INDEX = INDEX;
        TotalQtty = totalQtty;
        Change = change;
        ChangePercent = changePercent;
        ChangePercent_ = changePercent_;
        this.isChecked = isChecked;
        this.isTypeVnIndices = isTypeVnIndices;
    }

    public String getIndexValue() {
        return IndexValue;
    }

    public void setIndexValue(String indexValue) {
        IndexValue = indexValue;
    }

    public String getUpDown() {
        return UpDown;
    }

    public void setUpDown(String upDown) {
        UpDown = upDown;
    }

    public String getTotalValue() {
        return TotalValue;
    }

    public void setTotalValue(String totalValue) {
        TotalValue = totalValue;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getINDEX() {
        return INDEX;
    }

    public void setINDEX(String INDEX) {
        this.INDEX = INDEX;
    }

    public String getTotalQtty() {
        return TotalQtty;
    }

    public void setTotalQtty(String totalQtty) {
        TotalQtty = totalQtty;
    }

    public String getChange() {
        return Change;
    }

    public void setChange(String change) {
        Change = change;
    }

    public String getChangePercent() {
        return ChangePercent;
    }

    public void setChangePercent(String changePercent) {
        ChangePercent = changePercent;
    }

    public int getIsTypeVnIndices() {
        return isTypeVnIndices;
    }

    public void setIsTypeVnIndices(int isTypeVnIndices) {
        this.isTypeVnIndices = isTypeVnIndices;
    }

    public String getChangePercent_() {
        return ChangePercent_;
    }

    public void setChangePercent_(String changePercent_) {
        ChangePercent_ = changePercent_;
    }
}

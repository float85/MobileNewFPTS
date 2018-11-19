package mobile.fpts.com.ezmibile.model.entity.detail_home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailHomeHNX30 {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("statusSub")
    @Expose
    private String statusSub;
    @SerializedName("Index")
    @Expose
    private String index;
    @SerializedName("strArrow")
    @Expose
    private String strArrow;
    @SerializedName("Index_change")
    @Expose
    private String indexChange;
    @SerializedName("Index_change_per")
    @Expose
    private String indexChangePer;
    @SerializedName("Tong_KL")
    @Expose
    private String tongKL;
    @SerializedName("Tong_GT")
    @Expose
    private String tongGT;
    @SerializedName("Soma_tangtran")
    @Expose
    private String somaTangtran;
    @SerializedName("Soma_tang")
    @Expose
    private String somaTang;
    @SerializedName("Soma_khongdoi")
    @Expose
    private String somaKhongdoi;
    @SerializedName("Soma_giam")
    @Expose
    private String somaGiam;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatusSub() {
        return statusSub;
    }

    public void setStatusSub(String statusSub) {
        this.statusSub = statusSub;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getStrArrow() {
        return strArrow;
    }

    public void setStrArrow(String strArrow) {
        this.strArrow = strArrow;
    }

    public String getIndexChange() {
        return indexChange;
    }

    public void setIndexChange(String indexChange) {
        this.indexChange = indexChange;
    }

    public String getIndexChangePer() {
        return indexChangePer;
    }

    public void setIndexChangePer(String indexChangePer) {
        this.indexChangePer = indexChangePer;
    }

    public String getTongKL() {
        return tongKL;
    }

    public void setTongKL(String tongKL) {
        this.tongKL = tongKL;
    }

    public String getTongGT() {
        return tongGT;
    }

    public void setTongGT(String tongGT) {
        this.tongGT = tongGT;
    }

    public String getSomaTangtran() {
        return somaTangtran;
    }

    public void setSomaTangtran(String somaTangtran) {
        this.somaTangtran = somaTangtran;
    }

    public String getSomaTang() {
        return somaTang;
    }

    public void setSomaTang(String somaTang) {
        this.somaTang = somaTang;
    }

    public String getSomaKhongdoi() {
        return somaKhongdoi;
    }

    public void setSomaKhongdoi(String somaKhongdoi) {
        this.somaKhongdoi = somaKhongdoi;
    }

    public String getSomaGiam() {
        return somaGiam;
    }

    public void setSomaGiam(String somaGiam) {
        this.somaGiam = somaGiam;
    }
}

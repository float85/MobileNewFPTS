package mobile.fpts.com.ezmibile.model.entity.detail_home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailHomeUpcom {
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
    @SerializedName("Tong_GiaoDich")
    @Expose
    private String tongGiaoDich;
    @SerializedName("SoMa_Tang")
    @Expose
    private String soMaTang;
    @SerializedName("SoMa_Khongdoi")
    @Expose
    private String soMaKhongdoi;
    @SerializedName("SoMa_Giam")
    @Expose
    private String soMaGiam;
    @SerializedName("KLKhop_GDTTCP")
    @Expose
    private String kLKhopGDTTCP;
    @SerializedName("GTKhop_GDTTCP")
    @Expose
    private String gTKhopGDTTCP;
    @SerializedName("SoGD_GDTTCP")
    @Expose
    private String soGDGDTTCP;

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

    public String getTongGiaoDich() {
        return tongGiaoDich;
    }

    public void setTongGiaoDich(String tongGiaoDich) {
        this.tongGiaoDich = tongGiaoDich;
    }

    public String getSoMaTang() {
        return soMaTang;
    }

    public void setSoMaTang(String soMaTang) {
        this.soMaTang = soMaTang;
    }

    public String getSoMaKhongdoi() {
        return soMaKhongdoi;
    }

    public void setSoMaKhongdoi(String soMaKhongdoi) {
        this.soMaKhongdoi = soMaKhongdoi;
    }

    public String getSoMaGiam() {
        return soMaGiam;
    }

    public void setSoMaGiam(String soMaGiam) {
        this.soMaGiam = soMaGiam;
    }

    public String getKLKhopGDTTCP() {
        return kLKhopGDTTCP;
    }

    public void setKLKhopGDTTCP(String kLKhopGDTTCP) {
        this.kLKhopGDTTCP = kLKhopGDTTCP;
    }

    public String getGTKhopGDTTCP() {
        return gTKhopGDTTCP;
    }

    public void setGTKhopGDTTCP(String gTKhopGDTTCP) {
        this.gTKhopGDTTCP = gTKhopGDTTCP;
    }

    public String getSoGDGDTTCP() {
        return soGDGDTTCP;
    }

    public void setSoGDGDTTCP(String soGDGDTTCP) {
        this.soGDGDTTCP = soGDGDTTCP;
    }
}

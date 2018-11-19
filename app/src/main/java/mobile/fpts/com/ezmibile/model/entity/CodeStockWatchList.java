package mobile.fpts.com.ezmibile.model.entity;

/**
 * Created by dinht on 3/20/2018.
 */

public class CodeStockWatchList {
    private String code;
    private String UpDown;
    private String MatchPrice;
    private String ChangePrice;
    private String TotalQuantity;
    private String CenterNo;
    private String Ceiling;
    private String Floor;
    private String RefPrice;

    public CodeStockWatchList() {
    }

    public CodeStockWatchList(String code, String upDown, String matchPrice, String changePrice,
                              String totalQuantity, String centerNo, String ceiling, String floor, String refPrice) {
        this.code = code;
        UpDown = upDown;
        MatchPrice = matchPrice;
        ChangePrice = changePrice;
        TotalQuantity = totalQuantity;
        CenterNo = centerNo;
        Ceiling = ceiling;
        Floor = floor;
        RefPrice = refPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
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

    public String getRefPrice() {
        return RefPrice;
    }

    public void setRefPrice(String refPrice) {
        RefPrice = refPrice;
    }

    public String getChangePrice() {
        return ChangePrice;
    }

    public void setChangePrice(String changePrice) {
        ChangePrice = changePrice;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

}

package mobile.fpts.com.ezmibile.view.watchlist.model;

import java.io.Serializable;

public class StockQuote implements Serializable {
    private String QtyBD;

    private static final long serialVersionUID = 3L;
    private String symbol;
    private String pricecolor;
    private Double change;
    private Double changePct;
    private int volume;
    private Double ceiling;
    private Double reference;
    private Double floor;
    private Double highest;
    private Double lowest;
    private Double buy3;
    private int buy3Volume;
    private Double buy2;
    private int buy2Volume;
    private Double buy1;
    private int buy1Volume;
    private Double sell1;
    private int sell1Volume;
    private Double sell2;
    private int sell2Volume;
    private Double sell3;
    private int sell3Volume;
    private int foreignBuy;
    private int foreignSell;
    private Double centerNo;
    private Double company;
    private Double match;
    private int totalQtty;
    private Double average;

    private Double totalMatchingVol;
    private Double totalMatchingVal;
    private Double totalPutthroughVol;
    private Double totalPutthroughVal;
    private Double roomTotal;
    private Double roomCurrent;
    private Double roomRemain;
    private Double roomRatios;
    private Double openPrice;
    private Double highestPrice;
    private int matchprice;
    private Double changPrice;

    public String getQtyBD() {
        return QtyBD;
    }

    public void setQtyBD(String qtyBD) {
        QtyBD = qtyBD;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPricecolor() {
        return pricecolor;
    }

    public void setPricecolor(String pricecolor) {
        this.pricecolor = pricecolor;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getChangePct() {
        return changePct;
    }

    public void setChangePct(Double changePct) {
        this.changePct = changePct;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Double getCeiling() {
        return ceiling;
    }

    public void setCeiling(Double ceiling) {
        this.ceiling = ceiling;
    }

    public Double getReference() {
        return reference;
    }

    public void setReference(Double reference) {
        this.reference = reference;
    }

    public Double getFloor() {
        return floor;
    }

    public void setFloor(Double floor) {
        this.floor = floor;
    }

    public Double getHighest() {
        return highest;
    }

    public void setHighest(Double highest) {
        this.highest = highest;
    }

    public Double getLowest() {
        return lowest;
    }

    public void setLowest(Double lowest) {
        this.lowest = lowest;
    }

    public Double getBuy3() {
        return buy3;
    }

    public void setBuy3(Double buy3) {
        this.buy3 = buy3;
    }

    public int getBuy3Volume() {
        return buy3Volume;
    }

    public void setBuy3Volume(int buy3Volume) {
        this.buy3Volume = buy3Volume;
    }

    public Double getBuy2() {
        return buy2;
    }

    public void setBuy2(Double buy2) {
        this.buy2 = buy2;
    }

    public int getBuy2Volume() {
        return buy2Volume;
    }

    public void setBuy2Volume(int buy2Volume) {
        this.buy2Volume = buy2Volume;
    }

    public Double getBuy1() {
        return buy1;
    }

    public void setBuy1(Double buy1) {
        this.buy1 = buy1;
    }

    public int getBuy1Volume() {
        return buy1Volume;
    }

    public void setBuy1Volume(int buy1Volume) {
        this.buy1Volume = buy1Volume;
    }

    public Double getSell1() {
        return sell1;
    }

    public void setSell1(Double sell1) {
        this.sell1 = sell1;
    }

    public int getSell1Volume() {
        return sell1Volume;
    }

    public void setSell1Volume(int sell1Volume) {
        this.sell1Volume = sell1Volume;
    }

    public Double getSell2() {
        return sell2;
    }

    public void setSell2(Double sell2) {
        this.sell2 = sell2;
    }

    public int getSell2Volume() {
        return sell2Volume;
    }

    public void setSell2Volume(int sell2Volume) {
        this.sell2Volume = sell2Volume;
    }

    public Double getSell3() {
        return sell3;
    }

    public void setSell3(Double sell3) {
        this.sell3 = sell3;
    }

    public int getSell3Volume() {
        return sell3Volume;
    }

    public void setSell3Volume(int sell3Volume) {
        this.sell3Volume = sell3Volume;
    }

    public int getForeignBuy() {
        return foreignBuy;
    }

    public void setForeignBuy(int foreignBuy) {
        this.foreignBuy = foreignBuy;
    }

    public int getForeignSell() {
        return foreignSell;
    }

    public void setForeignSell(int foreignSell) {
        this.foreignSell = foreignSell;
    }

    public Double getCenterNo() {
        return centerNo;
    }

    public void setCenterNo(Double centerNo) {
        this.centerNo = centerNo;
    }

    public Double getCompany() {
        return company;
    }

    public void setCompany(Double company) {
        this.company = company;
    }

    public Double getMatch() {
        return match;
    }

    public void setMatch(Double match) {
        this.match = match;
    }

    public int getTotalQtty() {
        return totalQtty;
    }

    public void setTotalQtty(int totalQtty) {
        this.totalQtty = totalQtty;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getTotalMatchingVol() {
        return totalMatchingVol;
    }

    public void setTotalMatchingVol(Double totalMatchingVol) {
        this.totalMatchingVol = totalMatchingVol;
    }

    public Double getTotalMatchingVal() {
        return totalMatchingVal;
    }

    public void setTotalMatchingVal(Double totalMatchingVal) {
        this.totalMatchingVal = totalMatchingVal;
    }

    public Double getTotalPutthroughVol() {
        return totalPutthroughVol;
    }

    public void setTotalPutthroughVol(Double totalPutthroughVol) {
        this.totalPutthroughVol = totalPutthroughVol;
    }

    public Double getTotalPutthroughVal() {
        return totalPutthroughVal;
    }

    public void setTotalPutthroughVal(Double totalPutthroughVal) {
        this.totalPutthroughVal = totalPutthroughVal;
    }

    public Double getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(Double roomTotal) {
        this.roomTotal = roomTotal;
    }

    public Double getRoomCurrent() {
        return roomCurrent;
    }

    public void setRoomCurrent(Double roomCurrent) {
        this.roomCurrent = roomCurrent;
    }

    public Double getRoomRemain() {
        return roomRemain;
    }

    public void setRoomRemain(Double roomRemain) {
        this.roomRemain = roomRemain;
    }

    public Double getRoomRatios() {
        return roomRatios;
    }

    public void setRoomRatios(Double roomRatios) {
        this.roomRatios = roomRatios;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public int getMatchprice() {
        return matchprice;
    }

    public void setMatchprice(int matchprice) {
        this.matchprice = matchprice;
    }

    public Double getChangPrice() {
        return changPrice;
    }

    public void setChangPrice(Double changPrice) {
        this.changPrice = changPrice;
    }
}

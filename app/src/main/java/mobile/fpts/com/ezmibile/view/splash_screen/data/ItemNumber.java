package mobile.fpts.com.ezmibile.view.splash_screen.data;

import mobile.fpts.com.ezmibile.model.entity.StockMarket;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
import mobile.fpts.com.ezmibile.model.entity.CodeStockWatchList;
import mobile.fpts.com.ezmibile.model.entity.DetailsMarket;

public class ItemNumber extends ItemHomeChild {

    private int viewType;

    @Override
    public int getTypeView() {
        return viewType;
    }

    private String Indices;
    private String Last;
    private String Change;
    private String ChangeRatio;
    private String Qty;
    private String Value;
    private String ColorCode;
    private StockMarket StockMarket;
    private WorldIndices WorldIndices;
    private CodeStockWatchList CodeStockWatchList;
    private DetailsMarket DetailsMarket;

    public ItemNumber(String indices, String last, String change, String changeRatio, String qty,
                      String value, String colorCode, int viewType, StockMarket stockMarket, WorldIndices worldIndices,
                      CodeStockWatchList codeStockWatchList, DetailsMarket detailsMarket) {
        this.Indices = indices;
        this.Last = last;
        this.Change = change;
        this.Qty = qty;
        this.ChangeRatio = changeRatio;
        this.Value = value;
        this.ColorCode = colorCode;
        this.viewType = viewType;
        this.StockMarket = stockMarket;
        this.WorldIndices = worldIndices;
        this.CodeStockWatchList = codeStockWatchList;
        this.DetailsMarket = detailsMarket;
    }

    public String getIndices() {
        return Indices;
    }

    public void setIndices(String indices) {
        Indices = indices;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String last) {
        Last = last;
    }

    public String getChange() {
        return Change;
    }

    public void setChange(String change) {
        Change = change;
    }

    public String getChangeRatio() {
        return ChangeRatio;
    }

    public void setChangeRatio(String changeRatio) {
        ChangeRatio = changeRatio;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public StockMarket getStockMarket() {
        return StockMarket;
    }

    public void setStockMarket(StockMarket stockMarket) {
        StockMarket = stockMarket;
    }

    public WorldIndices getWorldIndices() {
        return WorldIndices;
    }

    public void setWorldIndices(WorldIndices worldIndices) {
        WorldIndices = worldIndices;
    }

    public CodeStockWatchList getCodeStockWatchList() {
        return CodeStockWatchList;
    }

    public void setCodeStockWatchList(CodeStockWatchList codeStockWatchList) {
        CodeStockWatchList = codeStockWatchList;
    }

    public DetailsMarket getDetailsMarket() {
        return DetailsMarket;
    }

    public void setDetailsMarket(DetailsMarket detailsMarket) {
        DetailsMarket = detailsMarket;
    }

    public String getColorCode() {
        return ColorCode;
    }

    public void setColorCode(String colorCode) {
        ColorCode = colorCode;
    }
}

package mobile.fpts.com.ezmibile.model.entity;


public class DetailsMarket {
    private String indexName;
    private String indexValue;
    private String Chang;
    private String Totalqty;
    private String TotalValue;
    private String ChangPerCent;


    public String getChang() {
        return Chang;
    }

    public void setChang(String chang) {
        Chang = chang;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getTotalqty() {
        return Totalqty;
    }

    public void setTotalqty(String totalqty) {
        Totalqty = totalqty;
    }

    public String getTotalValue() {
        return TotalValue;
    }

    public void setTotalValue(String totalValue) {
        TotalValue = totalValue;
    }

    public String getChangPerCent() {
        return ChangPerCent;
    }

    public void setChangPerCent(String changPerCent) {
        ChangPerCent = changPerCent;
    }
}

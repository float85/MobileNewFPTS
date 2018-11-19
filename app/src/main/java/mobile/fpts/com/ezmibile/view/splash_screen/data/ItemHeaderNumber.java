package mobile.fpts.com.ezmibile.view.splash_screen.data;


public class ItemHeaderNumber extends ItemHomeChild {
    private String Indices;
    private String Last;
    private String Change;
    private String ChangeRatio;
    private String Qty;
    private String Value;
    private int ViewType;

    public ItemHeaderNumber(String indices, String last, String change, String changeRatio,
                            String qty, String value, int ViewType) {
        this.Indices = indices;
        this.Last = last;
        this.Change = change;
        this.Qty = qty;
        this.ChangeRatio = changeRatio;
        this.Value = value;
        this.ViewType = ViewType;
    }

    @Override
    public int getTypeView() {
        return ViewType;
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
}

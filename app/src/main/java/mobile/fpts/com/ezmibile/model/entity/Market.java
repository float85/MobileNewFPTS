package mobile.fpts.com.ezmibile.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoaDT  on 4/17/2018.
 */
public class Market {
    @SerializedName("Name")
    @Expose
    private String MarketName;
    @SerializedName("LastPrice")
    @Expose
    private String MarketLastPrice;
    @SerializedName("Volumn")
    @Expose
    private String MarketVolumn;
    @SerializedName("Quantity")
    @Expose
    private String MarketQuantity;
    @SerializedName("ValueChange")
    @Expose
    private String MarketValueChange;
    @SerializedName("ValueChangeRatio")
    @Expose
    private String MarketValueChangeRatio;
    @SerializedName("IsChecked;")
    @Expose
    private boolean MarketIsChecked;

    public boolean isMarketIsChecked() {
        return MarketIsChecked;
    }

    public void setMarketIsChecked(boolean marketIsChecked) {
        MarketIsChecked = marketIsChecked;
    }

    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String marketName) {
        MarketName = marketName;
    }

    public String getMarketLastPrice() {
        return MarketLastPrice;
    }

    public void setMarketLastPrice(String marketLastPrice) {
        MarketLastPrice = marketLastPrice;
    }

    public String getMarketVolumn() {
        return MarketVolumn;
    }

    public void setMarketVolumn(String marketVolumn) {
        MarketVolumn = marketVolumn;
    }

    public String getMarketQuantity() {
        return MarketQuantity;
    }

    public void setMarketQuantity(String marketQuantity) {
        MarketQuantity = marketQuantity;
    }

    public String getMarketValueChange() {
        return MarketValueChange;
    }

    public void setMarketValueChange(String marketValueChange) {
        MarketValueChange = marketValueChange;
    }

    public String getMarketValueChangeRatio() {
        return MarketValueChangeRatio;
    }

    public void setMarketValueChangeRatio(String marketValueChangeRatio) {
        MarketValueChangeRatio = marketValueChangeRatio;
    }
}

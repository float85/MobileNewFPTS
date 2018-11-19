package mobile.fpts.com.ezmibile.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dinht on 1/16/2018.
 */
@Entity
public class MarketDB {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;
    @SerializedName("Name")
    private String MarketName;
    @SerializedName("LastPrice")
    private String MarketLastPrice;
    @SerializedName("Volumn")
    private String MarketVolumn;
    @SerializedName("Quantity")
    private String MarketQuantity;
    @SerializedName("ValueChange")
    private String MarketValueChange;
    @SerializedName("ValueChangeRatio")
    private String MarketValueChangeRatio;

    @Ignore
    public MarketDB() {
    }

    public MarketDB(String marketName, String marketLastPrice, String marketVolumn, String marketQuantity,
                    String marketValueChange, String marketValueChangeRatio) {
        MarketName = marketName;
        MarketLastPrice = marketLastPrice;
        MarketVolumn = marketVolumn;
        MarketQuantity = marketQuantity;
        MarketValueChange = marketValueChange;
        MarketValueChangeRatio = marketValueChangeRatio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

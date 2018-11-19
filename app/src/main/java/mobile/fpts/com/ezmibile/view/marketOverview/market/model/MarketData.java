package mobile.fpts.com.ezmibile.view.marketOverview.market.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by HoaDT  on 4/17/2018.
 */
@Entity
public class MarketData {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "marketName")
    private String marketName;

    @ColumnInfo(name = "isSave")
    private boolean isSave;


    @Ignore
    public MarketData() {
    }

    public MarketData(String marketName, boolean isSave) {
        this.marketName = marketName;
        this.isSave = isSave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }
}

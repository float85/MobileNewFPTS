package mobile.fpts.com.ezmibile.view.marketOverview.market;


import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.marketOverview.market.model.MarketData;

import static android.arch.persistence.room.Room.databaseBuilder;


/**
 * Created by HoaDT  on 4/17/2018.
 */

public class VnIndicesAdapterPresenter {
    AppDatabase database;

    public VnIndicesAdapterPresenter() {
        // TODO:HoaDT 6/19/2018 chuyển từ MarketDBDatabase sang Appdata
        database = databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public boolean getChecked(String marketName) {

        MarketData marketData = database.iMarketOverviewMarketDao().getMarketData(marketName);
        if (marketData == null) {
            return false;
        } else return marketData.isSave();
    }
}

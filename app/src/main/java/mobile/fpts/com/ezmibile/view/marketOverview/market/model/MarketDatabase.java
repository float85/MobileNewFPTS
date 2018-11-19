package mobile.fpts.com.ezmibile.view.marketOverview.market.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

/**
 * Created by HoaDT  on 4/17/2018.
 */
// TODO:HoaDT 6/19/2018
@Database(entities = {MarketData.class}, version = 2, exportSchema = false)
public abstract class MarketDatabase extends RoomDatabase {

    public abstract IMarketOverviewMarketDao dao();


    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };
}

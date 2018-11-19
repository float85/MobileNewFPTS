package mobile.fpts.com.ezmibile.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.entity.WatchlistData;
import mobile.fpts.com.ezmibile.model.entity.events.EventsDB;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategory;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategoryChild;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticleDB;
import mobile.fpts.com.ezmibile.model.entity.stock.StockInfoDB;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndicesDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.marketOverview.market.model.IMarketOverviewMarketDao;
import mobile.fpts.com.ezmibile.view.marketOverview.market.model.MarketData;

import static android.arch.persistence.room.Room.databaseBuilder;

@Database(exportSchema = false, version = 6,
        entities = {
                MenuCategory.class,
                MenuCategoryChild.class,
                EventsDB.class,
                NewsArticleDB.class,
                WorldIndicesDB.class,
                StockInfoDB.class,
                WatchlistData.class,
                MarketData.class
        })
public abstract class AppDatabase extends RoomDatabase {

    public abstract IMenuCategoryDao menuCategoryDao();

    public abstract IMenuCategoryChildDao menuCategoryChildDao();

    public abstract IEventDao eventDao();

    public abstract INewsDao newsDao();

    public abstract IWatchlistDao watchlistDao();

    public abstract IWorldIndicesDao worldIndicesDao();

    public abstract IStockInfoDao stockInfoDao();

    public abstract IMarketOverviewMarketDao iMarketOverviewMarketDao();

    public static AppDatabase getInstance(AppDatabase database) {
        if (database == null) {
            database = databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return database;
    }

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

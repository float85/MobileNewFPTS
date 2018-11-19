package mobile.fpts.com.ezmibile.view.watchlist.detail;

import android.arch.persistence.room.Room;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.stock.StockInfoDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Language;

/**
 * Created by FIT-thuctap22 on 3/23/2018.
 */

public class DetailCodePresenter {
    private IDetailView iDetailView;

    private AppDatabase database;
    private String symbol;

    public DetailCodePresenter(IDetailView iDetailView, String symbol) {
        this.iDetailView = iDetailView;
        this.symbol = symbol;
        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public String getNameCompany() {
        StockInfoDB stockInfoDB = database.stockInfoDao().getSearchStockDataBySymbol(symbol);
        if (stockInfoDB == null || stockInfoDB == new StockInfoDB())
            return "";

        Define.LANGUAGE_APP lan = Language.getLanguage();
        switch (lan) {
            case LANGUAGE_VI:
                return stockInfoDB.getName_vn();
            case LANGUAGE_EN:
            default:
                return stockInfoDB.getName_en();
        }
    }

    // TODO:HoaDT 7/5/2018 Load data companyname
//    private void Loadname() {
//        if (isLoadingName) {
//            return;
//        }
//        isLoadingName = true;
//        this.stockInfoList = new ArrayList<>();
//        ApiClientImp.getInstance()
//                .getListStockInfo("codename2", "0", "1")
//                .subscribeOn(Schedulers.io())
//                .map(stockInfoList -> stockInfoList)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(stockInfoList -> {
//                            Log.w("WatchlistPresenter", "Loadname: load success0 " + stockInfoList.size());
//                            this.stockInfoList = stockInfoList;
//                        }, throwable -> throwable.printStackTrace(),
//                        () -> isLoadingName = false);
//    }
//
//    public String getName(String code) {
//        for (int i = 0; i < stockInfoList.size(); i++) {
//            if (stockInfoList.get(i).getStockCode().equalsIgnoreCase(code))
//
//                return Language.getLanguage() == Define.LANGUAGE_APP.LANGUAGE_EN
//                        ? stockInfoList.get(i).getNameEN() : stockInfoList.get(i).getNameVN();
//        }
//        return "";
//    }
    public void destroy() {
//        database.close();
    }
}
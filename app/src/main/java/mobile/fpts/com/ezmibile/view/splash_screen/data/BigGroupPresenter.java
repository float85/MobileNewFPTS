package mobile.fpts.com.ezmibile.view.splash_screen.data;

import android.arch.persistence.room.Room;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.market.VnIndices;
import mobile.fpts.com.ezmibile.util.CheckInTime;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.marketOverview.market.model.MarketData;
import mobile.fpts.com.ezmibile.view.splash_screen.ISplashScreenView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BigGroupPresenter {
    private final int TIME_DELAY = 1000;
    ISplashScreenView.IBigGroupView iBigGroupView;
    private boolean isRequesting_indexes = false;
    List<VnIndices> indexesList = new ArrayList<>();
    private AppDatabase database;

    public BigGroupPresenter(ISplashScreenView.IBigGroupView iBigGroupView) {
        this.iBigGroupView = iBigGroupView;
        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
//        database.iMarketOverviewMarketDao().deleteAll();
        LoadIndexes();
    }
//
    // TODO:HoaDT 7/4/2018 realtime để sau
//    final Handler handler = new Handler();
//    int count1 = 0;
//    Runnable runnable_indexes = new Runnable() {
//        @Override
//        public void run() {
//            LoadIndexes();
//            if (count1 < 1) {
//                handler.postDelayed(runnable_indexes, TIME_DELAY);
//                count1++;
//            }
//            boolean check = CheckInTime.isInTime();
//            if (check) {
//                handler.postDelayed(runnable_indexes, TIME_DELAY);
//            }
//        }
//    };

    private void LoadIndexes() {
        if (isRequesting_indexes) {
            return;
        }
//        if (!CheckInTime.isInTime()) {
//            return;
//        }
        this.indexesList = new ArrayList<>();
        Log.w("BigGroupPresenter", "LoadIndexes: ");
        isRequesting_indexes = true;
        // TODO:HoaDT 6/27/2018 link gộp 2 thị trường HNX - HSX
        ApiClientImp.getInstance().getDataVnIndices(0, 1)
                .subscribeOn(Schedulers.io())
                .map(vnIndices -> {
                    Log.w("BigGroupPresenter", "LoadIndexes: " + vnIndices.size());
                    for (int i = 0; i < vnIndices.size(); i++) {
                        if (vnIndices.get(i).getChangePercent() == null) {
                            vnIndices.get(i).setChangePercent(vnIndices.get(i).getChangePercent_());
                        }
                    }
                    return vnIndices;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vnIndices -> {
                    saveDataVnIndices(vnIndices);
                    this.indexesList = getListMarketChecked(vnIndices);
                    iBigGroupView.onDisplay(indexesList);
                    Log.w("BigGroupPresenter", "LoadIndexes: done " + indexesList.size());
                }, throwable -> {
                    Log.w("BigGroupPresenter", "LoadIndexes: throwable");
                    throwable.printStackTrace();
                }, () -> {
                    isRequesting_indexes = false;
                });
    }

    // TODO:HoaDT 6/20/2018 Lấy ra các thị trường đã được lưu
    private List<VnIndices> getListMarketChecked(List<VnIndices> vnIndices) {
        List<VnIndices> vnIndicesList = new ArrayList<>();
        for (int i = 0; i < vnIndices.size(); i++) {
            // TODO:HoaDT 6/27/2018
            if (vnIndices.get(i).getUpDown() == null) {
                if (vnIndices.get(i).getUpDown() == null) {

                    if (vnIndices.get(i).getChange() != null) {
                        Double change = Double.parseDouble(vnIndices.get(i).getChange());
                        if (change > 0)
                            vnIndices.get(i).setUpDown("u");//up
                        else if (change == 0)
                            vnIndices.get(i).setUpDown("r");//ref
                        else vnIndices.get(i).setUpDown("d");//down
                    } else vnIndices.get(i).setUpDown("r");
                }
            }
        }

        for (int i = 0; i < vnIndices.size(); i++) {
            VnIndices tem = vnIndices.get(i);
            if (database.iMarketOverviewMarketDao().getMarketData(tem.getINDEX()) != null) {
                Log.w("BigGroupPresenter", "getListMarketChecked: " + tem.getINDEX() + " " + tem.isChecked());

                MarketData marketData = database.iMarketOverviewMarketDao().getMarketData(tem.getINDEX());
                if (marketData.isSave()) {
                    vnIndicesList.add(tem);
                }
            }
        }
        if (vnIndicesList == null || vnIndicesList.size() == 0) {
            return vnIndices;
        }

        return vnIndicesList;
    }

    // TODO:HoaDT 6/20/2018 Lấy dữ liệu Indices thành công thì lưu tên tất cả các thị trường lại
    private void saveDataVnIndices(List<VnIndices> vnIndices) {
        // TODO:HoaDT 6/22/2018 database null
//        if (database.iMarketOverviewMarketDao().getAll() == null || database.iMarketOverviewMarketDao().getAll().size() == 0) {
//            database.iMarketOverviewMarketDao().insert(new MarketData("HOSE", true));
//            database.iMarketOverviewMarketDao().insert(new MarketData("HNX", true));
//            database.iMarketOverviewMarketDao().insert(new MarketData("UPCOM", true));
//            database.iMarketOverviewMarketDao().insert(new MarketData("VN30", true));
//            database.iMarketOverviewMarketDao().insert(new MarketData("HNX30", true));
//
//            return;
//        }
        // TODO:HoaDT 6/22/2018 Chưa có: insert, Có: update
        for (VnIndices tem : vnIndices) {
            Log.w("BigGroupPresenter", "saveDataVnIndices: " + tem.getINDEX());
            if (database.iMarketOverviewMarketDao().getMarketData(tem.getINDEX()) == null)
                database.iMarketOverviewMarketDao().insert(new MarketData(tem.getINDEX(), true));
        }
    }
}

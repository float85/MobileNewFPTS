package mobile.fpts.com.ezmibile.view.marketOverview.market.hsx;

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
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.util.Utils;
import mobile.fpts.com.ezmibile.view.marketOverview.market.IMarket_View;
import mobile.fpts.com.ezmibile.view.marketOverview.market.model.MarketData;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.arch.persistence.room.Room.databaseBuilder;

/**
 * Created by HoaDT  on 4/17/2018.
 */

public class HSXPresenter {
    private final int TIME_DELAY = 2000;
    private IMarket_View view;
    private boolean isRequesting = false;
    private List<VnIndices> vnIndicesList = new ArrayList<>();
    AppDatabase database;
//    CompositeSubscription subscription = new CompositeSubscription();

    public HSXPresenter(IMarket_View view) {
        this.view = view;
        view.onLoading();
        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        handler.postDelayed(runnable, TIME_DELAY);
    }

    final Handler handler = new Handler();
    int count = 0;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getData();
            if (count < 1) {
                handler.postDelayed(runnable, TIME_DELAY);
                count++;
            }
            boolean check = CheckInTime.isInTime();
            if (check) {
                handler.postDelayed(runnable, TIME_DELAY);
            } else {
            }
        }
    };

    public void destroy() {
        handler.removeCallbacks(runnable);
//        database.close();
    }

    private void getData() {
        if (isRequesting) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            if (view != null) {
                view.onDisplayError(ErrorApp.ERROR_NETWORK);
            }
            if (vnIndicesList.size() > 0 && view != null) {
                view.onDisplay(vnIndicesList);
            }
            return;
        }
        isRequesting = true;
        vnIndicesList = new ArrayList<>();
        ApiClientImp.getInstance().getDataVnIndices(1, 1)
                .subscribeOn(Schedulers.io())
                .map(vnIndicesList -> {
                    for (int i = 0; i < vnIndicesList.size(); i++) {
                        if (vnIndicesList.get(i).getChangePercent() == null)
                            vnIndicesList.get(i).setChangePercent(vnIndicesList.get(i).getChangePercent_());
                    }
                    return vnIndicesList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vnIndicesList -> {
                    // TODO:HoaDT 6/21/2018 save data to database
                    saveDatabase(vnIndicesList);
                    this.vnIndicesList = getVnIndicesList(vnIndicesList);
                    if (view != null)
                        view.onDisplay(this.vnIndicesList);
                }, throwable -> {
                    Log.w("HSXPresenter", "throwable: ");
                    throwable.printStackTrace();
                }, () -> isRequesting = false);
    }

    public void OnClickItem(String marketName) {
        switch (marketName.trim().toLowerCase()) {
            case "vniindex":
            case "vni":
                view.onDisplayReplaceFragment(marketName);
                break;
            case "hnxindex":
            case "hnx":
                view.onDisplayReplaceFragment(marketName);
                break;
            case "upcom":
                view.onDisplayReplaceFragment(marketName);
                break;
            case "vn30":
                view.onDisplayReplaceFragment(marketName);
                break;
            case "hnx30":
                view.onDisplayReplaceFragment(marketName);
                break;
            default:
                view.onDisplayError(ErrorApp.NULL);
                break;
        }
    }

    public void OnClickItem(String marketName, Boolean isChecked) {
        Log.w("HSXPresenter", "OnClickItem: (star) " + marketName + (isChecked ? "true" : "false"));
        database.iMarketOverviewMarketDao().update(marketName, isChecked);
    }

    private void saveDatabase(List<VnIndices> vnIndicesList) {
        database = AppDatabase.getInstance(database);
        // TODO:HoaDT 6/25/2018 nếu chưa có thì thêm mới, check true
        for (VnIndices tem : vnIndicesList) {
            if (database.iMarketOverviewMarketDao().getMarketData(tem.getINDEX()) == null)
                database.iMarketOverviewMarketDao().insert(new MarketData(tem.getINDEX(), true));
        }

    }

    // TODO:HoaDT 6/27/2018 setValues + type
    private List<VnIndices> getVnIndicesList(List<VnIndices> list) {
        for (int i = 0; i < list.size(); i++) {
            VnIndices tem = list.get(i);
            MarketData marketData = database.iMarketOverviewMarketDao().getMarketData(tem.getINDEX());
            if (marketData != null) {
                list.get(i).setChecked(marketData.isSave());
            } else {
                //chưa có trong csdl
                list.get(i).setChecked(false);
            }
            if (tem.getUpDown() == null || tem.getUpDown() == "") {
                if (tem.getChange() == null || tem.getChange() == "")
                    list.get(i).setUpDown("u");
                else {
                    Double change = Double.parseDouble(tem.getChange());
                    if (change > 0)
                        list.get(i).setUpDown("u");
                    else if (change == 0)
                        list.get(i).setUpDown("r");
                    else list.get(i).setUpDown("d");
                }
            }
        }
        return list;
    }

}

package mobile.fpts.com.ezmibile.view.marketOverview.market.hnx;

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
public class HNXPresenter {
    private final int TIME_DELAY = 2000;
    private IMarket_View view;
    private boolean isRequesting = false;
    private List<VnIndices> vnIndicesList = new ArrayList<>();
    final Handler handler = new Handler();

    AppDatabase database;
//    CompositeSubscription subscription = new CompositeSubscription();

    public HNXPresenter(IMarket_View view) {
        this.view = view;
        view.onLoading();
        handler.postDelayed(runnable, TIME_DELAY);
        database = databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    int count = 0;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getData();
            boolean check = CheckInTime.isInTime();
            if (count < 1) {
                handler.postDelayed(runnable, TIME_DELAY);
                count++;
            }
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

    // TODO:HoaDT 6/19/2018 Loaddata
    private void getData() {
        if (isRequesting) {
            view.onDisplayError(ErrorApp.NULL);
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            view.onDisplayError(ErrorApp.ERROR_NETWORK);
            return;
        }
        isRequesting = true;
        vnIndicesList = new ArrayList<>();
        // TODO:HoaDT 6/21/2018 cập nhật link json mới
        ApiClientImp.getInstance().getDataVnIndices(2, 1)
                .subscribeOn(Schedulers.io())
                .map(otherIndexList -> otherIndexList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vnIndicesList -> {
                    Log.w("HNXPresenter", "getData: " + vnIndicesList.size());
                    vnIndicesList = saveDatabase(vnIndicesList);
                    this.vnIndicesList = getVnIndicesList(vnIndicesList);
                    // TODO:HoaDT 6/21/2018 save data to dbFD
                    // TODO:hoadt 6/20/2018 setChecked từ database

                    if (view != null)
                        view.onDisplay(this.vnIndicesList);
                }, throwable -> {
                    Log.w("HNXPresenter", "getData: throwable");
                    throwable.printStackTrace();
                }, () -> {
                    isRequesting = false;
                });
    }

    // TODO:HoaDT 6/19/2018 ko có connect
//    private void getDataFromSharedPreferences() {
//        SharedPreferences preferences = App.getInstance()
//                .getSharedPreferences(SHARED_PREFRENCES_MARKETOVERVIEW, Context.MODE_PRIVATE);
//        String str = preferences.getString(SHARED_PREFRENCES_MARKETOVERVIEW_LISTMARKET_HNX, "");
//        getCheck();
//    }

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

    // TODO:HoaDT 6/20/2018 Click to detail
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

    // TODO:HoaDT 6/19/2018 Click vào hình sao (trong MarketOverview fragment để đánh dấu chọn or bỏ chọn
    public void OnClickItem(String marketName, Boolean isChecked) {
        Log.w("HNXPresenter", "OnClickItem: (star) " + marketName + (isChecked ? "true" : "false"));
        database.iMarketOverviewMarketDao().update(marketName, isChecked);
    }

    private List<VnIndices> saveDatabase(List<VnIndices> vnIndices) {
        for (int i = 0; i < vnIndices.size(); i++) {
            if (database.iMarketOverviewMarketDao().getMarketData(vnIndices.get(i).getINDEX()) == null) {
                database.iMarketOverviewMarketDao().insert(new MarketData(vnIndices.get(i).getINDEX(), true));
                vnIndices.get(i).setChecked(true);
            }
        }
        return vnIndices;
    }
}

package mobile.fpts.com.ezmibile.view.watchlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.bin.david.form.data.Column;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.WatchlistData;
import mobile.fpts.com.ezmibile.model.entity.market.Quotes2;
import mobile.fpts.com.ezmibile.util.CheckInTime;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Utils;
import mobile.fpts.com.ezmibile.view.watchlist.model.Quote;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FIT-thuctap22 on 3/21/2018.
 */
public class WatchlistPresenter {
    private final int DELAY_MILLIS = 1000;
    private IWatchlistView view;
    private boolean isLoadingName = false;
    private boolean isLoadingAll = false;
    AppDatabase database;
    List<Column> columnList = new ArrayList<>();
    SharedPreferences preferences;
    private String quoteString = "";

    public String getQuoteString() {
        if (quoteString == null || quoteString == "") {
            quoteString = preferences.getString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTES, "FPT,FTS,GAS,VNM");
        }

        return quoteString;
    }

    public void setQuoteString(String quoteString) {
        this.quoteString = quoteString;
    }

    public WatchlistPresenter(IWatchlistView view) {
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        this.view = view;
        new LoadData().execute();
    }

    int temp = 0;
    final Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            Log.w("WatchlistPresenter", "run: temp = " + temp);
            if (temp < 1) {
                handler.postDelayed(runnable, DELAY_MILLIS);
                temp++;
            }
            boolean isInTime = CheckInTime.isInTime();
            if (isInTime) {
                handler.postDelayed(runnable, DELAY_MILLIS);
            }
            getData();
        }
    };

    public void destroy() {
        if (handler != null)
            handler.removeCallbacks(runnable);
//        database.close();
    }

    private class LoadData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            //load
            view.onLoading();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            ///ok
            handler.post(runnable);
            super.onPostExecute(result);
        }
    }

    private void getData() {
        if (isLoadingAll) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        isLoadingAll = true;
        ApiClientImp.getInstance().getListQuotes2(getQuoteString())
                .subscribeOn(Schedulers.io())
                .map(s -> s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quotes2s -> {///view
                    List<Quote> quoteList = new ArrayList<>();
                    for (int i = 0; i < quotes2s.size(); i++) {
                        Quotes2 tem = quotes2s.get(i);
                        Quote userData = new Quote(tem.getCode(), tem.getMatchPrice(), tem.getTotalQtty(), tem.getChangePrice(),
                                tem.getTotalQtty(), tem.getBuyPrice3(), tem.getBuyQtty3(), tem.getBuyPrice2(), tem.getBuyQtty2(),
                                tem.getBuyPrice1(), tem.getBuyQtty1(), tem.getSellPrice1(), tem.getSellQtty1(), tem.getSellPrice2(),
                                tem.getSellQtty2(), tem.getSellPrice3(), tem.getSellQtty3(), tem.getCeiling(), tem.getFloor(),
                                tem.getRefPrice(), tem.getOpenPrice(), tem.getHighestPrice(), tem.getLowestPrice(), tem.getForeignBuyQtty(),
                                tem.getForeignSellQtty(), tem.getUpDown());
                        quoteList.add(userData);
                    }
                    onLoadData(quoteList);
                }, throwable -> {
                }, () -> {
                    isLoadingAll = false;
                });
    }

    // TODO:HoaDT 6/25/2018 Lưu dãy mã quote vào Sharedpreferences
    public void saveQuoteString(String quoteString) {
        this.quoteString = quoteString;
        String quoteSave = preferences.getString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTES, "") + quoteString;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTES, quoteString);

        editor.apply();
        editor.commit();
        Log.w("WatchlistPresenter", "saveQuoteString:  " +
                App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE)
                        .getString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTES, ""));
    }

    public void onLoadData(final List<Quote> quoteList) {
        database = AppDatabase.getInstance(database);
        if (columnList == null || columnList.size() == 0) {
            columnList = getColumnTitleList();
        }
        List<Column> m = new ArrayList<>();
        if (database.watchlistDao().count() <= 0) {
            for (int i = 1; i < columnList.size(); i++)
                database.watchlistDao().insert(new WatchlistData(columnList.get(i).getColumnName(), i, true));
        }
        if (database.watchlistDao().count() > 0) {
            m.add(columnList.get(0));
            for (int i = 0; i < database.watchlistDao().getAll().size(); i++) {
                int count = database.watchlistDao().getAll().get(i).getSort();
                if (count < columnList.size() && database.watchlistDao().getAll().get(i).getCheck())
                    m.add(columnList.get(count));
            }
        }
        database.close();
        view.onDisplay(m, quoteList);
    }

    private List<Column> getColumnTitleList() {
        Column<String> column4 = new Column<>(App.getInstance().getResources().getString(R.string.symbol), "mack");
        column4.setTextAlign(Paint.Align.CENTER);
        column4.setFixed(true);
        List<Column> list = new ArrayList<>();
        list.add(column4);
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_last), "giakhop"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_match), "kl"));
        list.add(new Column<>("+/-", "di"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.volumn), "khoiluong"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_last_buy_3), "gm3"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_match_buy_3), "klm3"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_last__buy_2), "gm2"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_match_buy_2), "klm2"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_last_buy_1), "gm1"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_match_buy_1), "klm1"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_last_sell_1), "gb1"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_match_sell_1), "klb1"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_last_sell_2), "gb2"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_match_sell_2), "klb2"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_last_sell_3), "gb3"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_match_sell_3), "klb3"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_ceil), "tran"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_floor), "san"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_ref), "tc"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_open), "mo"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_high), "cao"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_low), "thap"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_foreign_buy), "nnmua"));
        list.add(new Column<>(App.getInstance().getResources().getString(R.string.watchlist_foreign_sell), "nnban"));

        return list;
    }

    public void setQuoteListToSharedPreferences(List<Quote> quoteList) {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(quoteList);
        editor.putString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTELIST, json);
        editor.apply();
        editor.commit();
    }

    public List<Quote> getQuoteListFromSharedPreferences() {
        List<Quote> quoteList = new ArrayList<>();
        Gson gson = new Gson();
        String json = preferences.getString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTELIST, "");
        if (json != null || json != "")
            quoteList = gson.fromJson(json, new TypeToken<List<Quote>>() {
            }.getType());

        return quoteList;
    }
}

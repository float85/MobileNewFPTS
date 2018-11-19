//package mobile.fpts.com.ezmibile.view.home;
//
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.AsyncTask;
//import android.os.Handler;
//import android.util.Log;
//import android.util.SparseArray;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import mobile.fpts.com.ezmibile.App;
//import mobile.fpts.com.ezmibile.R;
//import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
//import mobile.fpts.com.ezmibile.model.entity.CodeStockWatchList;
//import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
//import mobile.fpts.com.ezmibile.model.entity.StockMarket;
//import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
//import mobile.fpts.com.ezmibile.util.CheckInTime;
//import mobile.fpts.com.ezmibile.util.Define;
//import mobile.fpts.com.ezmibile.util.Utils;
//import mobile.fpts.com.ezmibile.view.home.data.BigGroup;
//import mobile.fpts.com.ezmibile.view.home.data.ItemHeaderNumber;
//import mobile.fpts.com.ezmibile.view.home.data.ItemHomeChild;
//import mobile.fpts.com.ezmibile.view.home.data.ItemNewsEvents;
//import mobile.fpts.com.ezmibile.view.home.data.ItemNumber;
//import mobile.fpts.com.ezmibile.view.home.data.ItemWorldIndices;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
//public class HomePresenter1 {
//    private final int TIME_DELAY = 100;
//    ISplashScreenView view;
//    Activity activity;
//
//    SparseArray<BigGroup> sparseArray;
//    List<StockMarket> indexesList = new ArrayList<>();
//    List<NewsArticle> newsArticleList = new ArrayList<>();
//    List<CodeStockWatchList> watchListList = new ArrayList<>();
//    List<WorldIndices> worldIndexList = new ArrayList<>();
//
//    private boolean isRequesting_indexes = false;
//    private boolean isRequesting_news = false;
//    private boolean isRequesting_watchlist = false;
//    private boolean isRequesting_worldIndex = false;
//
//    Handler handler_watchlist = new Handler();
//    Handler handler_indexes = new Handler();
//
//    public HomePresenter1(ISplashScreenView view, Activity activity) {
//        this.view = view;
//        this.activity = activity;
//        view.onLoading();
//        sparseArray = new SparseArray<>();
//        new Handler().postDelayed(() -> {
//            LoadviewDataHome task = new LoadviewDataHome();
//            task.execute();
//        }, TIME_DELAY);
//    }
//
//    public void onReload() {
//        new Handler().postDelayed(() -> {
//            sparseArray = new SparseArray<>();
//            LoadviewDataHome task = new LoadviewDataHome();
//            task.execute();
//        }, TIME_DELAY);
//    }
//
//    private class LoadviewDataHome extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Log.w("LoadviewDataHome", "onPreExecute: ");
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            Log.w("LoadviewDataHome", "doInBackground: ");
//            LoadIndexes();
//            LoadDataNews();
//            LoadDataWorldIndex();
//            LoadDataWachlist();
//            activity.runOnUiThread(() -> new Handler().postDelayed(() -> addData(), TIME_DELAY));
////            addData();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            Log.w("LoadviewDataHome", "onPostExecute: ");
//        }
//    }
//
//    int count1 = 0;
//    Runnable runable_watchlist = new Runnable() {
//        @Override
//        public void run() {
//            LoadDataWachlist();
//            if (count1 < 1) {
//                handler_watchlist.postDelayed(runable_watchlist, TIME_DELAY);
//                count1++;
//            }
//            boolean check = CheckInTime.isInTime();
//            if (!check) {
//                handler_watchlist.postDelayed(runable_watchlist, TIME_DELAY);
//            }
//        }
//    };
//
//    int count2 = 0;
//    Runnable runnable_indexes = new Runnable() {
//        @Override
//        public void run() {
//            LoadIndexes();
//            if (count2 < 1) {
//                handler_indexes.postDelayed(runnable_indexes, TIME_DELAY);
//                count2++;
//            } else if (!CheckInTime.isInTime()) {
//                handler_indexes.postDelayed(runnable_indexes, TIME_DELAY);
//            }
//        }
//    };
//
//    private void addData() {
////        // PHẦN INDEXES
//        sparseArray.append(sparseArray.size(), new BigGroup(Define.HOME_TYPE_INDEXES, "", new SparseArray<>()));
//        //WATCHLIST
//        SparseArray<ItemHomeChild> temp1 = new SparseArray<>();
//        temp1.append(temp1.size(), new ItemHeaderNumber(
//                App.getInstance().getResources().getString(R.string.indexes),
//                App.getInstance().getResources().getString(R.string.last),
//                App.getInstance().getResources().getString(R.string.change) + "◥",
//                App.getInstance().getResources().getString(R.string.change_percent) + " ◥",
//                App.getInstance().getResources().getString(R.string.volumn) + "◥",
//                App.getInstance().getResources().getString(R.string.value_bil) + "◥",
//                Define.HOME_TYPE_WATCHLIST));
//        for (int i = 0; i < watchListList.size(); i++) {
//            temp1.append(temp1.size(), new ItemNumber(watchListList.get(i).getCode(), watchListList.get(i).getMatchPrice(),
//                    watchListList.get(i).getChangePrice(), "", watchListList.get(i).getTotalQuantity(),
//                    "", watchListList.get(i).getUpDown(), Define.HOME_TYPE_WATCHLIST, null, null, watchListList.get(i), null));
//        }
//        sparseArray.append(sparseArray.size(), new BigGroup(Define.HOME_TYPE_WATCHLIST, App.getInstance().getResources().getString(R.string.watchlist), temp1));
//
//        // BẢNG GIÁ PHÁI SINH
//        sparseArray.append(sparseArray.size(), new BigGroup(Define.HOME_TYPE_BANG_GIA_PHAI_SINH, App.getInstance().getResources().getString(R.string.bang_gia_phai_sinh), new SparseArray<>()));
//
//        // NEWS
//        SparseArray<ItemHomeChild> temp_news = new SparseArray<>();
//        for (int i = 0; i < newsArticleList.size(); i++) {
//            temp_news.append(temp_news.size(),
//                    new ItemNewsEvents(newsArticleList.get(i).getNewsTitle(), newsArticleList.get(i).getNewsDate(), "",
//                            Define.HOME_TYPE_NEWS, newsArticleList.get(i).getNewsId(), newsArticleList.get(i).getNewsImg()));
//        }
//        sparseArray.append(sparseArray.size(), new BigGroup(Define.HOME_TYPE_NEWS, App.getInstance().getResources().getString(R.string.news), temp_news));
////        //PHẦN WORLD INDEXES
//        SparseArray<ItemHomeChild> temp_worldIndex = new SparseArray<>();
//        temp_worldIndex.append(temp_worldIndex.size(), new ItemHeaderNumber(
//                App.getInstance().getResources().getString(R.string.indexes),
//                App.getInstance().getResources().getString(R.string.last),
//                App.getInstance().getResources().getString(R.string.change) + "◥",
//                App.getInstance().getResources().getString(R.string.change_percent) + " ◥",
//                App.getInstance().getResources().getString(R.string.volumn) + "◥",
//                App.getInstance().getResources().getString(R.string.value_bil) + "◥",
//                Define.HOME_TYPE_WORLD_INDEXES));
//        for (int i = 0; i < worldIndexList.size(); i++) {
//            temp_worldIndex.append(temp_worldIndex.size(),
//                    new ItemWorldIndices(Define.HOME_TYPE_WORLD_INDEXES, worldIndexList.get(i)));
//        }
//        sparseArray.append(sparseArray.size(), new BigGroup(Define.HOME_TYPE_WORLD_INDEXES, App.getInstance().getResources().getString(R.string.world_index), temp_worldIndex));
//        view.onDisplay(sparseArray);
//    }
//
//    private void LoadIndexes() {
//        if (isRequesting_indexes) {
//            return;
//        }
//        if (!Utils.isNetworkAvailable()) {
//            //get data from room
//            return;
//        }
//
//        isRequesting_indexes = true;
//        indexesList = new ArrayList<>();
//        ApiClientImp.getInstance().getString("vn_indices").subscribeOn(Schedulers.io())
//                .map(s -> s)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> {
//                    try {
//                        String[] lines = s.split("@");
//                        for (int i = 0; i < lines.length - 1; i++) {
//                            StockMarket stockMarket = new StockMarket();
//                            String[] parts = lines[i + 1].split("#");
//                            if (parts.length > 6) {
//                                stockMarket.setMarketName(parts[0]);
//                                stockMarket.setMarketPrice(parts[1]);
//                                stockMarket.setMarketColorCode(parts[2]);
//                                stockMarket.setMarketValueChange(parts[3] + " ");
//                                stockMarket.setMarketValueChangeRatio(" " + parts[4]);
//                                stockMarket.setMarketToTalVolumn(parts[5]);
//                                stockMarket.setMarketToTalValue(parts[6].trim().replace(" bil", ""));
//                                stockMarket.setIsMarketData(true);
//                            }
//                            indexesList.add(stockMarket);
//                        }
//                        //save data to room
//
//
//                    } catch (Exception e) {
//                    }
//                }, throwable -> throwable.printStackTrace(), () -> isRequesting_indexes = false);
//    }
//
//    public void LoadDataNews() {
//        if (isRequesting_news) {
//            return;
//        }
//        if (!Utils.isNetworkAvailable()) {
//            //get data from room
//            return;
//        }
//        isRequesting_news = true;
//        newsArticleList = new ArrayList<>();
//        //link news chỉ có tiếng Việt
//        ApiClientImp.getInstance().getNews("news2", "86")//"news2", "82", "1", "1", "5")
//                .subscribeOn(Schedulers.io())
//                .map(s -> s)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> {
//                    Log.w("HomePresenter1", "LoadDataNews: " + s.size());
//                    newsArticleList = s;
//                    //save data to room
//                }, throwable -> {
//                    // get data from room
//                }, () -> isRequesting_news = false);
//    }
//
//    private void LoadDataWachlist() {
//        if (isRequesting_watchlist) {
//            return;
//        }
//        if (!Utils.isNetworkAvailable()) {
//            //get data from room
//            return;
//        }
//
//        isRequesting_watchlist = true;
//        watchListList = new ArrayList<>();
//        SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.HOME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
//        String watchlist = preferences.getString(Define.HOME_SHARED_PREFERENCES_WATCHLIST,
//                "fpt,fts,gas,vnm,cee,aaa,amd,anv,bhn,bcg,bmp,brc,cll,clw,cig,cdo,dhm,dmc,dlg,dat,flc,fmc,fit,ibc,ice,asm,stb,scr,hdb,mbb,flc,ctg,idi,hag,ssi,vcb,hbc,kbc,bid,hpg,hqc,dxg,lvhg,vic");
//        ApiClientImp.getInstance().getWatchlist()//"quotes", watchlist)
//                .subscribeOn(Schedulers.io())
//                .map(s -> s)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> {
//                    Log.w("HomePresenter1", "LoadDataWachlist: " + s.size());
//                    watchListList = s;
//
//                    //save data to  room
//                }, throwable -> {
//                }, () -> isRequesting_watchlist = false);
//    }
//
//    public void LoadDataWorldIndex() {
//        if (isRequesting_worldIndex) {
//            return;
//        }
//        if (!Utils.isNetworkAvailable()) {
//            //get data to room
//            return;
//        }
//
//        isRequesting_worldIndex = true;
//        worldIndexList = new ArrayList<>();
//        ApiClientImp.getInstance().getWorldIndices()
//                .subscribeOn(Schedulers.io())
//                .map(s -> s)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> {
//                    Log.w("HomePresenter1", "LoadDataWorldIndex: " + s.size());
//                    worldIndexList = s;
//                    //save data to room
//                }, throwable -> {
//                }, () -> isRequesting_worldIndex = false);
//    }
//
//    public void onDestroy() {
//        if (handler_indexes != null) {
//            handler_indexes.removeCallbacks(runnable_indexes);
//        }
//        if (handler_watchlist != null) {
//            handler_watchlist.removeCallbacks(runable_watchlist);
//        }
//    }
//}

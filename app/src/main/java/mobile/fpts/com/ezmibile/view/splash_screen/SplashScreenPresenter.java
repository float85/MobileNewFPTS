package mobile.fpts.com.ezmibile.view.splash_screen;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.CodeStockWatchList;
import mobile.fpts.com.ezmibile.model.entity.Market;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.model.entity.events.EventsDB;
import mobile.fpts.com.ezmibile.model.entity.market.Quotes2;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticleDB;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndicesDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Utils;
import mobile.fpts.com.ezmibile.view.splash_screen.data.BigGroup;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemHeaderNumber;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemHomeChild;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemNewsEvents;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemNumber;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemWorldIndices;
import mobile.fpts.com.ezmibile.view.watchlist.model.Quote;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashScreenPresenter implements ISplashScreenView.IHomePresenterview {
    private final int TIME_DELAY = 100;
    private ISplashScreenView view;
    private Activity activity;
    AppDatabase database;
    private String quoteString = "";
    List<Quotes2> quotes2List = new ArrayList<>();
    SparseArray<BigGroup> bigGroupSparseArray = new SparseArray<>();

    public String getQuoteString() {
        if (quoteString == null || quoteString == "") {
            quoteString = getQuoteCode();
        }
        return quoteString;
    }

    // TODO:HoaDT 7/4/2018 lấy ra list mã đã lưu
    private String getQuoteCode() {
        String quotesCode = "FPT,FTS,GAS,VNM";
        SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP,
                Context.MODE_PRIVATE);
        if (preferences != null)
            quotesCode = preferences.getString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTES, "FPT,FTS,GAS,VNM");
        return quotesCode;
    }


    public SplashScreenPresenter(ISplashScreenView view, Activity activity) {
        this.view = view;
        this.activity = activity;
        database = Room.databaseBuilder(activity, AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        new LoadviewDataHome().execute();
    }

    private class LoadviewDataHome extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            LoadWatchlist();
            activity.runOnUiThread(() -> new Handler().postDelayed(() -> addData(), 1000));
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    /**
     * update realtime
     */
    @Override
    public void onReload() {
        // TODO:HoaDT 7/4/2018 realtime để sau
        new Handler().postDelayed(() -> {
            bigGroupSparseArray = new SparseArray<>();
            LoadviewDataHome task = new LoadviewDataHome();
            task.execute();
        }, TIME_DELAY);
    }

    /**
     * remove handler callback
     */
    @Override
    public void onDestroy() {

    }

    /**
     * From server
     * link
     */
    @Override
    public void LoadWatchlist() {
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        ApiClientImp.getInstance().getListQuotes2(getQuoteString())
                .subscribeOn(Schedulers.io())
                .map(s -> s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quotes2s -> {///view
                    this.quotes2List = quotes2s;
                }, throwable -> {
                    Log.w("SplashScreenPresenter", "LoadWatchlist: throwable");
                    throwable.printStackTrace();
                }, () -> {
                });
    }

    /**
     * From database
     */
    @Override
    public List<WorldIndices> LoadWorldIndex() {
        List<WorldIndices> worldIndices = new ArrayList<>();

        List<WorldIndicesDB> worldIndicesDBList = database.worldIndicesDao().getWorldIndicesList();
        if (worldIndicesDBList != null && worldIndicesDBList.size() > 0) {
            for (WorldIndicesDB world : worldIndicesDBList) {
                worldIndices.add(new WorldIndices(world.getRowId(), world.getGroupId(), world.getTitle(),
                        world.getPrice(), world.getRegionId(), world.getChange(), world.getCreated(),
                        world.getCharturl(), world.getChangePct(), world.getLocaltime(), world.getVieword()));
                if (worldIndices.size() == 5)
                    break;
            }
        }
        return worldIndices;
    }

    /**
     * From database
     */
    @Override
    public List<NewsArticle> LoadNews() {
        List<NewsArticle> newsArticleList = new ArrayList<>();
        List<NewsArticleDB> newsArticleDBList = database.newsDao().getNewsList();
        if (newsArticleDBList != null && newsArticleDBList.size() > 0) {
            for (NewsArticleDB news : newsArticleDBList) {
                newsArticleList.add(new NewsArticle(news.getNewsId(), news.getNewsTitle(), news.getNewsDate(),
                        news.getNewsSizeInByte(), news.getNewsSizeInKB(), news.getNewsDate2(), news.getNewsFTitle(),
                        news.getNewsContent(), news.getNewsImg(), news.getNewStype()));
                if (newsArticleList.size() == 5)
                    break;
            }
        }
        return newsArticleList;
    }

    /**
     * From database
     */
    @Override
    public List<EventsApp> LoadEvents() {
        List<EventsApp> eventsList = new ArrayList<>();
        List<EventsDB> eventsDBList = database.eventDao().getEventsList();
        if (eventsDBList != null && eventsDBList.size() > 0) {

            for (EventsDB event : eventsDBList) {
                if (eventsList.size() < 5)
                    eventsList.add(new EventsApp(event.getEventIDX(), event.getEventGroupNm(), event.getEventId(),
                            event.getEventStockCode(), event.getEventContent(), event.getEventUrl(), event.getEventDate1()));
                else break;
            }
        }
        return eventsList;
    }

    private void addData() {
//        // PHẦN INDEXES
        bigGroupSparseArray.append(bigGroupSparseArray.size(), new BigGroup(Define.HOME_TYPE_INDEXES, "", new SparseArray<>()));
        //WATCHLIST
        SparseArray<ItemHomeChild> temp1 = new SparseArray<>();
        temp1.append(temp1.size(), new ItemHeaderNumber(
                App.getInstance().getResources().getString(R.string.indexes),
                App.getInstance().getResources().getString(R.string.last),
                App.getInstance().getResources().getString(R.string.change) + "◥",
                App.getInstance().getResources().getString(R.string.change_percent) + " ◥",
                App.getInstance().getResources().getString(R.string.volumn),
                App.getInstance().getResources().getString(R.string.value_bil),
                Define.HOME_TYPE_WATCHLIST));
        // TODO:HoaDT 7/4/2018 dữ liệu fake
//        codeStockWatchLists = getCodeStockWatchList();
//        for (int i = 0; i < codeStockWatchLists.size(); i++) {
//            temp1.append(temp1.size(), new ItemNumber(codeStockWatchLists.get(i).getCode(),
//                    codeStockWatchLists.get(i).getMatchPrice(), codeStockWatchLists.get(i).getChangePrice(),
//                    "", codeStockWatchLists.get(i).getTotalQuantity(),
//                    "", codeStockWatchLists.get(i).getUpDown(), Define.HOME_TYPE_WATCHLIST,
//                    null, null, codeStockWatchLists.get(i), null));
//        }
        // TODO:HoaDT 7/4/2018
        for (int i = 0; i < quotes2List.size(); i++) {
            Quotes2 quotes2 = quotes2List.get(i);
            temp1.append(temp1.size(), new ItemNumber(quotes2.getCode(), quotes2.getMatchPrice(), quotes2.getChangePrice(),
                    "", quotes2.getTotalQtty(), "", quotes2.getUpDown(), Define.HOME_TYPE_WATCHLIST,
                    null, null, null, null));
        }
        bigGroupSparseArray.append(bigGroupSparseArray.size(), new BigGroup(Define.HOME_TYPE_WATCHLIST, App.getInstance().getResources().getString(R.string.watchlist), temp1));

        // BẢNG GIÁ PHÁI SINH
        SparseArray<ItemHomeChild> temp2 = new SparseArray<>();
        temp2.append(temp2.size(), new ItemHeaderNumber(
                App.getInstance().getResources().getString(R.string.indexes),
                App.getInstance().getResources().getString(R.string.last),
                App.getInstance().getResources().getString(R.string.change) + "◥",
                App.getInstance().getResources().getString(R.string.change_percent) + " ◥",
                App.getInstance().getResources().getString(R.string.volumn),
                App.getInstance().getResources().getString(R.string.value_bil),
                Define.HOME_TYPE_BANG_GIA_PHAI_SINH));
        List<CodeStockWatchList> codeStockWatchLists = getCodeStockWatchListPS();
        for (int i = 0; i < codeStockWatchLists.size(); i++) {
            temp2.append(temp2.size(), new ItemNumber(codeStockWatchLists.get(i).getCode(),
                    codeStockWatchLists.get(i).getMatchPrice(), codeStockWatchLists.get(i).getChangePrice(),
                    "", codeStockWatchLists.get(i).getTotalQuantity(),
                    "", codeStockWatchLists.get(i).getUpDown(), Define.HOME_TYPE_BANG_GIA_PHAI_SINH,
                    null, null, codeStockWatchLists.get(i), null));
        }
        bigGroupSparseArray.append(bigGroupSparseArray.size(), new BigGroup(Define.HOME_TYPE_BANG_GIA_PHAI_SINH, App.getInstance().getResources().getString(R.string.bang_gia_phai_sinh), temp2));
        // NEWS
        SparseArray<ItemHomeChild> temp_news = new SparseArray<>();
        List<NewsArticle> newsArticleList = LoadNews();
        for (int i = 0; i < newsArticleList.size(); i++) {
            temp_news.append(temp_news.size(),
                    new ItemNewsEvents(newsArticleList.get(i).getNewsTitle(), newsArticleList.get(i).getNewsDate(), "",
                            Define.HOME_TYPE_NEWS, newsArticleList.get(i).getNewsId(), newsArticleList.get(i).getNewsImg()));
        }
        bigGroupSparseArray.append(bigGroupSparseArray.size(), new BigGroup(Define.HOME_TYPE_NEWS,
                App.getInstance().getResources().getString(R.string.news), temp_news));
        //EVENTS
        SparseArray<ItemHomeChild> temp_events = new SparseArray<>();

        List<EventsApp> eventsList = LoadEvents();
        for (int i = 0; i < eventsList.size(); i++) {
            EventsApp events = eventsList.get(i);
            temp_events.append(temp_events.size(),
                    new ItemNewsEvents(events.getContent(), events.getDate1(), "",
                            Define.HOME_TYPE_EVENTS, events.getiD(), events.getUrl()));
        }
        bigGroupSparseArray.append(bigGroupSparseArray.size(), new BigGroup(Define.HOME_TYPE_EVENTS, App.getInstance().getResources().getString(R.string.events), temp_events));
//        //PHẦN WORLD INDEXES
        SparseArray<ItemHomeChild> temp_worldIndex = new SparseArray<>();
        temp_worldIndex.append(temp_worldIndex.size(), new ItemHeaderNumber(
                App.getInstance().getResources().getString(R.string.indexes),
                App.getInstance().getResources().getString(R.string.last),
                App.getInstance().getResources().getString(R.string.change),
                App.getInstance().getResources().getString(R.string.change_percent),
                App.getInstance().getResources().getString(R.string.volumn),
                App.getInstance().getResources().getString(R.string.value_bil),
                Define.HOME_TYPE_WORLD_INDEXES));

        List<WorldIndices> worldIndicesList = LoadWorldIndex();
        for (int i = 0; i < worldIndicesList.size(); i++) {
            WorldIndices worldIndices = worldIndicesList.get(i);
            temp_worldIndex.append(temp_worldIndex.size(), new ItemWorldIndices(Define.HOME_TYPE_WORLD_INDEXES, worldIndices));
        }
        bigGroupSparseArray.append(bigGroupSparseArray.size(), new BigGroup(Define.HOME_TYPE_WORLD_INDEXES,
                App.getInstance().getResources().getString(R.string.world_index), temp_worldIndex));
        view.onDisplay(bigGroupSparseArray);
    }

    private List<CodeStockWatchList> getCodeStockWatchList() {
        List<CodeStockWatchList> list = new ArrayList<>();
/**
 * (String code, String upDown, String matchPrice, String changePrice,
 String totalQuantity, String centerNo, String ceiling, String floor, String refPrice)
 */
        list.add(new CodeStockWatchList("FTS", "u", "15.6", "0.1", "950", "1", "16.55", "14.45", "15.5"));
        list.add(new CodeStockWatchList("VNM", "d", "177.1", "-0.9", "124,680", "1", "190.4", "165.6", "178"));
        list.add(new CodeStockWatchList("SSI", "u", "34.8", "0.2", "1,393,790", "1", "37", "32.2", "34.6"));
        list.add(new CodeStockWatchList("HAG", "d", "5.13", "-0.07", "579,810", "1", "5.56", "4.84", "5.2"));
        list.add(new CodeStockWatchList("FPT", "u", "60.9", "1.4", "1,154,660", "1", "63.6", "55.4", "59.5"));

        return list;
    }

    private List<CodeStockWatchList> getCodeStockWatchListPS() {
        List<CodeStockWatchList> list = new ArrayList<>();
/**
 * (String code, String upDown, String matchPrice, String changePrice,
 String totalQuantity, String centerNo, String ceiling, String floor, String refPrice)
 */
        list.add(new CodeStockWatchList("VN30F1806", "u", "15.6", "0.1", "950", "1", "16.55", "14.45", "15.5"));
        list.add(new CodeStockWatchList("VN30F1807", "d", "177.1", "-0.9", "124,680", "1", "190.4", "165.6", "178"));
        list.add(new CodeStockWatchList("VN30F1809", "u", "34.8", "0.2", "1,393,790", "1", "37", "32.2", "34.6"));
        list.add(new CodeStockWatchList("VN30F1812", "d", "5.13", "-0.07", "579,810", "1", "5.56", "4.84", "5.2"));

        return list;
    }
}

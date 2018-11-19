package mobile.fpts.com.ezmibile.view.main;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.text.InputFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.model.entity.events.EventsDB;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticleDB;
import mobile.fpts.com.ezmibile.model.entity.stock.StockInfoDB;
import mobile.fpts.com.ezmibile.model.entity.stock.StockInfo;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndicesDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Utils;
import mobile.fpts.com.ezmibile.view.watchlist.search.CustomerAutoComleteTextView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements IMainView.IMainPresenterView {
    AppDatabase database;

    private IMainView view;
    private IMainView.ISearch viewSearch;
    Context context;
    boolean isEventsRequesting = false;
    boolean isNewsRequesting = false;
    boolean isNewsDetailRequesting = false;
    boolean isWorldIndicesRequesting = false;

    //database
    public MainPresenter(IMainView view, IMainView.ISearch viewSearch, Context context) {
        this.viewSearch = viewSearch;
        this.view = view;
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        LoadData();
        view.display();
    }

    private void LoadData() {
        LoadEvents();
        LoadNews();
        LoadWorldIndexes();
        loadNewsDetailList();
    }

    @Override
    public void LoadEvents() {
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        if (isEventsRequesting) {
            return;
        }
        isEventsRequesting = true;
        ApiClientImp.getInstance().getEvents("1")
                .subscribeOn(Schedulers.io())
                .map(events -> events)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                            Log.w("MainPresenter", "LoadEvents: load done");
                            setEvents(events);
                        },
                        throwable -> throwable.printStackTrace(),
                        () -> isEventsRequesting = false);
    }

    @Override
    public void LoadWorldIndexes() {
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        if (isWorldIndicesRequesting) {
            return;
        }
        isWorldIndicesRequesting = true;
        ApiClientImp.getInstance().getWorldIndices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(worldIndices -> worldIndices)
                .subscribe(worldIndices -> {
                            Log.w("MainPresenter", "LoadWorldIndexes: load done");
                            setWorldList(worldIndices);
                        },
                        throwable -> throwable.printStackTrace(),
                        () -> isWorldIndicesRequesting = false);
    }

    @Override
    public void LoadNews() {
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        if (isNewsRequesting) {
            return;
        }
        isNewsRequesting = true;
        //s=news2&folder=86
        ApiClientImp.getInstance().getListNews("86")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsList -> {
                            Log.w("MainPresenter", "LoadNews: load done");
                            setNews(newsList);
                        },
                        throwable -> throwable.printStackTrace(),
                        () -> isNewsRequesting = false
                );
    }

    public void loadNewsDetailList() {
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        if (isNewsDetailRequesting) {
            return;
        }
        isNewsDetailRequesting = true;
        ApiClientImp.getInstance().getListNews("512")
                .subscribeOn(Schedulers.io())
                .map(newsArticles -> {
                    Log.w("HomeNewsPresenter", "getData: " + newsArticles.size());
                    return newsArticles;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newsArticles -> {
                            Log.w("HomeNewsPresenter", "getData: " + newsArticles.size());
                            List<NewsArticle> list = new ArrayList<>();
                            for (NewsArticle news : newsArticles) {
                                list.add(news);
                            }
//                            setList(keyShered, list);
                            setNewsDetail(list, Define.Market_News);
//                            view.getDataSuccess(list);
                        },
                        throwable -> {
                            throwable.printStackTrace();
                        },
                        () -> {
                            isNewsRequesting = false;
                        });

        ApiClientImp.getInstance().getListNews("82")
                .subscribeOn(Schedulers.io())
                .map(newsArticles -> {
                    Log.w("HomeNewsPresenter", "getData: " + newsArticles.size());
                    return newsArticles;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newsArticles -> {
                            Log.w("HomeNewsPresenter", "getData: " + newsArticles.size());
                            List<NewsArticle> list = new ArrayList<>();
                            for (NewsArticle news : newsArticles) {
                                list.add(news);
                            }
//                            setList(keyShered, list);
                            setNewsDetail(list, Define.FPTS_News);
                        },
                        throwable -> {
                            throwable.printStackTrace();
                        },
                        () -> {
                            isNewsRequesting = false;
                        });
    }

    public void setNewsDetail(List<NewsArticle> newsArticleList, String stype) {
        Log.w("MainPresenter", "setNews: " + newsArticleList.size());

        for (NewsArticle news : newsArticleList) {
            database.newsDao().deleteNewsArticleDB_ID(news.getNewsId());
            database.newsDao().insert(new NewsArticleDB(news.getNewsId(), news.getNewsTitle(), news.getNewsDate(),
                    news.getNewsSizeInByte(), news.getNewsSizeInKB(), news.getNewsDate2(), news.getNewsFTitle(),
                    news.getNewsContent(), news.getNewsImg(), stype));
        }
    }

    @Override
    public void getLanguage() {
    }

    @Override
    public void setLanguage() {

    }

    public void setEvents(List<EventsApp> eventsList) {
        Log.w("MainPresenter", "setEvents: " + eventsList.size());
        database.eventDao().deleteEventsDB();
        for (EventsApp events : eventsList) {
            database.eventDao().insert(new EventsDB(events.getiDX(), events.getGroupNm(), events.getiD(), events.getStockCode(),
                    events.getContent(), events.getUrl(), events.getDate1()));
        }
    }

    @Override
    public void setNews(List<NewsArticle> newsArticleList) {
        Log.w("MainPresenter", "setNews: " + newsArticleList.size());
//        database.newsDao().deleteNewsArticleDB();
        for (NewsArticle news : newsArticleList) {
            database.newsDao().deleteNewsArticleDB_ID(news.getNewsId());
            database.newsDao().insert(new NewsArticleDB(news.getNewsId(), news.getNewsTitle(), news.getNewsDate(),
                    news.getNewsSizeInByte(), news.getNewsSizeInKB(), news.getNewsDate2(), news.getNewsFTitle(),
                    news.getNewsContent(), news.getNewsImg(), news.getNewStype()));
        }
    }

    @Override
    public void setWorldList(List<WorldIndices> worldIndicesList) {
        Log.w("MainPresenter", "setWorldList: " + worldIndicesList.size());
        database.worldIndicesDao().deleteWorldIndicesDB();
        for (WorldIndices item : worldIndicesList) {
            database.worldIndicesDao().insert(new WorldIndicesDB(item.getRowId(), item.getGroupId(), item.getTitle(),
                    item.getPrice(), item.getRegionId(), item.getChange(), item.getCreated(), item.getCharturl(),
                    item.getChangePct(), item.getLocaltime(), item.getVieword()));
        }
    }

    public void destroy() {
        if (database != null) {
            database.close();
        }
    }

    //Search
    public InputFilter[] validateEdittext() {
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {

            for (int i = start; i < end; i++) {
                if (!Character.isLetterOrDigit(source.charAt(i)) &&
                        !Character.toString(source.charAt(i)).equals(" ")) {
                    return "";
                }
            }
            return null;
        };

        InputFilter[] inputFilters = new InputFilter[]{filter, new InputFilter.LengthFilter(9)};
        return inputFilters;
    }

//    public boolean isConnected() {
//        ConnectivityManager cm =
//                (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//            return true;
//        }
//        return false;
//    }

    public void loadData() {
        final ArrayList<CustomerAutoComleteTextView> arrayList = new ArrayList<>();
        List<StockInfoDB> list = database.stockInfoDao().getSearchStockData();

        if (list.size() > 0) {
            for (int i = 0; i < list.size() - 1; i++) {
                arrayList.add(new CustomerAutoComleteTextView(list.get(i).getStock_code(), list.get(i).getName_vn(),
                        list.get(i).getName_en(), list.get(i).getPost_to()));
            }

            viewSearch.setDataSearchStock(arrayList);

        } else {
            if (Utils.isNetworkAvailable()) {
                ApiClientImp.getInstance().getStockInfo("0")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<StockInfo>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(List<StockInfo> listEvents) {
                                database.stockInfoDao().deleteSearchStockData();
                                if (listEvents != null || listEvents.size() > 0) {
                                    for (int i = 0; i < listEvents.size() - 1; i++) {
                                        arrayList.add(new CustomerAutoComleteTextView(listEvents.get(i).getStockCode(), listEvents.get(i).getNameVN(),
                                                listEvents.get(i).getNameEN(), listEvents.get(i).getPostTO()));

                                        database.stockInfoDao().addMaCk(new StockInfoDB(listEvents.get(i).getStockCode(),
                                                listEvents.get(i).getNameVN(), listEvents.get(i).getNameEN(),
                                                listEvents.get(i).getPostTO(), listEvents.get(i).getNameShort(),
                                                listEvents.get(i).getC()));
                                    }

                                    viewSearch.setDataSearchStock(arrayList);
                                }
                            }
                        });
            }
        }

    }
}

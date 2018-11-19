package mobile.fpts.com.ezmibile.view.news.home;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticleDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TrangDTH on 5/9/2018.
 */
public class HomeNewsPresenter {
    private INews.HomeNewsView view;
    private boolean isNewsLoading = false;

    //Luu trang thai
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String keyShered = "Tin_Tuc";

    AppDatabase database;

    public HomeNewsPresenter(INews.HomeNewsView view) {
        this.view = view;
//        sharedPreferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFERENCES_SECURITIES_PUBLIC, Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();

        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public void getData() {
        if (isNewsLoading) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            view.getConnectNetworkFail();
            try {

//                if (getList(keyShered) != null || getList(keyShered).size() > 0) {
//                    list.addAll(getList(keyShered));
//                    view.getDataSuccess(list);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        isNewsLoading = true;
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
                            setNews(list, Define.Market_News);
//                            view.getDataSuccess(list);
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            view.getConnectServerFail();
                        },
                        () -> {
                            isNewsLoading = false;
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
                            setNews(list, Define.FPTS_News);
//                            view.getDataSuccess(list);
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            view.getConnectServerFail();
                        },
                        () -> {
                            isNewsLoading = false;
                        });
    }

//    public List<NewsArticle> getList(String key) {
//        Gson gson = new Gson();
//        List<NewsArticle> list;
//
//        String json = get(key);
//        Type type = new TypeToken<List<NewsArticle>>() {
//        }.getType();
//        list = gson.fromJson(json, type);
//
//        return list;
//    }
//
//    public String get(String key) {
//        return sharedPreferences.getString(key, "");
//    }
//
//    public <T> void setList(String key, List<T> list) {
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//
//        editor.putString(key, json);
//        editor.apply();
//        editor.commit();
//    }

    public void setNews(List<NewsArticle> newsArticleList, String stype) {
        Log.w("MainPresenter", "setNews: " + newsArticleList.size());

        for (NewsArticle news : newsArticleList) {
            database.newsDao().deleteNewsArticleDB_ID(news.getNewsId());
            database.newsDao().insert(new NewsArticleDB(news.getNewsId(), news.getNewsTitle(), news.getNewsDate(),
                    news.getNewsSizeInByte(), news.getNewsSizeInKB(), news.getNewsDate2(), news.getNewsFTitle(),
                    news.getNewsContent(), news.getNewsImg(), stype));
        }
    }

}

package mobile.fpts.com.ezmibile.view.news.home.market_news;

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
import mobile.fpts.com.ezmibile.model.api.ApiClient;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticleDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.news.home.INews;

// TODO: TamHV 6/28/2018
public class MarketNewsPresenter implements INews.Presenter {
    private INews.View view;
    AppDatabase database;

    public MarketNewsPresenter(  INews.View view) {
        this.view = view;
        Log.w("MarketNewsPresenter", "MarketNewsPresenter: ");

        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public void getData() {
        Log.w("MarketNewsPresenter", "getData: ");
        List<NewsArticleDB> listAll = database.newsDao().getNewsListFormStype(Define.Market_News);
        List<NewsArticle> listNews = new ArrayList<>();

        for (int i = 0; i < listAll.size(); i++) {
            listNews.add(new NewsArticle(listAll.get(i).getNewsId(), listAll.get(i).getNewsTitle(), listAll.get(i).getNewsDate(),
                    listAll.get(i).getNewsSizeInByte(), listAll.get(i).getNewsSizeInKB(), listAll.get(i).getNewsDate2(),
                    listAll.get(i).getNewsFTitle(), listAll.get(i).getNewsContent(), listAll.get(i).getNewsImg()));
        }
        view.getDataDone(listNews, Define.Market_News);
    }
}

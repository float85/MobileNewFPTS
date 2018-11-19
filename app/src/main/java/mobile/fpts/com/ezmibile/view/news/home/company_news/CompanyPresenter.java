package mobile.fpts.com.ezmibile.view.news.home.company_news;

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
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticleDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.news.home.INews;

public class CompanyPresenter implements INews.Presenter {
    private INews.View view;
    AppDatabase database;

    public CompanyPresenter(INews.View view) {
        this.view = view;

        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public void getData() {
        Log.w("MarketNewsPresenter", "getData: ");
        List<NewsArticleDB> listAll = database.newsDao().getNewsListFormStype(Define.Company_News);
        List<NewsArticle> listNews = new ArrayList<>();

        for (int i = 0; i < listAll.size(); i++) {
            listNews.add(new NewsArticle(listAll.get(i).getNewsId(), listAll.get(i).getNewsTitle(), listAll.get(i).getNewsDate(),
                    listAll.get(i).getNewsSizeInByte(), listAll.get(i).getNewsSizeInKB(), listAll.get(i).getNewsDate2(),
                    listAll.get(i).getNewsFTitle(), listAll.get(i).getNewsContent(), listAll.get(i).getNewsImg()));
        }
        view.getDataDone(listNews, Define.Company_News);
    }

}

package mobile.fpts.com.ezmibile.view.news.detail;

import android.arch.persistence.room.Room;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticleDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.util.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewDetailPresenter {
    private INewsDetail view;
    private boolean isRequesting = false;
    private boolean isRequestingRelated = false;
    private String symbol;
    private String stype;
    AppDatabase database;

    public NewDetailPresenter(INewsDetail view, String NewsID, String symbol, String stype) {
        this.view = view;
        this.symbol = symbol;
        this.stype = stype;
        callData(NewsID);

        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public void callData(String id) {
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        Log.w("NewDetailPresenter", "callData: https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=news_detail&id=" + id);
        isRequesting = true;
        ApiClientImp.getInstance().getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .map(responseBodyResponse -> {
                    Log.w("NewDetailPresenter", "callData: " + responseBodyResponse.body());
                    return responseBodyResponse.body();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.w("NewDetailPresenter", "callData: " + response.toString());
                    try {
                        splitChiTietTin(response.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        view.onLoadError(ErrorApp.EXCEPTION);
                    }
                }, throwable -> {
                    Log.w("NewDetailPresenter", "callData: throwable");
                    throwable.printStackTrace();
                }, () -> isRequesting = false);
        view.onLoading();
    }

    private void splitChiTietTin(String body) {
        // TODO:HoaDT 7/5/2018 remove font, size, color
        body = body.replace("face=arial", "")
                .replace("size=2", "")
                .replace("size=3", "")
                .replace("color=#000000", "")
                .replace("color=#015396", "");

        Log.w("NewDetailPresenter", "splitChiTietTin: " + body);
        Document document = Jsoup.parse(body);
        document.body();

        String link = "";
        if (document.body().getElementsByTag("a").size() > 0) {
            link = document.body().getElementsByAttribute("href").attr("href")
                    .replace("</b></font>", "");
        }
        //document.body().getElementsByTag("a").remove().toString()
        String item[] = body.split("</b></font></P>");
        if (item.length > 2) {
            view.onDisplay(item[0], item[1].replace("Download File", "") + item[2], link);
//                    "<html><body style=\"text-align:justify\">" + item[0] + "</body></Html>",
        } else if (item.length > 1) {
            view.onDisplay(item[0], item[1].replace("Download File", ""), link);
        } else {
            view.onDisplay(item[0], "", link);
        }

        // TODO:HoaDT 7/5/2018
//        String title = "";
//        if (item != null && item.length > 0)
//            title = item[0].toString().replaceAll("<font face=arial size=3 color=#015396>", "");
//        title = title + "</b></P>";
//        String lead = "";
//        if (item.length > 1)
//            lead = item[1].toString().replaceAll("<font face=arial size=2 color=#000000>", "");
//        lead = lead + "</b></P>";
//        if (item.length > 2) {
//            String body2 = item[2].toString().replaceAll("<font face=arial size=2 color=#000000>", "");
//
//            view.onDisplay(title, lead + body2);
//        } else {
//            view.onDisplay(title, lead);
//        }
    }

    public void getListRelatedNews() {
        List<NewsArticleDB> list = database.newsDao().getNewsListFormStype(stype);
        List<NewsArticle> listNew = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listNew.add(new NewsArticle(list.get(i).getNewsId(), list.get(i).getNewsTitle(), list.get(i).getNewsDate(),
                    list.get(i).getNewsSizeInByte(), list.get(i).getNewsSizeInKB(), list.get(i).getNewsDate2(), list.get(i).getNewsFTitle(),
                    list.get(i).getNewsContent(), list.get(i).getNewsImg(), list.get(i).getNewStype()));
        }

        view.onDisplayRelated(listNew);
    }
}

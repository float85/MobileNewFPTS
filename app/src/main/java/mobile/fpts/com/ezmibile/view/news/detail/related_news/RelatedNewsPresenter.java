package mobile.fpts.com.ezmibile.view.news.detail.related_news;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;

/**
 * Created by TrangDTH on 2/22/2018.
 */

public class RelatedNewsPresenter implements IRelatedNews.Presenter {
    private IRelatedNews.View view;

    private List<NewsArticle> listNews;

    //Luu trang thai
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String keyShered = "Tin_Tuc";

    public RelatedNewsPresenter(IRelatedNews.View view, List<NewsArticle> newsList) {
        this.view = view;
        this.listNews = newsList;
        sharedPreferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFERENCES_SECURITIES_PUBLIC, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    @Override
    public void getData(String folder, String id) {
        List<NewsArticle> listAll = new ArrayList<>();
        listNews.clear();

        if (getList(keyShered) != null || getList(keyShered).size() > 0) {
            listAll.addAll(getList(keyShered));

        } else {
            view.dataFail();
        }

        for (int i = 0; i < listAll.size(); i++) {
            if (listAll.get(i).getNewsId().equals(id)) {
                continue;
            } else {
                if (listAll.get(i).getNewStype().equals(folder)) {
                    listNews.add(listAll.get(i));
                }
            }
        }
        view.dataTinLienQuanDone(listNews);
    }

    public List<NewsArticle> getList(String key) {
        Gson gson = new Gson();
        List<NewsArticle> list = new ArrayList<>();

        String json = get(key);
        Type type = new TypeToken<List<NewsArticle>>() {
        }.getType();
        list = gson.fromJson(json, type);

        return list;
    }

    public String get(String key) {
        return sharedPreferences.getString(key, "");
    }


}

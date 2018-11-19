package mobile.fpts.com.ezmibile.view.news.detail.related_news;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;

/**
 * Created by TrangDTH on 2/22/2018.
 */

public interface IRelatedNews {
    interface Presenter {
        void getData(String folder, String id);
    }

    interface View {

        void dataTinLienQuanDone(List<NewsArticle> newsList);

        void dataFail();

        void onLoad();

        void connectFail();

        void connectServerFail();
    }
}

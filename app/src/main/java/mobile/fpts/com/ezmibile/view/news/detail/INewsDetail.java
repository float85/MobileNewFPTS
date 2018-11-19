package mobile.fpts.com.ezmibile.view.news.detail;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.ErrorApp;

/**
 * Created by TrangDTH on 2/22/2018.
 */

public interface INewsDetail {
    void onLoading();

    void onDisplay(String title, String body, String link);

    void onDisplayRelated(List<NewsArticle> newsArticles);

    void onLoadError(ErrorApp error);

    void moveFragment(String id, String img);

    interface View {
        void dataDetailDone(String title, String body);

        void dataFail();

        void onLoad();

        void connectFail();

        void moveFragment(String id, String img);

        void connectServerFail();
    }

    interface Presenter {
        void callData(String id);
    }

}

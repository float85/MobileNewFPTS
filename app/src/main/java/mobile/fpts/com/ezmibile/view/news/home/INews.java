package mobile.fpts.com.ezmibile.view.news.home;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;

/**
 * Created by TrangDTH on 2/22/2018.
 */

public interface INews {
    interface View {
        void getDataDone(List<NewsArticle> newsList, String folder);

        void getDataFail();

        void onLoad();

        void moveFragmentView(String id, String img, String folder);
    }

    interface Presenter {
        void getData();
    }
    interface HomeNewsView {
        void getDataSuccess();

        void getDataFail();

        void getConnectServerFail();

        void getConnectNetworkFail();

        void onLoad(boolean b);
    }

}

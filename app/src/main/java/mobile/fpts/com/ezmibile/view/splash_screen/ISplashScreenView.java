package mobile.fpts.com.ezmibile.view.splash_screen;

import android.util.SparseArray;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.Market;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.model.entity.market.VnIndices;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.view.splash_screen.data.BigGroup;

public interface ISplashScreenView {

    void onLoading();

    void onDisplay(SparseArray<BigGroup> sparseArray);

    interface IBiggroupCallBack {
        /**
         * @param typeItem:   indexes, watchlist, news
         * @param typeAction: add, edit, detail, all
         */
        void onDisplayAction(int typeItem, Define.HOME_TYPE_ACTION typeAction, String marketCode);
    }

    interface IIndexesView {
        void onIndexesItemClick(int position, String marketCode);

    }

    interface IBigGroupView {
        void onDisplay(List<VnIndices> stockMarkets);

        void onError(ErrorApp error);
    }

    interface IHomePresenterview {
        void onReload();

        void onDestroy();

        //REALTIME
        // TODO:HoaDT 6/19/2018 Chuyển load phần tổng quan thị trường trong BiggroupAdapter
//        void LoadIndexes();

        void LoadWatchlist();

        // ROOM
        List<WorldIndices> LoadWorldIndex();

        List<NewsArticle> LoadNews();

        List<EventsApp> LoadEvents();

    }

}

package mobile.fpts.com.ezmibile.view.watchlist.detail.trading;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.model.entity.market.Quotes2;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeData;

public interface ITradingView {
    void onDisplay(DetailCodeData data);

    void onDisplay(Quotes2 quotes2, Define.TYPE_CHANGE_APP colorOpen,
                   Define.TYPE_CHANGE_APP colorHighest, Define.TYPE_CHANGE_APP colorLowest, double percent);

    void showChart(List<HistoryChartOtherIndex> dataD);

    void onDisplayNews(List<NewsArticle> newsArticles);

    interface INewsListener {
        void OnClick(String NewsID, String symbol);
    }
}

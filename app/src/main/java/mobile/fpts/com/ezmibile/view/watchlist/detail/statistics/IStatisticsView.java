package mobile.fpts.com.ezmibile.view.watchlist.detail.statistics;

import mobile.fpts.com.ezmibile.model.entity.market.Quotes2;
import mobile.fpts.com.ezmibile.util.Define;

public interface IStatisticsView {
    void display(StatisticData data);

    void display(Quotes2 quotes2, Define.TYPE_CHANGE_APP colorOpen,
                 Define.TYPE_CHANGE_APP colorHighest, Define.TYPE_CHANGE_APP colorLowest);


}

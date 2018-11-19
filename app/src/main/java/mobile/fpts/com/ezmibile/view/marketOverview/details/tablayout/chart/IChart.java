package mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.chart;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.util.ErrorApp;

public interface IChart {

    void onError(ErrorApp error);

    void onDisplay(List<HistoryChartOtherIndex> dataList);

    void onDisplayOneDay(List<HistoryChartOtherIndex> chartIndexData);
}

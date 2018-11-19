package mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.chart;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeChart;
import mobile.fpts.com.ezmibile.util.CheckInTime;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.util.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ChartPresenter {
    private IChart view;
    private String marketName = "";
    List<HistoryChartOtherIndex> chartIndexRealtime = new ArrayList<>();
    List<HistoryChartOtherIndex> chartIndexDataList = new ArrayList<>();
    List<List<HistoryChartOtherIndex>> chartIndexSuperList = new ArrayList<>();

    private boolean isRequesting = false;
    private boolean isRequesting1 = false;

    ChartPresenter(IChart view, String marketName) {
        this.view = view;
        this.marketName = marketName;
        LoadData_OneDay();
        LoadData_All();
    }

    /**
     * Load Data ph?n 1 ngày
     */
    private void LoadData_OneDay() {
        if (isRequesting) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            view.onError(ErrorApp.ERROR_NETWORK);
            return;
        }
        isRequesting = true;
        chartIndexRealtime = new ArrayList<>();
        ApiClientImp.getInstance().getChartRealtime(getSFromMarketNameForAll(marketName))
                .subscribeOn(Schedulers.io())
                .map(historyChartOtherIndexList -> historyChartOtherIndexList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        historyChartOtherIndexList -> {
                            Log.w("ChartPresenter", "LoadData_OneDay: success");
                            this.chartIndexRealtime = historyChartOtherIndexList;
                            if (chartIndexRealtime != null) {
                                selectTab(Define.TYPE_CHART_ONE_DAY);
                            }
                        }, throwable -> {
                            isRequesting = false;
                            Log.w("ChartPresenter", "LoadData_OneDay: throwable");
                            throwable.printStackTrace();
                            view.onError(ErrorApp.ERROR_CONNECT_SERVER);
                        }, () -> {
                            isRequesting = false;
                            Log.w("ChartPresenter", "LoadData_OneDay: action");
                        });
    }

    /**
     * Load data t?t c?
     */
    private void LoadData_All() {
        if (isRequesting1)
            return;
        if (!Utils.isNetworkAvailable()) {
            view.onError(ErrorApp.ERROR_NETWORK);
            return;
        }
        if (!CheckInTime.isInTime()) {
        }
        isRequesting1 = true;
        chartIndexDataList = new ArrayList<>();
        String s = getSFromMarketNameForAll(marketName);
        ApiClientImp.getInstance().getChartHistory(s)
                .subscribeOn(Schedulers.io())
                .map(historyChartOtherIndices -> historyChartOtherIndices)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(historyChartOtherIndices -> {
                    Log.w("ChartPresenter", "LoadData_All: success");
                    chartIndexSuperList = convert(historyChartOtherIndices);
                    isRequesting1 = false;
                }, throwable -> {
                    Log.w("ChartPresenter", "LoadData_All: ");
                    throwable.printStackTrace();
                    view.onError(ErrorApp.ERROR_CONNECT_SERVER);
                    isRequesting1 = false;
                }, () -> {
                    isRequesting1 = false;
                    Log.w("ChartPresenter", "LoadData_All: action");
                });
    }

    /**
     * @param type: 1D, 1W, 1M, 3M, 6M, 1Y, 2Y, ALL
     */
    void selectTab(int type) {
        if (type == Define.TYPE_CHART_ONE_DAY) {
            if (chartIndexRealtime == null || chartIndexRealtime.size() == 0) {
                view.onError(ErrorApp.NULL);
                return;
            } else {
                List<HistoryChartOtherIndex> list = new ArrayList<>();
                for (int i = 0; i < chartIndexRealtime.size(); i++) {
                    String[] strings = chartIndexRealtime.get(i).getCharTime().split("\\s");
                    list.add(new HistoryChartOtherIndex(chartIndexRealtime.get(i).getChartO(), chartIndexRealtime.get(i).getChartH(),
                            chartIndexRealtime.get(i).getChartL(), chartIndexRealtime.get(i).getChartC(),
                            chartIndexRealtime.get(i).getCharV(), strings[1]));
                }

                view.onDisplayOneDay(list);
                return;
            }
        } else {
            if (chartIndexSuperList == null || chartIndexSuperList.size() == 0) {

                view.onError(ErrorApp.NULL);
                return;
            }
            switch (type) {
                case Define.TYPE_CHART_ONE_WEEK:
                    if (chartIndexSuperList.size() > 0 && chartIndexSuperList.get(0) != null && chartIndexSuperList.get(0).size() > 0) {
                        view.onDisplay(chartIndexSuperList.get(0));
                    } else view.onError(ErrorApp.NULL);

                    break;
                case Define.TYPE_CHART_ONE_MONTH:
                    if (chartIndexSuperList.size() > 1 && chartIndexSuperList.get(1) != null && chartIndexSuperList.get(1).size() > 0) {
                        view.onDisplay(chartIndexSuperList.get(1));
                    } else view.onError(ErrorApp.NULL);

                    break;
                case Define.TYPE_CHART_THREE_MONTH:
                    if (chartIndexSuperList.size() > 2 && chartIndexSuperList.get(2) != null && chartIndexSuperList.get(2).size() > 0) {
                        view.onDisplay(chartIndexSuperList.get(2));
                    } else view.onError(ErrorApp.NULL);
                    break;
                case Define.TYPE_CHART_SIX_MONTH:
                    if (chartIndexSuperList.size() > 3 && chartIndexSuperList.get(3) != null && chartIndexSuperList.get(3).size() > 0) {
                        view.onDisplay(chartIndexSuperList.get(3));
                    } else view.onError(ErrorApp.NULL);
                    break;
                case Define.TYPE_CHART_ONE_YEAR:
                    if (chartIndexSuperList.size() > 4 && chartIndexSuperList.get(4) != null && chartIndexSuperList.get(4).size() > 0) {
                        view.onDisplay(chartIndexSuperList.get(4));
                    } else view.onError(ErrorApp.NULL);
                    break;
                case Define.TYPE_CHART_TWO_YEAR:
                    if (chartIndexSuperList.size() > 5 && chartIndexSuperList.get(5) != null && chartIndexSuperList.get(5).size() > 0) {
                        view.onDisplay(chartIndexSuperList.get(5));
                    } else view.onError(ErrorApp.NULL);
                    break;
                case Define.TYPE_CHART_ALL:
                    if (chartIndexSuperList.size() > 6 && chartIndexSuperList.get(6) != null && chartIndexSuperList.get(6).size() > 0) {
                        view.onDisplay(chartIndexSuperList.get(6));
                    } else view.onError(ErrorApp.NULL);
                    break;
                default:
                    if (chartIndexSuperList.size() > 0 && chartIndexSuperList.get(0) != null && chartIndexSuperList.get(0).size() > 0) {
                        view.onDisplay(chartIndexSuperList.get(0));
                    } else view.onError(ErrorApp.NULL);
                    break;
            }
        }
    }

    private String getSFromMarketNameForAll(String marketName) {

        switch (marketName.trim().toUpperCase()) {
            case "HOSE":
            case "VNINDEX":
            case "VNI":
                return "vnindex";
            case "HNX":
            case "HNXINDEX":
                return "hnxindex";
            case "UPCOM":
                return "upcomindex";
            case "VN30":
                return "vn30index";
            case "HNX30":
                return "hnx30index";
            default:
                return "";
        }
    }

    private List<List<HistoryChartOtherIndex>> convert(List<HistoryChartOtherIndex> chartIndices) {
        List<List<HistoryChartOtherIndex>> superList = new ArrayList<>();
        List<HistoryChartOtherIndex>[] tem = new List[7];

        for (int i = chartIndices.size() - 1; i > 0; i--) {
//        for (int i = 1; i < chartIndices.size(); i++) {
            if (i == chartIndices.size() - 1)
                for (int j = 0; j < tem.length; j++)
                    tem[j] = new ArrayList<>();

            if (i >= chartIndices.size() - 5) tem[0].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 5 * 4) tem[1].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 21 * 3) tem[2].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 21 * 6) tem[3].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 21 * 6 * 2) tem[4].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 21 * 6 * 2 * 2) tem[5].add(chartIndices.get(i));
            if (i >= 0) tem[6].add(chartIndices.get(i));
        }
        for (int i = 0; i < tem.length; i++) {
            superList.add(tem[i]);
        }
        return superList;
    }
}

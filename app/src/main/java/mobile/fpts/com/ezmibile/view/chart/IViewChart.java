package mobile.fpts.com.ezmibile.view.chart;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.chart.MacdData;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;

public interface IViewChart {
    void showChart(List<HistoryChartOtherIndex> yVals1);

    void showChart(ArrayList<HistoryChartOtherIndex> yVals1, List<MacdData> getMacd);

    void showChart(ArrayList<HistoryChartOtherIndex> yVals1, ArrayList<Double> sma, ArrayList<Double> sma1, double[] listRsi, String key, int p1, int p3);

    void showChart(ArrayList<Double> top, ArrayList<Double> mid, ArrayList<Double> bot, ArrayList<Float> ema, ArrayList<HistoryChartOtherIndex> yVals1, String key);

    void onload();

    void loadok();

    // TODO:HoaDT 6/25/2018 CompanyName saved to database
//    void loadNamecpn(List<String> lg);

    interface title {
        void setT(String tit);
    }
}

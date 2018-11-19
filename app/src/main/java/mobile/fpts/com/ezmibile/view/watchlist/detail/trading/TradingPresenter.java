package mobile.fpts.com.ezmibile.view.watchlist.detail.trading;


import android.graphics.Color;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.model.entity.market.Quotes2;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Utils;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeData;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TradingPresenter {
    private ITradingView iTradingView;
    private String symbol;

    boolean isRequesting = false;
    boolean isRequestingNews = false;
    boolean isRequestingPrice = false;

    List<HistoryChartOtherIndex> yVals1;

    public TradingPresenter(ITradingView view, String symbol, ArrayList<HistoryChartOtherIndex> yVals1) {
        this.iTradingView = view;
        this.symbol = symbol;
        this.yVals1 = yVals1;
        if (yVals1 == null || yVals1.isEmpty()) {
            LoadDataChartTotal();
        }
        LoadData();
        LoadNews();
        LoadPrice();
    }

    // TODO:HoaDT 7/3/2018 Load dữ liệu phần thông tin trong giờ giao dịch:
    private void LoadData() {
        if (isRequesting) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        isRequesting = true;
        ApiClientImp.getInstance().getEzsBasic(symbol)
                .subscribeOn(Schedulers.io())
                .map(list -> list)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            DetailCodeData detailCodeData;
                            if (list != null && list.size() > 0) {
                                detailCodeData = list.get(0);
                                iTradingView.onDisplay(detailCodeData);
                            }
                        }, throwable -> throwable.printStackTrace(),
                        () -> isRequesting = false);
    }

    private Define.TYPE_CHANGE_APP getColor(String ceilingPrice, String refPrice, String floorPrice, String valuePrice) {
        Define.TYPE_CHANGE_APP color = Define.TYPE_CHANGE_APP.NO_CHANGE;
        Double ceiling = 0d, ref = 0d, floor = 0d, value = 0d;
        try {
            ref = Double.parseDouble(refPrice);
        } catch (Exception e) {
            ref = 0d;
        }
        try {
            value = Double.parseDouble(valuePrice);
        } catch (Exception e) {
            value = 0d;
        }
        try {
            ceiling = Double.parseDouble(ceilingPrice);
        } catch (Exception e) {
            ceiling = 0d;
        }
        try {
            floor = Double.parseDouble(floorPrice);
        } catch (Exception e) {
            floor = 0d;
        }

        if (value == ceiling) return Define.TYPE_CHANGE_APP.UP_CEILING;
        if (value == ref) return Define.TYPE_CHANGE_APP.NO_CHANGE;
        if (value > ref) return Define.TYPE_CHANGE_APP.UP;
        if (value == floor) return Define.TYPE_CHANGE_APP.DOWN_FLOOR;
        if (value < ref) return Define.TYPE_CHANGE_APP.DOWN;
        return color;
    }

    public void LoadDataChartRealtime() {
        if (!Utils.isNetworkAvailable()) {
            return;
        }

        ApiClientImp.getInstance().getChartRealtime(symbol)
                .subscribeOn(Schedulers.io())
                .map(historyChartOtherIndexList -> {
                    if (historyChartOtherIndexList == null || historyChartOtherIndexList.size() == 0) {
                        return new ArrayList<HistoryChartOtherIndex>();
                    }
                    return historyChartOtherIndexList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(historyChartOtherIndexList -> {
                    Log.w("TradingPresenter", "LoadDataChartRealtime: " + historyChartOtherIndexList.size());
                    iTradingView.showChart(getHistoryChartOtherIndexList(historyChartOtherIndexList));

                }, throwable -> {
                    Log.w("TradingPresenter", "LoadDataChartRealtime: throwable");
                    throwable.printStackTrace();
                }, () -> {

                });
    }

    private void LoadDataChartTotal() {
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        ApiClientImp.getInstance().getChartHistory(symbol)
                .subscribeOn(Schedulers.io())
                .map(historyChartOtherIndexList -> {
                    if (historyChartOtherIndexList == null || historyChartOtherIndexList.size() == 0) {
                        return new ArrayList<HistoryChartOtherIndex>();
                    }

                    return historyChartOtherIndexList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(historyChartOtherIndexList -> {
                            int len = historyChartOtherIndexList.size();
                            if (len > 1) {
                                HistoryChartOtherIndex tem = historyChartOtherIndexList.get(len - 1);
                                Log.w("TradingPresenter", "LoadDataChartTotal: " + historyChartOtherIndexList.size());
                                // TODO:HoaDT 7/4/2018 remove giờ ở chỉ số cuối cùng
                                historyChartOtherIndexList.get(len - 1).setCharTime(tem.getCharTime().length() >= 9 ?
                                        tem.getCharTime().toString().substring(0, 9) : tem.getCharTime());
                                this.yVals1 = historyChartOtherIndexList;
                            }
                        }, throwable -> {
                            Log.w("TradingPresenter", "LoadDataChartTotal: throwable");
                            throwable.printStackTrace();
                        },
                        () -> {

                        });
    }

    public void loadDataChartFilter(int sizedate) {
        Log.w("TradingPresenter", "loadDataChartFilter: ");
        if (!yVals1.isEmpty()) {
            if (sizedate < 0)
                iTradingView.showChart(yVals1);

            else {
                ArrayList<HistoryChartOtherIndex> yVals11 = new ArrayList<>();
                int numsize = 0;
                if (sizedate > 0 && sizedate < yVals1.size()) {
                    numsize = yVals1.size() - sizedate;
                }
                for (int i = numsize; i < yVals1.size(); i++) {
                    HistoryChartOtherIndex chartix = new HistoryChartOtherIndex();
                    chartix.setChartC(yVals1.get(i).getChartC());
                    chartix.setChartH(yVals1.get(i).getChartH());
                    chartix.setCharTime(yVals1.get(i).getCharTime());
                    chartix.setChartL(yVals1.get(i).getChartL());
                    chartix.setChartO(yVals1.get(i).getChartO());
                    chartix.setCharV(yVals1.get(i).getCharV());
                    yVals11.add(chartix);
                }
                iTradingView.showChart(yVals11);
            }
        }
    }

    private void LoadNews() {
        if (isRequestingNews) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        isRequestingNews = true;
        ApiClientImp.getInstance().getListNews_company(symbol, "1", "8")
                .subscribeOn(Schedulers.io())
                .map(newsArticles -> newsArticles)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsArticles -> {
                    Log.w("TradingPresenter", "LoadNews: loaddata done");
                    List<NewsArticle> newsArticleList = new ArrayList<>();
                    for (int i = 0; i < 5 && newsArticles.size() >= 5; i++) {
                        newsArticleList.add(newsArticles.get(i));
                    }
                    iTradingView.onDisplayNews(newsArticleList);

                }, throwable -> {
                    Log.w("TradingPresenter", "LoadNews: exception");
                    throwable.printStackTrace();
                }, () -> {
                    isRequestingNews = false;
                });
    }

    // TODO:HoaDT 7/2/2018 https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=quotes2&symbol=vnm
    private void LoadPrice() {
        if (isRequestingPrice) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        ApiClientImp.getInstance().getListQuotes2(symbol)
                .subscribeOn(Schedulers.io())
                .map(quotes2List -> quotes2List)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quotes2List -> {
                    Log.w("TradingPresenter", "LoadPrice: " + quotes2List.size());
                    Quotes2 quotes2 = quotes2List.get(0);
                    //String ceilingPrice, String refPrice, String floorPrice, String valuePrice
                    Define.TYPE_CHANGE_APP colorOpen = getColor(quotes2.getCeiling(), quotes2.getRefPrice(),
                            quotes2.getFloor(), quotes2.getOpenPrice());
                    Define.TYPE_CHANGE_APP colorHighest = getColor(quotes2.getCeiling(), quotes2.getRefPrice(),
                            quotes2.getFloor(), quotes2.getHighestPrice());
                    Define.TYPE_CHANGE_APP colorLowest = getColor(quotes2.getCeiling(), quotes2.getRefPrice(),
                            quotes2.getFloor(), quotes2.getLowestPrice());

                    if (quotes2List != null || quotes2List.size() > 0)
                        iTradingView.onDisplay(quotes2, colorOpen, colorHighest, colorLowest, getPercent(quotes2));
                }, throwable -> {
                    Log.w("TradingPresenter", "LoadPrice: throwable");
                    throwable.printStackTrace();
                }, () -> {
                    isRequestingPrice = false;
                });
    }

    private double getPercent(Quotes2 quotes2) {
        double BuyPrice3 = Double.parseDouble(quotes2.getBuyPrice3() != null && quotes2.getBuyPrice3() != "" ?
                quotes2.getBuyPrice3().replace(",", ".") : "0");
        double BuyQtty3 = Double.parseDouble(quotes2.getBuyQtty3() != null && quotes2.getBuyQtty3() != "" ?
                quotes2.getBuyQtty3().replace(",", ".") : "0");
        double BuyPrice2 = Double.parseDouble(quotes2.getBuyPrice2() != null && quotes2.getBuyPrice2() != "" ?
                quotes2.getBuyPrice2().replace(",", ".") : "0");
        double BuyQtty2 = Double.parseDouble(quotes2.getBuyQtty2() != null && quotes2.getBuyQtty2() != "" ?
                quotes2.getBuyQtty2().replace(",", ".") : "0");
        double BuyPrice1 = Double.parseDouble(quotes2.getBuyPrice1() != null && quotes2.getBuyPrice1() != "" ?
                quotes2.getBuyPrice1().replace(",", ".") : "0");
        double BuyQtty1 = Double.parseDouble(quotes2.getBuyQtty1() != null && quotes2.getBuyQtty1() != "" ?
                quotes2.getBuyQtty1().replace(",", ".") : "0");
        double SellPrice1 = Double.parseDouble(quotes2.getSellPrice1() != null && quotes2.getSellPrice1() != "" ?
                quotes2.getSellPrice1().replace(",", ".") : "0");
        double SellQtty1 = Double.parseDouble(quotes2.getSellQtty1() != null && quotes2.getSellQtty1() != "" ?
                quotes2.getSellQtty1().replace(",", ".") : "0");
        double SellPrice2 = Double.parseDouble(quotes2.getSellPrice2() != null && quotes2.getSellPrice2() != "" ?
                quotes2.getSellPrice2().replace(",", ".") : "0");
        double SellQtty2 = Double.parseDouble(quotes2.getSellQtty2() != null && quotes2.getSellQtty2() != "" ?
                quotes2.getSellQtty2().replace(",", ".") : "0");
        double SellPrice3 = Double.parseDouble(quotes2.getSellPrice3() != null && quotes2.getSellPrice3() != "" ?
                quotes2.getSellPrice3().replace(",", ".") : "0");
        double SellQtty3 = Double.parseDouble(quotes2.getSellQtty3() != null && quotes2.getSellQtty3() != "" ?
                quotes2.getSellQtty3().replace(",", ".") : "0");
        double sumBuy = BuyPrice1 + BuyPrice2 + BuyPrice3 + BuyQtty1 + BuyQtty2 + BuyQtty3;
        double sumSell = SellPrice1 + SellPrice2 + SellPrice3 + SellQtty1 + SellQtty2 + SellQtty3;
        DecimalFormat format = new DecimalFormat("0.00");
        if (sumSell + sumBuy > 0)
            return Double.parseDouble(format.format(sumBuy * 100 / (sumSell + sumBuy)));

        return 50;
    }

    // TODO:HoaDT 7/4/2018 remove ngày trong xử lý:"Date":"2018-07-04 09:16"  -> "Date":"09:16"
    private List<HistoryChartOtherIndex> getHistoryChartOtherIndexList(List<HistoryChartOtherIndex> list) {
        List<HistoryChartOtherIndex> data = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HistoryChartOtherIndex history = list.get(i);
            if (history.getCharTime() != null && history.getCharTime().length() >= 11)
                history.setCharTime(history.getCharTime().toString().substring(11, history.getCharTime().toString().length()));


            data.add(history);
        }

        return data;
    }
}

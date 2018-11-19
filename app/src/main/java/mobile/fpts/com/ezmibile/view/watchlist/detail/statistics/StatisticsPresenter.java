package mobile.fpts.com.ezmibile.view.watchlist.detail.statistics;


import android.util.Log;

import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class StatisticsPresenter {
    private String symbol;

    boolean isRequesting = false;
    private IStatisticsView view;

    public StatisticsPresenter(String symbol, IStatisticsView view) {
        this.symbol = symbol;
        this.view = view;

        LoadData();
    }

    private void LoadData() {
        if (isRequesting) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        isRequesting = true;
        // TODO:HoaDT 7/3/2018 sửa lại, lấy dữ liệu giống phần trading
//        ApiClientImp.getInstance().getListQuotes2(symbol)
//                .subscribeOn(Schedulers.io())
//                .map(s -> s)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(quotes2List -> {
//                    Quotes2 quotes2 = quotes2List.get(0);
//                    //String ceilingPrice, String refPrice, String floorPrice, String valuePrice
//                    Define.TYPE_CHANGE_APP colorOpen = getColor(quotes2.getCeiling(), quotes2.getRefPrice(),
//                            quotes2.getFloor(), quotes2.getOpenPrice());
//                    Define.TYPE_CHANGE_APP colorHighest = getColor(quotes2.getCeiling(), quotes2.getRefPrice(),
//                            quotes2.getFloor(), quotes2.getHighestPrice());
//                    Define.TYPE_CHANGE_APP colorLowest = getColor(quotes2.getCeiling(), quotes2.getRefPrice(),
//                            quotes2.getFloor(), quotes2.getLowestPrice());
//
//                    if (quotes2List != null && quotes2List.size() > 0) {
//                        view.display(quotes2List.get(0), colorOpen, colorHighest, colorLowest);
//                    }
//                }, throwable -> {
//                    Log.w("StatisticsPresenter", "LoadData: throwable");
//                    throwable.printStackTrace();
//                }, () -> {
//                    isRequesting = false;
//                });
        // TODO:HoaDT 7/5/2018
        ApiClientImp.getInstance().getStatistic(symbol)
                .subscribeOn(Schedulers.io())
                .map(statisticData -> statisticData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(statisticData -> {
                            if (statisticData != null && statisticData.size() > 0){
                                StatisticData data = statisticData.get(0);
//                                DecimalFormat decimalFormat   = new DecimalFormat("0.00");
//                                data.setbASICPRICE(decimalFormat.format(data.getbASICPRICE().replace(",", ".")));
//                                data.setcEILINGPRICE(decimalFormat.format(data.getcEILINGPRICE().replace(",", ".")));
//                                data.setfLOORPRICE(decimalFormat.format(data.getfLOORPRICE().replace(",", ".")));
//                                data.setoPENPRICE(decimalFormat.format(data.getoPENPRICE().replace(",", ".")));
//                                data.sethIGHESTPRICE(decimalFormat.format(data.gethIGHESTPRICE().replace(",", ".")));
//                                data.setlOWESTPRICE(decimalFormat.format(data.getlOWESTPRICE().replace(",", ".")));


                                view.display(statisticData.get(0));
                            }
                        }, throwable -> {
                            Log.w("StatisticsPresenter", "LoadData: throwable");
                            throwable.printStackTrace();
                        },
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


}

package mobile.fpts.com.ezmibile.view.marketOverview.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.api.api_detail_market.API;
import mobile.fpts.com.ezmibile.model.api.api_detail_market.APIClient;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX30;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeItem_VNI;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeUpcom;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeVNI;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeVNI30;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DetailMarketPresenter {
    String baseApi;
    IDetailsHome view;
    CompositeSubscription subscription = new CompositeSubscription();
    String sLink;

    public DetailMarketPresenter(IDetailsHome view, String sLink) {
        this.sLink = sLink;
//        this.baseApi = "http://gateway.fpts.com.vn/g5g/fpts/?s=" + sLink;
        this.view = view;
    }

    public void loadDataFormServer() {
        if (isConnected()) {
            if (sLink.equalsIgnoreCase("realtime_index_ho")) {
                subscription.add(ApiClientImp.getInstance().getDetailHomeVNI()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(detailHomes -> {
                            view.loadDataVNI(detailHomes.get(0));
                            if (detailHomes.size() >= 1) {
                                for (int i = 1; i < detailHomes.size(); i++) {
                                    String sPhien = "Phiên " + i + ": ";
                                    DetailHomeVNI detailHomeVNI1 = detailHomes.get(i);
                                    DetailHomeItem_VNI detailHomeItemVNI = new DetailHomeItem_VNI(detailHomeVNI1.getMARKETINDEX(), detailHomeVNI1.getStrArrow0(),
                                            detailHomeVNI1.getCHGINDEX(), detailHomeVNI1.getPCTINDEX(), detailHomeVNI1.getTOTALQTTY(),
                                            detailHomeVNI1.getTOTALVALUE(), detailHomeVNI1.getTOTALTRADE());

                                    view.loadDataItemVNI(detailHomeItemVNI, sPhien);
                                }
                            }

                            return detailHomes;
                        })
                        .subscribe(new Subscriber<List<DetailHomeVNI>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(List<DetailHomeVNI> listEvents) {
                            }
                        })
                );
            }

            if (sLink.equalsIgnoreCase("realtime_index_ha")) {
                subscription.add(ApiClientImp.getInstance().getDetailHomeHNX()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(detailHomeHNXES -> {
                            view.loadDataHNX(detailHomeHNXES.get(0));

                            return detailHomeHNXES;
                        })
                        .subscribe(new Subscriber<List<DetailHomeHNX>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(List<DetailHomeHNX> listEvents) {
                            }
                        })
                );
            }

            if (sLink.equalsIgnoreCase("realtime_index_up")) {
                subscription.add(ApiClientImp.getInstance().getDetailHomeUpcom()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(detailHomeUpcoms -> {
                            view.loadDataUPCOM(detailHomeUpcoms.get(0));

                            return detailHomeUpcoms;
                        })
                        .subscribe(new Subscriber<List<DetailHomeUpcom>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(List<DetailHomeUpcom> listEvents) {
                            }
                        })
                );
            }

            if (sLink.equalsIgnoreCase("realtime_index_vni30")) {
                subscription.add(ApiClientImp.getInstance().getDetailHomeVNI30()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(detailHomeVNI30s -> {
                            view.loadDataVNI30(detailHomeVNI30s.get(0));

                            return detailHomeVNI30s;
                        })
                        .subscribe(new Subscriber<List<DetailHomeVNI30>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(List<DetailHomeVNI30> listEvents) {
                            }
                        })
                );
            }

            if (sLink.equalsIgnoreCase("realtime_index_hnx30")) {
                subscription.add(ApiClientImp.getInstance().getDetailHomeHNX30()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(detailHomeHNX30s -> {
                            view.loadDataHNX_30(detailHomeHNX30s.get(0));

                            return detailHomeHNX30s;
                        })
                        .subscribe(new Subscriber<List<DetailHomeHNX30>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(List<DetailHomeHNX30> listEvents) {
                            }
                        })
                );
            }
        }
    }

    public ArrayList<HistoryChartOtherIndex> loadDataChart(String idURL) {
        ArrayList<HistoryChartOtherIndex> listY = new ArrayList<>();

        if (Utils.isNetworkAvailable()) {
            APIClient apiClient = new APIClient();
            Retrofit retrofit = apiClient.retrofit("https://eztrade3.fpts.com.vn/GateWAYDEV/history/?s=" + idURL);
            API service = retrofit.create(API.class);
            Call<String> stringCall = service.getAnswers();
            stringCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
//                getSaveData(response.body());
                    String[] s1 = response.body().split("[]]");
                    for (int i = 0; i < s1.length; i++) {
                        String[] s2 = s1[i].split("[,]");
                        if (s2.length > 0)
                            if (i == 0) {
                                HistoryChartOtherIndex historyChartOtherIndex = new HistoryChartOtherIndex();
                                //String chartO, String chartH, String chartL, String chartC, String charV, String charTime
                                try {
                                    historyChartOtherIndex.setChartO(s2[0]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setChartO("0");
                                }
                                try {
                                    historyChartOtherIndex.setChartH(s2[1]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setChartH("0");
                                }
                                try {
                                    historyChartOtherIndex.setChartL(s2[2]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setChartL("0");
                                }
                                try {
                                    historyChartOtherIndex.setChartC(s2[3]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setChartC("0");
                                }
                                try {
                                    historyChartOtherIndex.setCharV(s2[4]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setCharV("0");
                                }
                                try {
                                    historyChartOtherIndex.setCharTime(s2[5]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setCharTime("");
                                }
                                listY.add(historyChartOtherIndex);
                            } else {
                                HistoryChartOtherIndex historyChartOtherIndex = new HistoryChartOtherIndex();
                                //String chartO, String chartH, String chartL, String chartC, String charV, String charTime
                                try {
                                    historyChartOtherIndex.setChartO(s2[1]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setChartO("0");
                                }
                                try {
                                    historyChartOtherIndex.setChartH(s2[2]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setChartH("0");
                                }
                                try {
                                    historyChartOtherIndex.setChartL(s2[3]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setChartL("0");
                                }
                                try {
                                    historyChartOtherIndex.setChartC(s2[4]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setChartC("0");
                                }
                                try {
                                    historyChartOtherIndex.setCharV(s2[5]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setCharV("0");
                                }
                                try {
                                    historyChartOtherIndex.setCharTime(s2[6]);
                                } catch (Exception e) {
                                    historyChartOtherIndex.setCharTime("");
                                }
                                listY.add(historyChartOtherIndex);
                            }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.w("DetailsHomePresenter", "onFailure: throwable");
                    t.printStackTrace();
                }
            });
        }

        Log.w("DetailsHomePresenter", "loadDataChart: " + listY.size());
        return listY;
    }

    public boolean getBooleanModeTheme() {
        SharedPreferences pre = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, App.getInstance().MODE_PRIVATE);
        boolean b = pre.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

        return b;
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}

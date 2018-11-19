package mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.top_losers;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.api.api_detail_market.API;
import mobile.fpts.com.ezmibile.model.api.api_detail_market.APIClient;
import mobile.fpts.com.ezmibile.model.entity.detail_home.TopRealtime;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.DataTopGainers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class TopLosersPresenter {
    ITopLosers view;
    String intURl;
    CompositeSubscription subscription = new CompositeSubscription();

    public TopLosersPresenter(ITopLosers view, int intURL) {
        this.intURl = String.valueOf(intURL);
//        this.baseApi = "http://gateway.fpts.com.vn/g5g/fpts/?s=top_realtime&c=" + intURL + "&type=2";
        this.view = view;
    }

    public void loadDataformServer() {
        subscription.add(ApiClientImp.getInstance().getTopRealtime(intURl, "2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<TopRealtime>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<TopRealtime> listEvents) {
                        if (listEvents != null || listEvents.size() > 0) {
                            ArrayList<DataTopGainers> list = new ArrayList<>();
                            for (int i = 0; i < listEvents.size(); i++) {
                                view.loadData(listEvents.get(i));
                            }
                        }
                    }
                })
        );
    }

    public boolean getBooleanModeTheme() {
        SharedPreferences pre = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, App.getInstance().MODE_PRIVATE);
        boolean b = pre.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

        return b;
    }

    public int setColorTextView(TopRealtime dataTopGainers, String values) {
        int color_no_change;

        if (getBooleanModeTheme()) {
            color_no_change = R.color.colorFont;
        } else {
            color_no_change = R.color.colorBackground;
        }

        double val = 0;
        try {
            val = Double.parseDouble(values);
        } catch (Exception e) {
            val = 0;
        }
        try {
            double ref, ceiling, floor;
            try {
                ref = Double.parseDouble(dataTopGainers.getSRefercence());
            } catch (Exception e) {
                ref = 0;
            }
            try {
                ceiling = Double.parseDouble(dataTopGainers.getSCeiling());
            } catch (Exception e) {
                ceiling = 0;
            }
            try {
                floor = Double.parseDouble(dataTopGainers.getSFloor());
            } catch (Exception e) {
                floor = 0;
            }
            if (val >= ceiling) {
                return R.color.purple;
            } else if (val == ref) {
                return R.color.orange;
            } else if (val <= floor) {
                return R.color.blue;
            } else if (val < ref && val > floor) {
                return R.color.red;
            } else if (val > ref && val < ceiling) {
                return R.color.green;
            }
        } catch (Exception e) {
            return color_no_change;
        }

        return color_no_change;
    }
}

package mobile.fpts.com.ezmibile.view.watchlist.detail.financeOverview;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.util.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FIT-thuctap22 on 2/7/2018.
 */

public class FinanceOverviewPresenter {
    IFinanceOverviewView v;
    private String symbol;
    private boolean isRequesting = false;

    public FinanceOverviewPresenter(IFinanceOverviewView v, String symbol) {
        this.v = v;
        this.symbol = symbol;
        LoadDataEzsReport();
    }

    private void LoadDataEzsReport() {
        if (isRequesting) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            return;
        }
        isRequesting = true;
        ApiClientImp.getInstance().getListEzsReportData(symbol)
                .subscribeOn(Schedulers.io())
                .map(ezsReportDataList -> {

                    List<EzsReportData> list = new ArrayList<>();
                    for (int i = 0; i < ezsReportDataList.size(); i++) {
                        if (ezsReportDataList.get(i).getCpnyID() != null && ezsReportDataList.get(i).getCpnyID() != "")
                            list.add(ezsReportDataList.get(i));
                    }
                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.w("FinanceOvPresenter", "LoadDataEzsReport: " + data.size());
                    v.displayEzsReport(data);
                }, throwable -> {
                    Log.w("FinanceOvPresenter", "LoadDataEzsReport: throwable");
                    throwable.printStackTrace();
                }, () -> {
                    isRequesting = false;
                });
    }
}

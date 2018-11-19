package mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures;

import android.util.Log;

import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.util.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by FIT-thuctap22 on 3/23/2018.
 */

public class FinancialFiguresPresenter {
    private IFinancialFiguresView view;
    private Boolean isRequesting = false;

    private String symbol;

    public FinancialFiguresPresenter(IFinancialFiguresView view, String symbol) {
        this.view = view;
        this.symbol = symbol;
        LoadData();
    }

    public void LoadData() {
        if (isRequesting) {
            return;
        }
        if (!Utils.isNetworkAvailable()) {
            return;
        }

        isRequesting = true;
        ApiClientImp.getInstance().getListEzsFinanceData(symbol)
                .subscribeOn(Schedulers.io())
                .map(ezsFinanceDataList -> ezsFinanceDataList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ezsReportDataList -> {
                            Log.w("FinancialFPresenter", "LoadDatachitieu: " + ezsReportDataList.size());
                            view.display(ezsReportDataList);
                        },
                        throwable -> {
                            Log.w("FinancialFigPresenter", "LoadData: throwable");
                            throwable.printStackTrace();
                        },
                        () -> isRequesting = false);
    }
}

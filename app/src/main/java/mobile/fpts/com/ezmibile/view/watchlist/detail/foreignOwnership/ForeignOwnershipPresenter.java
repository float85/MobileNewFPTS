package mobile.fpts.com.ezmibile.view.watchlist.detail.foreignOwnership;


import android.util.Log;

import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.util.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ForeignOwnershipPresenter {

    IForeignOwnershipView view;
    private String symbol;
    private boolean isRequesting = false;

    public ForeignOwnershipPresenter(IForeignOwnershipView view, String symbol) {
        this.view = view;
        this.symbol = symbol;

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
        // TODO:HoaDT 6/21/2018  
        ApiClientImp.getInstance().getEzsBasic(symbol)
                .subscribeOn(Schedulers.io())
                .map(detailCodeData -> detailCodeData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detailCodeData -> {
                            if (detailCodeData != null && detailCodeData.size() > 0)
                                view.display(detailCodeData.get(0));
                        },
                        throwable -> {
                            Log.w("ForeignOwPresenter", "LoadData: throwable");
                            throwable.printStackTrace();
                        },
                        () -> isRequesting = false);
    }
}

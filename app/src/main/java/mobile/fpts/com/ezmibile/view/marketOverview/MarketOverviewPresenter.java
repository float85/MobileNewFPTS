package mobile.fpts.com.ezmibile.view.marketOverview;

import android.os.Handler;

/**
 * Created by HoaDT  on 4/17/2018.
 */
public class MarketOverviewPresenter {

    IMarketOverviewView view;

    public MarketOverviewPresenter(IMarketOverviewView view) {
        this.view = view;

        view.onLoading();
        new Handler().postDelayed(() -> view.onDisplay(), 100);
    }
}

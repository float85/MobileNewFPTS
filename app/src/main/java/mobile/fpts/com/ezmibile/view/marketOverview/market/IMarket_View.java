package mobile.fpts.com.ezmibile.view.marketOverview.market;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.market.VnIndices;
import mobile.fpts.com.ezmibile.util.ErrorApp;


/**
 * Created by HoaDT  on 4/17/2018.
 */

public interface IMarket_View {
    void onLoading();

    // TODO:HoaDT 6/19/2018 sửa Market -> VnIndices theo link lấy json
    void onDisplay(List<VnIndices> marketList);

    void onDisplayReplaceFragment(String marketCode);

    void onDisplayError(ErrorApp error);

    interface IOnclickListener {
        void onItemClick(String marketName);

        void onItemClick(String marketName, boolean isChecked);
    }
}

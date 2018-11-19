package mobile.fpts.com.ezmibile.view.watchlist.sort;

import android.content.Context;
import android.content.SharedPreferences;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.util.Define;

public class SortCodePresenter {
    private ISortCodeView view;

    public SortCodePresenter(ISortCodeView view) {
        this.view = view;
        getCodeList();
    }

    private void getCodeList() {
        String[] list = new String[0];
        SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        String str = preferences.getString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTES, "");
        if (str != null && str != "") {
            list = str.split(",");
        }
        if (list == null || list.length == 0) {
            list = ("FPT,FTS,GAS,VNM,STB,BID,HPG,VIC,VCB,NVL,DPM,PLX,MBB,SBT,HSG,CII,NT2,REE," +
                    "MWG,KDC,SAB,NVL,PLX,DPM,HPG,SBT,SAB,GMD,KDC,ACB,CEO,HUT,VCG,KVC,NSH,MST,MST,APS,PHC").split(",");
        }

        if (view != null) {
            view.onDisplay(list);
        }
    }
}

package mobile.fpts.com.ezmibile.util;

import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HoaDT  on 4/17/2018.
 */
public class CheckInTime {
    private static boolean check = false;

    //http://10.26.2.46/GateWAY/fpts/?s=CheckDateTime
    public static boolean isInTime() {
        if (!Utils.isNetworkAvailable()) {
            return check;
        }
        ApiClientImp.getInstance().checkDateTime()
                .subscribeOn(Schedulers.io())
                .map(checkDateTimeData -> checkDateTimeData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(checkDateTimeData -> {
                            if (checkDateTimeData.getIsWorkingDay().equalsIgnoreCase("1")
                                    && checkDateTimeData.getIsWorkingTime().equalsIgnoreCase("1"))
                                check = true;
                        }, throwable -> throwable.printStackTrace(),
                        () -> {
                        });

        return check;
    }
}


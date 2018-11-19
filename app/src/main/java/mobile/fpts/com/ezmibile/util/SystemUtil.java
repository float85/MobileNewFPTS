package mobile.fpts.com.ezmibile.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;


public class SystemUtil {
    public static String mCurVersionName;
    public static int mCurVersionCode;
    // private static long mInstalledDate = 0;
//    private static final String PACKAGENAME = "mobile.fpts.com.ezmibile";

    public static boolean getCurVersion(Context context) {
        int i = 0;
        List<PackageInfo> installedPackagesList = context.getPackageManager()
                .getInstalledPackages(PackageManager.GET_META_DATA);
        for (PackageInfo pi : installedPackagesList) {
            i = i + 1;
            if (pi.packageName.compareTo(Define.PACKAGENAME) == 0) {
                mCurVersionName = pi.versionName;
//                mCurVersionName = "0.9";
                mCurVersionCode = pi.versionCode;
//                System.out.println("mCurVersionName:" + mCurVersionCode);
                return true;
            }
        }
        return false;
    }
}

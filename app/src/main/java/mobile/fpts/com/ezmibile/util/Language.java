package mobile.fpts.com.ezmibile.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

import mobile.fpts.com.ezmibile.App;


public class Language {
    public static Define.LANGUAGE_APP getLanguage() {
        SharedPreferences preferences = App.getInstance()
                .getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        String language = preferences.getString(Define.SHARED_PREFRENCES_LANGUAGE, "en");

        if (language.equalsIgnoreCase("en"))
            return Define.LANGUAGE_APP.LANGUAGE_EN;


        return Define.LANGUAGE_APP.LANGUAGE_VI;
    }

    public static void setLanguage() {
        String lan = "";
        if (getLanguage() == Define.LANGUAGE_APP.LANGUAGE_EN)
            lan = "vi";
        else lan = "en";

        SharedPreferences.Editor editor = App.getInstance()
                .getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE).edit();

        editor.putString(Define.SHARED_PREFRENCES_LANGUAGE, lan);
        editor.apply();
        editor.commit();

        try {
            Locale myLocale = new Locale(lan);
            Resources res = App.getInstance().getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

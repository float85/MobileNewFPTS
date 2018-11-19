package mobile.fpts.com.ezmibile;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import mobile.fpts.com.ezmibile.view.crash_screen.CrashActivity;


public class App extends MultiDexApplication {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private static int position;

    public static void setInstance(App instance) {
        App.instance = instance;
    }

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        App.position = position;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
        configCrash();
    }

    private void configCrash() {
        CaocConfig.Builder.create()
                .errorActivity(CrashActivity.class)
                .showErrorDetails(true)
                .showRestartButton(true)
                .trackActivities(true)
                .minTimeBetweenCrashesMs(3000)
                .apply();
    }
}

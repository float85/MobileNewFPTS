package mobile.fpts.com.ezmibile.util.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class FragmentApp extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreencall();
    }

    public void FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = getActivity().getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getActivity().getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static void hideBar(Activity context) {
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            context.getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = context.getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }
}

package mobile.fpts.com.ezmibile.util.custormView;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.Spinner;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;

/**
 * Created by HoaDT on 3/27/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class SpinnerFont extends Spinner {
    private Typeface typeface;

    public SpinnerFont(Context context) {
        super(context);
        init();
    }

    public SpinnerFont(Context context, int mode) {
        super(context, mode);
        init();
    }

    public SpinnerFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpinnerFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SpinnerFont(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        init();
    }

    private void init() {
        if (typeface == null)
            typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans);
    }

}

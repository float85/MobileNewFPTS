package mobile.fpts.com.ezmibile.util.custormView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;

/**
 * Created by dinht on 2/28/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
@SuppressLint("AppCompatCustomView")
public class TextViewFont extends TextView {
    Typeface typeface;

    public TextViewFont(Context context) {
        super(context);
        initView();
    }

    public TextViewFont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TextViewFont(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        try {
            if (typeface == null)
//                tf = Typeface.createFromAsset(getContext().getAssets(), "font/FreeSans.ttf");
                typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans);
            setTypeface(typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

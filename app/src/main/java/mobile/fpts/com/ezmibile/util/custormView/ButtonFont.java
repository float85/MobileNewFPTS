package mobile.fpts.com.ezmibile.util.custormView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.Button;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;

/**
 * Created by dinht on 2/28/2018.
 */
@SuppressLint("AppCompatCustomView")
public class ButtonFont extends Button {
    Typeface typeface;

    public ButtonFont(Context context) {
        super(context);
        initView();
    }

    public ButtonFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ButtonFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @SuppressLint("NewApi")
    public ButtonFont(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {
        try {
            if (typeface == null)
                typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans);
            setTypeface(typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package mobile.fpts.com.ezmibile.util.custormView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.EditText;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;

/**
 * Created by dinht on 2/28/2018.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
@SuppressLint("AppCompatCustomView")
public class EditTextFont extends EditText {
    Typeface typeface;

    public EditTextFont(Context context) {
        super(context);
        initView();
    }

    public EditTextFont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public EditTextFont(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

package mobile.fpts.com.ezmibile.util.custormView;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;

/**
 * Created by dinht on 2/28/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class TabLayoutFont extends TabLayout {
    private Typeface typeface;

    public TabLayoutFont(Context context) {
        super(context);
        init();
    }

    public TabLayoutFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabLayoutFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (typeface == null)
            typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans_bold);
//        typeface = Typeface.createFromAsset(getContext().getAssets(), "font/FreeSansBold.ttf");
    }

    @Override
    public void addTab(Tab tab, int position, boolean setSelected) {
        super.addTab(tab, position, setSelected);
        ViewGroup mainView = (ViewGroup) getChildAt(0);
        ViewGroup tabView = (ViewGroup) mainView.getChildAt(tab.getPosition());
        int tabChildCount = tabView.getChildCount();
        for (int i = 0; i < tabChildCount; i++) {
            View tabViewChild = tabView.getChildAt(i);
            if (tabViewChild instanceof TextView) {
                ((TextView) tabViewChild).setTypeface(typeface, Typeface.NORMAL);
            }
        }
    }
}

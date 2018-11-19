package mobile.fpts.com.ezmibile.util.custormView.table;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobile.fpts.com.ezmibile.App;

public class TableView extends LinearLayout {
    private int mHeight;
    private int mWidth;
    private int mTextSize;
    private Typeface mTypeface;
    private int mColor;

    public TableView(Context context) {
        super(context);
    }


    private LinearLayout createLine(int length) {
        LinearLayout linearLayout = new LinearLayout(App.getInstance());
        linearLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight));

        for (int i = 0; i < length; i++) {
            linearLayout.addView(initCell(mWidth, mHeight));
        }

        return linearLayout;
    }

    private LinearLayout initCell(int width, int height) {
        LinearLayout linearLayout = new LinearLayout(App.getInstance());
        linearLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        linearLayout.addView(initTextView(width, height));

        return linearLayout;
    }

    private TextView initTextView(int width, int height) {
        TextView textView = new TextView(App.getInstance());

        textView.setHeight(height);
        textView.setWidth(width);
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView.setGravity(4);
        textView.setTypeface(mTypeface);

        return textView;
    }
}

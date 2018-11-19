package mobile.fpts.com.ezmibile.view.watchlist.search;

import android.text.InputFilter;

import java.util.ArrayList;
import java.util.List;

public interface ISearchView {
    void loadDataOfAutoComleteTextView(ArrayList<CustomerAutoComleteTextView> arrayList);

    void validateEdittext(InputFilter[] inputFilter);

    void onDisplay(List<String> list);

    interface IListener{
        void  onClick(String symbol);
    }
}

package mobile.fpts.com.ezmibile.view.watchlist.sort;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemWatchlistSortCodeBinding;

/**
 * Created by HoaDT  on 4/17/2018.
 */
public class SortCodeAdapter extends ArrayAdapter<String> {
    private String[] codeList;

    ItemWatchlistSortCodeBinding binding;

    public SortCodeAdapter(String[]  codeList) {
        super(App.getInstance(), R.layout.item_watchlist_sort_code, codeList);
        this.codeList = codeList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_watchlist_sort_code, parent, false);

        binding.textviewCode.setText(codeList[position]);
        return binding.getRoot();
    }

}

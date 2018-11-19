package mobile.fpts.com.ezmibile.view.watchlist;

import com.bin.david.form.data.Column;

import java.util.List;

import mobile.fpts.com.ezmibile.view.watchlist.model.Quote;

/**
 * Created by FIT-thuctap22 on 3/22/2018.
 */

public interface IWatchlistView {
    void onLoading();

//    void loadSuccess();

    void onDisplay(List<Column> columnList, List<Quote> testData);
}
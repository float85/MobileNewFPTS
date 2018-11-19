package mobile.fpts.com.ezmibile.view.watchlist;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.Column;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;
import com.bin.david.form.data.format.draw.TextImageDrawFormat;
import com.bin.david.form.data.format.title.TitleImageDrawFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.bin.david.form.utils.DensityUtils;

import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ContentMainBinding;
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistBinding;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.main.MainActivity;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeFragment;
import mobile.fpts.com.ezmibile.view.watchlist.model.CompanyName;
import mobile.fpts.com.ezmibile.view.watchlist.model.Quote;
import mobile.fpts.com.ezmibile.view.watchlist.search.WatchListSearchFragment;
import mobile.fpts.com.ezmibile.view.watchlist.sort.SortCodeFragment;

import static android.arch.persistence.room.RoomMasterTable.TABLE_NAME;

@RequiresApi(api = Build.VERSION_CODES.M)
public class WatchlistFragment extends FragmentApp implements IWatchlistView, OnColumnItemClickListener {

    FragmentWatchlistBinding binding;
    ContentMainBinding mainBinding;
    private List<Quote> quoteList;
    private WatchlistPresenter presenter;
    private Typeface fontString;
    SharedPreferences preferences;

    public static WatchlistFragment newInstance() {
        WatchlistFragment frag = new WatchlistFragment();
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.setPosition(Define.TYPE_MENU_WATCHLIST);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist, container, false);
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.content_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.watchlist_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                Log.w("WatchlistFragment", "onOptionsItemSelected: add");
                setFragment(WatchListSearchFragment.newInstance());
                break;
            case R.id.item_edit:
                Log.w("WatchlistFragment", "onOptionsItemSelected: edit");
                setFragment(SortCodeFragment.newInstance());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.watchlist);
        fontString = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans_bold);
        binding.smarttable.getConfig().setFixedTitle(true);
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        binding.smarttable.getConfig().setContentBackgroundColor(isLight ?
                getResources().getColor(R.color.colorBackground) : getResources().getColor(R.color.colorContentDark));
        binding.smarttable.getConfig().setFixedCountRow(false);
        binding.smarttable.getConfig().setShowYSequence(false);
        binding.smarttable.getConfig().setShowXSequence(false);
        binding.smarttable.setZoom(false);
        binding.smarttable.getConfig().setColumnTitleBackgroundColor(isLight ? getResources().getColor(R.color.colorHeader) :
                getResources().getColor(R.color.colorHeaderDark));

        binding.smarttable.getConfig().setShowTableTitle(false);
        binding.smarttable.getConfig().setHorizontalPadding(50);
        binding.smarttable.getConfig().setVerticalPadding(15);
        binding.smarttable.getConfig().setColumnTitleHorizontalPadding(50);
        binding.smarttable.getConfig().setColumnTitleVerticalPadding(15);

        binding.smarttable.getConfig().setContentBackgroundColor(R.color.gray);
        FontStyle fontStyle = new FontStyle();
        FontStyle fontStyle1 = new FontStyle();

        fontStyle.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont) :
                App.getInstance().getResources().getColor(R.color.colorFontDark));
        fontStyle1.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont) :
                App.getInstance().getResources().getColor(R.color.colorFontDark));

        fontStyle.setTypeface(fontString);
        fontStyle1.setTypeface(fontString);
        fontStyle.setAlign(Paint.Align.RIGHT);
        fontStyle1.setAlign(Paint.Align.RIGHT);

        binding.smarttable.getConfig().setContentStyle(fontStyle);
        binding.smarttable.getConfig().setColumnTitleStyle(fontStyle1);
        //sắp xếp
        binding.smarttable.setOnColumnClickListener(columnInfo -> {
            if (!columnInfo.column.getFieldName().equals("mack")) {
                binding.smarttable.setSortColumn(columnInfo.column, !columnInfo.column.isReverseSort());
            }
            String quoteString = "";
            for (int i = 0; i < binding.smarttable.getTableData().getLineSize(); i++) {
                quoteString += binding.smarttable.getTableData().getColumnByFieldName("mack").getDatas().get(i) + ",";
            }
            presenter.setQuoteString(quoteString);
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null)
            presenter.destroy();
        binding.smarttable.destroyDrawingCache();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new WatchlistPresenter(this);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    public void setFragment(String symbol, String color) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(mainBinding.contentview.getId(), new DetailCodeFragment().newInstance(symbol, false));
        transaction.addToBackStack("");
        transaction.commit();
    }

    public void setFragment(Fragment fragmemt) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(mainBinding.contentview.getId(), fragmemt)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        if (getActivity() != null) {
            getActivity().setProgressBarIndeterminateVisibility(false);
        }
    }

    // TODO:HoaDT 7/6/2018 ko xử lý realtime nên ẩn
//    @Override
//    public void loadSuccess() {
//        if (getActivity() != null) {
//            getActivity().setProgressBarIndeterminateVisibility(true);
//        }
//        binding.progressBar.setVisibility(View.GONE);
//    }

    @Override
    public void onDisplay(List<Column> columnList, List<Quote> quoteList) {
        // TODO:HoaDT 7/6/2018 ẩn progressbar
        if (getActivity() != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
        }
        binding.progressBar.setVisibility(View.GONE);

        TableData<Quote> tableData = new TableData<>(TABLE_NAME, quoteList, columnList);
        this.quoteList = quoteList;
        Log.w("WatchlistFragment", "onDisplay: " + columnList.size());
        //click to detail Fragment
        for (int i = 0; i < columnList.size(); i++) {
            if (i == 0) {
                columnList.get(0).setOnColumnItemClickListener((column, value, s, position)
                        -> setFragment(value, quoteList.get(position).getColor()));
            } else
                columnList.get(i).setOnColumnItemClickListener(this);
        }
        tableData.setColumns(columnList);
        int size = DensityUtils.dp2px(App.getInstance(), 15);
        //change title when click to sort

        tableData.setTitleDrawFormat(new TitleImageDrawFormat(size, size, TitleImageDrawFormat.RIGHT, 10) {
            @Override
            protected Context getContext() {
                return App.getInstance();
            }

            @Override
            protected int getResourceID(Column column) {
                if (!column.isParent()) {
                    if (tableData.getSortColumn() == column) {
                        setDirection(TextImageDrawFormat.LEFT);
                        if (column.isReverseSort()) {
                            return R.mipmap.sort_up;
                        }
                        return R.mipmap.sort_down;
                    }
                    return 0;
                }
                return 0;
            }
        });
        setBackground(tableData);
        presenter.setQuoteListToSharedPreferences(quoteList);
        binding.smarttable.setTableData(tableData);
    }

    private void setBackground(final TableData<Quote> data) {
        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        List<Quote> oldQuoteList = presenter.getQuoteListFromSharedPreferences();
        ICellBackgroundFormat<CellInfo> backgroundFormat = new BaseCellBackgroundFormat<CellInfo>() {

            @Override
            public int getBackGroundColor(CellInfo cellInfo) {
                int position = cellInfo.position;//row
                // TODO:HoaDT 7/6/2018 trường hợp thêm mã
                if (position < 0 || position >= oldQuoteList.size()) {
                    return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);
                }
                // TODO:HoaDT 7/6/2018 trường hợp dãy null
                if (oldQuoteList == null || oldQuoteList.size() == 0) {
                    return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);
                }
                Quote quote = data.getT().get(position);
                if (cellInfo.column.getFieldName().equals("tran"))
                    if (quote.getTran() != oldQuoteList.get(position).getTran())
                        return isLight ? getResources().getColor(R.color.colorHeader) :
                                getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("san"))
                    if (quote.getSan() != oldQuoteList.get(position).getSan())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("tc"))
                    if (quote.getTc() != oldQuoteList.get(position).getTc())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("mo"))
                    if (quote.getMo() != oldQuoteList.get(position).getMo())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("thap"))
                    if (quote.getThap() != oldQuoteList.get(position).getThap())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("cao"))
                    if (quote.getCao() != oldQuoteList.get(position).getCao())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("di") || cellInfo.column.getFieldName().equals("giakhop"))
                    if (quote.getDi() != oldQuoteList.get(position).getDi()
                            || data.getT().get(position).getGiakhop() != oldQuoteList.get(position).getGiakhop())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("gm3"))
                    if (quote.getGm3() != oldQuoteList.get(position).getGm3())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("klm3"))
                    if (quote.getKlm3() != oldQuoteList.get(position).getKlm3())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("gm2"))
                    if (quote.getGm2() != oldQuoteList.get(position).getGm2())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("klm2"))
                    if (quote.getKlm2() != oldQuoteList.get(position).getKlm2())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("gm1"))
                    if (quote.getGm1() != oldQuoteList.get(position).getGm1())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("klm1"))
                    if (quote.getKlm1() != oldQuoteList.get(position).getKlm1())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("gb1"))
                    if (quote.getGb1() != oldQuoteList.get(position).getGb1())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("klb1"))
                    if (quote.getKlb1() != oldQuoteList.get(position).getKlb1())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("gb2"))
                    if (quote.getGb2() != oldQuoteList.get(position).getGb2())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("klb2"))
                    if (quote.getKlb2() != oldQuoteList.get(position).getKlb2())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("gb3"))
                    if (quote.getGb3() != oldQuoteList.get(position).getGb3())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("klb3"))
                    if (quote.getKlb3() != oldQuoteList.get(position).getKlb3())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("khoiluong"))
                    if (quote.getKhoiluong() != oldQuoteList.get(position).getKhoiluong())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("kl"))
                    if (quote.getKl() != oldQuoteList.get(position).getKl())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("nnmua"))
                    if (quote.getNnmua() != oldQuoteList.get(position).getNnmua())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                if (cellInfo.column.getFieldName().equals("nnban"))
                    if (quote.getNnban() != oldQuoteList.get(position).getNnban())
                        return isLight ? getResources().getColor(R.color.colorHeader) : getResources().getColor(R.color.colorHeaderDark);

                return isLight ? getResources().getColor(R.color.colorBackground) : getResources().getColor(R.color.colorBackgroundDark);
            }

            @Override
            public int getTextColor(CellInfo cellInfo) {
                Quote item = data.getT().get(cellInfo.position);
                if (cellInfo.column.getFieldName().equals("tran"))
                    return App.getInstance().getResources().getColor(R.color.purple);
                if (cellInfo.column.getFieldName().equals("san"))
                    return App.getInstance().getResources().getColor(R.color.blue);
                if (cellInfo.column.getFieldName().equals("tc"))
                    return App.getInstance().getResources().getColor(R.color.orange);
                if (cellInfo.column.getFieldName().equals("di") || cellInfo.column.getFieldName().equals("giakhop"))
                    return ColorTr.setWacthListDetailColor(item, item.getGiakhop());
                if (cellInfo.column.getFieldName().equals("mack"))
                    return ColorTr.changeColormck(item.getColor());
                if (cellInfo.column.getFieldName().equals("gm3"))
                    return ColorTr.setQuotesColorTextViewMrkBWL(item.getGm3(), item);
                if (cellInfo.column.getFieldName().equals("gm2"))
                    return ColorTr.setQuotesColorTextViewMrkBWL(item.getGm2(), item);
                if (cellInfo.column.getFieldName().equals("gm1"))
                    return ColorTr.setQuotesColorTextViewMrkBWL(item.getGm1(), item);
                if (cellInfo.column.getFieldName().equals("gb1"))
                    return ColorTr.setQuotesColorTextViewMrkBWL(item.getGb1(), item);
                if (cellInfo.column.getFieldName().equals("gb2"))
                    return ColorTr.setQuotesColorTextViewMrkBWL(item.getGb2(), item);
                if (cellInfo.column.getFieldName().equals("gb3"))
                    return ColorTr.setQuotesColorTextViewMrkBWL(item.getGb3(), item);
                if (cellInfo.column.getFieldName().equals("mo"))
                    return ColorTr.setTextViewColorOpen(item.getMo(), item.getTc());
                if (cellInfo.column.getFieldName().equals("thap"))
                    return ColorTr.setTextViewColorOpen(item.getThap(), item.getTc());
                if (cellInfo.column.getFieldName().equals("cao"))
                    return ColorTr.setTextViewColorOpen(item.getCao(), item.getTc());
                return isLight ? getResources().getColor(R.color.colorFont) :
                        getResources().getColor(R.color.colorFontDark);
            }
        };
        binding.smarttable.getConfig().setContentBackgroundFormat(backgroundFormat);
        presenter.setQuoteListToSharedPreferences(oldQuoteList);
    }

    public int searchKey(List<CompanyName> datas, String codestock) {
        int index = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getstockcode().equals(codestock)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void onClick(Column column, String value, Object o, int position) {
        setFragment(quoteList.get(position).getMack(), quoteList.get(position).getColor());
    }
}

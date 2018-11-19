package mobile.fpts.com.ezmibile.view.watchlist.detail.trading;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ActivityMainBinding;
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistTradingBinding;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.model.entity.market.Quotes2;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.util.custormView.TextViewFont;
import mobile.fpts.com.ezmibile.util.custormView.TextViewFontBold;
import mobile.fpts.com.ezmibile.view.news.detail.NewsDetailFragment;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeData;

public class TradingFragment extends FragmentApp implements OnChartValueSelectedListener, ITradingView,
        View.OnClickListener {
    private static final NavigableMap<Float, String> suffixes = new TreeMap<>();

    static {
        suffixes.put((float) 1_000L, "K");
        suffixes.put((float) 1_000_000L, "M");
        suffixes.put((float) 1_000_000_000L, "G");
        suffixes.put((float) 1_000_000_000_000L, "T");
        suffixes.put((float) 1_000_000_000_000_000L, "P");
        suffixes.put((float) 1_000_000_000_000_000_000L, "E");
    }

    private String symbol;
    private FragmentWatchlistTradingBinding binding;
    private ActivityMainBinding mainBinding;

    private TradingPresenter presenter;
    private SharedPreferences preferences;
    private boolean isLight = false;
    private ArrayList<HistoryChartOtherIndex> yVals1 = new ArrayList<>();
    private Typeface typeface;

    public static TradingFragment newInstance(String symbol) {
        TradingFragment fragment = new TradingFragment();
        Bundle args = new Bundle();
        args.putString("symbol", symbol);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            symbol = getArguments().getString("symbol");
//            color = getArguments().getString("color");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_trading, container, false);
        mainBinding = ActivityMainBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new TradingPresenter(this, symbol, yVals1);
        presenter.LoadDataChartRealtime();

        typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans);

        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        binding.txtBVolTitle.setText(Html.fromHtml(getString(R.string.watchlist_detail_bestbuy)));
        binding.txtSMrkTitle.setText(Html.fromHtml(getString(R.string.watchlist_detail_bestseller)));
        binding.linearLayoutHeaderNews.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorHeader) :
                App.getInstance().getResources().getColor(R.color.colorHeaderDark));
        binding.linearLayoutHeaderNews.setOnClickListener(v -> {
            if (binding.recyclerViewNews.getVisibility() == View.VISIBLE || binding.recyclerViewNews.getVisibility() == View.INVISIBLE) {
                binding.recyclerViewNews.setVisibility(View.GONE);
            } else binding.recyclerViewNews.setVisibility(View.VISIBLE);
        });
        binding.linearLayoutHeaderChart.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorHeader) :
                App.getInstance().getResources().getColor(R.color.colorHeaderDark));
        binding.linearLayoutHeaderChart.setOnClickListener(v -> {
            if (binding.relativeLayoutChart.getVisibility() == View.VISIBLE || binding.relativeLayoutChart.getVisibility() == View.INVISIBLE) {
                binding.relativeLayoutChart.setVisibility(View.GONE);
            } else binding.relativeLayoutChart.setVisibility(View.VISIBLE);
        });
        binding.linearLayoutHeaderAnalysis.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorHeader) :
                App.getInstance().getResources().getColor(R.color.colorHeaderDark));
        binding.linearLayoutHeaderComment.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorHeader) :
                App.getInstance().getResources().getColor(R.color.colorHeaderDark));

        binding.imageviewDetailNews.setImageResource(isLight ? R.drawable.ic_keyboard_arrow_right_black_24dp :
                R.drawable.ic_keyboard_arrow_right_white_24dp);
        binding.imageviewDetailChart.setImageResource(isLight ? R.drawable.ic_keyboard_arrow_right_black_24dp :
                R.drawable.ic_keyboard_arrow_right_white_24dp);
        binding.imageviewDetailAnalysis.setImageResource(isLight ? R.drawable.ic_keyboard_arrow_right_black_24dp :
                R.drawable.ic_keyboard_arrow_right_white_24dp);
        binding.imageviewDetailComment.setImageResource(isLight ? R.drawable.ic_keyboard_arrow_right_black_24dp :
                R.drawable.ic_keyboard_arrow_right_white_24dp);

        //trú thích biểu đồ
        binding.barchart.getLegend().setEnabled(false);
        binding.lineChart.getLegend().setEnabled(false);
        binding.candleStick.getLegend().setEnabled(false);
        //miêu tả
        binding.barchart.getDescription().setEnabled(false);
        binding.lineChart.getDescription().setEnabled(false);
        binding.candleStick.getDescription().setEnabled(false);
        //ẩn cột bên phải
        binding.barchart.getAxisRight().setEnabled(false);
        binding.candleStick.getAxisRight().setEnabled(false);
        binding.lineChart.getAxisRight().setEnabled(false);
        //chặn zoom
        binding.barchart.setScaleEnabled(false);
        binding.lineChart.setScaleEnabled(false);
        binding.candleStick.setScaleEnabled(false);
        //
        binding.barchart.setDrawGridBackground(false);
        binding.candleStick.setDrawGridBackground(false);
        binding.lineChart.setDrawGridBackground(false);
        ////////////barchart
        binding.barchart.setOnChartValueSelectedListener(this);
        binding.barchart.setDrawBarShadow(false);
        binding.barchart.setDrawValueAboveBar(true);
        // TODO:HoaDT 7/5/2018 font chữ no data
        binding.barchart.setNoDataTextTypeface(typeface);
        binding.lineChart.setNoDataTextTypeface(typeface);
        binding.candleStick.setNoDataTextTypeface(typeface);
        binding.barchart.setNoDataTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont) :
                App.getInstance().getResources().getColor(R.color.colorFontDark));
        binding.lineChart.setNoDataTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont) :
                App.getInstance().getResources().getColor(R.color.colorFontDark));
        binding.candleStick.setNoDataTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont) :
                App.getInstance().getResources().getColor(R.color.colorFontDark));

        binding.barchart.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barchart.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barchart.setBorderColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barchart.getXAxis().setTypeface(typeface);
        binding.barchart.getAxisLeft().setTypeface(typeface);
        //////////////candle
        binding.candleStick.setAutoScaleMinMaxEnabled(false);
        binding.candleStick.getXAxis().setTypeface(typeface);
        binding.candleStick.getAxisLeft().setTypeface(typeface);
        binding.candleStick.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        ///////////////line
        binding.lineChart.setOnChartValueSelectedListener(this);
        binding.lineChart.getXAxis().setTypeface(typeface);
        binding.lineChart.getAxisLeft().setTypeface(typeface);
        binding.lineChart.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));

        binding.candleStick.setVisibility(View.GONE);
        binding.lineChart.setVisibility(View.VISIBLE);
        binding.candleStick.setVisibility(View.GONE);

        binding.tabChartOneDay.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.tabChartOneDay.setBackgroundResource(isLight ?
                R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);

        binding.tabChartOneWeek.setTextColor(getResources().getColor(R.color.gray));
        binding.tabChartOneWeek.setBackgroundResource(isLight ?
                R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
        binding.tabChartOneMonth.setTextColor(getResources().getColor(R.color.gray));
        binding.tabChartOneMonth.setBackgroundResource(isLight ?
                R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
        binding.tabChartOneYear.setTextColor(getResources().getColor(R.color.gray));
        binding.tabChartOneYear.setBackgroundResource(isLight ?
                R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
        binding.tabChartFiveYear.setTextColor(getResources().getColor(R.color.gray));
        binding.tabChartFiveYear.setBackgroundResource(isLight ?
                R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
        binding.tabChartMax.setTextColor(getResources().getColor(R.color.gray));
        binding.tabChartMax.setBackgroundResource(isLight ?
                R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
        presenter.LoadDataChartRealtime();

        binding.tabChartOneDay.setOnClickListener(this);
        binding.tabChartOneWeek.setOnClickListener(this);
        binding.tabChartOneMonth.setOnClickListener(this);
        binding.tabChartOneYear.setOnClickListener(this);
        binding.tabChartFiveYear.setOnClickListener(this);
        binding.tabChartMax.setOnClickListener(this);

        //Phần mua bán
        for (int i = 0; i < binding.linearLayoutBuySell.getChildCount(); i++) {
            View buysellView = binding.linearLayoutBuySell.getChildAt(i);
            TextViewFontBold txtBuyTitle = buysellView.findViewById(R.id.txtBuyTitle);
            txtBuyTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            TextViewFontBold txtSellTitle = buysellView.findViewById(R.id.txtSellTitle);
            txtSellTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void onDisplay(DetailCodeData detailCodeData) {
        binding.linearLayoutBasic.setVisibility(View.VISIBLE);

        binding.textviewChange.setText("(" + detailCodeData.getPerChange() + "%)");// + "(" + quotes2.get.getPerChange() + "%)");
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(0).findViewById(R.id.txtString1)).setText(R.string.watchlist_detail_klbq_30_ngay);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(0).findViewById(R.id.txtNum1)).setText(detailCodeData.getKLGD30Days());
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(0).findViewById(R.id.txtString2)).setText(R.string.watchlist_detail_eps);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(0).findViewById(R.id.txtNum2)).setText(detailCodeData.getResEPS());

        ((TextViewFont) binding.linearLayoutBasic.getChildAt(1).findViewById(R.id.txtString1)).setText(R.string.watchlist_detail_gia_thap_nhat_52T);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(1).findViewById(R.id.txtNum1)).setText(detailCodeData.getWk52Low());
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(1).findViewById(R.id.txtString2)).setText(R.string.watchlist_detail_eps_dieu_chinh);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(1).findViewById(R.id.txtNum2)).setText(detailCodeData.getEPSAdjustedSTC());

        ((TextViewFont) binding.linearLayoutBasic.getChildAt(2).findViewById(R.id.txtString1)).setText(R.string.watchlist_detail_gia_cao_nhat_52T);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(2).findViewById(R.id.txtNum1)).setText(detailCodeData.getWk52High());
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(2).findViewById(R.id.txtString2)).setText(R.string.watchlist_detail_PE);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(2).findViewById(R.id.txtNum2)).setText(detailCodeData.getPE());

        ((TextViewFont) binding.linearLayoutBasic.getChildAt(3).findViewById(R.id.txtString1)).setText(R.string.watchlist_detail_NN_mua_YTD);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(3).findViewById(R.id.txtNum1)).setText(detailCodeData.getNNMUA_YTD());
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(3).findViewById(R.id.txtString2)).setText(R.string.watchlist_detail_PB);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(3).findViewById(R.id.txtNum2)).setText(detailCodeData.getPB());

        ((TextViewFont) binding.linearLayoutBasic.getChildAt(4).findViewById(R.id.txtString1)).setText(R.string.watchlist_detail_nuoc_ngoai);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(4).findViewById(R.id.txtNum1)).setText(detailCodeData.getTLSHNN());
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(4).findViewById(R.id.txtString2)).setText(R.string.watchlist_detail_gia_tri_so_sach);
//        ((TextViewFont) binding.linearLayoutBasic.getChildAt(4).findViewById(R.id.txtNum2)).setText(detailCodeData.get);

        ((TextViewFont) binding.linearLayoutBasic.getChildAt(5).findViewById(R.id.txtString1)).setText(R.string.watchlist_detail_von_hoa);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(5).findViewById(R.id.txtNum1)).setText(detailCodeData.getMktCap());
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(5).findViewById(R.id.txtString2)).setText(R.string.watchlist_detail_KLDNY);
        ((TextViewFont) binding.linearLayoutBasic.getChildAt(5).findViewById(R.id.txtNum2)).setText(detailCodeData.getQty());
    }

    // TODO:hoadt 6/20/2018 show phần dữ liệu hiện thị chung
    @Override
    public void onDisplay(Quotes2 quotes2, Define.TYPE_CHANGE_APP colorOpen,
                          Define.TYPE_CHANGE_APP colorHighest, Define.TYPE_CHANGE_APP colorLowest, double percent) {
        // TODO:HoaDT 6/19/2018 set color for item
        switch (quotes2.getUpDown()) {
            case "b":///trắng
                binding.imgIcon.setVisibility(View.GONE);
                binding.textviewChange.setText("");
                break;
            case "c"://tím
                binding.imgIcon.setImageResource(R.drawable.ic_up_violet);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case "u":///green
                binding.imgIcon.setImageResource(R.drawable.ic_up_green);
                binding.textviewChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case "d"://đỏ
                binding.imgIcon.setImageResource(R.drawable.ic_down_red);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case "r"://vàng
                binding.imgIcon.setImageResource(R.drawable.ic_no_change);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case "f"://blue
                binding.imgIcon.setImageResource(R.drawable.ic_down_blue);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        switch (colorOpen) {
            case UP_CEILING:
                binding.textviewOpen.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case UP:
                binding.textviewOpen.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case NO_CHANGE:
                binding.textviewOpen.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case DOWN:
                binding.textviewOpen.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case DOWN_FLOOR:
                binding.textviewOpen.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        switch (colorHighest) {
            case UP_CEILING:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case UP:
                binding.textviewHighest.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case NO_CHANGE:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case DOWN:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case DOWN_FLOOR:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        switch (colorLowest) {
            case UP_CEILING:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case UP:
                binding.textviewLowest.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case NO_CHANGE:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case DOWN:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case DOWN_FLOOR:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        binding.textviewVolumn.setText(quotes2.getMatchQtty());
        binding.textviewChange.setText(quotes2.getChangePrice());// + "(" + quotes2.get.getPerChange() + "%)");
        binding.textviewPrice.setText(quotes2.getMatchPrice());
        binding.textviewQty.setText(quotes2.getTotalQtty());

        binding.textviewOpen.setText(quotes2.getOpenPrice());
        binding.textviewCeiling.setText(quotes2.getCeiling());
        binding.textviewRef.setText(quotes2.getRefPrice());
        binding.textviewFloor.setText(quotes2.getFloor());
        binding.textviewHighest.setText(quotes2.getHighestPrice());
        binding.textviewLowest.setText(quotes2.getLowestPrice());
        binding.textviewBuy.setText(quotes2.getForeignBuyQtty());
        binding.textviewSell.setText(quotes2.getForeignSellQtty());
        //Phần thông tin chung
//

        binding.linearLayoutBuySell.getChildAt(0).findViewById(R.id.txtBuyTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        ((TextViewFontBold) binding.linearLayoutBuySell.getChildAt(0).findViewById(R.id.txtBuyTitle)).setText(R.string.watchlist_detail_buy);
        ((TextViewFontBold) binding.linearLayoutBuySell.getChildAt(0).findViewById(R.id.txtSellTitle)).setText(R.string.watchlist_detail_sell);
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(0).findViewById(R.id.txtBuyMrkValue)).setText(quotes2.getBuyQtty1());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(0).findViewById(R.id.txtBuyVolValue)).setText(quotes2.getBuyPrice1());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(0).findViewById(R.id.txtSellVolValue)).setText(quotes2.getSellPrice1());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(0).findViewById(R.id.txtSellMrkValue)).setText(quotes2.getSellQtty1());


        ((TextViewFontBold) binding.linearLayoutBuySell.getChildAt(1).findViewById(R.id.txtBuyTitle)).setText(R.string.watchlist_detail_buy);
        ((TextViewFontBold) binding.linearLayoutBuySell.getChildAt(1).findViewById(R.id.txtSellTitle)).setText(R.string.watchlist_detail_sell);
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(1).findViewById(R.id.txtBuyMrkValue)).setText(quotes2.getBuyQtty2());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(1).findViewById(R.id.txtBuyVolValue)).setText(quotes2.getBuyPrice2());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(1).findViewById(R.id.txtSellVolValue)).setText(quotes2.getSellPrice2());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(1).findViewById(R.id.txtSellMrkValue)).setText(quotes2.getSellQtty2());


        ((TextViewFontBold) binding.linearLayoutBuySell.getChildAt(2).findViewById(R.id.txtBuyTitle)).setText(R.string.watchlist_detail_buy);
        ((TextViewFontBold) binding.linearLayoutBuySell.getChildAt(2).findViewById(R.id.txtSellTitle)).setText(R.string.watchlist_detail_sell);
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(2).findViewById(R.id.txtBuyMrkValue)).setText(quotes2.getBuyQtty3());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(2).findViewById(R.id.txtBuyVolValue)).setText(quotes2.getBuyPrice3());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(2).findViewById(R.id.txtSellVolValue)).setText(quotes2.getSellPrice3());
        ((TextViewFont) binding.linearLayoutBuySell.getChildAt(2).findViewById(R.id.txtSellMrkValue)).setText(quotes2.getSellQtty3());

        // TODO:HoaDT 7/2/2018 phần thanh Progressbar
        binding.linearLayoutTitleBuySell.setVisibility(View.VISIBLE);
        binding.linearLayoutProgressbar.setVisibility(View.VISIBLE);

        binding.determinateBar.setProgress((int) percent);
        binding.txtSellPercent.setText(new DecimalFormat("0.00").format(percent) + "%");
        binding.txtBuyPercent.setText(new DecimalFormat("0.00").format(100 - percent) + "%");
    }

    // TODO:hoadt 6/20/2018 show phần biểu đồ
    @Override
    public void showChart(List<HistoryChartOtherIndex> yVals1) {
        binding.linearLayoutHeaderChart.setVisibility(View.VISIBLE);
        binding.relativeLayoutChart.setVisibility(View.VISIBLE);

        Log.w("TradingFragment", "showChart: ");
        final ArrayList<BarEntry> datachart = new ArrayList<>();
        final ArrayList<String> DateList = new ArrayList<>();
        final ArrayList<CandleEntry> chartcandle = new ArrayList<>();
        final ArrayList<Entry> dataline = new ArrayList<>();

        binding.lineChart.clear();
        binding.barchart.clear();
        binding.candleStick.clear();
        for (int i = 0; i < yVals1.size(); i++) {
            datachart.add(new BarEntry(i, Float.parseFloat(yVals1.get(i).getCharV())));
            DateList.add(yVals1.get(i).getCharTime());
        }
        for (int i = 0; i < yVals1.size(); i++) {
            dataline.add(new Entry(i, Float.parseFloat(yVals1.get(i).getChartH()), null));
        }
        for (int i = 0; i < yVals1.size(); i++) {
            chartcandle.add(new CandleEntry(
                    i,
                    Float.parseFloat(yVals1.get(i).getChartH()),
                    Float.parseFloat(yVals1.get(i).getChartL()),
                    Float.parseFloat(yVals1.get(i).getChartO()),
                    Float.parseFloat(yVals1.get(i).getChartC()),
                    null));
        }
        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(DateList);

        XAxis xAxis = binding.barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setLabelCount(4);
        xAxis.setTextColor(isLight ? getResources().getColor(R.color.colorFont) : getResources().getColor(R.color.colorFontDark));
        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = binding.barchart.getAxisLeft();
        leftAxis.setLabelCount(4, false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setTextColor(isLight ? getResources().getColor(R.color.colorFont) : getResources().getColor(R.color.colorFontDark));
        leftAxis.setEnabled(true);
        leftAxis.setValueFormatter(custom);
        leftAxis.setAxisMinimum(2f); // start at zero

        BarDataSet set1;
        if (binding.barchart.getData() != null &&
                binding.barchart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) binding.barchart.getData().getDataSetByIndex(0);
            set1.setValues(datachart);
            binding.barchart.getData().notifyDataChanged();
            binding.barchart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(datachart, "");
            set1.setDrawIcons(false);
            set1.setValueTextColor(isLight ? getResources().getColor(R.color.colorFont) : getResources().getColor(R.color.colorFontDark));
            set1.setColors(isLight ? getResources().getColor(R.color.green) : getResources().getColor(R.color.greenDark));
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            //data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);
            binding.barchart.setData(data);
            for (IDataSet set : binding.barchart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());
            binding.barchart.invalidate();
        }
        binding.candleStick.getXAxis().setValueFormatter(xAxisFormatter);

        XAxis xAxis1 = binding.candleStick.getXAxis();
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis1.setDrawGridLines(false);
        xAxis1.setTextColor(isLight ? getResources().getColor(R.color.colorFont) : getResources().getColor(R.color.colorFontDark));
        xAxis1.setGranularity(1f);
        xAxis1.setLabelCount(4);

        YAxis leftAxis1 = binding.candleStick.getAxisLeft();
        leftAxis1.setLabelCount(4, false);
        leftAxis1.setDrawGridLines(true);
        leftAxis1.setTextColor(isLight ? getResources().getColor(R.color.colorFont) : getResources().getColor(R.color.colorFontDark));
        leftAxis1.setEnabled(true);
        leftAxis1.setDrawAxisLine(true);
        leftAxis1.setAxisLineColor(getResources().getColor(R.color.black));

//        YAxis rightAxis1 = binding.candleStick.getAxisRight();
//        rightAxis1.setEnabled(false);
//        rightAxis1.setAxisLineColor(Color.BLACK);
        if (datachart != null && datachart.size() > 0) {
            CandleDataSet set12 = new CandleDataSet(chartcandle, "Data Set");

            set12.setDrawIcons(false);
            set12.setAxisDependency(YAxis.AxisDependency.LEFT);
//            set12.setShadowColor(Color.DKGRAY); - back ground dưới đường line
            set12.setShadowWidth(0.7f);
            set12.setDecreasingColor(getResources().getColor(R.color.red));
            set12.setDecreasingPaintStyle(Paint.Style.FILL);
            set12.setIncreasingColor(getResources().getColor(R.color.green));
            set12.setNeutralColor(getResources().getColor(R.color.blue));
            set12.setIncreasingPaintStyle(Paint.Style.FILL);

            set12.setValueTextColor(isLight ? getResources().getColor(R.color.colorFont)
                    : getResources().getColor(R.color.colorFontDark));

            binding.candleStick.setData(new CandleData(set12));
            binding.candleStick.invalidate();
            //
            for (IDataSet set : binding.candleStick.getData().getDataSets())
                set.setDrawValues(false);

            binding.candleStick.invalidate();

        }
        /////line
        XAxis xAxis2 = binding.lineChart.getXAxis();
//        xAxis2.enableGridDashedLine(10f, 10f, 0f);
        xAxis2.setLabelCount(4);
        xAxis2.setTextColor(isLight ? getResources().getColor(R.color.colorFont)
                : getResources().getColor(R.color.colorFontDark));
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setValueFormatter(xAxisFormatter);
        //Set Cột bên trái
        YAxis leftAxis2 = binding.lineChart.getAxisLeft();
        leftAxis2.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        //leftAxis2.setYOffset(20f);
        leftAxis2.setDrawGridLines(true);
        leftAxis2.setDrawAxisLine(true);
//        leftAxis2.enableGridDashedLine(10f, 10f, 0f);
        leftAxis2.setDrawZeroLine(false);
        leftAxis2.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));

        // limit lines are drawn behind data (and not on top)
        leftAxis2.setDrawLimitLinesBehindData(true);

        LineDataSet set3;
        if (binding.lineChart.getData() != null && binding.lineChart.getData().getDataSetCount() > 0) {
            set3 = (LineDataSet) binding.lineChart.getData().getDataSetByIndex(0);
            set3.setValues(dataline);
            binding.lineChart.getData().notifyDataChanged();
            binding.lineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set3 = new LineDataSet(dataline, "");
            set3.setValueTextColor(isLight ? getResources().getColor(R.color.colorFont)
                    : getResources().getColor(R.color.colorFontDark));
            set3.setDrawIcons(false);
            set3.setDrawCircles(false);
            // set the line to be drawn like this "- - - - - -"
//            set3.enableDashedLine(10f, 5f, 0f);
//            set3.enableDashedHighlightLine(10f, 5f, 0f);
            set3.setColor(isLight ? getResources().getColor(R.color.colorFont)
                    : getResources().getColor(R.color.colorFontDark));
            set3.setCircleColor(isLight ? getResources().getColor(R.color.colorFont)
                    : getResources().getColor(R.color.colorFontDark));
            set3.setLineWidth(0f);
            set3.setCircleRadius(0f);
            set3.setDrawCircleHole(false);
            set3.setValueTextSize(0f);
            set3.setDrawFilled(true);
            set3.setFormLineWidth(0f);
            set3.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set3.setFormSize(10f);

            set3.setFillColor(isLight ? getResources().getColor(R.color.colorFont)
                    : getResources().getColor(R.color.colorFontDark));

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set3); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);
            binding.lineChart.setData(data);

            // TODO:HoaDT 6/22/2018  ẩn cột kẻ dọc
            binding.lineChart.getXAxis().setDrawGridLines(false);
            binding.lineChart.getAxisRight().setDrawGridLines(false);
        }
        //dont forget to refresh the drawing
        binding.lineChart.invalidate();
        //show values
        if (binding.lineChart.getData() != null && binding.lineChart.getData().getDataSets() != null) {
            List<ILineDataSet> sets = binding.lineChart.getData().getDataSets();
            for (ILineDataSet iSet : sets) {
                LineDataSet set = (LineDataSet) iSet;
                set.setDrawValues(true);
            }
            binding.lineChart.invalidate();
        }

        if (binding.lineChart.getVisibleXRange() > 50) {
            for (IDataSet set : binding.lineChart.getData().getDataSets())
                set.setDrawValues(false);
        }
    }

    @Override
    public void onDisplayNews(List<NewsArticle> newsArticles) {
        NewsAdapter adapter = new NewsAdapter(newsArticles, symbol, new INewsListener() {
            @Override
            public void OnClick(String NewsID, String symbol) {
                setFragment(NewsDetailFragment.newInstance(NewsID, symbol, Define.Analysis_Comment));
            }
        });
        binding.recyclerViewNews.setAdapter(adapter);


//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.tabChartOneDay.getId()) {
            binding.lineChart.setVisibility(View.VISIBLE);
            binding.candleStick.setVisibility(View.GONE);

            binding.tabChartOneDay.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                    getResources().getColor(R.color.colorFontDark));
            binding.tabChartOneDay.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
            binding.tabChartOneWeek.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneWeek.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneMonth.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneMonth.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartFiveYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartFiveYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartMax.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartMax.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            presenter.LoadDataChartRealtime();
        } else if (v.getId() == binding.tabChartOneWeek.getId()) {
            binding.lineChart.setVisibility(View.GONE);
            binding.candleStick.setVisibility(View.VISIBLE);

            binding.tabChartOneDay.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneDay.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneWeek.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                    getResources().getColor(R.color.colorFontDark));
            binding.tabChartOneWeek.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
            binding.tabChartOneMonth.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneMonth.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartFiveYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartFiveYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartMax.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartMax.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            presenter.loadDataChartFilter(5);
        } else if (v.getId() == binding.tabChartOneMonth.getId()) {
            binding.lineChart.setVisibility(View.GONE);
            binding.candleStick.setVisibility(View.VISIBLE);

            binding.tabChartOneDay.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneDay.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneWeek.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneWeek.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneMonth.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                    getResources().getColor(R.color.colorFontDark));
            binding.tabChartOneMonth.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
            binding.tabChartOneYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartFiveYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartFiveYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartMax.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartMax.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            presenter.loadDataChartFilter(21);
        } else if (v.getId() == binding.tabChartOneYear.getId()) {
            binding.lineChart.setVisibility(View.GONE);
            binding.candleStick.setVisibility(View.VISIBLE);

            binding.tabChartOneDay.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneDay.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneWeek.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneWeek.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneMonth.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneMonth.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneYear.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                    getResources().getColor(R.color.colorFontDark));
            binding.tabChartOneYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
            binding.tabChartFiveYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartFiveYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartMax.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartMax.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            presenter.loadDataChartFilter(63);
        } else if (v.getId() == binding.tabChartFiveYear.getId()) {

            binding.lineChart.setVisibility(View.GONE);
            binding.candleStick.setVisibility(View.VISIBLE);

            binding.tabChartOneDay.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneDay.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneWeek.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneWeek.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneMonth.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneMonth.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartFiveYear.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                    getResources().getColor(R.color.colorFontDark));
            binding.tabChartFiveYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
            binding.tabChartMax.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartMax.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            presenter.loadDataChartFilter(252 * 5);

        } else if (v.getId() == binding.tabChartMax.getId()) {
            binding.lineChart.setVisibility(View.GONE);
            binding.candleStick.setVisibility(View.VISIBLE);
            binding.tabChartOneDay.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneDay.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneWeek.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneWeek.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneMonth.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneMonth.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartOneYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartOneYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartFiveYear.setTextColor(getResources().getColor(R.color.gray));
            binding.tabChartFiveYear.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
            binding.tabChartMax.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                    getResources().getColor(R.color.colorFontDark));
            binding.tabChartMax.setBackgroundResource(isLight ?
                    R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
            presenter.loadDataChartFilter(-1);
        }
    }

    private class DayAxisValueFormatter implements IAxisValueFormatter {

        private ArrayList<String> Date;

        public DayAxisValueFormatter(ArrayList<String> Date) {
            this.Date = Date;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            if (value >= Date.size() || value < 0) return "";
            else
                return Date.get((int) value);
        }
    }

    private class MyAxisValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyAxisValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return format(value);
        }
    }

    // TODO:HoaDT 6/26/2018 Cập nhật cột giá trị K, G, M, T, P, E
    public static String format(float value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Float.toString(value); //deal with easy case

        Map.Entry<Float, String> e = suffixes.floorEntry(value);
        Float divideBy = e.getKey();
        String suffix = e.getValue();

        float truncated = Math.round(value / (divideBy / 10) * 1000) / 1000; //the number part of the output times 10
        // Log.e("truncated ",String.valueOf(truncated));
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction//.remove(this)
                .replace(mainBinding.appBarMain.contentMain.contentview.getId(), fragment);
        transaction.addToBackStack("");
        transaction.commit();

    }
}

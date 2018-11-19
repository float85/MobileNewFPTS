package mobile.fpts.com.ezmibile.view.chart;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.DialogChartCustomBinding;
import mobile.fpts.com.ezmibile.databinding.FragmentChartBinding;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.model.entity.chart.MacdData;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.util.custormView.TextViewFontBold;

public class ChartFragment extends FragmentApp implements IViewChart, View.OnClickListener {
    private static final NavigableMap<Float, String> suffixes = new TreeMap<>();

    FragmentChartBinding binding;
    private Typeface typeface;
    private SharedPreferences preferences;
    private boolean isLight = true;

    String[] listchart = {ChartPresenter.VOLUMN, ChartPresenter.MACD, ChartPresenter.RSI,
            ChartPresenter.BOLINGER_BAND, ChartPresenter.EMA, ChartPresenter.STOCHASTICS};
    int a = 12, b = 26, c = 9;

    List<String> lg = new ArrayList<>();
    private String key = ChartPresenter.VOLUMN;
    private int count = 5;
    List<String> list = new ArrayList<>();
    private ChartPresenter pr;
    private ArrayList<HistoryChartOtherIndex> yVals1 = new ArrayList<>();

    public static ChartFragment newInstance() {
        ChartFragment fragment = new ChartFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        App.setPosition(Define.TYPE_MENU_CHART);
        binding = FragmentChartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapterSpinner();
        chartconfig();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.chart1);
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

        setAdapter();
        chartconfig();
        binding.spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (listchart[i]) {
                    case ChartPresenter.VOLUMN:
                        key = ChartPresenter.VOLUMN;
                        pr.loadFilter(count, key, a, b, c);
                        binding.barChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.VISIBLE);
                        break;
                    case ChartPresenter.MACD:
                        key = ChartPresenter.MACD;
                        showDialog(key);
                        break;
                    case ChartPresenter.RSI:
                        key = ChartPresenter.RSI;
                        showDialog(key);
                        break;
                    case ChartPresenter.BOLINGER_BAND:
                        key = ChartPresenter.BOLINGER_BAND;
                        showDialog(key);
                        break;
                    case ChartPresenter.EMA:
                        key = ChartPresenter.EMA;
                        showDialog(key);
                        break;
                    case ChartPresenter.STOCHASTICS:
                        key = ChartPresenter.STOCHASTICS;
                        showDialog(key);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        pr = new ChartPresenter(this, App.getInstance(), yVals1);

        binding.txtCode.setOnItemClickListener((parent, view1, position, id) -> {
            String code = binding.txtCode.getText().toString();
            code = code.substring(0, code.indexOf("-")).trim();

            pr.loadDt(code, count, key, a, b, c);
            binding.txtCode.setText("");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.chart1 + code);
            ((AppCompatActivity) getActivity()).getSupportActionBar()
                    .setTitle(App.getInstance().getResources().getString(R.string.chart1) + " " + code);
//            hidekeyBoard();
        });
//        getActivity().setTitle("Biểu đồ VNINDEX");
        selectTap(Define.TYPE_CHART_ONE_WEEK);

        binding.tabOneWeek.setOnClickListener(this);
        binding.tabOneMonth.setOnClickListener(this);
        binding.tabThreeMonth.setOnClickListener(this);
        binding.tabSixMonth.setOnClickListener(this);
        binding.tabOneYear.setOnClickListener(this);
        binding.tabAll.setOnClickListener(this);
    }

    // TODO:HoaDT 6/25/2018 đã làm trong FragmentApp
//    private void hidekeyBoard() {
//        View view = getActivity().getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }

    private void setAdapterSpinner() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listchart.length; i++) {
            list.add(listchart[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(App.getInstance(), R.layout.item_chart_spinner_layout, list);
        adapter.setDropDownViewResource(R.layout.item_chart_spinner_layout);
        binding.spinnerType.setAdapter(adapter);
    }

    private void setAdapter() {
        for (int i = 0; i < listchart.length; i++) {
            list.add(listchart[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(App.getInstance(), R.layout.item_chart_spinner_layout, list);
        adapter.setDropDownViewResource(R.layout.item_chart_spinner_layout);
        binding.spinnerType.setAdapter(adapter);
    }

    private void showDialog(final String key1) {
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        final DialogChartCustomBinding bd =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_chart_custom, null, false);
        dialog.setContentView(bd.getRoot());
        bd.tieude.setText(key1);
        switch (key1) {
            case ChartPresenter.MACD:
                bd.name.setHint("Fast avg (12)");
                bd.name1.setHint("Slow avg (26)");
                bd.name2.setHint("Singal avg (9)");
                setEditTextMaxLength(bd.name2, 1);
                break;
            case ChartPresenter.RSI:
                bd.name.setHint("Period (14)");
                bd.name1.setVisibility(View.GONE);
                bd.name2.setVisibility(View.GONE);
                break;
            case ChartPresenter.STOCHASTICS:
                bd.name.setHint("Period (14)");
                bd.name1.setHint("%K avg (3)");
                bd.name2.setHint("%D avg (3)");
                bd.name1.setVisibility(View.VISIBLE);
                bd.name2.setVisibility(View.VISIBLE);
                setEditTextMaxLength(bd.name1, 1);
                setEditTextMaxLength(bd.name2, 1);
                break;
            case ChartPresenter.BOLINGER_BAND:
                bd.name.setHint("Period (20)");
                bd.name1.setHint("Deviations (2.0)");
                bd.name1.setVisibility(View.VISIBLE);
                bd.name2.setVisibility(View.GONE);
                setEditTextMaxLength(bd.name1, 1);
                break;
            case ChartPresenter.EMA:
                bd.name.setHint("First period (5)");
                setEditTextMaxLength(bd.name, 1);
                bd.name1.setVisibility(View.GONE);
                bd.name2.setVisibility(View.GONE);
                break;
        }
        bd.save.setOnClickListener(v -> {
            switch (key1) {
                case ChartPresenter.MACD:
                    if (bd.name.getText().toString().equals("")) a = 12;
                    else a = Integer.parseInt(bd.name.getText().toString());
                    if (bd.name1.getText().toString().equals("")) b = 26;
                    else b = Integer.parseInt(bd.name1.getText().toString());
                    if (bd.name2.getText().toString().equals("")) c = 9;
                    else c = Integer.parseInt(bd.name2.getText().toString());

                    if (a > 12 || b > 26 || c > 9) showMess();
                    else {
                        pr.loadFilter(count, key1, a, b, c);
                        binding.barChart.setVisibility(View.GONE);
                        binding.combineChart2.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                    break;
                case ChartPresenter.RSI:
                    if (bd.name.getText().toString().equals("")) a = 14;
                    else a = Integer.parseInt(bd.name.getText().toString());

                    if (a > 14) showMess();
                    else {
                        pr.computing(key1, a, b, c, count);
//                            pr.loadFilter(count, key1, a, b, c);
                        binding.barChart.setVisibility(View.GONE);
                        binding.combineChart2.setVisibility(View.GONE);
                        binding.lineChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                    break;
                case ChartPresenter.STOCHASTICS:
                    if (bd.name.getText().toString().equals(""))
                        a = 14;
                    else
                        a = Integer.parseInt(bd.name.getText().toString());
                    if (bd.name1.getText().toString().equals(""))
                        b = 3;
                    else
                        b = Integer.parseInt(bd.name1.getText().toString());
                    if (bd.name2.getText().toString().equals(""))
                        c = 3;
                    else
                        b = Integer.parseInt(bd.name2.getText().toString());
                    if (a > 14 || b > 3 || c > 3)
                        showMess();
                    else {
                        pr.computing(key1, a, b, c, count);
                        binding.barChart.setVisibility(View.GONE);
                        binding.combineChart2.setVisibility(View.GONE);
                        binding.lineChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.VISIBLE);

                        dialog.dismiss();
                    }
                    break;
                case ChartPresenter.BOLINGER_BAND:
                    if (bd.name.getText().toString().equals("")) a = 20;
                    else a = Integer.parseInt(bd.name.getText().toString());
                    if (bd.name1.getText().toString().equals("")) b = 2;
                    else b = Integer.parseInt(bd.name1.getText().toString());
                    if (a > 20 || b > 2) showMess();
                    else {
                        pr.computing(key1, a, b, c, count);
                        binding.barChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.VISIBLE);
                        binding.combineChart2.setVisibility(View.GONE);
                        binding.lineChart.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                    break;

                case ChartPresenter.EMA:
                    if (bd.name.getText().toString().equals("")) a = 5;
                    else a = Integer.parseInt(bd.name.getText().toString());

                    if (a > 5) showMess();
                    else {
                        pr.computing(key1, a, b, c, count);
                        binding.barChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.VISIBLE);
                        binding.combineChart2.setVisibility(View.GONE);
                        binding.lineChart.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.GONE);

                        dialog.dismiss();
                    }
                    break;
            }
        });
        bd.exit.setOnClickListener(v -> {
            key = ChartPresenter.VOLUMN;
            binding.spinnerType.setSelection(0);
            dialog.dismiss();
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    private void setEditTextMaxLength(final EditText editText, int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(FilterArray);
    }

    private void showMess() {
        Toast.makeText(App.getInstance(), getResources().getString(R.string.chart_error_message_values), Toast.LENGTH_SHORT).show();
    }

    // TODO:HoaDT 6/25/2018 One-week, One-month, Three-month, Six-Month, One-year, All
    private void selectTap(int position) {
        for (int i = 0; i < binding.menuTab.getChildCount(); i++) {
            TextViewFontBold textview = (TextViewFontBold) binding.menuTab.getChildAt(i);
            textview.setBackgroundResource(R.drawable.bg_home_tab_selector);
            textview.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.gray)
                    : App.getInstance().getResources().getColor(R.color.colorFontDark));
        }
        switch (position) {
            case Define.TYPE_CHART_ONE_WEEK:
                pr.loadFilter(5, key, a, b, c);
                count = 5;
                binding.tabOneWeek.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabOneWeek.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont)
                        : App.getInstance().getResources().getColor(R.color.colorFontDark));
                break;
            case Define.TYPE_CHART_ONE_MONTH:
                pr.loadFilter(21, key, a, b, c);
                count = 21;
                binding.tabOneMonth.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabOneMonth.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont)
                        : App.getInstance().getResources().getColor(R.color.colorFontDark));
                break;
            case Define.TYPE_CHART_THREE_MONTH:
                pr.loadFilter(63, key, a, b, c);
                count = 63;
                binding.tabThreeMonth.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabThreeMonth.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont)
                        : App.getInstance().getResources().getColor(R.color.colorFontDark));
                break;
            case Define.TYPE_CHART_SIX_MONTH:
                pr.loadFilter(126, key, a, b, c);
                count = 126;
                binding.tabSixMonth.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabSixMonth.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont)
                        : App.getInstance().getResources().getColor(R.color.colorFontDark));
                break;
            case Define.TYPE_CHART_ONE_YEAR:
                pr.loadFilter(252, key, a, b, c);
                count = 252;
                binding.tabOneYear.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabOneYear.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont)
                        : App.getInstance().getResources().getColor(R.color.colorFontDark));
                break;
            case Define.TYPE_CHART_ALL:
                pr.loadFilter(408, key, a, b, c);
                count = 408;
                binding.tabAll.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabAll.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont)
                        : App.getInstance().getResources().getColor(R.color.colorFontDark));
                break;
            default:
                binding.tabOneWeek.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabOneWeek.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.colorFont)
                        : App.getInstance().getResources().getColor(R.color.colorFontDark));
                break;
        }
    }

    private void chartconfig() {
        // TODO:HoaDT 6/25/2018 barchart
        binding.barChart.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barChart.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barChart.setNoDataTextTypeface(typeface);
        binding.barChart.getAxisRight().setDrawGridLines(true);
        binding.barChart.setPinchZoom(false);

        binding.barChart.setBackgroundColor(isLight ? getResources().getColor(R.color.colorBackground) :
                getResources().getColor(R.color.colorBackgroundDark));
        binding.barChart.setDrawBarShadow(false);
        binding.barChart.setDrawValueAboveBar(true);
        binding.barChart.getDescription().setEnabled(false);
        binding.barChart.setMaxVisibleValueCount(10000);
        binding.barChart.setDrawGridBackground(false);
        binding.barChart.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barChart.setBorderColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barChart.getLegend().setEnabled(false);
        binding.barChart.getXAxis().setTypeface(typeface);
        binding.barChart.getAxisLeft().setTypeface(typeface);
        binding.barChart.getAxisRight().setTypeface(typeface);

        // TODO:HoaDT 7/6/2018 candleStick
        binding.candleStick.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.setNoDataTextTypeface(typeface);
        binding.candleStick.getAxisRight().setDrawGridLines(true);
        binding.candleStick.setPinchZoom(false);

        // TODO:HoaDT 7/6/2018 combineChart1
        binding.combineChart1.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.combineChart1.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.combineChart1.setNoDataTextTypeface(typeface);
        binding.combineChart1.getAxisRight().setDrawGridLines(true);
        binding.combineChart1.setPinchZoom(false);
        binding.combineChart1.getDescription().setEnabled(false);
        binding.combineChart1.setBackgroundColor(isLight ? getResources().getColor(R.color.white) :
                getResources().getColor(R.color.black));
        binding.combineChart1.setDrawGridBackground(false);
        binding.combineChart1.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE
        });
        binding.combineChart1.getXAxis().setTypeface(typeface);
        binding.combineChart1.getAxisLeft().setTypeface(typeface);
        binding.combineChart1.getAxisRight().setTypeface(typeface);

        // TODO:HoaDT 7/6/2018 combineChart2
        binding.combineChart2.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.combineChart2.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.combineChart2.setNoDataTextTypeface(typeface);
        binding.combineChart2.getAxisRight().setDrawGridLines(true);
        binding.combineChart2.setPinchZoom(false);
        binding.combineChart2.getDescription().setEnabled(false);
        binding.combineChart2.setBackgroundColor(isLight ? getResources().getColor(R.color.white) :
                getResources().getColor(R.color.black));
        binding.combineChart2.setDrawGridBackground(false);

        binding.combineChart2.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        // TODO:HoaDT 7/6/2018 Linechart
        binding.lineChart.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.lineChart.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.lineChart.setNoDataTextTypeface(typeface);
        binding.lineChart.setDrawGridBackground(false);
        binding.lineChart.setBackgroundColor(isLight ? getResources().getColor(R.color.white) :
                getResources().getColor(R.color.black));
        binding.lineChart.getLegend().setEnabled(false);
        binding.lineChart.getDescription().setEnabled(false);
        binding.lineChart.setPinchZoom(false);
        binding.lineChart.getXAxis().setTypeface(typeface);
        binding.lineChart.getAxisLeft().setTypeface(typeface);
        binding.lineChart.getAxisRight().setTypeface(typeface);

        // TODO:HoaDT 7/6/2018 candleStick
        binding.candleStick.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.setNoDataTextTypeface(typeface);
        binding.candleStick.setBackgroundColor(isLight ? getResources().getColor(R.color.colorBackground) :
                getResources().getColor(R.color.colorBackgroundDark));
        binding.candleStick.getDescription().setEnabled(false);
        binding.candleStick.setPinchZoom(false);
        binding.candleStick.setAutoScaleMinMaxEnabled(false);
        binding.candleStick.setDoubleTapToZoomEnabled(false);
        binding.candleStick.setDrawGridBackground(false);
        binding.candleStick.getXAxis().setTypeface(typeface);
        binding.candleStick.getAxisLeft().setTypeface(typeface);
        binding.candleStick.getAxisRight().setTypeface(typeface);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.tabOneWeek.getId()) {
            selectTap(Define.TYPE_CHART_ONE_WEEK);
        } else if (v.getId() == binding.tabOneMonth.getId()) {
            selectTap(Define.TYPE_CHART_ONE_MONTH);
        } else if (v.getId() == binding.tabThreeMonth.getId()) {
            selectTap(Define.TYPE_CHART_THREE_MONTH);
        } else if (v.getId() == binding.tabSixMonth.getId()) {
            selectTap(Define.TYPE_CHART_SIX_MONTH);
        } else if (v.getId() == binding.tabOneYear.getId()) {
            selectTap(Define.TYPE_CHART_ONE_YEAR);
        } else if (v.getId() == binding.tabAll.getId()) {
            selectTap(Define.TYPE_CHART_ALL);
        }
    }

    // TODO:HoaDT 6/25/2018 CandleStick + BarChart
    @Override
    public void showChart(List<HistoryChartOtherIndex> yVals1) {
        binding.candleStick.clear();
        binding.barChart.clear();
        binding.combineChart2.clear();
        binding.combineChart1.clear();
        binding.lineChart.clear();
        GetData.getCendel(App.getInstance(), binding, yVals1, getDate(yVals1));
        GetData.getBarValue(App.getInstance(), binding, yVals1, getDate(yVals1));
    }

    @Override
    public void showChart(ArrayList<HistoryChartOtherIndex> yVals1, List<MacdData> getMacd) {
        binding.candleStick.clear();
        binding.barChart.clear();
        binding.combineChart2.clear();
        binding.combineChart1.clear();
        binding.lineChart.clear();
        GetData.getCendel(App.getInstance(), binding, yVals1, getDate(yVals1));
        GetData.getcombind(App.getInstance(), binding, getMacd, getDate(yVals1));
    }

    // TODO:HoaDT 6/25/2018 CandleStick  + LineChart
    @Override
    public void showChart(ArrayList<HistoryChartOtherIndex> yVals1, ArrayList<Double> sma,
                          ArrayList<Double> sma1, double[] listRsi, String key, int p1, int p3) {
        binding.candleStick.clear();
        binding.barChart.clear();
        binding.combineChart2.clear();
        binding.combineChart1.clear();
        binding.lineChart.clear();
        GetData.getCendel(App.getInstance(), binding, yVals1, getDate(yVals1));
        GetData.showlineC(App.getInstance(), binding, listRsi, sma, sma1, getDate(yVals1), p1, p3, key);
    }

    // TODO:HoaDT 6/25/2018 CombineChart1 + BarChart
    @Override
    public void showChart(ArrayList<Double> top, ArrayList<Double> mid, ArrayList<Double> bot,
                          ArrayList<Float> ema, ArrayList<HistoryChartOtherIndex> yVals1, String key) {
        binding.candleStick.clear();
        binding.barChart.clear();
        binding.combineChart2.clear();
        binding.combineChart1.clear();
        binding.lineChart.clear();
        GetData.getcombind1(App.getInstance(), binding, yVals1, ema, top, mid, bot, getDate(yVals1), key);
        GetData.getBarValue(App.getInstance(), binding, yVals1, getDate(yVals1));
    }

    private ArrayList<String> getDate(List<HistoryChartOtherIndex> yVals1) {
        final ArrayList<String> Date = new ArrayList<>();
        for (int i = 0; i < yVals1.size(); i++) {
            Date.add(yVals1.get(i).getCharTime());
        }
        return Date;
    }

    @Override
    public void onload() {

    }

    @Override
    public void loadok() {

    }
}

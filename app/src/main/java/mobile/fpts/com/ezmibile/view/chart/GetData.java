package mobile.fpts.com.ezmibile.view.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import mobile.fpts.com.ezmibile.databinding.FragmentChartBinding;
import mobile.fpts.com.ezmibile.model.entity.chart.MacdData;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import mobile.fpts.com.ezmibile.R;

public class GetData {
    private static final NavigableMap<Float, String> suffixes = new TreeMap<>();

    static {
        suffixes.put((float) 1_000L, "K");
        suffixes.put((float) 1_000_000L, "M");
        suffixes.put((float) 1_000_000_000L, "G");
        suffixes.put((float) 1_000_000_000_000L, "T");
        suffixes.put((float) 1_000_000_000_000_000L, "P");
        suffixes.put((float) 1_000_000_000_000_000_000L, "E");
    }

    private static Double UNDEF_VALUE = new Double(Double.MIN_VALUE);

    private static BarData getBar(Context context, FragmentChartBinding binding, List<MacdData> getMacd) {


        List<Integer> colors = new ArrayList<Integer>();

        final ArrayList<BarEntry> datachart = new ArrayList<>();

        for (int i = 0; i < getMacd.size(); i++) {

            datachart.add(new BarEntry(i, getMacd.get(i).getBardataMACD()));

            if (datachart.get(i).getY() < 0) {
                colors.add(context.getResources().getColor(R.color.red));
            } else {
                colors.add(context.getResources().getColor(R.color.greenor));

            }

        }


        BarDataSet set1;

        if (binding.combineChart2.getData() != null &&
                binding.combineChart2.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) binding.combineChart2.getData().getDataSetByIndex(0);
            set1.setValues(datachart);
            binding.combineChart2.getData().notifyDataChanged();
            binding.combineChart2.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(datachart, null);

            set1.setDrawIcons(false);

            set1.setDrawValues(false);
            set1.setValueTextColor(context.getResources().getColor(R.color.colorFont));

            set1.setColors(colors);


            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
//
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            //data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            return data;
            //binding.barchart1.setData(data);


//            for (IDataSet set : binding.barchart1.getData().getDataSets())
//                set.setDrawValues(!set.isDrawValuesEnabled());
//
//            binding.barchart1.invalidate();

        }

        return null;

    }

    private static CandleData getcandle(List<HistoryChartOtherIndex> yVals1, Context context) {
        final ArrayList<CandleEntry> chartcandle = new ArrayList<>();

        for (int i = 0; i < yVals1.size(); i++) {

            chartcandle.add(new CandleEntry(
                    i,
                    Float.parseFloat(yVals1.get(i).getChartH()),
                    Float.parseFloat(yVals1.get(i).getChartL()),
                    Float.parseFloat(yVals1.get(i).getChartO()),
                    Float.parseFloat(yVals1.get(i).getChartC()),
                    null));
        }
        if (chartcandle != null && chartcandle.size() > 0) {
            CandleDataSet set12 = new CandleDataSet(chartcandle, "Data Set");

            set12.setDrawIcons(false);
            set12.setDrawValues(false);
            set12.setAxisDependency(YAxis.AxisDependency.LEFT);
            set12.setShadowColor(Color.DKGRAY);
            set12.setShadowWidth(0.7f);
            set12.setDecreasingColor(Color.RED);
            set12.setDecreasingPaintStyle(Paint.Style.FILL);
            set12.setIncreasingColor(Color.rgb(122, 242, 84));
            set12.setNeutralColor(Color.BLUE);
            set12.setIncreasingPaintStyle(Paint.Style.FILL);

            set12.setValueTextColor(context.getResources().getColor(R.color.colorFont));


            return new CandleData(set12);


        }


        return null;

    }

    private static LineData generateDataLine(Context context, List<MacdData> getMacd) {
        ArrayList<Entry> e1 = new ArrayList<Entry>();
        ArrayList<Entry> e2 = new ArrayList<Entry>();


        for (int index = 0; index < getMacd.size(); index++)
            e1.add(new Entry(index, getMacd.get(index).getMacd()));

        for (int index = 0; index < getMacd.size(); index++)
            e2.add(new Entry(index, getMacd.get(index).getSignalMACD()));


        LineDataSet d1 = new LineDataSet(e1, null);
        d1.setLineWidth(1.5f);
        d1.setCircleRadius(0f);
        d1.setDrawCircles(false);
        d1.setHighLightColor(Color.rgb(20, 150, 200));
//        d1.setColors(Color.GRAY);
        d1.setColors(context.getResources().getColor(R.color.greenor));
        d1.setDrawValues(false);

        LineDataSet d2 = new LineDataSet(e2, null);
        d2.setLineWidth(1.5f);
        d2.setCircleRadius(0f);
        d2.setDrawCircles(false);
        d2.setHighLightColor(Color.rgb(20, 150, 200));
        d2.setDrawValues(false);

        d2.setColors(context.getResources().getColor(R.color.red));
        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        sets.add(d2);

        LineData cd = new LineData(sets);
        return cd;


    }

    private static LineData getLine(Context context, double[] listRsi) {
        ArrayList<Entry> e1 = new ArrayList<Entry>();
        ArrayList<Entry> e2 = new ArrayList<Entry>();
        ArrayList<Entry> e3 = new ArrayList<Entry>();

//        double[] listRsi = pr.RSIc(yVals1, value);

        for (int index = 0; index < listRsi.length; index++)
            e1.add(new Entry(index, (float) listRsi[index]));

        for (int index = 0; index < listRsi.length; index++)
            e2.add(new Entry(index, 30));


        for (int index = 0; index < listRsi.length; index++)
            e3.add(new Entry(index, 70));


        LineDataSet d1 = new LineDataSet(e1, null);
        d1.setLineWidth(1.5f);
        d1.setCircleRadius(0f);
        d1.setDrawCircles(false);
        d1.setHighLightColor(Color.rgb(20, 150, 200));
//        d1.setColors(Color.GRAY);
        d1.setColors(context.getResources().getColor(R.color.black));
        d1.setDrawValues(false);

        LineDataSet d2 = new LineDataSet(e2, null);
        d2.setLineWidth(1.5f);
        d2.setCircleRadius(0f);
        d2.setDrawCircles(false);
        d2.setHighLightColor(Color.rgb(20, 150, 200));
        d2.setDrawValues(false);

        d2.setColors(context.getResources().getColor(R.color.orange));

        LineDataSet d3 = new LineDataSet(e3, null);
        d3.setLineWidth(1.5f);
        d3.setCircleRadius(0f);
        d3.setDrawCircles(false);
        d3.setHighLightColor(Color.rgb(20, 150, 200));
        d3.setColors(context.getResources().getColor(R.color.orange));
        d3.setDrawValues(false);


        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        sets.add(d2);
        sets.add(d3);

        LineData cd = new LineData(sets);
        return cd;


    }

    private static LineData getLine(Context context, ArrayList<Float> ema) {
        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int index = 0; index < ema.size(); index++)
            e1.add(new Entry(index, ema.get(index)));


        LineDataSet d1 = new LineDataSet(e1, null);
        d1.setLineWidth(1.5f);
        d1.setCircleRadius(0f);
        d1.setDrawCircles(false);
        d1.setHighLightColor(Color.rgb(20, 150, 200));
//        d1.setColors(Color.GRAY);
        d1.setColors(context.getResources().getColor(R.color.black));
        d1.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        return new LineData(sets);


    }

    private static LineData getLine(Context context, ArrayList<Double> top, ArrayList<Double> mid, ArrayList<Double> bot) {
        ArrayList<Entry> e1 = new ArrayList<Entry>();
        ArrayList<Entry> e2 = new ArrayList<Entry>();
        ArrayList<Entry> e3 = new ArrayList<Entry>();

        for (int index = 0; index < top.size(); index++)
            e1.add(new Entry(index, top.get(index).floatValue()));

        for (int index = 0; index < mid.size(); index++)
            e2.add(new Entry(index, mid.get(index).floatValue()));

        for (int index = 0; index < bot.size(); index++)
            e3.add(new Entry(index, bot.get(index).floatValue()));


        LineDataSet d1 = new LineDataSet(e1, null);
        d1.setLineWidth(1.5f);
        d1.setCircleRadius(0f);
        d1.setDrawCircles(false);
        d1.setHighLightColor(Color.rgb(20, 150, 200));
//        d1.setColors(Color.GRAY);
        d1.setColors(context.getResources().getColor(R.color.black));
        d1.setDrawValues(false);

        LineDataSet d2 = new LineDataSet(e2, null);
        d2.setLineWidth(1.5f);
        d2.setCircleRadius(0f);
        d2.setDrawCircles(false);
        d2.setHighLightColor(Color.rgb(20, 150, 200));
        d2.setDrawValues(false);

        d2.setColors(context.getResources().getColor(R.color.orange));

        LineDataSet d3 = new LineDataSet(e3, null);
        d3.setLineWidth(1.5f);
        d3.setCircleRadius(0f);
        d3.setDrawCircles(false);
        d3.setHighLightColor(Color.rgb(20, 150, 200));
        d3.setColors(context.getResources().getColor(R.color.black));
        d3.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        sets.add(d2);
        sets.add(d3);
        return new LineData(sets);
    }

    private static LineData getLine(Context context, ArrayList<Double> pk, ArrayList<Double> pk1) {
        ArrayList<Entry> e1 = new ArrayList<Entry>();
        ArrayList<Entry> e2 = new ArrayList<Entry>();
        ArrayList<Entry> e3 = new ArrayList<Entry>();
        ArrayList<Entry> e4 = new ArrayList<Entry>();


        for (int i = 0; i < pk.size(); i++) e1.add(new Entry(i, pk.get(i).floatValue()));
        for (int i = 0; i < pk1.size(); i++) e2.add(new Entry(i, pk1.get(i).floatValue()));
        for (int i = 0; i < pk1.size(); i++) e3.add(new Entry(i, 20));
        for (int i = 0; i < pk1.size(); i++) e4.add(new Entry(i, 80));


        LineDataSet d1 = new LineDataSet(e1, null);
        d1.setLineWidth(1.5f);
        d1.setCircleRadius(0f);
        d1.setDrawCircles(false);
        d1.setColors(context.getResources().getColor(R.color.black));
        d1.setDrawValues(false);

        LineDataSet d2 = new LineDataSet(e2, null);
        d2.setLineWidth(1.5f);
        d2.setCircleRadius(0f);
        d2.setDrawCircles(false);

        d2.setColors(context.getResources().getColor(R.color.red));


        d2.setDrawValues(false);


        LineDataSet gioihanMACD1 = new LineDataSet(e3, null);
        gioihanMACD1.setLineWidth(1.5f);
        gioihanMACD1.setCircleRadius(0f);
        gioihanMACD1.setDrawCircles(false);
        gioihanMACD1.setHighLightColor(Color.rgb(20, 150, 200));
        gioihanMACD1.setColors(context.getResources().getColor(R.color.orange));
        gioihanMACD1.setDrawValues(false);

        LineDataSet gioihanMACD2 = new LineDataSet(e4, null);
        gioihanMACD2.setLineWidth(1.5f);
        gioihanMACD2.setCircleRadius(0f);
        gioihanMACD2.setDrawCircles(false);
        gioihanMACD2.setHighLightColor(Color.rgb(20, 150, 200));
        gioihanMACD2.setColors(context.getResources().getColor(R.color.orange));
        gioihanMACD2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        sets.add(d2);
        sets.add(gioihanMACD1);
        sets.add(gioihanMACD2);

        LineData cd = new LineData(sets);
        return cd;
    }

    public static void getcombind(Context context, FragmentChartBinding binding, List<MacdData> getMacd, ArrayList<String> Date) {
        binding.combineChart2.getLegend().setEnabled(false);
        YAxis leftAxis = binding.combineChart2.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        leftAxis.setEnabled(true);
        leftAxis.setLabelCount(6, false);
        leftAxis.setEnabled(true);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(context.getResources().getColor(R.color.colorFont));
        leftAxis.setDrawZeroLine(true); // draw a zero line
        leftAxis.setZeroLineColor(Color.GRAY);
        leftAxis.setZeroLineWidth(0.7f);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(Date);
        XAxis xAxis = binding.combineChart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(5, true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(xAxisFormatter);

        CombinedData data = new CombinedData();


        data.setData(generateDataLine(context, getMacd));
        data.setData(getBar(context, binding, getMacd));

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        binding.combineChart2.setData(data);
        binding.combineChart2.invalidate();
    }

    public static void getcombind1(Context context, FragmentChartBinding binding,
                                   ArrayList<HistoryChartOtherIndex> yVals1, ArrayList<Float> ema, ArrayList<Double> top, ArrayList<Double> mid, ArrayList<Double> bot, ArrayList<String> Date, String key) {
        binding.combineChart1.getLegend().setEnabled(false);
        binding.combineChart1.getAxisRight().setEnabled(false);

        YAxis leftAxis = binding.combineChart1.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        leftAxis.setEnabled(true);
        leftAxis.setLabelCount(6, false);
        leftAxis.setEnabled(true);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(context.getResources().getColor(R.color.colorFont));
        leftAxis.setDrawZeroLine(true); // draw a zero line
        leftAxis.setZeroLineColor(Color.GRAY);
        leftAxis.setZeroLineWidth(0.7f);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(Date);
        XAxis xAxis = binding.combineChart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(5, true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(xAxisFormatter);

        CombinedData data = new CombinedData();

        data.setData(getcandle(yVals1, context));
        if (key.equals("EMA")) data.setData(getLine(context, ema));
        else data.setData(getLine(context, top, mid, bot));

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        binding.combineChart1.setData(data);
        binding.combineChart1.invalidate();
    }

    public static void getBarValue(Context context, FragmentChartBinding binding, List<HistoryChartOtherIndex> yVals1,
                                   ArrayList<String> Date) {

        final ArrayList<BarEntry> datachart = new ArrayList<>();

        for (int i = 0; i < yVals1.size(); i++) {
            datachart.add(new BarEntry(i, Float.parseFloat(yVals1.get(i).getCharV())));
            Date.add(yVals1.get(i).getCharTime());
        }
        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(Date);
        binding.barChart.getAxisRight().setEnabled(false);
        XAxis xAxis = binding.barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(xAxisFormatter);

        xAxis.setLabelCount(5, true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setTextColor(context.getResources().getColor(R.color.colorFont));
        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = binding.barChart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setAxisLineColor(Color.BLACK);

        leftAxis.setTextColor(context.getResources().getColor(R.color.colorFont));
        leftAxis.setEnabled(true);
        leftAxis.setValueFormatter(custom);
        leftAxis.setAxisMinimum(0f); // start at zero
        BarDataSet set1;

        if (binding.barChart.getData() != null &&
                binding.barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) binding.barChart.getData().getDataSetByIndex(0);
            set1.setValues(datachart);
            binding.barChart.getData().notifyDataChanged();
            binding.barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(datachart, null);

            set1.setDrawIcons(false);

            set1.setValueTextColor(context.getResources().getColor(R.color.colorFont));
            set1.setColors(context.getResources().getColor(R.color.green));
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
//
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            //data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            binding.barChart.setData(data);
            for (IDataSet set : binding.barChart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());
            binding.barChart.invalidate();
        }
    }

    public static void showlineC(Context context, FragmentChartBinding binding, double[] listRsi, ArrayList<Double> pk, ArrayList<Double> pk1, ArrayList<String> Date, int vlu, int d, String key) {

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(Date);
        XAxis xAxis = binding.lineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setLabelCount(5, true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(xAxisFormatter);
        //Set Cột bên trái
        YAxis leftAxis = binding.lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines


        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        binding.lineChart.getAxisRight().setEnabled(false);

//        binding.lineChart.setData(getLine(context, binding, listRsi, vlu));
        if (key.equals("RSI")) binding.lineChart.setData(getLine(context, listRsi));
        else binding.lineChart.setData(getLine(context, pk, pk1));


    }

    public static void getCendel(Context context, FragmentChartBinding binding, List<HistoryChartOtherIndex> yVals1, List<String> Date) {
        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(Date);
        ////
        binding.candleStick.getXAxis().setValueFormatter(xAxisFormatter);
        XAxis xAxis1 = binding.candleStick.getXAxis();
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis1.setDrawGridLines(false);
        xAxis1.setTextColor(context.getResources().getColor(R.color.colorFont));
        xAxis1.setAxisLineColor(Color.BLACK);
        xAxis1.setDrawAxisLine(true);
        xAxis1.setLabelCount(5, true);
        xAxis1.setCenterAxisLabels(true);
        YAxis leftAxis1 = binding.candleStick.getAxisLeft();
//        leftAxis.setEnabled(false);
        leftAxis1.setLabelCount(7, false);
        leftAxis1.setDrawGridLines(false);
        leftAxis1.setTextColor(context.getResources().getColor(R.color.colorFont));
        leftAxis1.setDrawAxisLine(true);
        leftAxis1.setAxisLineColor(Color.BLACK);
        leftAxis1.setTextSize(10f);

        YAxis rightAxis1 = binding.candleStick.getAxisRight();
        rightAxis1.setEnabled(false);
        rightAxis1.setAxisLineColor(Color.BLACK);
        binding.candleStick.setData(getcandle(yVals1, context));
        binding.candleStick.invalidate();
        binding.candleStick.getLegend().setEnabled(false);
        //
        for (IDataSet set : binding.candleStick.getData().getDataSets())
            set.setDrawValues(false);

        binding.candleStick.invalidate();
    }

    private static class DayAxisValueFormatter implements IAxisValueFormatter {

        private List<String> Date = new ArrayList<>();

        public DayAxisValueFormatter(List<String> Date) {
            this.Date = Date;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            if (value >= Date.size()) return null;
            else
                return Date.get((int) value);
        }
    }

    private static class MyAxisValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyAxisValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return format(value);
        }
    }

    public static List<String> getNamecompany(String res) {

        final List<String> lg = new ArrayList<>();
        String[] lines = res.split("@");
        for (int i = 0; i < lines.length - 1; i++) {

            String[] parts = lines[i].split("#");

            try {
                lg.add(parts[1] + " - " + parts[2]);
            } catch (NullPointerException e) {
                lg.add("");
            }
        }
        return lg;
    }

    public static ArrayList<HistoryChartOtherIndex> getDt(String res) {
        ArrayList<HistoryChartOtherIndex> yVals1 = new ArrayList<>();
        try {
            String[] line = res.split("\\]\\,\\[");
            for (int i = 0; i < line.length; i++) {
                String[] part = line[i].split("\\,");
                HistoryChartOtherIndex chartOtherIndex = new HistoryChartOtherIndex();

                String datet;
                if (i == 0 || i == line.length - 1) {
                    chartOtherIndex.setCharTime(part[0].substring(3, part[0].length() - 1).replace("\"", ""));
                } else {
                    chartOtherIndex.setCharTime(part[0].replace("\"", ""));
                }
//                            if (datet.contains(":")) datet = datet.substring(0, datet.length()-5);
//                            chartOtherIndex.setCharTime(datet);
                chartOtherIndex.setChartO(part[1]);
                chartOtherIndex.setChartH(part[2]);
                chartOtherIndex.setChartL(part[3]);
                chartOtherIndex.setChartC(part[4]);
                if (i == line.length - 1) {
                    chartOtherIndex.setCharV(part[5].substring(0, part[5].length() - 2));
                } else {
                    chartOtherIndex.setCharV(part[5]);
                }
                yVals1.add(chartOtherIndex);
            }

        } catch (IndexOutOfBoundsException e) {

        }
        return yVals1;
    }


    public static ArrayList<Double> bbands(ArrayList<HistoryChartOtherIndex> data, int period, double mult, int type) {

        ArrayList<Double> bband = new ArrayList<>();
        period = period - 1;
        for (int i = 0; i < data.size(); i++) {
            if (i < period) {
                bband.add((double) 0);
            } else {
                double _s = 0;
                ArrayList<HistoryChartOtherIndex> dataSigma = new ArrayList<>();
                for (int j = 0; j < data.subList(i - period, i + 1).size(); j++) {
                    _s = _s + Double.parseDouble(data.subList(i - period, i + 1).get(j).getChartC());
                    dataSigma.add(data.subList(i - period, i + 1).get(j));
                }
                double sigma1 = computeStdDev(dataSigma, period);
                double mu = _s / (period + 1);
                if (type == 1) {
                    bband.add(mu + mult * sigma1);
                } else {
                    if (type == 2) {
                        bband.add(mu);
                    } else {
                        if (type == 3) {
                            bband.add(mu - mult * sigma1);
                        }
                    }
                }
            }

        }

        return bband;
    }

    public static double computeStdDev(ArrayList<HistoryChartOtherIndex> data, int period) {
        int start = period - 1;
        int count = data.size();
        double[] res = new double[count];
        double res1;

        for (int i = 0; i < start; ++i) {
            res[i] = UNDEF_VALUE.doubleValue();
        }

        int j = (start < -1) ? -start : 0;

        double[] vals = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            vals[i] = Double.parseDouble(data.get(i).getChartC());
        }


        double avg, dev, sum;

        avg = computeAverage(vals, j, period);
        sum = 0;
        for (int k = j, c = k + period; k < c; ++k) {
            if (k < vals.length) {
                dev = avg - vals[k];
                sum += dev * dev;
            }
        }
        res1 = Math.sqrt(sum / period);

        return res1;
    }

    public static double computeAverage(double[] vals, int start, int period) {
        double avg = 0;
        for (int i = start, c = start + period; i < c; ++i) {
            if (i < vals.length) {
                avg += vals[i];
            } else {
                break;
            }
        }
        return avg / period;
    }

    public static ArrayList<Double> _sma(ArrayList<Double> data, int period) {
        ArrayList<Double> s_ = new ArrayList<>();
        float _sum = 0;
        period = period - 1;
        for (int i = 0; i < data.size(); i++) {
            if (i < period) {
                s_.add(Double.valueOf(0));
            } else {
                _sum = 0;
                int n = data.subList(i - period, i + 1).size();
                for (int j = 0; j < n; j++) {
                    _sum += data.subList(i - period, i + 1).get(j);
                }
                s_.add((double) _sum / (period + 1));
            }
            //var t = data.subList(i - period, i + 1);

        }
        return s_;
    }

    public static ArrayList<Float> _ema(ArrayList<HistoryChartOtherIndex> data, int period) {
        ArrayList<Float> e_ = new ArrayList<>();
        float mult = (float) 2 / (period + 1);

        for (int i = 0; i < data.size(); i++) {
            if (i < period) {
                e_.add(Float.valueOf(0));

            } else {
                if (e_.size() > 0) {
                    float nt = (float) (1 - mult) * e_.get(i - 1);
                    float ft = Float.parseFloat(data.get(i).getChartO());
                    float nd = ft * mult;
                    e_.add(nd + nt);
                } else {
                    e_.add(Float.parseFloat(data.get(i).getChartO()));
                }
            }
        }
        return e_;
    }

    public static double[] RSIc(ArrayList<HistoryChartOtherIndex> data, int lookback) {
        double up = 0, down = 0;
        double rs;

        double[] rsi = new double[data.size()];
        for (int k = 0; k < data.size(); k++) {
            rsi[k] = UNDEF_VALUE.doubleValue();
        }

        // empty array plus initialization for 0.

        double prev = Double.parseDouble(data.get(0).getChartC());  // close
        if (lookback > data.size()) lookback = data.size();
        for (int i = 1; i < lookback; i++) {//huynq modified 10-10-2014
            double diff = Double.parseDouble(data.get(i).getChartC()) - prev;  // change
            if (diff > 0) {
                up = up + diff; // Sumgain
            } else {
                down = down - diff;  // Sumloss
            }
            // rsi.push(undefined);
            prev = Double.parseDouble(data.get(i).getChartC());
        }
        up /= lookback; // avg Gain
        down /= lookback; // avg Loss
        rs = (down == 0) ? 0 : (up / down); // RS		// modified huynq 14-10-2014
        for (int i = lookback; i < data.size(); i++) {
            double diff = Double.parseDouble(data.get(i).getChartC()) - prev;
            if (i != lookback) {
                if (diff > 0) {
                    up = (up * (lookback - 1) + diff) / lookback;
                    down = down * (lookback - 1) / lookback;
                } else {
                    down = (down * (lookback - 1) - diff) / lookback;
                    up = up * (lookback - 1) / lookback;
                }
            }
            rs = (down == 0) ? 0 : (up / down); // RS		// modified huynq 14-10-2014

            rsi[i] = 100 - 100 / (1 + rs);

            prev = Double.parseDouble(data.get(i).getChartC());
        }


        return rsi;
    }

    public static ArrayList<Double> computerD(ArrayList<HistoryChartOtherIndex> arrayListHistoryChart, int k) {

        float min, max;
        ArrayList<Float> d_ = new ArrayList<>();
        k = k - 1;
        ArrayList<Double> pk = new ArrayList<>();
        for (int i = 0; i < arrayListHistoryChart.size(); i++) {
            if (i < k) {
                pk.add((double) 0);
            } else {
                d_ = _minmax2d(arrayListHistoryChart.subList(i - k, i + 1));
                min = d_.get(0);
                max = d_.get(1);
                try {
                    pk.add((Double.parseDouble(arrayListHistoryChart.get(i).getChartC()) - min) / (max - min) * 100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pk;

    }

    public static ArrayList<Float> _minmax2d(List<HistoryChartOtherIndex> historyChartOtherIndexes) {
        float max = 0;
        float min = 10000000;

        float a[][] = new float[historyChartOtherIndexes.size()][4];
        for (int i = 0; i < historyChartOtherIndexes.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    a[i][j] = Float.parseFloat(historyChartOtherIndexes.get(i).getChartO());
                } else {
                    if (j == 1) {
                        a[i][j] = Float.parseFloat(historyChartOtherIndexes.get(i).getChartH());
                    } else {
                        if (j == 2) {
                            a[i][j] = Float.parseFloat(historyChartOtherIndexes.get(i).getChartL());
                        } else {
                            if (j == 3) {
                                a[i][j] = Float.parseFloat(historyChartOtherIndexes.get(i).getChartC());
                            }
                        }
                    }
                }
            }
        }

        ArrayList<Float> _d = new ArrayList<>();
        for (int i = 0; i < historyChartOtherIndexes.size(); i++) {
            for (int j = 0; j < 4; j++)

            {
                if (a[i][j] >= max) max = a[i][j];
                if (a[i][j] < min) min = a[i][j];
            }
        }
        _d.add(min);
        _d.add(max);
        return _d;
    }

    public static List<MacdData> getMacd(ArrayList<HistoryChartOtherIndex> data, int p1, int p2, int signal) {
        float[] d = new float[data.size()];
        for (int i = 0; i < data.size(); i++) {
            d[i] = Float.parseFloat(data.get(i).getChartC());
        }
        List<MacdData> macdData = new ArrayList<>();
        MacdData macdDataObject = new MacdData();
        macdDataObject.setEma1(Float.parseFloat(data.get(0).getChartC()));
        macdDataObject.setEma2(Float.parseFloat(data.get(0).getChartC()));
        macdDataObject.setMacd(0);
        macdDataObject.setSignalMACD(0);
        macdData.add(macdDataObject);
        for (int i = 1; i < d.length; i++) {
            MacdData macdDataObject1 = new MacdData();

            float emaN1 = macdData.get(i - 1).getEma1();
            float a1 = ((float) 2 / (p1 + 1)) * d[i];
            float a2 = 1 - ((float) 2 / (p1 + 1));

            float ema1 = a1 + a2 * emaN1;

            float emaN2 = macdData.get(i - 1).getEma2();
            float b1 = ((float) 2 / (p2 + 1)) * d[i];
            float b2 = 1 - ((float) 2 / (p2 + 1));
            float ema2 = b1 + b2 * emaN2;

            macdDataObject1.setEma1(ema1);
            macdDataObject1.setEma2(ema2);

            macdDataObject1.setMacd(ema1 - ema2);


            macdDataObject1.setSignalMACD(((float) 2 / (signal + 1)) * (ema1 - ema2) + (1 - ((float) 2 / (signal + 1))) * macdData.get(i - 1).getSignalMACD());

            macdDataObject1.setBardataMACD(macdDataObject1.getMacd() - macdDataObject1.getSignalMACD());
            macdData.add(macdDataObject1);

        }

        return macdData;
    }

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

}

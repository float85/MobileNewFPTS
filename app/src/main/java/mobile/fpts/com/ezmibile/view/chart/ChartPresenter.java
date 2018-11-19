package mobile.fpts.com.ezmibile.view.chart;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.api.ApiClientImp;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.model.entity.chart.MacdData;
import mobile.fpts.com.ezmibile.util.Define;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FIT-thuctap22 on 2/7/2018.
 */

public class ChartPresenter {

    public static final String VOLUMN = "VOLUMN";
    public static final String MACD = "MACD";
    public static final String RSI = "RSI";
    public static final String BOLINGER_BAND = "BOLINGER_BAND";
    public static final String EMA = "EMA";
    public static final String STOCHASTICS = "STOCHASTICS";

    private static Double UNDEF_VALUE = new Double(Double.MIN_VALUE);
    AppDatabase database;
    IViewChart view;
    Context context;
    List<HistoryChartOtherIndex> yVals1;
    double[] listRsi = null;
    ArrayList<Double> sma = new ArrayList<>();
    ArrayList<Double> pks_ = new ArrayList<>();
    ArrayList<Double> pds_ = new ArrayList<>();
    ArrayList<Double> top = new ArrayList<>();
    ArrayList<Double> mid = new ArrayList<>();
    ArrayList<Double> bot = new ArrayList<>();

    ArrayList<Float> ema = new ArrayList<>();

    public ChartPresenter(IViewChart view, Context context, ArrayList<HistoryChartOtherIndex> yVals1) {
        this.context = context;
        this.view = view;
        this.yVals1 = yVals1;
        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        // TODO:HoaDT 6/25/2018  
        if (yVals1.isEmpty()) {
            loadDt("VNINDEX", 5, VOLUMN, 12, 6, 9);
        }

    }

    @SuppressLint("NewApi")
    public void loadDt(String code, final int sizedate, final String key, final int p1, final int p2, final int p3) {
        if (!yVals1.isEmpty()) {
            yVals1.clear();
            if (listRsi != null && listRsi.length > 0) listRsi = null;
            if (!sma.isEmpty()) sma.clear();
            if (!pks_.isEmpty()) pks_.clear();
            if (!pds_.isEmpty()) pds_.clear();
            if (!top.isEmpty()) top.clear();
            if (!mid.isEmpty()) mid.clear();
            if (!bot.isEmpty()) bot.clear();
            if (!ema.isEmpty()) ema.clear();

        }

        view.onload();
        ApiClientImp.getInstance().getChartHistory(code)
                .subscribeOn(Schedulers.io())
                .map(s -> s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                            yVals1 = s;
                            view.loadok();
                            if (!yVals1.isEmpty()) {
                                computerData cp = new computerData(key, p1, p2, p3, sizedate);
                                cp.execute();
                            }
                        },
                        throwable -> {
                            view.loadok();
                        }, () -> {
                            view.loadok();

                        });

    }

    @SuppressLint("NewApi")
    private class computerData extends AsyncTask<String, Void, String> {

        String key;
        int p1;
        int p2;
        int p3;
        int count;

        private computerData(String key, int p1, int p2, int p3, int count) {
            this.key = key;
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
            this.count = count;
        }

        @Override
        protected void onPreExecute() {
            //load
            view.onload();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            addData(key, p1, p2, p3);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            ///ok
            loadFilter(count, key, p1, p2, p3);
            view.loadok();
            super.onPostExecute(result);
        }

    }


    @SuppressLint("NewApi")
    public void computing(String key, int p1, int p2, int p3, int count) {
        computerData cp = new computerData(key, p1, p2, p3, count);
        cp.execute();

    }

    private void addData(String key, int p1, int p2, int p3) {
        if (!yVals1.isEmpty()) {

            //Clear du lieu cu
            if (listRsi != null && listRsi.length > 0) listRsi = null;
            if (!sma.isEmpty()) sma.clear();
            if (!pks_.isEmpty()) pks_.clear();
            if (!pds_.isEmpty()) pds_.clear();
            if (!top.isEmpty()) top.clear();
            if (!mid.isEmpty()) mid.clear();
            if (!bot.isEmpty()) bot.clear();
            if (!ema.isEmpty()) ema.clear();

            switch (key) {
                case RSI:
                    listRsi = RSIc(yVals1, p1);
                    sma = computerD(yVals1, p1);
                    pks_ = (ArrayList<Double>) _sma(sma, p3).clone();
                    pds_ = (ArrayList<Double>) _sma(pks_, p3).clone();

                    break;
                case STOCHASTICS:
                    listRsi = RSIc(yVals1, p1);
                    sma = computerD(yVals1, p1);
                    pks_ = (ArrayList<Double>) _sma(sma, p3).clone();
                    pds_ = (ArrayList<Double>) _sma(pks_, p3).clone();

                    break;
                case BOLINGER_BAND:
                    top = (ArrayList<Double>) bbands(yVals1, p1, p2, 1).clone();
                    mid = (ArrayList<Double>) bbands(yVals1, p1, p2, 2).clone();
                    bot = (ArrayList<Double>) bbands(yVals1, p1, p2, 3).clone();
                    ema = (ArrayList<Float>) _ema(yVals1, p1).clone();
                    break;
                case EMA:
                    top = (ArrayList<Double>) bbands(yVals1, p1, p2, 1).clone();
                    mid = (ArrayList<Double>) bbands(yVals1, p1, p2, 2).clone();
                    bot = (ArrayList<Double>) bbands(yVals1, p1, p2, 3).clone();
                    ema = (ArrayList<Float>) _ema(yVals1, p1).clone();
                    break;
            }
        }
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


    public static ArrayList<Float> _ema(List<HistoryChartOtherIndex> data, int period) {
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

    public static ArrayList<Double> bbands(List<HistoryChartOtherIndex> data, int period, double mult, int type) {
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

    public static ArrayList<Double> computerD(List<HistoryChartOtherIndex> arrayListHistoryChart, int k) {

        float min, max;
        ArrayList<Float> d_;
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

    public void loadFilter(int sizedate, String key, int p1, int p2, int p3) {
        if (!yVals1.isEmpty()) {
            ArrayList<Double> top1 = new ArrayList<>();
            ArrayList<Double> mid1 = new ArrayList<>();
            ArrayList<Double> bot1 = new ArrayList<>();
            ArrayList<Float> ema1 = new ArrayList<>();
            ArrayList<Double> sma1 = new ArrayList<>();
            ArrayList<Double> sma12 = new ArrayList<>();
            ArrayList<HistoryChartOtherIndex> yVals11 = new ArrayList<>();
            double[] listRsi1 = null;

            int numsize = 0;
            if (sizedate > 0 && sizedate < yVals1.size()) {

                numsize = yVals1.size() - sizedate;
                listRsi1 = new double[sizedate];
            } else {
                listRsi1 = new double[yVals1.size()];
            }
//            view.showChart(top1, mid1, bot1, ema1, sma1, sma12, listRsi1, yVals11, getMacd(yVals11, p1, p2, p3), key, p1, p2, p3);
            switch (key) {
                case VOLUMN:
                    for (int i = numsize; i < yVals1.size(); i++) {
                        HistoryChartOtherIndex chartix = new HistoryChartOtherIndex();
                        chartix.setChartC(yVals1.get(i).getChartC());
                        chartix.setChartH(yVals1.get(i).getChartH());
                        chartix.setCharTime(yVals1.get(i).getCharTime());
                        chartix.setChartL(yVals1.get(i).getChartL());
                        chartix.setChartO(yVals1.get(i).getChartO());
                        chartix.setCharV(yVals1.get(i).getCharV());
                        yVals11.add(chartix);
                    }
                    view.showChart(yVals11);
                    break;
                case MACD:
                    for (int i = numsize; i < yVals1.size(); i++) {
                        HistoryChartOtherIndex chartix = new HistoryChartOtherIndex();
                        chartix.setChartC(yVals1.get(i).getChartC());
                        chartix.setChartH(yVals1.get(i).getChartH());
                        chartix.setCharTime(yVals1.get(i).getCharTime());
                        chartix.setChartL(yVals1.get(i).getChartL());
                        chartix.setChartO(yVals1.get(i).getChartO());
                        chartix.setCharV(yVals1.get(i).getCharV());
                        yVals11.add(chartix);
                    }
                    view.showChart(yVals11, getMacd(yVals11, p1, p2, p3));
                    break;
                case RSI:
                    for (int i = numsize; i < yVals1.size(); i++) {
                        HistoryChartOtherIndex chartix = new HistoryChartOtherIndex();
                        chartix.setChartC(yVals1.get(i).getChartC());
                        chartix.setChartH(yVals1.get(i).getChartH());
                        chartix.setCharTime(yVals1.get(i).getCharTime());
                        chartix.setChartL(yVals1.get(i).getChartL());
                        chartix.setChartO(yVals1.get(i).getChartO());
                        chartix.setCharV(yVals1.get(i).getCharV());
                        yVals11.add(chartix);
                    }
                    int j = 0;
                    for (int i = numsize; i < listRsi.length; i++) {
                        if (j < sizedate) {
                            listRsi1[j] = listRsi[i];
                            j++;
                        }
                    }
                    for (int i = numsize; i < pks_.size(); i++) sma1.add(pks_.get(i));
                    for (int i = numsize; i < pds_.size(); i++) sma12.add(pds_.get(i));

                    view.showChart(yVals11, sma1, sma12, listRsi1, key, p1, p3);
                    break;
                case BOLINGER_BAND:
                    for (int i = numsize; i < yVals1.size(); i++) {
                        HistoryChartOtherIndex chartix = new HistoryChartOtherIndex();
                        chartix.setChartC(yVals1.get(i).getChartC());
                        chartix.setChartH(yVals1.get(i).getChartH());
                        chartix.setCharTime(yVals1.get(i).getCharTime());
                        chartix.setChartL(yVals1.get(i).getChartL());
                        chartix.setChartO(yVals1.get(i).getChartO());
                        chartix.setCharV(yVals1.get(i).getCharV());
                        yVals11.add(chartix);
                    }
                    for (int i = numsize; i < ema.size(); i++) ema1.add(ema.get(i));
                    for (int i = numsize; i < top.size(); i++) top1.add(top.get(i));
                    for (int i = numsize; i < mid.size(); i++) mid1.add(mid.get(i));
                    for (int i = numsize; i < bot.size(); i++) bot1.add(bot.get(i));
                    view.showChart(top1, mid1, bot1, ema1, yVals11, key);
                    break;
                case EMA:
                    for (int i = numsize; i < yVals1.size(); i++) {
                        HistoryChartOtherIndex chartix = new HistoryChartOtherIndex();
                        chartix.setChartC(yVals1.get(i).getChartC());
                        chartix.setChartH(yVals1.get(i).getChartH());
                        chartix.setCharTime(yVals1.get(i).getCharTime());
                        chartix.setChartL(yVals1.get(i).getChartL());
                        chartix.setChartO(yVals1.get(i).getChartO());
                        chartix.setCharV(yVals1.get(i).getCharV());
                        yVals11.add(chartix);
                    }
                    for (int i = numsize; i < ema.size(); i++) ema1.add(ema.get(i));
                    for (int i = numsize; i < top.size(); i++) top1.add(top.get(i));
                    for (int i = numsize; i < mid.size(); i++) mid1.add(mid.get(i));
                    for (int i = numsize; i < bot.size(); i++) bot1.add(bot.get(i));
                    view.showChart(top1, mid1, bot1, ema1, yVals11, key);
                    break;
                case STOCHASTICS:
                    for (int i = numsize; i < yVals1.size(); i++) {
                        HistoryChartOtherIndex chartix = new HistoryChartOtherIndex();
                        chartix.setChartC(yVals1.get(i).getChartC());
                        chartix.setChartH(yVals1.get(i).getChartH());
                        chartix.setCharTime(yVals1.get(i).getCharTime());
                        chartix.setChartL(yVals1.get(i).getChartL());
                        chartix.setChartO(yVals1.get(i).getChartO());
                        chartix.setCharV(yVals1.get(i).getCharV());
                        yVals11.add(chartix);
                    }
                    int j1 = 0;
                    for (int i = numsize; i < listRsi.length; i++) {

                        if (j1 < sizedate) {
                            listRsi1[j1] = listRsi[i];


                            j1++;
                        }
                    }
                    for (int i = numsize; i < pks_.size(); i++) sma1.add(pks_.get(i));
                    for (int i = numsize; i < pds_.size(); i++) sma12.add(pds_.get(i));
                    view.showChart(yVals11, sma1, sma12, listRsi1, key, p1, p3);
                    break;
            }
        }
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
            macdDataObject1.setSignalMACD(((float) 2 / (signal + 1)) * (ema1 - ema2) +
                    (1 - ((float) 2 / (signal + 1))) * macdData.get(i - 1).getSignalMACD());

            macdDataObject1.setBardataMACD(macdDataObject1.getMacd() - macdDataObject1.getSignalMACD());
            macdData.add(macdDataObject1);
        }
        return macdData;
    }

    //Cach tinh Bieu do RSI
    public static double[] RSIc(List<HistoryChartOtherIndex> data, int lookback) {
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

}

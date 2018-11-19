package mobile.fpts.com.ezmibile.model.entity.chart;

/**
 * Created by HuyPD on 5/4/2016.
 */
public class MacdData {
    private float ema1;
    private float ema2;
    private float macd;
    private String date;
    private float SignalMACD;
    private float BardataMACD;

    public float getBardataMACD() {
        return BardataMACD;
    }

    public void setBardataMACD(float bardataMACD) {
        BardataMACD = bardataMACD;
    }

    public float getSignalMACD() {
        return SignalMACD;
    }

    public void setSignalMACD(float signalMACD) {
        SignalMACD = signalMACD;
    }

    public MacdData() {
        super();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getEma1() {
        return ema1;
    }

    public void setEma1(float ema1) {
        this.ema1 = ema1;
    }

    public float getEma2() {
        return ema2;
    }

    public void setEma2(float ema2) {
        this.ema2 = ema2;
    }

    public float getMacd() {
        return macd;
    }

    public void setMacd(float macd) {
        this.macd = macd;
    }
}

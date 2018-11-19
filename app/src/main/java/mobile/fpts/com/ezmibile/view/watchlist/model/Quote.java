package mobile.fpts.com.ezmibile.view.watchlist.model;

import android.graphics.Paint;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * Created by huang on 2017/11/1.
 */
@SmartTable(name = "Quote")
public class Quote {
    @SmartColumn(id = 1, name = "Mã CK")
    private String mack;
    @SmartColumn(id = 2, name = "Giá khớp", align = Paint.Align.RIGHT)
    private double giakhop;
    @SmartColumn(id = 3, name = "Kl khớp", align = Paint.Align.LEFT)
    private double kl;
    @SmartColumn(id = 4, name = "+/-", align = Paint.Align.LEFT)
    private double di;
    @SmartColumn(id = 5, name = "Khối Lượng", align = Paint.Align.LEFT)
    private double khoiluong;
    @SmartColumn(id = 6, name = "G.M3", align = Paint.Align.RIGHT)
    private double gm3;
    @SmartColumn(id = 7, name = "KL.M3", align = Paint.Align.RIGHT)
    private double klm3;
    @SmartColumn(id = 8, name = "G.M2", align = Paint.Align.RIGHT)
    private double gm2;
    @SmartColumn(id = 9, name = "KL.M2", align = Paint.Align.RIGHT)
    private double klm2;
    @SmartColumn(id = 10, name = "G.M1", align = Paint.Align.RIGHT)
    private double gm1;
    @SmartColumn(id = 11, name = "KL.M1", align = Paint.Align.RIGHT)
    private double klm1;
    @SmartColumn(id = 12, name = "G.B1", align = Paint.Align.RIGHT)
    private double gb1;
    @SmartColumn(id = 13, name = "KL.B1", align = Paint.Align.RIGHT)
    private double klb1;
    @SmartColumn(id = 14, name = "G.B2", align = Paint.Align.RIGHT)
    private double gb2;
    @SmartColumn(id = 15, name = "KL.B2", align = Paint.Align.RIGHT)
    private double klb2;
    @SmartColumn(id = 16, name = "G.B3", align = Paint.Align.RIGHT)
    private double gb3;
    @SmartColumn(id = 17, name = "KL.B3", align = Paint.Align.RIGHT)
    private double klb3;
    @SmartColumn(id = 18, name = "Trần", align = Paint.Align.RIGHT)
    private double tran;
    @SmartColumn(id = 19, name = "Sàn", align = Paint.Align.RIGHT)
    private double san;
    @SmartColumn(id = 20, name = "TC", align = Paint.Align.RIGHT)
    private double tc;
    @SmartColumn(id = 21, name = "Mở", align = Paint.Align.RIGHT)
    private double mo;
    @SmartColumn(id = 22, name = "Cao", align = Paint.Align.RIGHT)
    private double cao;
    @SmartColumn(id = 23, name = "Thấp", align = Paint.Align.RIGHT)
    private double thap;
    @SmartColumn(id = 24, name = "NN mua", align = Paint.Align.RIGHT)
    private double nnmua;
    @SmartColumn(id = 25, name = "NN bán", align = Paint.Align.RIGHT)
    private double nnban;
    private String color;

    public Quote(String mack, String giakhop, String kl, String di, String khoiluong, String gm3, String klm3,
                 String gm2, String klm2, String gm1, String klm1, String gb1, String klb1, String gb2, String klb2,
                 String gb3, String klb3, String tran, String san, String tc, String mo, String cao, String thap,
                 String nnmua, String nnban, String color) {
        this.mack = mack;
        try {
            this.giakhop = Double.parseDouble(giakhop);
        } catch (Exception e) {
            this.giakhop = 0;
        }
        try {
            this.kl = Double.parseDouble(kl);
        } catch (Exception e) {
            this.kl = 0;
        }
        try {
            this.di = Double.parseDouble(di);
        } catch (Exception e) {
            this.di = 0;
        }
        try {
            this.khoiluong = Double.parseDouble(khoiluong);
        } catch (Exception e) {
            this.khoiluong = 0;
        }
        try {
            this.gm3 = Double.parseDouble(gm3);
        } catch (Exception e) {
            this.gm3 = 0;
        }
        try {
            this.klm3 = Double.parseDouble(klm3);
        } catch (Exception e) {
            this.klm3 = 0;
        }
        try {
            this.gm2 = Double.parseDouble(gm2);
        } catch (Exception e) {
            this.gm2 = 0;
        }
        try {
            this.klm2 = Double.parseDouble(klm2);
        } catch (Exception e) {
            this.klm2 = 0;
        }
        try {
            this.gm1 = Double.parseDouble(gm1);
        } catch (Exception e) {
            this.gm1 = 0;
        }
        try {
            this.klm1 = Double.parseDouble(klm1);
        } catch (Exception e) {
            this.klm1 = 0;

        }
        try {
            this.gb1 = Double.parseDouble(gb1);
        } catch (Exception e) {
            this.gb1 = 0;
        }
        try {
            this.klb1 = Double.parseDouble(klb1);
        } catch (Exception e) {
            this.klb1 = 0;
        }
        try {
            this.gb2 = Double.parseDouble(gb2);
        } catch (Exception e) {
            this.gb2 = 0;
        }
        try {
            this.klb2 = Double.parseDouble(klb2);
        } catch (Exception e) {
            this.klb2 = 0;
        }
        try {
            this.gb3 = Double.parseDouble(gb3);
        } catch (Exception e) {
            this.gb3 = 0;
        }
        try {
            this.klb3 = Double.parseDouble(klb3);
        } catch (Exception e) {
            this.klb3 = 0;
        }
        try {
            this.tran = Double.parseDouble(tran);
        } catch (Exception e) {
            this.tran = 0;
        }
        try {
            this.san = Double.parseDouble(san);
        } catch (Exception e) {
            this.san = 0;
        }
        try {
            this.tc = Double.parseDouble(tc);
        } catch (Exception e) {
            this.tc = 0;
        }
        try {
            this.mo = Double.parseDouble(mo);
        } catch (Exception e) {
            this.mo = 0;
        }
        try {
            this.cao = Double.parseDouble(cao);
        } catch (Exception e) {
            this.cao = 0;
        }
        try {
            this.thap = Double.parseDouble(thap);
        } catch (Exception e) {
            this.thap = 0;
        }
        try {
            this.nnmua = Double.parseDouble(nnmua);
        } catch (Exception e) {
            this.nnmua = 0;
        }
        try {
            this.nnban = Double.parseDouble(nnban);
        } catch (Exception e) {
            this.nnban = 0;
        }
        this.color = color;
    }

    public Quote(String mack, double giakhop, int kl, double di, int khoiluong, double gm3, int klm3,
                 double gm2, int klm2, double gm1, int klm1, double gb1, int klb1, double gb2, int klb2,
                 double gb3, int klb3, double tran, double san, double tc, double mo, double cao, double thap,
                 int nnmua, int nnban, String color) {
        this.mack = mack;
        this.giakhop = giakhop;
        this.kl = kl;
        this.di = di;
        this.khoiluong = khoiluong;
        this.gm3 = gm3;
        this.klm3 = klm3;
        this.gm2 = gm2;
        this.klm2 = klm2;
        this.gm1 = gm1;
        this.klm1 = klm1;
        this.gb1 = gb1;
        this.klb1 = klb1;
        this.gb2 = gb2;
        this.klb2 = klb2;
        this.gb3 = gb3;
        this.klb3 = klb3;
        this.tran = tran;
        this.san = san;
        this.tc = tc;
        this.mo = mo;
        this.cao = cao;
        this.thap = thap;
        this.nnmua = nnmua;
        this.nnban = nnban;
        this.color = color;
    }

    public String getMack() {
        return mack;
    }

    public void setMack(String mack) {
        this.mack = mack;
    }

    public double getGiakhop() {
        return giakhop;
    }

    public void setGiakhop(double giakhop) {
        this.giakhop = giakhop;
    }

    public double getKl() {
        return kl;
    }

    public void setKl(double kl) {
        this.kl = kl;
    }

    public double getDi() {
        return di;
    }

    public void setDi(double di) {
        this.di = di;
    }

    public double getKhoiluong() {
        return khoiluong;
    }

    public void setKhoiluong(double khoiluong) {
        this.khoiluong = khoiluong;
    }

    public double getGm3() {
        return gm3;
    }

    public void setGm3(double gm3) {
        this.gm3 = gm3;
    }

    public double getKlm3() {
        return klm3;
    }

    public void setKlm3(double klm3) {
        this.klm3 = klm3;
    }

    public double getGm2() {
        return gm2;
    }

    public void setGm2(double gm2) {
        this.gm2 = gm2;
    }

    public double getKlm2() {
        return klm2;
    }

    public void setKlm2(double klm2) {
        this.klm2 = klm2;
    }

    public double getGm1() {
        return gm1;
    }

    public void setGm1(double gm1) {
        this.gm1 = gm1;
    }

    public double getKlm1() {
        return klm1;
    }

    public void setKlm1(double klm1) {
        this.klm1 = klm1;
    }

    public double getGb1() {
        return gb1;
    }

    public void setGb1(double gb1) {
        this.gb1 = gb1;
    }

    public double getKlb1() {
        return klb1;
    }

    public void setKlb1(double klb1) {
        this.klb1 = klb1;
    }

    public double getGb2() {
        return gb2;
    }

    public void setGb2(double gb2) {
        this.gb2 = gb2;
    }

    public double getKlb2() {
        return klb2;
    }

    public void setKlb2(double klb2) {
        this.klb2 = klb2;
    }

    public double getGb3() {
        return gb3;
    }

    public void setGb3(double gb3) {
        this.gb3 = gb3;
    }

    public double getKlb3() {
        return klb3;
    }

    public void setKlb3(double klb3) {
        this.klb3 = klb3;
    }

    public double getTran() {
        return tran;
    }

    public void setTran(double tran) {
        this.tran = tran;
    }

    public double getSan() {
        return san;
    }

    public void setSan(double san) {
        this.san = san;
    }

    public double getTc() {
        return tc;
    }

    public void setTc(double tc) {
        this.tc = tc;
    }

    public double getMo() {
        return mo;
    }

    public void setMo(double mo) {
        this.mo = mo;
    }

    public double getCao() {
        return cao;
    }

    public void setCao(double cao) {
        this.cao = cao;
    }

    public double getThap() {
        return thap;
    }

    public void setThap(double thap) {
        this.thap = thap;
    }

    public double getNnmua() {
        return nnmua;
    }

    public void setNnmua(double nnmua) {
        this.nnmua = nnmua;
    }

    public double getNnban() {
        return nnban;
    }

    public void setNnban(double nnban) {
        this.nnban = nnban;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

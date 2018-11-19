package mobile.fpts.com.ezmibile.model.entity.stock;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class StockInfoDB {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "stock_code")
    private String stock_code;

    @ColumnInfo(name = "name_vn")
    private String name_vn;

    @ColumnInfo(name = "name_en")
    private String name_en;

    @ColumnInfo(name = "post_to")
    private String post_to;

    @ColumnInfo(name = "name_short")
    private String name_short;

    @ColumnInfo(name = "mien")
    private String mien;

    @Ignore
    public StockInfoDB() {
    }

    public StockInfoDB(String stock_code, String name_vn, String name_en, String post_to, String name_short, String mien) {
        this.stock_code = stock_code;
        this.name_vn = name_vn;
        this.name_en = name_en;
        this.post_to = post_to;
        this.name_short = name_short;
        this.mien = mien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getName_vn() {
        return name_vn;
    }

    public void setName_vn(String name_vn) {
        this.name_vn = name_vn;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getPost_to() {
        return post_to;
    }

    public void setPost_to(String post_to) {
        this.post_to = post_to;
    }

    public String getName_short() {
        return name_short;
    }

    public void setName_short(String name_short) {
        this.name_short = name_short;
    }

    public String getMien() {
        return mien;
    }

    public void setMien(String mien) {
        this.mien = mien;
    }
}

package mobile.fpts.com.ezmibile.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by FIT-thuctap22 on 2/1/2018.
 */

@Entity
public class WatchlistData {
    @PrimaryKey(autoGenerate = true)
    public long mId;
    @ColumnInfo(name = "name")
    private String column;
    @ColumnInfo(name = "sort")
    private int sort;
    @ColumnInfo(name = "check")
    private boolean check = true;

    public WatchlistData(String column, int sort, boolean check) {
        this.column = column;
        this.sort = sort;
        this.check = check;
    }

    @Ignore
    public WatchlistData() {
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}

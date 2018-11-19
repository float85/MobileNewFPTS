package mobile.fpts.com.ezmibile.view.splash_screen.data;

import android.util.SparseArray;

/**
 * Created by dinht on 2/1/2018.
 */

public class BigGroup {
    private int type;
    private String title;
    private SparseArray<ItemHomeChild> groupSparseArray;

    public BigGroup() {
    }

    public BigGroup(int type, String title, SparseArray<ItemHomeChild> groupList) {
        this.type = type;
        this.title = title;
        this.groupSparseArray = groupList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SparseArray<ItemHomeChild> getGroupSparseArray() {
        return groupSparseArray;
    }

    public void setGroupSparseArray(SparseArray<ItemHomeChild> groupSparseArray) {
        this.groupSparseArray = groupSparseArray;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package mobile.fpts.com.ezmibile.model.entity.menu_navigation;

import android.util.SparseArray;

/**
 * Created by dinht on 2/1/2018.
 */

public class MenuBigGroup {
    private String title;
    private SparseArray<Group> groupSparseArray;

    public MenuBigGroup() {
    }

    public MenuBigGroup(String title, SparseArray<Group> groupList) {
        this.title = title;
        this.groupSparseArray = groupList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SparseArray<Group> getGroupSparseArray() {
        return groupSparseArray;
    }

    public void setGroupSparseArray(SparseArray<Group> groupSparseArray) {
        this.groupSparseArray = groupSparseArray;
    }
}

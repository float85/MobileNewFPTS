package mobile.fpts.com.ezmibile.model.entity.worldIndices;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;

@Entity
public class WorldIndices {
    @ColumnInfo(name = "rowId")
    @Expose
    private String rowId;
    @ColumnInfo(name = "groupId")
    @Expose
    private String groupId;

    @ColumnInfo(name = "title")
    @Expose
    private String title;

    @ColumnInfo(name = "price")
    @Expose
    private String price;
    @ColumnInfo(name = "regionId")
    @Expose
    private String regionId;
    @ColumnInfo(name = "change")
    @Expose
    private String change;

    @ColumnInfo(name = "created")
    @Expose
    private String created;
    @ColumnInfo(name = "charturl")
    @Expose
    private String charturl;
    @ColumnInfo(name = "changePct")
    @Expose
    private String changePct;
    @ColumnInfo(name = "localtime")
    @Expose
    private String localtime;
    @ColumnInfo(name = "vieword")
    @Expose
    private String vieword;

    @Ignore
    public WorldIndices() {
    }

    public WorldIndices(String rowId, String groupId, String title, String price, String regionId,
                        String change, String created, String charturl, String changePct,
                        String localtime, String vieword) {
        this.rowId = rowId;
        this.groupId = groupId;
        this.title = title;
        this.price = price;
        this.regionId = regionId;
        this.change = change;
        this.created = created;
        this.charturl = charturl;
        this.changePct = changePct;
        this.localtime = localtime;
        this.vieword = vieword;
    }


    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCharturl() {
        return charturl;
    }

    public void setCharturl(String charturl) {
        this.charturl = charturl;
    }

    public String getChangePct() {
        return changePct;
    }

    public void setChangePct(String changePct) {
        this.changePct = changePct;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public String getVieword() {
        return vieword;
    }

    public void setVieword(String vieword) {
        this.vieword = vieword;
    }
}

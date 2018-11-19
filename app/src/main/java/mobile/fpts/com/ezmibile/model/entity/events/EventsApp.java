package mobile.fpts.com.ezmibile.model.entity.events;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.os.Build.ID;

@Entity
public class EventsApp implements Parcelable {
    // TODO: TamHV 7/2/2018 Sửa lại hết

    @SerializedName("IDX")
    @Expose
    private String iDX;
    @SerializedName("GroupNm")
    @Expose
    private String groupNm;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("stock_code")
    @Expose
    private String stockCode;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("Date1")
    @Expose
    private String date1;

    @Ignore
    public EventsApp() {
    }

    public EventsApp(String IDX, String groupNm, String ID, String stockCode,
                     String content, String url, String date1) {
        this.iDX = IDX;
        this.groupNm = groupNm;
        this.iD = ID;
        this.stockCode = stockCode;
        this.content = content;
        this.url = url;
        this.date1 = date1;
    }

    protected EventsApp(Parcel in) {
        this.iDX = in.readString();
        this.groupNm = in.readString();
        this.iD = in.readString();
        stockCode = in.readString();
        content = in.readString();
        url = in.readString();
        date1 = in.readString();
    }

    public static final Creator<EventsApp> CREATOR = new Creator<EventsApp>() {
        @Override
        public EventsApp createFromParcel(Parcel in) {
            return new EventsApp(in);
        }

        @Override
        public EventsApp[] newArray(int size) {
            return new EventsApp[size];
        }
    };

    public String getiDX() {
        return iDX;
    }

    public void setiDX(String iDX) {
        this.iDX = iDX;
    }

    public String getGroupNm() {
        return groupNm;
    }

    public void setGroupNm(String groupNm) {
        this.groupNm = groupNm;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iDX);
        dest.writeString(groupNm);
        dest.writeString(ID);
        dest.writeString(stockCode);
        dest.writeString(content);
        dest.writeString(url);
        dest.writeString(date1);
    }
}

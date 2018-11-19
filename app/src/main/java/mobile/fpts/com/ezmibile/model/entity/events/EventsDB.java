package mobile.fpts.com.ezmibile.model.entity.events;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

/**
 * Created by dinht on 1/27/2018.
 */

@Entity
public class EventsDB {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "event_idx")
    private String eventIDX;

    @ColumnInfo(name = "event_group_nm")
    private String eventGroupNm;

    @ColumnInfo(name = "event_id")
    private String eventId;

    @ColumnInfo(name = "stock_code")
    private String eventStockCode;

    @ColumnInfo(name = "content")
    private String eventContent;

    @ColumnInfo(name = "url")
    private String eventUrl;

    @ColumnInfo(name = "date1")
    private String eventDate1;

    @Ignore
    public EventsDB() {
    }

    public EventsDB(String eventIDX, String eventGroupNm, String eventId, String eventStockCode,
                    String eventContent, String eventUrl, String eventDate1) {
        this.eventIDX = eventIDX;
        this.eventGroupNm = eventGroupNm;
        this.eventId = eventId;
        this.eventStockCode = eventStockCode;
        this.eventContent = eventContent;
        this.eventUrl = eventUrl;
        this.eventDate1 = eventDate1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventIDX() {
        return eventIDX;
    }

    public void setEventIDX(String eventIDX) {
        this.eventIDX = eventIDX;
    }

    public String getEventGroupNm() {
        return eventGroupNm;
    }

    public void setEventGroupNm(String eventGroupNm) {
        this.eventGroupNm = eventGroupNm;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventStockCode() {
        return eventStockCode;
    }

    public void setEventStockCode(String eventStockCode) {
        this.eventStockCode = eventStockCode;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public String getEventDate1() {
        return eventDate1;
    }

    public void setEventDate1(String eventDate1) {
        this.eventDate1 = eventDate1;
    }
}

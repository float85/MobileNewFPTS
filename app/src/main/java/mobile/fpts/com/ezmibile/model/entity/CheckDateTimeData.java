package mobile.fpts.com.ezmibile.model.entity;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CheckDateTimeData {
    @SerializedName("IsWorkingDay")
    @Expose
    private String IsWorkingDay;

    @SerializedName("IsWorkingTime")
    @Expose
    private String IsWorkingTime;

    @SerializedName("IsBreakingTime")
    @Expose
    private String IsBreakingTime;

    public CheckDateTimeData() {
    }

    public CheckDateTimeData(String isWorkingDay, String isWorkingTime, String isBreakingTime) {
        IsWorkingDay = isWorkingDay;
        IsWorkingTime = isWorkingTime;
        IsBreakingTime = isBreakingTime;
    }

    public String getIsWorkingDay() {
        return IsWorkingDay;
    }

    public void setIsWorkingDay(String isWorkingDay) {
        IsWorkingDay = isWorkingDay;
    }

    public String getIsWorkingTime() {
        return IsWorkingTime;
    }

    public void setIsWorkingTime(String isWorkingTime) {
        IsWorkingTime = isWorkingTime;
    }

    public String getIsBreakingTime() {
        return IsBreakingTime;
    }

    public void setIsBreakingTime(String isBreakingTime) {
        IsBreakingTime = isBreakingTime;
    }
}


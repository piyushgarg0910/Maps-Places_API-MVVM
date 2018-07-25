package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class OpeningHours implements Serializable {

    @SerializedName("open_now")
    private Boolean mOpenNow;

    @SerializedName("weekday_text")
    private ArrayList<Object> mWeekdayText = new ArrayList<>();

    public OpeningHours(Boolean mOpenNow, ArrayList<Object> mWeekdayText) {
        this.mOpenNow = mOpenNow;
        this.mWeekdayText = mWeekdayText;
    }

    public Boolean getmOpenNow() {
        return mOpenNow;
    }

    public void setmOpenNow(Boolean mOpenNow) {
        this.mOpenNow = mOpenNow;
    }

    public ArrayList<Object> getmWeekdayText() {
        return mWeekdayText;
    }

    public void setmWeekdayText(ArrayList<Object> mWeekdayText) {
        this.mWeekdayText = mWeekdayText;
    }
}

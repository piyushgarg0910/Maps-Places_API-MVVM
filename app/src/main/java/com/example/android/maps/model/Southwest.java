package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Southwest implements Serializable{

    @SerializedName("lat")
    private Double mLatitude;

    @SerializedName("lng")
    private Double mLongitude;

    public Southwest(Double mLatitude, Double mLongitude) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }
}

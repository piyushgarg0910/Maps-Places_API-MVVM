package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable{

    @SerializedName("lat")
    private Double mLat;

    @SerializedName("lng")
    private Double mLng;

    public Location(Double mLat, Double mLng) {
        this.mLat = mLat;
        this.mLng = mLng;
    }

    public Double getmLat() {
        return mLat;
    }

    public void setmLat(Double mLat) {
        this.mLat = mLat;
    }

    public Double getmLng() {
        return mLng;
    }

    public void setmLng(Double mLng) {
        this.mLng = mLng;
    }
}

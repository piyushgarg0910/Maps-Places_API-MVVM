package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Northeast implements Serializable {

    @SerializedName("lat")
    private Double mLati;

    @SerializedName("lng")
    private Double mLongi;

    public Northeast(Double mLati, Double mLongi) {
        this.mLongi = mLongi;
        this.mLati = mLati;
    }

    public Double getmLati() {
        return mLati;
    }

    public void setmLati(Double mLati) {
        this.mLati = mLati;
    }

    public Double getmLongi() {
        return mLongi;
    }

    public void setmLongi(Double mLongi) {
        this.mLongi = mLongi;
    }
}

package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geometry implements Serializable {

    @SerializedName("location")
    private Location mLocation;

    @SerializedName("viewport")
    private Viewport mViewport;

    public Geometry(Location mLocation, Viewport mViewport) {
        this.mLocation = mLocation;
        this.mViewport = mViewport;
    }

    public Location getmLocation() {
        return mLocation;
    }

    public void setmLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    public Viewport getmViewport() {
        return mViewport;
    }

    public void setmViewport(Viewport mViewport) {
        this.mViewport = mViewport;
    }
}
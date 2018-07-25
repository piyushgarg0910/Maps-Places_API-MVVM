package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Viewport implements Serializable {

    @SerializedName("northeast")
    private Northeast mNortheast;

    @SerializedName("southwest")
    private Southwest mSouthwest;

    public Viewport(Northeast mNortheast, Southwest mSouthwest) {
        this.mNortheast = mNortheast;
        this.mSouthwest = mSouthwest;
    }

    public Northeast getmNortheast() {
        return mNortheast;
    }

    public void setmNortheast(Northeast mNortheast) {
        this.mNortheast = mNortheast;
    }

    public Southwest getmSouthwest() {
        return mSouthwest;
    }

    public void setmSouthwest(Southwest mSouthwest) {
        this.mSouthwest = mSouthwest;
    }
}

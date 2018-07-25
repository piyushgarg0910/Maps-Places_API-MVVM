package com.example.android.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class MapResponse {

    @SerializedName("html_attributions")
    private ArrayList<Object> mHTMLAttributions = new ArrayList<>();

    @SerializedName("next_page_token")
    private String mNextPageToken;

    @SerializedName("results")
    private ArrayList<MapResults> mResults = new ArrayList<>();

    @SerializedName("status")
    private String mStatus;

    public MapResponse(ArrayList<Object> mHTMLAttributions, String mNextPageToken,
                       ArrayList<MapResults> mResults, String mStatus) {
        this.mHTMLAttributions = mHTMLAttributions;
        this.mNextPageToken = mNextPageToken;
        this.mResults = mResults;
        this.mStatus = mStatus;
    }

    public ArrayList<Object> getmHTMLAttributions() {
        return mHTMLAttributions;
    }

    public void setmHTMLAttributions(ArrayList<Object> mHTMLAttributions) {
        this.mHTMLAttributions = mHTMLAttributions;
    }

    public String getmNextPageToken() {
        return mNextPageToken;
    }

    public void setmNextPageToken(String mNextPageToken) {
        this.mNextPageToken = mNextPageToken;
    }

    public ArrayList<MapResults> getmResults() {
        return mResults;
    }

    public void setmResults(ArrayList<MapResults> mResults) {
        this.mResults = mResults;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}

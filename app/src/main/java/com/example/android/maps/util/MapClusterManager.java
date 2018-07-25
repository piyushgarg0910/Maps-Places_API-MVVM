package com.example.android.maps.util;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MapClusterManager implements ClusterItem{

    private LatLng latLng;
    private String title;
    private String URL;
    private String vicinity;
    private Double mRatings;

    public MapClusterManager(LatLng latLng, String title, String URL, String vicinity,
                             Double mRatings) {
        this.latLng = latLng;
        this.title = title;
        this.URL = URL;
        this.vicinity = vicinity;
        this.mRatings = mRatings;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public String getURL() {
        return URL;
    }

    public Double getmRatings() {
        return mRatings;
    }

    public String getVicinity() {
        return vicinity;
    }

    public Double getLatitude(){
        return latLng.latitude;
    }

    public Double getLongitude(){
        return latLng.longitude;
    }
}

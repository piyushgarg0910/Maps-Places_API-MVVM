package com.example.android.maps.map;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapUI {

    private GoogleMap googleMap;

    public MapUI(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public void UpdateUI()
    {
        try {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMapToolbarEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setAllGesturesEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
        }
        catch (SecurityException e)
        {
            Log.e("Google Map Exception: ", e.getMessage());
        }
    }

    public void UpdateMap(GoogleMap googleMap, LatLng latLng, String string,
                          BitmapDescriptor bitmapDescriptor,Boolean result){
        if (result)
        {
            googleMap.addMarker(new MarkerOptions().position(latLng).title(string).icon(bitmapDescriptor));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.5f));
        }
    }
}

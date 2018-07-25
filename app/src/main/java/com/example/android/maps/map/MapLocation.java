package com.example.android.maps.map;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapLocation {
    private Location location;
    private Double Latitude;
    private Double Longitude;
    private BitmapDescriptor bitmapDescriptor;
    private String string;
    private MapUI mapUI;
    private GoogleMap googleMap;

    public MapLocation(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapUI = new MapUI(this.googleMap);
        Latitude = 39.0094286;
        Longitude = -76.8960054;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Boolean result, Task<Location> task)
    {
        if (result) {
            try {
                task.addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task2) {
                        if (task2.isSuccessful()) {
                            location = task2.getResult();
                            Latitude = location.getLatitude();
                            Longitude = location.getLongitude();
                            bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                            string = "My Location";
                        }
                        else
                        {
                            bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE);
                            string = "Default Location";
                            location = null;
                        }
                        LatLng latLng = new LatLng(Latitude, Longitude);
                        mapUI.UpdateMap(googleMap, latLng,string,bitmapDescriptor,result);
                    }
                });
            } catch (SecurityException e) {
                Log.e("Google Map Exception: ", e.getMessage());
            }
        }
    }
}

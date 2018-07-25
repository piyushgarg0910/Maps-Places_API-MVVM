package com.example.android.maps.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.maps.R;
import com.example.android.maps.map.MapCluster;
import com.example.android.maps.map.MapLocation;
import com.example.android.maps.map.MapUI;
import com.example.android.maps.model.MapResponse;
import com.example.android.maps.util.MapClusterManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;

public class FragmentMap extends SupportMapFragment implements GoogleMap.OnMapLoadedCallback,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMapClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnMarkerClickListener {

    GeoDataClient geoDataClient;
    PlaceDetectionClient placeDetectionClient;
    FusedLocationProviderClient fusedLocationProviderClient;
    GoogleMap googleMap;
    Task <Location> task;
    BitmapDescriptor bitmapDescriptor;
    static Location location;
    MapUI mapUI;
    MapLocation mapLocation;
    private ClusterManager<MapClusterManager> mapClusterManager;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().
                findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geoDataClient = Places.getGeoDataClient(getContext());
        placeDetectionClient = Places.getPlaceDetectionClient(getContext());
        fusedLocationProviderClient = LocationServices.
                getFusedLocationProviderClient(getContext());

        /*if(bundle != null)
        {
            location = bundle.getParcelable("LastKnownLocation");
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
    }

    @Override
    public void onMapClick(LatLng latLng) {
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapLoaded() {
        try {
            task = fusedLocationProviderClient.getLastLocation();
            mapLocation.setLocation(result,task);
        }
        catch (SecurityException e)
        {
            Log.e("Google Map Exception: ", e.getMessage());
        }
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location loc) {
                location = loc;
            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapLocation = new MapLocation(this.googleMap);
        mapUI = new MapUI(this.googleMap);
        mapClusterManager = new ClusterManager<MapClusterManager>(getContext(),googleMap);

        if (getResult()) {
            try {
                mapUI.UpdateUI();
                result = true;
                LatLng greenbelt = new LatLng(39.0094286, -76.8960054);
                bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
                mapUI.UpdateMap(googleMap,greenbelt,"Remote Tiger",bitmapDescriptor,result);
                googleMap.setOnMapLoadedCallback(this);
            }
            catch (SecurityException e){
                Log.e("Google Map Exception:",e.getMessage());
            }
        }
    }

    private Boolean result = false;

    public Boolean getResult() {
        if ((ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
                &&(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED))
        {
            return true;
        }
        else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                &&(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)))
        {
            Snackbar snackbar = Snackbar.make(getView(),"Please enable location permission", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK",new okListener());
            snackbar.show();
        }
        else {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},101);
        }
        return result;
    }

    public void setMarkers(ArrayList<MapResponse> mapResponsesArrayList){
        MapCluster mapCluster;
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        mapCluster = new MapCluster(mapUI,googleMap,result,latLng,getContext());
        mapCluster.showMarkerCluster(mapResponsesArrayList);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 101:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    mapUI.UpdateUI();
                    result = true;
                    LatLng greenbelt = new LatLng(39.0094286, -76.8960054);
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    mapUI.UpdateMap(googleMap,greenbelt,"Remote Tiger",bitmapDescriptor,result);
                    googleMap.setOnMapLoadedCallback(this);
                }
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED)
                {
                    result = getResult();
                }
        }
    }

    private class okListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},101);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        //bundle.putParcelable("MapCameraPosition", googleMap.getCameraPosition());
        //bundle.putParcelable("LastKnownLocation",location);
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
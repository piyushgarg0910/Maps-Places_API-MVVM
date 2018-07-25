package com.example.android.maps.map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;

import com.example.android.maps.model.MapResponse;
import com.example.android.maps.util.CustomClusterAdapter;
import com.example.android.maps.util.CustomClusterRenderer;
import com.example.android.maps.util.MapClusterManager;
import com.example.android.maps.view.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;

public class MapCluster{

    private MapUI mapUI;
    private GoogleMap googleMap;
    private Boolean result;
    private LatLng latLng1;
    private ClusterManager<MapClusterManager> mapClusterManager;
    private CustomClusterRenderer customClusterRenderer;
    private CustomClusterAdapter customClusterAdapter;
    private Context context;


    public MapCluster(MapUI mapUI, GoogleMap googleMap,
                                 Boolean result, LatLng latLng1, Context context) {
        this.mapUI = mapUI;
        this.googleMap = googleMap;
        this.result = result;
        this.latLng1 = latLng1;
        mapClusterManager = new ClusterManager<>(context, googleMap);
        customClusterRenderer = new CustomClusterRenderer(context,googleMap,mapClusterManager);
        mapClusterManager.setRenderer(customClusterRenderer);
        customClusterAdapter = new CustomClusterAdapter(context);
        this.googleMap.setInfoWindowAdapter(customClusterAdapter);
        this.context = context;
    }

    public void showMarkerCluster(ArrayList<MapResponse> mapResponsesArrayList)
    {
        googleMap.clear();
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
        mapUI.UpdateMap(googleMap,latLng1, "My Location",bitmapDescriptor,result);
        bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
        LatLng greenbelt = new LatLng(39.0094286, -76.8960054);
        mapUI.UpdateMap(googleMap,greenbelt, "Remote Tiger",bitmapDescriptor,result);

        mapClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MapClusterManager>() {
            @Override
            public boolean onClusterClick(Cluster<MapClusterManager> cluster) {
                LatLngBounds.Builder builder = LatLngBounds.builder();
                for (ClusterItem item : cluster.getItems()) {
                    builder.include(item.getPosition());
                }
                final LatLngBounds bounds = builder.build();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 300));
                return true;
            }
        });

        mapClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MapClusterManager>() {
            @Override
            public boolean onClusterItemClick(MapClusterManager mapClusterManager) {
                if(mapClusterManager == null)
                {
                    customClusterAdapter.setMapClusterManager(null);
                }
               // mapClusterManager.setVicinity();
                else
                customClusterAdapter.setMapClusterManager(mapClusterManager);
                return false;
            }
        });

        mapClusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<MapClusterManager>() {
            @Override
            public void onClusterItemInfoWindowClick(MapClusterManager mapClusterManager) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setIcon(android.R.drawable.ic_dialog_map);
                alertDialog.setTitle("Navigate to location");
                alertDialog.setMessage("Do you wish to navigate to this location?");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri gmIntentURI = Uri.parse("google.navigation:q="+mapClusterManager.getLatitude()
                                +","+mapClusterManager.getLongitude()+"&mode=d");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmIntentURI);
                        context.startActivity(mapIntent);
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"NO", (DialogInterface.OnClickListener) null);
                alertDialog.show();
            }
        });

        customClusterRenderer.setMinClusterSize(1);

        //customClusterRenderer.setOnClusterClickListener(mapClusterManager);

        //showMarkers(mapResultsArrayList);
        showCluster(mapResponsesArrayList);
    }

    /*private void showMarkers(ArrayList<MapResponse> mapResponseArrayList){
        for (int i = 0; i < mapResponseArrayList.size(); i++)
        {
            for (int j = 0; j < mapResponseArrayList.get(i).getmResults().size(); j++) {
                LatLng latLng = new LatLng(mapResponseArrayList.get(i).getmResults().get(j)
                        .getmGeometry().getmLocation().getmLat(),
                        mapResponseArrayList.get(i).getmResults().get(j).
                                getmGeometry().getmLocation().getmLng());
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA);
                mapUI.UpdateMap(googleMap, latLng, mapResponseArrayList.get(i).
                        getmResults().get(j).getmName(), bitmapDescriptor, result);
            if (i == mapResponseArrayList.size()-1)
            {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 11.8f));
            }
            }
        }
    }*/

    private void showCluster(ArrayList<MapResponse> mapResponsesArrayList){
        mapClusterManager.clearItems();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 12.5f));
        for (int i = 0; i < mapResponsesArrayList.size(); i++)
        {
            for (int j = 0; j < mapResponsesArrayList.get(i).getmResults().size(); j++) {
                final LatLng latLng = new LatLng(mapResponsesArrayList.get(i).getmResults().get(j).
                        getmGeometry().getmLocation().getmLat(),
                        mapResponsesArrayList.get(i).getmResults().get(j).
                                getmGeometry().getmLocation().getmLng());
                mapClusterManager.addItem(new MapClusterManager(latLng,
                        mapResponsesArrayList.get(i).getmResults().get(j).getmName(),
                        mapResponsesArrayList.get(i).getmResults().get(j).getmIcon(),
                        mapResponsesArrayList.get(i).getmResults().get(j).getmVicinity(),
                        mapResponsesArrayList.get(i).getmResults().get(j).getmRating()));
            }
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 11.8f));
        googleMap.setOnCameraMoveListener(onCameraMoveListener);
        googleMap.setOnMarkerClickListener(mapClusterManager);
        googleMap.setOnInfoWindowClickListener(mapClusterManager);
    }

    private GoogleMap.OnCameraMoveListener onCameraMoveListener = new GoogleMap.OnCameraMoveListener() {
        @Override
        public void onCameraMove() {
            final CameraPosition[] mPreviousCameraPosition = {null};
            CameraPosition position = googleMap.getCameraPosition();
            if(mPreviousCameraPosition[0] == null || mPreviousCameraPosition[0].zoom != position.zoom) {
                mPreviousCameraPosition[0] = googleMap.getCameraPosition();
                mapClusterManager.cluster();
            }
        }
    };
}

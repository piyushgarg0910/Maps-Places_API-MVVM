package com.example.android.maps.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.android.maps.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

public class CustomClusterAdapter implements GoogleMap.InfoWindowAdapter{

    private final View view;
    private  MapClusterManager mapClusterManager;
    Context context;

    public CustomClusterAdapter(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.adapter_layout, null);
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    public void setMapClusterManager(MapClusterManager mapClusterManager) {
        this.mapClusterManager = mapClusterManager;
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView name = (TextView) view.findViewById(R.id.adapterTitle);
        TextView address = (TextView) view.findViewById(R.id.adapterAddress);
        TextView lat = (TextView) view.findViewById(R.id.adapterLat);
        TextView lng = (TextView) view.findViewById(R.id.adapterLng);
        TextView ratings = (TextView) view.findViewById(R.id.adapterRatings);

        if (mapClusterManager == null)
        {
            name.setText(marker.getTitle());
            address.setText("Address: N/A");
            lat.setText("Latitude: "+String.valueOf(marker.getPosition().latitude));
            lng.setText("Longitude: "+String.valueOf(marker.getPosition().longitude));
            ratings.setText("Ratings: N/A");
        }
        else {
            name.setText(mapClusterManager.getTitle());
            lng.setText(String.valueOf("Longitude: "+mapClusterManager.getLongitude()));
            address.setText("Address: "+mapClusterManager.getVicinity());
            lat.setText(String.valueOf("Latitude: "+mapClusterManager.getLatitude()));
            ratings.setText(String.valueOf(mapClusterManager.getmRatings()));
        }
        mapClusterManager = null;

        return view;
    }

    ClusterManager.OnClusterItemInfoWindowClickListener onClusterItemInfoWindowClickListener =
            new ClusterManager.OnClusterItemInfoWindowClickListener() {
        @Override
        public void onClusterItemInfoWindowClick(ClusterItem clusterItem) {

        }
    };
}

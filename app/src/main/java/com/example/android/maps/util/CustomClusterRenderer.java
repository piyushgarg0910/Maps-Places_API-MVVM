package com.example.android.maps.util;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class CustomClusterRenderer extends DefaultClusterRenderer<MapClusterManager> {
    private Context context;
    private CustomClusterMarker customClusterMarker = new CustomClusterMarker();

    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<MapClusterManager> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
    }

    @Override
    public void setMinClusterSize(int minClusterSize) {
        super.setMinClusterSize(minClusterSize);
    }

    @Override
    protected void onBeforeClusterItemRendered(MapClusterManager item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
    }

    @Override
    protected void onClusterItemRendered(MapClusterManager clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
        Bitmap bitmap = customClusterMarker.getMarkerBitmapFromView(clusterItem.getURL(),context);
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
    }

    @Override
    public void setOnClusterClickListener(ClusterManager.OnClusterClickListener<MapClusterManager> listener) {
        super.setOnClusterClickListener(listener);
    }

    @Override
    public void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<MapClusterManager> listener) {
        super.setOnClusterInfoWindowClickListener(listener);
    }
}

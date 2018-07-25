package com.example.android.maps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.maps.R;
import com.example.android.maps.model.MapResponse;
import com.example.android.maps.model.MapResults;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridRecyclerViewHolder> {

    private Context context;
    private ArrayList<MapResponse>mapResponseArrayList = new ArrayList<>();
    private ArrayList<MapResults> mapResultsArrayList = new ArrayList<>();

    public void setMapResponseArrayList(ArrayList<MapResponse> mapResponseArrayList) {
        this.mapResponseArrayList = mapResponseArrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull GridRecyclerViewHolder holder, int position) {
        holder.title.setText(mapResultsArrayList.get(position).getmName());
        String add = "Address: " + mapResultsArrayList.get(position).getmVicinity();
        holder.address.setText(add);
        String lat =  "Latitude: " + String.valueOf(mapResultsArrayList.get(position).getmGeometry().
                getmLocation().getmLat());
        String lng = ", Longitude: " +String.valueOf(mapResultsArrayList.get(position).getmGeometry().
                getmLocation().getmLng());
        String loc = lat + lng;
        holder.location.setText(loc);
        String rate = "Rating: " + String.valueOf(mapResultsArrayList.get(position).getmRating());
        holder.ratings.setText(rate);
        Picasso.with(holder.imageView.getContext()).load(mapResultsArrayList.get(position).
                getmIcon()).into(holder.imageView);
    }

    @NonNull
    @Override
    public GridRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_recycler_view,
                parent,false);
        context = parent.getContext();
        return new GridRecyclerViewHolder(view,mapResultsArrayList);
    }

    @Override
    public int getItemCount() {
        if (mapResponseArrayList == null)
        {
            return 0;
        }
        else {
            mapResultsArrayList.clear();
            for (int i = 0; i < mapResponseArrayList.size(); i++)
            {
                mapResultsArrayList.addAll(mapResponseArrayList.get(i).getmResults());
            }
            return mapResultsArrayList.size();
        }
    }
}

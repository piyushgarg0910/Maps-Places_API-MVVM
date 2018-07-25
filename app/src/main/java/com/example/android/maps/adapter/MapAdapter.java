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

import java.util.ArrayList;

public class MapAdapter extends RecyclerView.Adapter<MapRecyclerViewHolder> {

    private ArrayList<MapResponse> mapResponseArrayList = new ArrayList<>();
    private ArrayList<MapResults> mapResultsArrayList = new ArrayList<>();
    private Context context;
    private MapRecyclerViewHolder mapRecyclerViewHolder;

    public void setMapResponseArrayList(ArrayList<MapResponse> mapResponseArrayList){
        this.mapResponseArrayList = mapResponseArrayList;
    }

    @NonNull
    @Override
    public MapRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_recycler_view,
                parent, false);
        context = parent.getContext();
        return new MapRecyclerViewHolder(view,mapResultsArrayList);
    }

    @Override
    public void onBindViewHolder(@NonNull MapRecyclerViewHolder holder, int position) {
        if (mapResultsArrayList.get(position).getmName().length() >= 18)
            holder.Title.setText(mapResultsArrayList.get(position).getmName().substring(0,18));
        else
            holder.Title.setText(mapResultsArrayList.get(position).getmName());
        holder.Ratings.setText(String.valueOf(mapResultsArrayList.get(position).getmRating()));
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

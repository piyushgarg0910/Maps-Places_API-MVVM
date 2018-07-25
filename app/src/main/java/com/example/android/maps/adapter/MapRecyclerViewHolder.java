package com.example.android.maps.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.maps.R;
import com.example.android.maps.model.MapResults;
import com.example.android.maps.view.MapDetailFragment;

import java.util.ArrayList;

public class MapRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView Title,Ratings;
    private Context context;

    public MapRecyclerViewHolder(View itemView, ArrayList<MapResults>mapResultsArrayList) {
        super(itemView);

        context = itemView.getContext();

        Title = (TextView) itemView.findViewById(R.id.title);
        Ratings = (TextView) itemView.findViewById(R.id.ratings);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapResults mapResults = mapResultsArrayList.get(getAdapterPosition());
                Bundle bundle = new Bundle();
                bundle.putSerializable("Results", mapResults);
                Fragment fragment = new MapDetailFragment();
                fragment.setArguments(bundle);
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction()
                        .replace(R.id.mapFrag,fragment).addToBackStack("MapDetails");
                fragmentTransaction.commit();
            }
        });
    }
}

package com.example.android.maps.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.maps.R;
import com.example.android.maps.model.MapResults;
import com.example.android.maps.view.GridDetailFragment;

import java.util.ArrayList;

public class GridRecyclerViewHolder extends RecyclerView.ViewHolder{

    TextView title,address,location,ratings;
    ImageView imageView;
    private Context context;

    public GridRecyclerViewHolder(View itemView, ArrayList<MapResults>mapResultsArrayList) {
        super(itemView);

        context = itemView.getContext();
        title = (TextView) itemView.findViewById(R.id.GridName);
        address = (TextView) itemView.findViewById(R.id.GridAddress);
        location = (TextView) itemView.findViewById(R.id.GridLocation);
        ratings = (TextView) itemView.findViewById(R.id.GridRatings);
        imageView = (ImageView) itemView.findViewById(R.id.gridImage);

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MapResults mapResults = mapResultsArrayList.get(getAdapterPosition());
                Bundle bundle = new Bundle();
                bundle.putSerializable("Results", mapResults);
                Fragment fragment = new GridDetailFragment();
                fragment.setArguments(bundle);
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction()
                        .replace(R.id.GridView,fragment).addToBackStack("GridDetails");
                fragmentTransaction.commit();
            }
        });
    }
}

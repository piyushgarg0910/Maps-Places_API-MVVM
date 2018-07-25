package com.example.android.maps.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.maps.R;
import com.example.android.maps.model.MapResults;
import com.squareup.picasso.Picasso;

public class GridDetailFragment extends Fragment{

    private MapResults mapResults;
    private TextView Name,Rating,Location,Address;
    private Button button;
    private ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_detail,container,false);

        Name = (TextView) view.findViewById(R.id.GridItemName);
        Address = (TextView) view.findViewById(R.id.GridItemAddress);
        Rating = (TextView) view.findViewById(R.id.GridItemRatings);
        Location = (TextView) view.findViewById(R.id.GridItemLocation);
        button =(Button) view.findViewById(R.id.GridItemButton);
        imageView = (ImageView) view.findViewById(R.id.GridItemImage);

        Bundle bundle = getArguments();
        mapResults = (MapResults) bundle.getSerializable("Results");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Name.setText(mapResults.getmName());
        String add = "Address: " + mapResults.getmVicinity();
        Address.setText(add);
        String rate = "Rating: " + mapResults.getmRating();
        Rating.setText(rate);
        String lat = "Latitude: " + mapResults.getmGeometry().getmLocation().getmLat();
        String lng = " Longitude: " + mapResults.getmGeometry().getmLocation().getmLng();
        String loc = lat + "," + lng;
        Location.setText(loc);
        Picasso.with(view.getContext()).load(mapResults.getmIcon()).into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Navigating to location",
                        Toast.LENGTH_SHORT).show();
                Uri gmIntentURI = Uri.parse("google.navigation:q="+mapResults.getmGeometry()
                        .getmLocation().getmLat()+","+mapResults.getmGeometry().
                        getmLocation().getmLng()+"&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmIntentURI);
                startActivity(mapIntent);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
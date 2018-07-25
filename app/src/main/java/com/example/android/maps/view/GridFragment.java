package com.example.android.maps.view;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.maps.R;
import com.example.android.maps.adapter.GridAdapter;
import com.example.android.maps.model.MapResponse;
import com.example.android.maps.viewmodel.MapViewModel;

import java.util.ArrayList;

public class GridFragment extends Fragment {

    String placeType;
    Double latitude;
    Double longitude;
    int radius;
    MapViewModel mapViewModel;
    ArrayList<MapResponse> mapResponseArrayList = new ArrayList<>();
    GridAdapter gridAdapter = new GridAdapter();
    RecyclerView recyclerView;
    FloatingActionButton mapButton,gridButton;
    FragmentMap fragmentMap;
    static int count =0;
    Toolbar toolbar;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_fragment,container,false);

        mapButton = (FloatingActionButton) view.findViewById(R.id.MapButton);
        gridButton = (FloatingActionButton) view.findViewById(R.id.GridsButton);

        setHasOptionsMenu(true);

        gridButton.setRippleColor(getResources().getColor(R.color.OliveGreen,null));

        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count%2 !=0) {
                    mapButton.animate().translationY(0.0f);
                    gridButton.setBackgroundTintList(ColorStateList.valueOf(getResources().
                            getColor(R.color.colorAccent, null)));
                }
                else {
                    mapButton.animate().translationY(-164.0f);
                    gridButton.setBackgroundTintList(ColorStateList.valueOf(getResources().
                            getColor(R.color.NavyBlue,null)));
                }
                count++;
                if (count == 10)
                    count =0;
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                mapButton.setRippleColor(getResources().getColor(R.color.OliveGreen,null));
                mapButton.animate().translationY(0.0f);
                count = 0;
                gridButton.setBackgroundTintList(ColorStateList.valueOf(getResources().
                        getColor(R.color.colorAccent, null)));

                bundle1.putString("type",placeType);
                bundle1.putDouble("latitude",fragmentMap.location.getLatitude());
                bundle1.putDouble("longitude",fragmentMap.location.getLongitude());
                bundle1.putInt("radius",5000);
                Fragment fragment = new MapScreenFragment();
                fragment.setArguments(bundle1);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.GridView,fragment);
                fragmentTransaction.commit();
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        Bundle bundle = getArguments();
        if (bundle != null) {
            placeType = bundle.getString("type");
            latitude = bundle.getDouble("latitude");
            longitude = bundle.getDouble("longitude");
            radius = bundle.getInt("radius");
            getPlaces(placeType,latitude,longitude,radius);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main1,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_button1).getActionView();
        searchView.setFocusable(true);
        //searchView.requestFocusFromTouch();
        searchView.setQueryHint("eg: restaurant, atm, etc.");
        SearchManager searchManager = (SearchManager) getActivity().
                getSystemService(Context.SEARCH_SERVICE);


        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null)
                {
                    placeType = query;
                    if (fragmentMap.location != null)
                    {
                        getPlaces(placeType,latitude,
                                longitude,5000);
                        //getPlaces(string, "19.0760000,72.8777000", 5000);
                    }
                    else {
                        getPlaces(placeType, 39.0094286,-76.8960054, 5000);
                    }
                    searchView.clearFocus();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.GridRecyclerView);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(gridAdapter);

        toolbar = (Toolbar) view.findViewById(R.id.GridToolbar);
        toolbar.setTitle("Maps");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    public void getPlaces(String placeType, Double latitude, Double longitude, int radius){
        mapResponseArrayList.clear();
        mapViewModel.getMapResponseLiveData(placeType,latitude.toString() + "," +
                longitude.toString(),radius).
                observe(this, new Observer<ArrayList<MapResponse>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MapResponse> mapResponses) {
                mapResponseArrayList = mapResponses;
                gridAdapter.setMapResponseArrayList(mapResponseArrayList);
                gridAdapter.notifyDataSetChanged();
            }
        });
    }
}
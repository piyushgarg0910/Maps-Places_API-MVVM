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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.maps.R;
import com.example.android.maps.adapter.MapAdapter;
import com.example.android.maps.model.MapResponse;
import com.example.android.maps.viewmodel.MapViewModel;

import java.util.ArrayList;

public class MapScreenFragment extends Fragment {

    private MapViewModel mapViewModel;
    Toolbar toolbar;
    String string;
    ArrayList<MapResponse> mapResponseArrayList = new ArrayList<>();
    FragmentMap fragmentMap;
    RecyclerView recyclerView;
    MapAdapter mapAdapter = new MapAdapter();
    FloatingActionButton mapButton, gridButton;
    static int count = 0 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {

        View view = layoutInflater.inflate(R.layout.map_fragment,viewGroup,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.MapRecyclerViewLayout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mapAdapter);
        setHasOptionsMenu(true);
        fragmentMap = (FragmentMap) getChildFragmentManager().findFragmentById(R.id.map);

        mapButton = (FloatingActionButton) view.findViewById(R.id.MapsButton);
        gridButton = (FloatingActionButton) view.findViewById(R.id.GridButton);

        mapButton.setRippleColor(getResources().getColor(R.color.OliveGreen,null));

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count%2 !=0) {
                    gridButton.animate().translationY(0.0f);
                    mapButton.setBackgroundTintList(ColorStateList.valueOf(getResources().
                            getColor(R.color.colorAccent, null)));
                }
                else {
                    gridButton.animate().translationY(-164.0f);
                    mapButton.setBackgroundTintList(ColorStateList.valueOf(getResources().
                            getColor(R.color.NavyBlue,null)));
                }
                count++;
                if (count == 10)
                    count =0;
            }
        });

        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                gridButton.animate().translationY(0.0f);
                count = 0;
                mapButton.setBackgroundTintList(ColorStateList.valueOf(getResources().
                        getColor(R.color.colorAccent, null)));
                bundle1.putString("type",string);
                bundle1.putDouble("latitude",fragmentMap.location.getLatitude());
                bundle1.putDouble("longitude",fragmentMap.location.getLongitude());
                bundle1.putInt("radius",5000);
                Fragment fragment = new GridFragment();
                fragment.setArguments(bundle1);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mapFrag,fragment);
                gridButton.setRippleColor(getResources().getColor(R.color.OliveGreen,null));
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar =(Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Maps");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_button).getActionView();
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
                    string = query;
                    if (fragmentMap.location != null)
                    {
                        getPlaces(string,String.valueOf(fragmentMap.location.getLatitude())
                                +","+String.valueOf(fragmentMap.location.getLongitude()),5000);
                        //getPlaces(string, "19.0760000,72.8777000", 5000);
                    }
                    else {
                        getPlaces(string, "39.0094286,-76.8960054", 5000);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*if (savedInstanceState != null)
        {
            mapResponseArrayList = (ArrayList<MapResponse>) savedInstanceState.
                    getSerializable("ResponseArrayObject");
            fragmentMap.setMarkers(mapResponseArrayList);
        }
        else
            mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);*/
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        Bundle bundle = getArguments();
        if (bundle != null) {
            //String placeType = bundle.getString("type");
            String placeType = bundle.getString("type");
            Double latitude = bundle.getDouble("latitude");
            Double longitude = bundle.getDouble("longitude");
            int radius = bundle.getInt("radius");

            String loc = latitude + "," + longitude;
            mapResponseArrayList.clear();
            getPlaces(placeType, loc, radius);
        }
    }

    public void getPlaces(String type, String location, int radius)
    {
        mapResponseArrayList.clear();
        /*mapViewModel.getMapResponseLiveData(type,location,radius).observe(this,
                new Observer<MapResponse>() {
            @Override
            public void onChanged(@Nullable MapResponse mapRes00ponse) {
                    mapResultsArrayList = mapResponse.getmResults();
                    fragmentMap.setMarkers(mapResultsArrayList);
            }
        });*/
        mapViewModel.getMapResponseLiveData(type,location,radius).observe(this, new Observer<ArrayList<MapResponse>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MapResponse> mapResponses) {
                mapResponseArrayList = mapResponses;
                mapAdapter.setMapResponseArrayList(mapResponseArrayList);
                mapAdapter.notifyDataSetChanged();
                fragmentMap.setMarkers(mapResponseArrayList);
                // printData(mapResponses);
            }
        });
    }

    /*public void printData(ArrayList<MapResponse> mapResponses){
        for (int i = 0; i < mapResponses.size(); i++){
            for (int j = 0; j < mapResponses.get(i).getmResults().size(); j++)
            {
                Log.e("Name"+(i+1)+": ",mapResponses.get(i).getmResults().get(j).getmName());
            }
        }
    }*/

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //outState.putSerializable("ResponseArrayObject",mapResponseArrayList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}

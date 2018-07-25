package com.example.android.maps.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.maps.model.MapResponse;
import com.example.android.maps.repo.MapRepo;

import java.util.ArrayList;

public class MapViewModel extends ViewModel {

    private LiveData<ArrayList<MapResponse>> mapResponseLiveData;
    private MapRepo mapRepo = new MapRepo();

    public MapViewModel() {
    }

    private void getMapResult(String type, String loc, int radius){
        mapResponseLiveData = mapRepo.getMapResponse(type,loc,radius);
    }

    public LiveData<ArrayList<MapResponse>> getMapResponseLiveData(String type, String loc, int radius) {
        getMapResult(type, loc, radius);
        return mapResponseLiveData;
    }
}

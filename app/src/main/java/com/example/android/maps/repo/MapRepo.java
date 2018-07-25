package com.example.android.maps.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.android.maps.model.MapResponse;
import com.example.android.maps.newtork.MapInterface;
import com.example.android.maps.newtork.MapRetro;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapRepo {

    private ArrayList<MapResponse> mapResponseArrayList = new ArrayList<>();
    private static int count = 0;
    private MutableLiveData<ArrayList<MapResponse>> mapResponseMutableArrayList
            = new MutableLiveData<>();

    public LiveData<ArrayList<MapResponse>> getMapResponse(String placeType, String location, int radius)
    {
        //final MutableLiveData<MapResponse> mapResponseMutableLiveData = new MutableLiveData<>();
        final MapInterface mapInterface;
        mapInterface = MapRetro.getRetrofit().create(MapInterface.class);
        mapResponseArrayList.clear();
        MakeNetworkCall(mapInterface,placeType,location,radius);
        return mapResponseMutableArrayList;
    }

   private void MakeNetworkCall (final MapInterface mapInterface, final String placeType,
                                 final String location, final int radius){

       mapInterface.getMapResponse(placeType,location,radius).enqueue(new Callback<MapResponse>() {
           @Override
           public void onResponse(Call<MapResponse> call, Response<MapResponse> response) {
               //mapResponseMutableLiveData.setValue(response.body());
               mapResponseArrayList.add(response.body());
               count ++;
               if(response.body().getmNextPageToken() != null)
               {
                   MakeNextPageCall(mapInterface,response.body().getmNextPageToken());
               }
               else
               {
                   mapResponseMutableArrayList.postValue(mapResponseArrayList);
               }
           }
           @Override
           public void onFailure(Call<MapResponse> call, Throwable t) {
               Log.e("Some Error..!!",t.getMessage());
           }
       });
    }

    private void MakeNextPageCall (final MapInterface mapInterface, final String token){

        mapInterface.getNextPage(token).enqueue(new Callback<MapResponse>() {
            @Override
            public void onResponse(Call<MapResponse> call, Response<MapResponse> response) {
                mapResponseArrayList.add(response.body());
                count++;
                if ((response.body().getmNextPageToken() != null) && (count < 3)) {
                    MakeNextPageCall(mapInterface, token);
                } else {
                    mapResponseMutableArrayList.postValue(mapResponseArrayList);
                }
            }

            @Override
            public void onFailure(Call<MapResponse> call, Throwable t) {
                Log.e("Some Error..!!",t.getMessage());
            }
        });
    }
}

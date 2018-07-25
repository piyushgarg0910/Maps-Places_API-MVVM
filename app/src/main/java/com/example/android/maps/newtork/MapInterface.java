package com.example.android.maps.newtork;

import com.example.android.maps.model.MapResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MapInterface {

    @GET("nearbysearch/json?sensor=true&key=AIzaSyCvmhK4CPh86-W9lU39QQQViaRKNty3s2A")
    Call<MapResponse> getMapResponse(@Query("type") String placeType,
                                     @Query("location") String location, @Query("radius")
                                             int radius);

    @GET("nearbysearch/json?sensor=true&key=AIzaSyCvmhK4CPh86-W9lU39QQQViaRKNty3s2A")
    Call<MapResponse> getNextPage(@Query("pagetoken") String token);
}

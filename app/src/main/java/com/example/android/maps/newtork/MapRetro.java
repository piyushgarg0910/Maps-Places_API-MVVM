package com.example.android.maps.newtork;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapRetro {

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

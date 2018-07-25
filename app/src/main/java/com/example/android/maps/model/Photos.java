package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Photos implements Serializable {

    @SerializedName("height")
    private Integer mHeight;

    @SerializedName("html_attributions")
    private ArrayList<String> mHTMLAttributes = new ArrayList<>();

    @SerializedName("photo_reference")
    private String mPhotos;

    @SerializedName("width")
    private Integer mWidth;

    public Photos(Integer mHeight, ArrayList<String> mHTMLAttributes, String mPhotos, Integer mWidth) {
        this.mHeight = mHeight;
        this.mHTMLAttributes = mHTMLAttributes;
        this.mPhotos = mPhotos;
        this.mWidth = mWidth;
    }

    public Integer getmHeight() {
        return mHeight;
    }

    public void setmHeight(Integer mHeight) {
        this.mHeight = mHeight;
    }

    public ArrayList<String> getmHTMLAttributes() {
        return mHTMLAttributes;
    }

    public void setmHTMLAttributes(ArrayList<String> mHTMLAttributes) {
        this.mHTMLAttributes = mHTMLAttributes;
    }

    public String getmPhotos() {
        return mPhotos;
    }

    public void setmPhotos(String mPhotos) {
        this.mPhotos = mPhotos;
    }

    public Integer getmWidth() {
        return mWidth;
    }

    public void setmWidth(Integer mWidth) {
        this.mWidth = mWidth;
    }
}

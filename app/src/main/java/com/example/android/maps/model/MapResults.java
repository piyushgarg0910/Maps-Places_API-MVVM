package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MapResults implements Serializable{

    @SerializedName("geometry")
    private Geometry mGeometry;

    @SerializedName("icon")
    private String mIcon;

    @SerializedName("id")
    private String mID;

    @SerializedName("name")
    private String mName;

    @SerializedName("opening_hours")
    private OpeningHours mOpeningHours;

    @SerializedName("photos")
    private ArrayList<Photos> mPhotos = new ArrayList<>();

    @SerializedName("place_id")
    private String mPlaceId;

    @SerializedName("rating")
    private Double mRating;

    @SerializedName("reference")
    private String mReferences;

    @SerializedName("scope")
    private String mScope;

    @SerializedName("types")
    private ArrayList<String> mTypes = new ArrayList<>();

    @SerializedName("vicinity")
    private String mVicinity;

    @SerializedName("price_level")
    private Integer mPriceLevel;

    @SerializedName("plus_code")
    private PlusCode mPlusCode;

    public MapResults(Geometry mGeometry, String mIcon, String mID, String mName,
                      OpeningHours mOpeningHours, ArrayList<Photos> mPhotos, String mPlaceId,
                      Double mRating, String mReferences, String mScope, ArrayList<String> mTypes,
                      String mVicinity, Integer mPriceLevel, PlusCode mPlusCode) {
        this.mGeometry = mGeometry;
        this.mIcon = mIcon;
        this.mID = mID;
        this.mName = mName;
        this.mOpeningHours = mOpeningHours;
        this.mPhotos = mPhotos;
        this.mPlaceId = mPlaceId;
        this.mRating = mRating;
        this.mReferences = mReferences;
        this.mScope = mScope;
        this.mTypes = mTypes;
        this.mVicinity = mVicinity;
        this.mPriceLevel = mPriceLevel;
        this.mPlusCode = mPlusCode;
    }

    public Geometry getmGeometry() {
        return mGeometry;
    }

    public void setmGeometry(Geometry mGeometry) {
        this.mGeometry = mGeometry;
    }

    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public OpeningHours getmOpeningHours() {
        return mOpeningHours;
    }

    public void setmOpeningHours(OpeningHours mOpeningHours) {
        this.mOpeningHours = mOpeningHours;
    }

    public ArrayList<Photos> getmPhotos() {
        return mPhotos;
    }

    public void setmPhotos(ArrayList<Photos> mPhotos) {
        this.mPhotos = mPhotos;
    }

    public String getmPlaceId() {
        return mPlaceId;
    }

    public void setmPlaceId(String mPlaceId) {
        this.mPlaceId = mPlaceId;
    }

    public Double getmRating() {
        return mRating;
    }

    public void setmRating(Double mRating) {
        this.mRating = mRating;
    }

    public String getmReferences() {
        return mReferences;
    }

    public void setmReferences(String mReferences) {
        this.mReferences = mReferences;
    }

    public String getmScope() {
        return mScope;
    }

    public void setmScope(String mScope) {
        this.mScope = mScope;
    }

    public ArrayList<String> getmTypes() {
        return mTypes;
    }

    public void setmTypes(ArrayList<String> mTypes) {
        this.mTypes = mTypes;
    }

    public String getmVicinity() {
        return mVicinity;
    }

    public void setmVicinity(String mVicinity) {
        this.mVicinity = mVicinity;
    }

    public Integer getmPriceLevel() {
        return mPriceLevel;
    }

    public void setmPriceLevel(Integer mPriceLevel) {
        this.mPriceLevel = mPriceLevel;
    }

    public PlusCode getmPlusCode() {
        return mPlusCode;
    }

    public void setmPlusCode(PlusCode mPlusCode) {
        this.mPlusCode = mPlusCode;
    }
}

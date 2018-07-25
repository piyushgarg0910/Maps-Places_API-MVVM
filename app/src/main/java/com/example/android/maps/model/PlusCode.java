package com.example.android.maps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlusCode implements Serializable{

    @SerializedName("compound_code")
    private String mCompoundCode;

    @SerializedName("global_code")
    private String mGlobalCode;

    public PlusCode(String mCompoundCode, String mGlobalCode) {
        this.mCompoundCode = mCompoundCode;
        this.mGlobalCode = mGlobalCode;
    }

    public String getmCompoundCode() {
        return mCompoundCode;
    }

    public void setmCompoundCode(String mCompoundCode) {
        this.mCompoundCode = mCompoundCode;
    }

    public String getmGlobalCode() {
        return mGlobalCode;
    }

    public void setmGlobalCode(String mGlobalCode) {
        this.mGlobalCode = mGlobalCode;
    }
}

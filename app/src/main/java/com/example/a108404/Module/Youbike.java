package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Youbike implements Serializable {
    @SerializedName("sna")
    public String stationName;

    @SerializedName("sbi")
    public String canBorrow;

    @SerializedName("lat")
    public String lat;

    @SerializedName("lng")
    public String lng;

    @SerializedName("bemp")
    public String bemp;

    public Youbike(String stationName, String canBorrow, String lat, String lng, String bemp){
        this.stationName = stationName;
        this.canBorrow = canBorrow;
        this.lat = lat;
        this.lng = lng;
        this.bemp = bemp;
    }

    public String getLat() {
        return lat;
    }

    public String getBemp() {
        return bemp;
    }

    public String getCanBorrow() {
        return canBorrow;
    }

    public String getLng() {
        return lng;
    }

    public String getStationName() {
        return stationName;
    }
}

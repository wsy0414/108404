package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Youbike implements Serializable {
    @SerializedName("StationName_zh")
    public String stationName;

    @SerializedName("Longitude")
    public String lng;

    @SerializedName("Latitude")
    public String lat;

    @SerializedName("AvailableRentBikes")
    public String bemp;

    @SerializedName("AvailableReturnBikes")
    public String canBorrow;

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

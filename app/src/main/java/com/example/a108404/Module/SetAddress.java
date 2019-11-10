package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class SetAddress{
    @SerializedName("Longitude")
    public String lon;
    @SerializedName("Latitude")
    public String lat;

    public SetAddress(String lon, String lat){
        this.lat = lat;
        this.lon = lon;
    }
}

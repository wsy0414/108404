package com.example.a108404;

import com.google.gson.annotations.SerializedName;

public class SetAddress{
    @SerializedName("Longitude")
    public String lon;
    @SerializedName("Latitude")
    public String lat;

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}

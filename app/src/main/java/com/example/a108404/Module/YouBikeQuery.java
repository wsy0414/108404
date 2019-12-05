package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class YouBikeQuery{
    @SerializedName("Longitude")
    double lng;

    @SerializedName("Latitude")
    double lat;

    @SerializedName("type")
    String type;

    @SerializedName("City")
    String city;

    public YouBikeQuery(double lng, double lat, String type, String city) {
        this.lng = lng;
        this.lat = lat;
        this.type = type;
        this.city = city;
    }
}

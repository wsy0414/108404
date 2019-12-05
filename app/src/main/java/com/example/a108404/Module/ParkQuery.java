package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class ParkQuery {
    @SerializedName("Longitude")
    double lng;

    @SerializedName("Latitude")
    double lat;

    @SerializedName("contain")
    String contain;

    @SerializedName("type")
    String type;

    public ParkQuery(double lng, double lat, String contain, String type) {
        this.lat = lat;
        this.lng = lng;
        this.contain = contain;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getContain() {
        return contain;
    }
}

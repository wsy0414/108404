package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class ParkQuery extends SetAddress {
    @SerializedName("contain")
    String contain;

    @SerializedName("type")
    String type;

    public ParkQuery(String lon, String lat, String contain, String type) {
        super(lon, lat);
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

package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllYouBikeQuery {
    @SerializedName("GetBike")
    String getBike;

    @SerializedName("City")
    String city;

    public AllYouBikeQuery(String getBike, String city){
        this.getBike = getBike;
        this.city = city;
    }
}

package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParkNTPC {
    @SerializedName("result")
    String result;

    @SerializedName("type")
    String type;

    @SerializedName("parks")
    ArrayList<Park> park;

    public ParkNTPC(String result, String type,  ArrayList<Park> park){
        this.result = result;
        this.type = type;
        this.park = park;
    }

    public String getResult() {
        return result;
    }

    public ArrayList<Park> getPark() {
        return park;
    }

    public String getType() {
        return type;
    }
}

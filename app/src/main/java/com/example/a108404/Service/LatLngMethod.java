package com.example.a108404.Service;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LatLngMethod {
    Gson gson = new GsonBuilder().create();
    public String addresses(Context context, String addressStr){
        Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addressLocation = geoCoder.getFromLocationName(addressStr, 1);
            return  gson.toJson(addressLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

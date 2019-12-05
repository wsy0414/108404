package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class Retval {
    @SerializedName("bikes")
    public ArrayList<Youbike> retVal;

    public Retval(ArrayList<Youbike> retval){
        this.retVal = retval;
    }

    public ArrayList<Youbike> getRetVal() {
        return retVal;
    }
}

package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class Retval {
    @SerializedName("retCode")
    public String retCode;

    @SerializedName("retVal")
    public Map<String, Youbike> retVal;

    public Retval(String retCode, Map<String, Youbike> retval){
        this.retCode = retCode;
        this.retVal = retval;
    }

    public String getRetCode() {
        return retCode;
    }

    public Map<String, Youbike> getRetVal() {
        return retVal;
    }
}

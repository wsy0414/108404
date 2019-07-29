package com.example.a108404;

import com.google.gson.annotations.SerializedName;

public class Oil{
    @SerializedName("92")
    public String oil92;
    @SerializedName("95")
    public String oil95;
    @SerializedName("98")
    public String oil98;
    @SerializedName("超級柴油")
    public String superOil;

    public Oil(String oil92, String oil95, String oil98, String superOil){
        this.oil92 = oil92;
        this.oil95 = oil95;
        this.oil98 = oil98;
        this.superOil = superOil;
    }

    public String getOil92() {
        return oil92;
    }

    public String getOil95() {
        return oil95;
    }

    public String getOil98() {
        return oil98;
    }

    public String getSuperOil() {
        return superOil;
    }
}


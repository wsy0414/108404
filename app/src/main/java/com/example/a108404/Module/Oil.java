package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class Oil{
    @SerializedName("unleaded")
    public String oil92;
    @SerializedName("super_")
    public String oil95;
    @SerializedName("supreme")
    public String oil98;
    @SerializedName("diesel")
    public String superOil;
    @SerializedName("result")
    public int result;
    @SerializedName("alcoholGas")
    public String alcoholGas;
    @SerializedName("liquefiedGas")
    public String liquefiedGas;

    public Oil(String oil92, String oil95, String oil98, String superOil, int result, String alcoholGas, String liquefiedGas){
        this.oil92 = oil92;
        this.oil95 = oil95;
        this.oil98 = oil98;
        this.superOil = superOil;
        this.result = result;
        this.alcoholGas = alcoholGas;
        this.liquefiedGas = liquefiedGas;
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


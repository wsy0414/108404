package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class Aqi{
    @SerializedName("SiteName")
    public String siteName;
    @SerializedName("County")
    public String country;
    @SerializedName("AQI")
    public String AQI;
    @SerializedName("Pollutant")
    public String pollutant;
    @SerializedName("AQIStatus")
    public String status;
    @SerializedName("PM2.5Status")
    public String pmStatus;
    @SerializedName("PM10")
    public String PM10;
    @SerializedName("PM2.5")
    public String PM25;
    @SerializedName("So2")
    public String So2;
    @SerializedName("Co")
    public String Co;
    @SerializedName("O3")
    public String O3;

    public Aqi(String siteName, String country, String aqi, String pollutant, String status, String pm10, String pm25, String so2, String co, String o3, String pmStatus){
        this.siteName = siteName;
        this.country = country;
        this.AQI = aqi;
        this.pollutant = pollutant;
        this.status = status;
        this.PM10 = pm10;
        this.PM25 = pm25;
        this.So2 = so2;
        this.Co = co;
        this.O3 = o3;
        this.pmStatus = pmStatus;
    }

    public String getAQI() {
        return AQI;
    }

    public String getCountry() {
        return country;
    }

    public String getPollutant() {
        return pollutant;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getCo() {
        return Co;
    }

    public String getPM10() {
        return PM10;
    }

    public String getStatus() {
        return status;
    }

    public String getO3() {
        return O3;
    }

    public String getPM25() {
        return PM25;
    }

    public String getSo2() {
        return So2;
    }

    public String getPmStatus() {
        return pmStatus;
    }
}


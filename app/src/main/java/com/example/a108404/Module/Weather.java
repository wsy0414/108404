package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class Weather{
    @SerializedName("tempNow")
    String tempNow;
    @SerializedName("tempMax")
    String tempMax;
    @SerializedName("tempMin")
    String tempMin;
    @SerializedName("windDir")
    String windDir;
    @SerializedName("windSpeed")
    String windSpeed;
    @SerializedName("humidity")
    String humidity;
    @SerializedName("rainFall")
    String rainFall;
    @SerializedName("uviH")
    String uviH;

    public Weather(String tempNow, String tempMax, String tempMin, String windDir, String windSpeed, String humidity, String rainFall, String uviH){
        this.tempNow = tempNow;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.windDir = windDir;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.rainFall = rainFall;
        this.uviH = uviH;
    }

    public String getTempNow() {
        return tempNow;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getRainFall() {
        return rainFall;
    }

    public String getUviH() {
        return uviH;
    }

    public String getWindDir() {
        return windDir;
    }

    public String getWindSpeed() {
        return windSpeed;
    }
}

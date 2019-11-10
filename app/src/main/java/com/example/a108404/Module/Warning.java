package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class Warning {
    @SerializedName("result")
    public String result;
    @SerializedName("city")
    public String city;
    @SerializedName("warning")
    public String warning;
    @SerializedName("date")
    public String date;
    @SerializedName("time")
    public String time;

    public Warning(String result, String city, String warning, String date, String time){
        this.result = result;
        this.city = city;
        this.warning = warning;
        this.date = date;
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getWarning() {
        return warning;
    }
}

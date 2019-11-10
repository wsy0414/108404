package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class PreWeather {
    @SerializedName("result")
    String result;

    @SerializedName("NowStartDate")
    String nowStartDate;

    @SerializedName("NowEndDate")
    String nowEndDate;

    @SerializedName("NowStartTime")
    String nowStartTime;

    @SerializedName("NowEndTime")
    String nowEndTime;

    @SerializedName("NowMaxT")
    String nowMax;

    @SerializedName("NowMinT")
    String nowMin;

    @SerializedName("NowPoP")
    String nowRainFall;

    @SerializedName("NowWx")
    String nowState;

    @SerializedName("CloseStartDate")
    String closeStartDate;

    @SerializedName("CloseEndDate")
    String closeEndDate;

    @SerializedName("CloseStartTime")
    String closeStartTime;

    @SerializedName("CloseEndTime")
    String closeEndTime;

    @SerializedName("CloseMaxT")
    String closeMax;

    @SerializedName("CloseMinT")
    String closeMin;

    @SerializedName("ClosePoP")
    String closeRainFall;

    @SerializedName("CloseWx")
    String closeState;

    @SerializedName("FarStartDate")
    String farStartDate;

    @SerializedName("FarEndDate")
    String farEndDate;

    @SerializedName("FarStartTime")
    String farStartTime;

    @SerializedName("FarEndTime")
    String farEndTime;

    @SerializedName("FarMaxT")
    String farMax;

    @SerializedName("FarMinT")
    String farMin;

    @SerializedName("FarPoP")
    String farRainFall;

    @SerializedName("FarWx")
    String farState;

    public PreWeather(String result, String nowStartDate, String nowEndDate, String nowStartTime, String nowEndTime, String nowMax, String nowMin, String nowRainFall, String nowState, String closeStartDate
            , String closeEndDate, String closeStartTime, String closeEndTime, String closeMax, String closeMin, String closeRainFall, String closeState, String farStartDate, String farEndDate
            ,String farStartTime, String farEndTime, String farMax, String farMin, String farRainFall, String farState){
        this.result = result;
        this.nowStartDate = nowStartDate;
        this.nowEndDate = nowEndDate;
        this.nowStartTime = nowStartTime;
        this.nowEndTime = nowEndTime;
        this.nowMax = nowMax;
        this.nowMin = nowMin;
        this.nowRainFall = nowRainFall;
        this.nowState = nowState;
        this.closeStartDate = closeStartDate;
        this.closeEndDate = closeEndDate;
        this.closeStartTime = closeStartTime;
        this.closeEndTime = closeEndTime;
        this.closeMax = closeMax;
        this.closeMin = closeMin;
        this.closeRainFall = closeRainFall;
        this.closeState = closeState;
        this.farStartDate = farStartDate;
        this.farEndDate = farEndDate;
        this.farStartTime = farStartTime;
        this.farEndTime = farEndTime;
        this.farMax = farMax;
        this.farMin = farMin;
        this.farRainFall = farRainFall;
        this.farState = farState;
    }

    public String getCloseEndDate() {
        return closeEndDate;
    }

    public String getCloseStartDate() {
        return closeStartDate;
    }

    public String getCloseStartTime() {
        return closeStartTime;
    }

    public String getNowEndDate() {
        return nowEndDate;
    }

    public String getCloseEndTime() {
        return closeEndTime;
    }

    public String getCloseMax() {
        return closeMax;
    }

    public String getCloseMin() {
        return closeMin;
    }

    public String getCloseRainFall() {
        return closeRainFall;
    }

    public String getCloseState() {
        return closeState;
    }

    public String getFarStartDate() {
        return farStartDate;
    }

    public String getNowEndTime() {
        return nowEndTime;
    }

    public String getNowMax() {
        return nowMax;
    }

    public String getFarEndDate() {
        return farEndDate;
    }

    public String getNowMin() {
        return nowMin;
    }

    public String getFarStartTime() {
        return farStartTime;
    }

    public String getNowRainFall() {
        return nowRainFall;
    }

    public String getNowStartDate() {
        return nowStartDate;
    }

    public String getFarEndTime() {
        return farEndTime;
    }

    public String getFarMax() {
        return farMax;
    }

    public String getNowStartTime() {
        return nowStartTime;
    }

    public String getNowState() {
        return nowState;
    }

    public String getResult() {
        return result;
    }

    public String getFarMin() {
        return farMin;
    }

    public String getFarRainFall() {
        return farRainFall;
    }

    public String getFarState() {
        return farState;
    }
}

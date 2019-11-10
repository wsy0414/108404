package com.example.a108404.Module;

import com.google.gson.annotations.SerializedName;

public class Park {
    @SerializedName("NAME")
    String name;

    @SerializedName("DAY")
    String day;

    @SerializedName("HOUR")
    String hour;

    @SerializedName("PAY")
    String pay;

    @SerializedName("PAYCASH")
    String payCash;

    @SerializedName("MEMO")
    String memo;

    @SerializedName("CellStatus")
    String cellStatus;

    @SerializedName("IsNowCash")
    String isNowCash;

    @SerializedName("ParkStatus")
    String parkStatus;

    @SerializedName("ParkStatusZh")
    String parkStatusZh;

    @SerializedName("Haversine")
    String haversine;

    @SerializedName("Longitude")
    String longitude;

    @SerializedName("Latitude")
    String latitude;

    public Park(String name, String day, String hour, String pay, String payCash, String memo, String cellStatus, String isNowCash, String parkStatus, String parkStatusZh, String haversine, String latitude, String longitude){
        this.name = name;
        this.day = day;
        this.hour = hour;
        this.pay = pay;
        this.payCash = payCash;
        this.memo = memo;
        this.cellStatus = cellStatus;
        this.isNowCash = isNowCash;
        this.parkStatus = parkStatus;
        this.parkStatusZh = parkStatusZh;
        this.haversine = haversine;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCellStatus() {
        return cellStatus;
    }

    public String getDay() {
        return day;
    }

    public String getHaversine() {
        return haversine;
    }

    public String getHour() {
        return hour;
    }

    public String getIsNowCash() {
        return isNowCash;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getMemo() {
        return memo;
    }

    public String getName() {
        return name;
    }

    public String getParkStatus() {
        return parkStatus;
    }

    public String getParkStatusZh() {
        return parkStatusZh;
    }

    public String getPay() {
        return pay;
    }

    public String getPayCash() {
        return payCash;
    }
}

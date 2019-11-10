package com.example.a108404;

import com.example.a108404.Module.Aqi;
import com.example.a108404.Module.Oil;
import com.example.a108404.Module.Park;
import com.example.a108404.Module.ParkNTPC;
import com.example.a108404.Module.PreWeather;
import com.example.a108404.Module.Warning;
import com.example.a108404.Module.Weather;
import com.example.a108404.Module.Youbike;

import java.util.ArrayList;

public interface setData{
    void setOilData(Oil oilData);

    void setAqiData(Aqi aqiData);

    void setWeatherData(Weather weatherData);

    void setUbikeData(ArrayList<Youbike> ubikeData);

    void setParkData(ParkNTPC parkData);

    void setWarnData(Warning warnData);

    void setPreWeather(PreWeather preWeather);
}

package com.example.a108404.Service;

import com.example.a108404.MyAPIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager{
    private static RetrofitManager mInstance = new RetrofitManager();

    private MyAPIService myAPIService;

    private RetrofitManager() {
//        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(60, TimeUnit.SECONDS)   // 設置連線Timeout
//                .build();

        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://topic-ntub.herokuapp.com")           //https://topic-ntub.herokuapp.com/
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                //.client(okHttpClient)


        myAPIService = retrofit.create(MyAPIService.class);
    }

    public static RetrofitManager getInstance() {
        return mInstance;
    }

    public MyAPIService getAPI() {
        return myAPIService;
    }
}


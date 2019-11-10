package com.example.a108404.Service;

import com.example.a108404.MyAPIService;
import com.example.a108404.YoubikeAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoubikeRetrofitManager {
    private static YoubikeRetrofitManager mInstance = new YoubikeRetrofitManager();

    private YoubikeAPIService myAPIService;

    private YoubikeRetrofitManager() {
//        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(60, TimeUnit.SECONDS)   // 設置連線Timeout
//                .build();

        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tcgbusfs.blob.core.windows.net/blobyoubike/")           //https://topic-ntub.herokuapp.com/
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //.client(okHttpClient)


        myAPIService = retrofit.create(YoubikeAPIService.class);
    }

    public static YoubikeRetrofitManager getInstance() {
        return mInstance;
    }

    public YoubikeAPIService getAPI() {
        return myAPIService;
    }
}

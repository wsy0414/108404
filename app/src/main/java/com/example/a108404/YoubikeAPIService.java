package com.example.a108404;

import com.example.a108404.Module.Oil;
import com.example.a108404.Module.OilQuery;
import com.example.a108404.Module.Retval;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface YoubikeAPIService {
    @GET("YouBikeTP.json") // 用@Body表示要傳送Body資料
    Call<Retval> getYoubike();
}

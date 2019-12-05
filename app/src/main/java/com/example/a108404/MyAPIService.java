package com.example.a108404;

import com.example.a108404.Module.AllYouBikeQuery;
import com.example.a108404.Module.Aqi;
import com.example.a108404.Module.Oil;
import com.example.a108404.Module.OilQuery;
import com.example.a108404.Module.ParkNTPC;
import com.example.a108404.Module.ParkQuery;
import com.example.a108404.Module.PreWeather;
import com.example.a108404.Module.Retval;
import com.example.a108404.Module.SetAddress;
import com.example.a108404.Module.Warning;
import com.example.a108404.Module.Weather;
import com.example.a108404.Module.YouBikeQuery;
import com.example.a108404.Module.Youbike;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyAPIService {
    // 測試網站      https://jsonplaceholder.typicode.com/
    // GET網址      https://jsonplaceholder.typicode.com/albums/1
    // POST網址     https://jsonplaceholder.typicode.com/albums
    // ...typicode.com/[這裡就是API的路徑]
//
//    @GET("oils")    // 設置一個GET連線，路徑為albums/1
//    Call<Oil> getOil();    // 取得的回傳資料用Albums物件接收，連線名稱取為getAlbums
//
//    @GET("albums/{id}") // 用{}表示路徑參數，@Path會將參數帶入至該位置
//    Call<Albums> getAlbumsById(@Path("id") int id);

    @POST("/gasprice/") // 用@Body表示要傳送Body資料
    Call<Oil> postOil(@Body OilQuery oilQuery);


    @POST("/aqi/") // 用@Body表示要傳送Body資料
    Call<Aqi> postAqi(@Body SetAddress setAddress);

    @POST("/weather/") // 用@Body表示要傳送Body資料
    Call<Weather> postWeather(@Body SetAddress setAddress);

    @POST("/preweather/") // 用@Body表示要傳送Body資料
    Call<PreWeather> postPreWeather(@Body SetAddress setAddress);

    @POST("/parkNTPC/") // 用@Body表示要傳送Body資料
    Call<ParkNTPC> postParkNTPC(@Body ParkQuery parkQuery);

    @POST("/warning/") // 用@Body表示要傳送Body資料
    Call<Warning> postWarning(@Body SetAddress setAddress);

    @POST("/getCloseBike/") // 用@Body表示要傳送Body資料
    Call<Youbike> postUbike(@Body YouBikeQuery youBikeQuery);

    @POST("/getAllBike/") // 用@Body表示要傳送Body資料
    Call<Retval> postAllBike(@Body AllYouBikeQuery allYouBikeQuery);

    @POST("/parkNTPC/") // 用@Body表示要傳送Body資料
    Call<ParkNTPC> postAllPark(@Body ParkQuery parkQuery);
}


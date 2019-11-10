package com.example.a108404.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a108404.Adapter.MasterAdapter;
import com.example.a108404.DistanceCalculation;
import com.example.a108404.Module.Aqi;
import com.example.a108404.Module.OilQuery;
import com.example.a108404.Module.Park;
import com.example.a108404.Module.ParkNTPC;
import com.example.a108404.Module.ParkQuery;
import com.example.a108404.Module.PreWeather;
import com.example.a108404.Module.Retval;
import com.example.a108404.Module.SetAddress;
import com.example.a108404.Module.Warning;
import com.example.a108404.Module.Weather;
import com.example.a108404.Module.Youbike;
import com.example.a108404.OnItemClickListener;
import com.example.a108404.Service.LocationMethod;
import com.example.a108404.MyAPIService;
import com.example.a108404.Module.Oil;
import com.example.a108404.R;
import com.example.a108404.Service.RetrofitManager;
import com.example.a108404.Module.ToolList;
import com.example.a108404.Service.YoubikeRetrofitManager;
import com.example.a108404.YoubikeAPIService;
import com.example.a108404.setData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements setData {
    public FragmentManager fragmentManager = getSupportFragmentManager();
    ViewPager myViewPager;
    Toolbar toolbar;
    //public MasterFraAdapter masterFraAdapter;
    //private LocationMethod locationMethod;
    //public String nowLocation;
    public String[] loc = {"中正市", "天氣", "晴時多雲", "垃圾車", "再30分鐘即將到達"};
    String[] weatherTitle = {"現在天氣", "空氣品質", "今天白天"};
    String oilTitle = "油價";
    ArrayList<String> title =new ArrayList<String>();

    ArrayList test = new ArrayList();
    Oil oilData;

    ArrayList<ToolList> usinglist = new ArrayList<ToolList>();
    ArrayList<ToolList> nonList = new ArrayList<ToolList>();
    ArrayList<ToolList> toollist = new ArrayList<ToolList>();
    Gson gson = new GsonBuilder().create();
    TextView loctext;
    RecyclerView masterrecview;
    //String[] test = {"中正市", "天氣", "晴時多雲", "垃圾車", "再30分鐘即將到達"};
    MyAPIService myAPIService;
    MasterAdapter masterAdapter;
    YoubikeAPIService youbikeAPIService;
    DistanceCalculation distanceCalculation = new DistanceCalculation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragmentManager = getSupportFragmentManager();
        //myViewPager = (ViewPager)findViewById(R.id.myViewPager);

        initToolBar();

        loctext = (TextView)findViewById(R.id.localtext);
        masterrecview = (RecyclerView)findViewById(R.id.masterRec);

        //從本機端取得資料
        initTitleData();

        //取得現在位置
        //locationMethod = LocationMethod.getInstance(this);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(this);
       // myViewPager = (ViewPager)findViewById(R.id.myViewPager);
        //初始化recyclerview
        initRecyclerView();




    }

    @Override
    public void setOilData(Oil oilData) {
        //this.oilData = oilData;
        masterAdapter.oilData = oilData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAqiData(Aqi aqiData) {
        //this.oilData = oilData;
        masterAdapter.aqiData = aqiData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setWeatherData(Weather weatherData) {
        //this.oilData = oilData;
        masterAdapter.weatherData = weatherData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUbikeData(ArrayList<Youbike> ubikeData) {
        //this.oilData = oilData;
        masterAdapter.ubikeData = ubikeData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setParkData(ParkNTPC parkData){
        masterAdapter.parkData = parkData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setWarnData(Warning warnData){
        masterAdapter.warnData = warnData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPreWeather(PreWeather preWeatherData){
        masterAdapter.preWeatherData = preWeatherData;
        masterAdapter.notifyDataSetChanged();
    }

    private void initToolBar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.setting);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTitleData(){
        SharedPreferences spf = getSharedPreferences("tool", MODE_PRIVATE);
        if(spf.getString("use", "") == ""){
            ToolList toolWeather = new ToolList(true, "天氣");
            ToolList toolOil = new ToolList(true, "油價");
            ToolList toolPark = new ToolList(true, "新北市汽車停車");
            ToolList toolUbike = new ToolList(true, "YouBike");
            ToolList toolWarning = new ToolList(true, "警報");
            usinglist.add(toolWeather);
            usinglist.add(toolOil);
            usinglist.add(toolPark);
            usinglist.add(toolUbike);
            usinglist.add(toolWarning);

            for(int i = 0; i < usinglist.size(); i++){
                if(usinglist.get(i).getToolName().equals("天氣")){
                    title.add("現在天氣");
                    title.add("空氣品質");
                    title.add("今天白天");
                }

                if(usinglist.get(i).getToolName().equals("油價")){
                    title.add("油價");
                }

                if(usinglist.get(i).getToolName().equals("新北市汽車停車")){
                    title.add("新北市汽車停車");
                }

                if(usinglist.get(i).getToolName().equals("YouBike")){
                    title.add("YouBike");
                }

                if(usinglist.get(i).getToolName().equals("警報")){
                    title.add("警報");
                }
            }

            //toollist.add(usinglist);
            //toollist.add(nonList);

            String jsonStr = gson.toJson(usinglist);
            spf.edit().putString("use", jsonStr).apply();

        }else{
            Type collectionType = new TypeToken<ArrayList<ToolList>>() {}.getType();
            usinglist = gson.fromJson(spf.getString("use", ""), collectionType);
            for(int i = 0; i < usinglist.size(); i++){
                if(usinglist.get(i).getToolName().equals("天氣")){
                    title.add("現在天氣");
                    title.add("空氣品質");
                    title.add("今天白天");
                }

                if(usinglist.get(i).getToolName().equals("油價")){
                    title.add("油價");
                }

                if(usinglist.get(i).getToolName().equals("新北市汽車停車")){
                    title.add("新北市汽車停車");
                }

                if(usinglist.get(i).getToolName().equals("YouBike")){
                    title.add("YouBike");
                }

                if(usinglist.get(i).getToolName().equals("警報")){
                    title.add("警報");
                }
            }
        }
    }

    private void getUBikeData(final SetAddress setAddress){
        youbikeAPIService = YoubikeRetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //OilQuery oilQuery = new OilQuery(1);

        Call<Retval> result = youbikeAPIService.getYoubike();

        // 4. 執行call
        result.enqueue(new Callback<Retval>() {

            @Override
            public void onResponse(Call<Retval> call, Response<Retval> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    //setOilData(response.body());
                    //String title = response.body().getOil92();
                    //etAddress setAddress = new SetAddress("121.524471", "25.067043");
                    ArrayList<Youbike> ubikeData = distanceCalculation.calDistanceTopThree(response.body().getRetVal(), setAddress);
                    setUbikeData(ubikeData);
                    Log.d("ubike", ubikeData.toString());
                }
            }

            @Override
            public void onFailure(Call<Retval> call, Throwable t) {
                // 連線失敗
                Log.d("ubike", "error");
            }
        });
    }

    private void getParkData(ParkQuery parkQuery){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //ParkQuery parkQuery = new ParkQuery("121.502569", "24.986205", "0", "1");

        Call<ParkNTPC> call = myAPIService.postParkNTPC(parkQuery);

        // 4. 執行call
        call.enqueue(new Callback<ParkNTPC>() {

            @Override
            public void onResponse(Call<ParkNTPC> call, Response<ParkNTPC> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    setParkData(response.body());
                    //String title = response.body().getOil92();
                    //if(response.body().getPark() != null){
                       // setParkData(response.body().getPark());
                    //}

                    //Log.d("park", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ParkNTPC> call, Throwable t) {
                // 連線失敗
            }
        });
    }

    private void getOilData(){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        OilQuery oilQuery = new OilQuery(1);

        Call<Oil> call = myAPIService.postOil(oilQuery);

            // 4. 執行call
            call.enqueue(new Callback<Oil>() {

                @Override
                public void onResponse(Call<Oil> call, Response<Oil> response) {
                    // 連線成功
                    // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                    if (response != null) {
                        setOilData(response.body());
                        //String title = response.body().getOil92();
                        Log.d("oil", response.message());
                    }
                }

                @Override
                public void onFailure(Call<Oil> call, Throwable t) {
                    // 連線失敗
                }
            });
    }

    private void getPreWeatherData(SetAddress setAddress){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //OilQuery oilQuery = new OilQuery(1);

        Call<PreWeather> call = myAPIService.postPreWeather(setAddress);

        // 4. 執行call
        call.enqueue(new Callback<PreWeather>() {

            @Override
            public void onResponse(Call<PreWeather> call, Response<PreWeather> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    setPreWeather(response.body());
                    Log.d("preWeather", response.message());
                }
            }

            @Override
            public void onFailure(Call<PreWeather> call, Throwable t) {
                // 連線失敗
            }
        });
    }

    private void getWarnData(SetAddress setAddress){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //OilQuery oilQuery = new OilQuery(1);

        Call<Warning> call = myAPIService.postWarning(setAddress);

        // 4. 執行call
        call.enqueue(new Callback<Warning>() {

            @Override
            public void onResponse(Call<Warning> call, Response<Warning> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    setWarnData(response.body());
                    //setOilData(response.body());
                    //String title = response.body().getOil92();
                    Log.d("warning", response.message());
                }
            }

            @Override
            public void onFailure(Call<Warning> call, Throwable t) {
                // 連線失敗
            }
        });
    }

    private void getAqiData(SetAddress setAddress){
        myAPIService = RetrofitManager.getInstance().getAPI();
        //SetAddress location = new SetAddress(lon, lat);
        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //SetAddress setAddress = new SetAddress("121.565427", "25.789574");
        Call<Aqi> call = myAPIService.postAqi(setAddress);

        try {
            // 4. 執行call
            call.enqueue(new Callback<Aqi>() {
                @Override
                public void onResponse(Call<Aqi> call, Response<Aqi> response) {
                    // 連線成功
                    // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
                    Log.d("aqi", "success");
                    if (response != null) {
                        setAqiData(response.body());
                        //String title = response.body().getCountry();
                        //Log.d("title", title);
                    }
                }

                @Override
                public void onFailure(Call<Aqi> call, Throwable t) {
                    // 連線失敗
                    Log.d("aqi", "fail");
                }
            });
        }catch (Exception e){
            Log.d("AqiError", e.toString());
        }
    }

    private void getWeatherData(SetAddress setAddress){
        myAPIService = RetrofitManager.getInstance().getAPI();
        //SetAddress location = new SetAddress(lon, lat);
        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //SetAddress setAddress = new SetAddress("121.565427", "25.789574");
        Call<Weather> call = myAPIService.postWeather(setAddress);

        try {
            // 4. 執行call
            call.enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {
                    // 連線成功
                    // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
                    Log.d("weather", "success");
                    if (response != null) {
                        setWeatherData(response.body());
                        //String title = response.body().getCountry();
                        //Log.d("title", title);
                    }
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {
                    // 連線失敗
                    Log.d("weather", "fail");
                }
            });
        }catch (Exception e){
            Log.d("WeatherError", e.toString());
        }
    }

    private void initRecyclerView(){
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == title.indexOf("油價") || position == title.indexOf("今天白天") || position == title.indexOf("YouBike") || position == title.indexOf("新北市汽車停車") || position == title.indexOf("警報")){
                    return 2;
                }else{
                    return 1;
                }
            }
        });
        masterAdapter = new MasterAdapter(this, title);
        masterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, DetailAQIActivity.class);
                startActivity(intent);
            }
        });
        masterrecview.setLayoutManager(manager);
        masterrecview.setAdapter(masterAdapter);
    }

    class MyAsyncTask extends AsyncTask<Context, Integer, String> {
        Location location;
        LocationMethod locationMethod = new LocationMethod();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //LocationMethod locationMethod = new LocationMethod();
            Log.d("preExcute", "ok");
            location = LocationMethod.getLocation(MainActivity.this);
        }

        @Override
        protected String doInBackground(Context... contexts) {
            return locationMethod.showLocation(location, contexts[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("result", result);
            SetAddress setAddress = new SetAddress(String.valueOf(location.getLongitude()), String.valueOf(location.getLatitude()));
            ParkQuery parkQuery = new ParkQuery(String.valueOf(location.getLongitude()), String.valueOf(location.getLatitude()), "0", "1");
            getUBikeData(setAddress);

            //連接API
            getOilData();
            getAqiData(setAddress);
            getWeatherData(setAddress);
            getParkData(parkQuery);
            getWarnData(setAddress);
            getPreWeatherData(setAddress);

            //loc[0] = result;
            loctext.setText(result);
        }
    }
}
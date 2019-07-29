package com.example.a108404;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public FragmentManager fragmentManager = getSupportFragmentManager();
    ViewPager myViewPager;
    Toolbar toolbar;
    public MasterFraAdapter masterFraAdapter;
    //private LocationMethod locationMethod;
    //public String nowLocation;
    public String[] loc = {"中正市", "天氣", "晴時多雲", "垃圾車", "再30分鐘即將到達"};

    ArrayList<ToolList> usinglist = new ArrayList<ToolList>();
    ArrayList<ToolList> nonList = new ArrayList<ToolList>();
    ArrayList<ToolList> toollist = new ArrayList<ToolList>();
    Gson gson = new GsonBuilder().create();
    TextView loctext;
    RecyclerView masterrecview;
    String[] test = {"中正市", "天氣", "晴時多雲", "垃圾車", "再30分鐘即將到達"};
    MyAPIService myAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragmentManager = getSupportFragmentManager();
        //myViewPager = (ViewPager)findViewById(R.id.myViewPager);
        //自訂義toolbar
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
        loctext = (TextView)findViewById(R.id.localtext);
        masterrecview = (RecyclerView)findViewById(R.id.masterRec);
        SharedPreferences spf = getSharedPreferences("tool", MODE_PRIVATE);
        if(spf.getString("use", "") == ""){
            ToolList toolWeather = new ToolList(true, "天氣");
            ToolList toolAir = new ToolList(true, "空氣品質");
            ToolList toolOil = new ToolList(true, "油價");
            usinglist.add(toolWeather);
            usinglist.add(toolAir);
            usinglist.add(toolOil);

            //toollist.add(usinglist);
            //toollist.add(nonList);


            String jsonStr = gson.toJson(usinglist);
            spf.edit().putString("use", jsonStr).commit();

        }else{
            Type collectionType = new TypeToken<ArrayList<ToolList>>() {}.getType();
            usinglist = gson.fromJson(spf.getString("use", ""), collectionType);
        }

        //取得現在位置
        //locationMethod = LocationMethod.getInstance(this);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(this);
       // myViewPager = (ViewPager)findViewById(R.id.myViewPager);

        MasterAdapter masterAdapter = new MasterAdapter(this, usinglist);
        masterrecview.setLayoutManager(new LinearLayoutManager(this));
        masterrecview.setAdapter(masterAdapter);
//        masterFraAdapter = new MasterFraAdapter(fragmentManager, toollist);
//        myViewPager.setAdapter(masterFraAdapter);

//-------------------連線測試-------------------------------------------------------------------------------------
        // 2. 透過RetrofitManager取得連線基底
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        Call<Oil> call = myAPIService.postOil();

        // 4. 執行call
        call.enqueue(new Callback<Oil>() {
            @Override
            public void onResponse(Call<Oil> call, Response<Oil> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    String title = response.body().getOil92();
                    Log.d("title", title);
                }
            }

            @Override
            public void onFailure(Call<Oil> call, Throwable t) {
                // 連線失敗
            }
        });

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

            loc[0] = result;
            loctext.setText(result);
        }
    }
}


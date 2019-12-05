package com.example.a108404.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a108404.Adapter.MasterAdapter;
import com.example.a108404.DistanceCalculation;
import com.example.a108404.MasterFraAdapter;
import com.example.a108404.Module.Aqi;

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

public class MainActivity extends AppCompatActivity {
    public FragmentManager fragmentManager = getSupportFragmentManager();
    ViewPager myViewPager;
    Toolbar toolbar;
    ArrayList<String> title =new ArrayList<String>();
    ArrayList<String> county = new ArrayList<String>();

    ArrayList<ToolList> usinglist = new ArrayList<ToolList>();
    ArrayList<ToolList> nonList = new ArrayList<ToolList>();
    ArrayList<ToolList> toollist = new ArrayList<ToolList>();
    Gson gson = new GsonBuilder().create();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        myViewPager = (ViewPager)findViewById(R.id.myViewPager);
        initCounty();
        initTitleData();
        MasterFraAdapter masterFraAdapter = new MasterFraAdapter
                (getSupportFragmentManager(), county, title);
        myViewPager.setAdapter(masterFraAdapter);

        initToolBar();
    }

    private void initCounty(){
        SharedPreferences spf = getSharedPreferences("county", MODE_PRIVATE);
        if(spf.getString("county", "") == ""){
            county.add("現在地區");
            if(county != null) {
                String jsonStr = gson.toJson(county);
                spf.edit().putString("county", jsonStr).apply();
            }
        }else{
            Type collectionType = new TypeToken<ArrayList<String>>() {}.getType();
            county = gson.fromJson(spf.getString("county", ""), collectionType);
        }
    }
//
    private void initToolBar(){

        toolbar = (Toolbar)findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Button setBtn = (Button)findViewById(R.id.setBtn);
        Button locBtn = (Button)findViewById(R.id.locBtn);
        setBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        locBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    /***
     * 設定工具選項
     */
    private void initTitleData(){
        SharedPreferences spf = getSharedPreferences("tool", MODE_PRIVATE);
        if(spf.getString("use", "") == ""){
            ToolList toolWeather = new ToolList(true, "天氣");
            ToolList toolOil = new ToolList(true, "油價");
            ToolList toolPark = new ToolList(true, "新北市汽車停車");
            ToolList toolUbike1 = new ToolList(true, "台北市YouBike");
            ToolList toolUbike2 = new ToolList(true, "新北市YouBike");
            ToolList toolUbike3 = new ToolList(true, "台中YouBike");
            ToolList toolUbike4 = new ToolList(true, "台南YouBike");
            ToolList toolUbike5 = new ToolList(true, "彰化YouBike");
            ToolList toolUbike6 = new ToolList(true, "苗栗YouBike");
            ToolList toolUbike7 = new ToolList(true, "新竹YouBike");
            ToolList toolUbike8 = new ToolList(true, "高雄YouBike");
            ToolList toolUbike9 = new ToolList(true, "屏東YouBike");
            ToolList toolUbike10 = new ToolList(true, "桃園YouBike");
            ToolList toolWarning = new ToolList(true, "警報");
            usinglist.add(toolWeather);
            usinglist.add(toolOil);
            usinglist.add(toolPark);
            usinglist.add(toolUbike1);
            usinglist.add(toolUbike2);
            usinglist.add(toolUbike3);
            usinglist.add(toolUbike4);
            usinglist.add(toolUbike5);
            usinglist.add(toolUbike6);
            usinglist.add(toolUbike7);
            usinglist.add(toolUbike8);
            usinglist.add(toolUbike9);
            usinglist.add(toolUbike10);
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

                if(usinglist.get(i).getToolName().equals("台北市YouBike")){
                    title.add("台北市YouBike");
                }
                if(usinglist.get(i).getToolName().equals("新北市YouBike")){
                    title.add("新北市YouBike");
                }
                if(usinglist.get(i).getToolName().equals("桃園YouBike")){
                    title.add("桃園YouBike");
                }
                if(usinglist.get(i).getToolName().equals("台中YouBike")){
                    title.add("台中YouBike");
                }
                if(usinglist.get(i).getToolName().equals("彰化YouBike")){
                    title.add("彰化YouBike");
                }
                if(usinglist.get(i).getToolName().equals("苗栗YouBike")){
                    title.add("苗栗YouBike");
                }
                if(usinglist.get(i).getToolName().equals("屏東YouBike")){
                    title.add("屏東YouBike");
                }
                if(usinglist.get(i).getToolName().equals("高雄YouBike")){
                    title.add("高雄YouBike");
                }
                if(usinglist.get(i).getToolName().equals("台南YouBike")){
                    title.add("台南YouBike");
                }
                if(usinglist.get(i).getToolName().equals("新竹YouBike")){
                    title.add("新竹YouBike");
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

                if(usinglist.get(i).getToolName().equals("台北市YouBike")){
                    title.add("台北市YouBike");
                }
                if(usinglist.get(i).getToolName().equals("新北市YouBike")){
                    title.add("新北市YouBike");
                }
                if(usinglist.get(i).getToolName().equals("桃園YouBike")){
                    title.add("桃園YouBike");
                }
                if(usinglist.get(i).getToolName().equals("台中YouBike")){
                    title.add("台中YouBike");
                }
                if(usinglist.get(i).getToolName().equals("彰化YouBike")){
                    title.add("彰化YouBike");
                }
                if(usinglist.get(i).getToolName().equals("苗栗YouBike")){
                    title.add("苗栗YouBike");
                }
                if(usinglist.get(i).getToolName().equals("屏東YouBike")){
                    title.add("屏東YouBike");
                }
                if(usinglist.get(i).getToolName().equals("高雄YouBike")){
                    title.add("高雄YouBike");
                }
                if(usinglist.get(i).getToolName().equals("台南YouBike")){
                    title.add("台南YouBike");
                }
                if(usinglist.get(i).getToolName().equals("新竹YouBike")){
                    title.add("新竹YouBike");
                }

                if(usinglist.get(i).getToolName().equals("警報")){
                    title.add("警報");
                }
            }
        }
    }
}
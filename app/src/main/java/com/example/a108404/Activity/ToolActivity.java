package com.example.a108404.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.a108404.Adapter.ToolAdapter;
import com.example.a108404.Module.ToolList;
import com.example.a108404.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class ToolActivity extends AppCompatActivity {
    private RecyclerView toolRecyclerView;
    ArrayList<ToolList> nonData;
    ArrayList<ToolList> useData;
    ToolAdapter toolAdapter;
    Toolbar toolbar;
    Gson gson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);
        initToolBar();

        SharedPreferences spf = getSharedPreferences("tool", MODE_PRIVATE);
        Type collectionType = new TypeToken<ArrayList<ToolList>>() {}.getType();
        //usinglist = gson.fromJson(spf.getString("use", ""), collectionType);
        if (spf.getString("non", "") != ""){
            nonData = gson.fromJson(spf.getString("non", ""), collectionType);
        }
        if (spf.getString("use", "") != ""){
            useData = gson.fromJson(spf.getString("use", ""), collectionType);
        }
        toolRecyclerView = (RecyclerView)findViewById(R.id.toolRecyclerView);
        toolAdapter = new ToolAdapter(this, nonData, useData);
        toolRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolRecyclerView.setAdapter(toolAdapter);
    }

    public void initToolBar(){
        setContentView(R.layout.activity_tool);
        toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("tool", MODE_PRIVATE);
                String usestr = gson.toJson(toolAdapter.useData);
                String nonstr = gson.toJson(toolAdapter.nonData);
                preferences.edit().putString("use", usestr).putString("non", nonstr).commit();

                Intent intent = new Intent(ToolActivity.this, SettingActivity.class);
                startActivity(intent);

            }
        });
    }
}

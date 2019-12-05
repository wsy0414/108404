package com.example.a108404.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.a108404.Adapter.SearchAdapter;
import com.example.a108404.Module.ToolList;
import com.example.a108404.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    List<String> countyData = new ArrayList<String>();
    SearchView searchView;
    RecyclerView searchRecycler;
    SearchAdapter searchAdapter;
    Gson gson = new GsonBuilder().create();;
    public ArrayList<String> county = new ArrayList<String>();
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences spf = getSharedPreferences("county", MODE_PRIVATE);
                String jsonStr = gson.toJson(searchAdapter.getNowCounty());
                spf.edit().putString("county", jsonStr).apply();
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        loadCountyData();

        initCountyData();

        searchView = findViewById(R.id.mySearch);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);

        searchRecycler = (RecyclerView)findViewById(R.id.searchRecycler);
        searchAdapter = new SearchAdapter(this, "0", county, county);   //0表示顯示現有
        searchRecycler.setLayoutManager(new LinearLayoutManager(this));
        searchRecycler.setAdapter(searchAdapter);
    }

    private void loadCountyData(){
        countyData = Arrays.asList(getResources().getStringArray(R.array.county));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(s != "") {
            ArrayList<String> searchResult = searchString(s);
            updateRecycler(searchResult);
        }else{
            updateRecycler(county);
        }
        return false;
    }

    private ArrayList<String> searchString(String newString){
        ArrayList<String> mSearchList = new ArrayList<String>();
        for (int i = 0; i < countyData.size(); i++) {
            int index = countyData.get(i).indexOf(newString);

            if (index != -1) {
                mSearchList.add(countyData.get(i));
            }
        }
        return mSearchList;
    }

    private void updateRecycler(ArrayList<String> countyData){
        searchRecycler.setAdapter(new SearchAdapter(this, "1", countyData, county));
    }

    public void initCountyData(){
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
}

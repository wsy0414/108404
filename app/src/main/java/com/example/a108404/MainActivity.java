package com.example.a108404;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public FragmentManager fragmentManager = getSupportFragmentManager();
    ViewPager myViewPager;
    Toolbar toolbar;
    public MasterFraAdapter masterFraAdapter;
    //private LocationMethod locationMethod;
    //public String nowLocation;
    public String[] loc = {"中正市", "天氣", "晴時多雲", "垃圾車", "再30分鐘即將到達"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragmentManager = getSupportFragmentManager();
        myViewPager = (ViewPager)findViewById(R.id.myViewPager);
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

        //locationMethod = LocationMethod.getInstance(this);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(this);
       // myViewPager = (ViewPager)findViewById(R.id.myViewPager);

        masterFraAdapter = new MasterFraAdapter(fragmentManager, loc);
        myViewPager.setAdapter(masterFraAdapter);

    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        locationMethod.removeLocationUpdatesListener();
//    }

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
            if (masterFraAdapter != null) {
                masterFraAdapter.notifyDataSetChanged();
                myViewPager.setAdapter(masterFraAdapter);
            }


        }


    }
}

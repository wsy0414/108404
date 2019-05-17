package com.example.a108404;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ViewPager myViewPager;
    private MasterFraAdapter masterFraAdapter;
    private String[] loc = {"中正市", "天氣", "晴時多雲", "垃圾車", "再30分鐘即將到達"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewPager = (ViewPager)findViewById(R.id.myViewPager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        masterFraAdapter = new MasterFraAdapter(fragmentManager, loc);
        myViewPager.setAdapter(masterFraAdapter);
    }
}

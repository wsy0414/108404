package com.example.a108404.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a108404.Module.Aqi;
import com.example.a108404.R;
import com.google.gson.Gson;

import java.util.Objects;

public class DetailAQIActivity extends AppCompatActivity {
    TextView aqiText;
    TextView pmText;
    TextView aqiStatusText;
    TextView pmStatusText;
    TextView pm10Text;
    TextView coText;
    TextView soText;
    TextView oText;
    Toolbar toolbar;
    ImageView aqiImg;
    ImageView pmImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_aqi);

        initToolBar();

        aqiText = (TextView)findViewById(R.id.aqiText);
        pmText = (TextView)findViewById(R.id.pmText);
        aqiStatusText = (TextView)findViewById(R.id.aqiStatus);
        pmStatusText = (TextView)findViewById(R.id.pmStatusText);
        pm10Text = (TextView)findViewById(R.id.PM_10_text);
        coText = (TextView)findViewById(R.id.coText);
        soText = (TextView)findViewById(R.id.soText);
        oText = (TextView)findViewById(R.id.oText);
        aqiImg = (ImageView)findViewById(R.id.aqiImg);
        pmImg = (ImageView)findViewById(R.id.pmImg);

        Intent intent = getIntent();
        String jsonStr = intent.getStringExtra("data");
        Aqi aqidata = new Gson().fromJson(jsonStr, Aqi.class);
        if(aqidata != null) {
            int aqi = Integer.valueOf(aqidata.getAQI());
            int pm = Integer.valueOf(aqidata.getPM25());

            aqiText.setText(aqidata.getAQI());
            pmText.setText(aqidata.getPM25());
            aqiStatusText.setText(aqidata.getStatus());
            pmStatusText.setText(aqidata.getPmStatus());
            pm10Text.setText(aqidata.getPM10());
            coText.setText(aqidata.getCo());
            soText.setText(aqidata.getSo2());
            oText.setText(aqidata.getO3());

            if(aqi <= 50){
                aqiImg.setImageResource(R.drawable.greenline);
            }else if(aqi <= 100){
                aqiImg.setImageResource(R.drawable.yellowline);
            }else{
                aqiImg.setImageResource(R.drawable.redline);
            }
            if(pm <= 50){
                pmImg.setImageResource(R.drawable.greenline);
            }else if(pm <= 100){
                pmImg.setImageResource(R.drawable.yellowline);
            }else{
                pmImg.setImageResource(R.drawable.redline);
            }
        }
    }

    public void initToolBar(){
        setContentView(R.layout.activity_detail_aqi);
        toolbar = (Toolbar)findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailAQIActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}

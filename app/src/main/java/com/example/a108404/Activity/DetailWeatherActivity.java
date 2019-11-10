package com.example.a108404.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.a108404.Module.Weather;
import com.example.a108404.R;
import com.google.gson.Gson;

import java.util.Objects;

public class DetailWeatherActivity extends AppCompatActivity {
    Toolbar toolbar;
    Gson gson;
    TextView tempNowText;
    TextView tempText;
    TextView tempMaxText;
    TextView tempMinText;
    TextView rainText;
    TextView windText;
    TextView humidityText;
    TextView lightText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather);

        initToolBar();

        tempNowText = (TextView)findViewById(R.id.tempText);
        tempText = (TextView)findViewById(R.id.tempText);
        tempMaxText = (TextView)findViewById(R.id.tempMaxText);
        tempMinText = (TextView)findViewById(R.id.tempMinText);
        rainText = (TextView)findViewById(R.id.rainText);
        windText = (TextView)findViewById(R.id.windText);
        humidityText = (TextView)findViewById(R.id.humidityText);
        lightText = (TextView)findViewById(R.id.lightText);

        Intent intent = getIntent();
        String jsonStr = intent.getStringExtra("data");
        Weather weatherdata = new Gson().fromJson(jsonStr, Weather.class);

        tempNowText.setText(weatherdata.getTempNow());
        //tempNowText.setText(weatherdata.getTempNow());
        tempMaxText.setText(weatherdata.getTempMax());
        tempMinText.setText(weatherdata.getTempMin());
        rainText.setText(weatherdata.getRainFall());
        windText.setText(weatherdata.getWindSpeed());
        humidityText.setText(weatherdata.getHumidity());
        lightText.setText(weatherdata.getUviH());
    }

    public void initToolBar(){
        setContentView(R.layout.activity_detail_weather);
        toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailWeatherActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}

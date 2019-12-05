package com.example.a108404.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.a108404.Module.Park;
import com.example.a108404.Module.ParkNTPC;
import com.example.a108404.Module.ParkQuery;
import com.example.a108404.MyAPIService;
import com.example.a108404.R;
import com.example.a108404.Service.LocationMethod;
import com.example.a108404.Service.RetrofitManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.PendingIntent.getActivity;

public class ParkMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MyAPIService myAPIService;
    ArrayList<Park> parkData = new ArrayList<Park>();
    ProgressBar progressBar;
    Toolbar toolbar;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_maps);
        progressBar = findViewById(R.id.pg1);
        initToolBar();
        LocationMethod locationMethod = new LocationMethod();
        location = locationMethod.getLocation(ParkMapsActivity.this);
        ParkQuery parkQuery = new ParkQuery(121.502569, 24.986205, "0", "0");
        getParkData(parkQuery);
    }

    public void initToolBar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ParkMapsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void getParkData(ParkQuery parkQuery){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的postAllPark連線
        Call<ParkNTPC> call = myAPIService.postAllPark(parkQuery);
        progressBar.setVisibility(View.VISIBLE);

        // 4. 執行call
        call.enqueue(new Callback<ParkNTPC>() {

            @Override
            public void onResponse(Call<ParkNTPC> call, Response<ParkNTPC> response) {
                // 連線成功
                // 回傳的資料已轉成物件，可直接用get方法取得特定欄位

                if (response != null) {

                    parkData = response.body().getPark();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(ParkMapsActivity.this);

                    //Log.d("park", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ParkNTPC> call, Throwable t) {
                // 連線失敗
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        for(int i = 0; i < parkData.size(); i++) {
            LatLng sydney = new LatLng(Double.valueOf(parkData.get(i).getLatitude()), Double.valueOf(parkData.get(i).getLongitude()));
            mMap.addMarker(new MarkerOptions().position(sydney).title("停車位").snippet("狀態：" + parkData.get(i).getParkStatusZh() + "付費：" + parkData.get(i).getPayCash()).icon(BitmapDescriptorFactory.fromResource(R.drawable.park_marker)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
        LatLng marker = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 14));
        progressBar.setVisibility(View.GONE);
    }

    public static Bitmap loadBitmapFromView(View v) {

        if (v.getMeasuredHeight() <= 0) {
            v.measure(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            v.draw(c);
            return b;
        }
        return null;
    }
}

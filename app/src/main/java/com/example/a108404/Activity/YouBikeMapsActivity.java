package com.example.a108404.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.a108404.Adapter.YouBikeMapAdapter;
import com.example.a108404.Module.AllYouBikeQuery;
import com.example.a108404.Module.Retval;
import com.example.a108404.Module.Youbike;
import com.example.a108404.MyAPIService;
import com.example.a108404.R;
import com.example.a108404.Service.LocationMethod;
import com.example.a108404.Service.RetrofitManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YouBikeMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Youbike> ubikeData = new ArrayList<>();
    RecyclerView mRecyclerView;
    YouBikeMapAdapter youBikeMapAdapter;
    Toolbar toolbar;
    String citName;
    MyAPIService myAPIService;
    ProgressBar ppgg;
    Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youbike_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        ppgg = findViewById(R.id.progressBar);
        initToolBar();

        LocationMethod locationMethod = new LocationMethod();
        location = locationMethod.getLocation(YouBikeMapsActivity.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //ubikeData = (ArrayList<Youbike>) bundle.getSerializable("data");
        citName = intent.getStringExtra("city");
//        mRecyclerView = (RecyclerView)findViewById(R.id.recycle);
//        youBikeMapAdapter = new YouBikeMapAdapter(YouBikeMapsActivity.this, ubikeData);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(youBikeMapAdapter);
        AllYouBikeQuery youBikeQuery = null;// = new AllYouBikeQuery("1", "Taipei");
        if(citName.equals("台北市YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "Taipei");
        }else if(citName.equals("新北市YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "NewTaipei");
        }else if(citName.equals("台中YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "Taichung");
        }else if(citName.equals("台南YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "Tainan");
        }else if(citName.equals("新竹YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "Hsinchu");
        }else if(citName.equals("彰化YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "ChanghuaCounty");
        }else if(citName.equals("屏東YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "PingtungCounty");
        }else if(citName.equals("苗栗YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "MiaoliCounty");
        }else if(citName.equals("桃園YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "Taoyuan");
        }else if(citName.equals("高雄YouBike")){
            youBikeQuery = new AllYouBikeQuery("1", "Kaohsiungu");
        }
        getAllBike(youBikeQuery);
    }

    public void initToolBar(){
        //setContentView(R.layout.activity_youbike_maps);
        toolbar = (Toolbar)findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(YouBikeMapsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void getAllBike(AllYouBikeQuery youBikeQuery){
        myAPIService = RetrofitManager.getInstance().getAPI();
        //SetAddress location = new SetAddress(lon, lat);
        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //SetAddress setAddress = new SetAddress("121.565427", "25.789574");
        //AllYouBikeQuery youBikeQuery = new AllYouBikeQuery("1", "Taipei");
        ppgg.setVisibility(View.VISIBLE);
        Call<Retval> call = myAPIService.postAllBike(youBikeQuery);

        try {
            // 4. 執行call
            call.enqueue(new Callback<Retval>() {
                @Override
                public void onResponse(Call<Retval> call, Response<Retval> response) {
                    // 連線成功
                    // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
                    Log.d("weather", "success");
                    if (response != null) {
                        ubikeData = response.body().getRetVal();
                        //String title = response.body().getCountry();
                        //Log.d("title", title);
                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                        mapFragment.getMapAsync(YouBikeMapsActivity.this);
                        ppgg.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Retval> call, Throwable t) {
                    // 連線失敗
                    Log.d("weather", "fail");
                }
            });
        }catch (Exception e){
            Log.d("WeatherError", e.toString());
        }
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


        if(ubikeData != null){
            for(int i = 0; i < ubikeData.size(); i++){
                LatLng marker1 = new LatLng(Double.valueOf(ubikeData.get(i).getLat()), Double.valueOf(ubikeData.get(i).getLng()));
                mMap.addMarker(new MarkerOptions().position(marker1).title(ubikeData.get(i).getStationName()).snippet("可借：" + ubikeData.get(i).getCanBorrow() + "空位：" + ubikeData.get(i).getBemp()).icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_marker)));

            }
        }
        // Add a marker in Sydney and move the camera
//        LatLng marker1 = new LatLng(Double.valueOf(ubikeData.get(0).getLat()), Double.valueOf(ubikeData.get(0).getLng()));
//        mMap.addMarker(new MarkerOptions().position(marker1).title(ubikeData.get(0).getStationName()).snippet("可借：" + ubikeData.get(0).getCanBorrow() + "空位：" + ubikeData.get(0).getBemp()));
        LatLng marker = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 14));
    }
}

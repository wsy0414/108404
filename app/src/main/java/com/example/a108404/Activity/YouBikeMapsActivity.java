package com.example.a108404.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.a108404.Adapter.YouBikeMapAdapter;
import com.example.a108404.Module.Aqi;
import com.example.a108404.Module.Youbike;
import com.example.a108404.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

public class YouBikeMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Youbike> ubikeData = new ArrayList<>();
    RecyclerView mRecyclerView;
    YouBikeMapAdapter youBikeMapAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youbike_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        initToolBar();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ubikeData = (ArrayList<Youbike>) bundle.getSerializable("data");

        mRecyclerView = (RecyclerView)findViewById(R.id.recycle);
        youBikeMapAdapter = new YouBikeMapAdapter(YouBikeMapsActivity.this, ubikeData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(youBikeMapAdapter);
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
        LatLng marker1 = new LatLng(Double.valueOf(ubikeData.get(0).getLat()), Double.valueOf(ubikeData.get(0).getLng()));
        mMap.addMarker(new MarkerOptions().position(marker1).title(ubikeData.get(0).getStationName()).snippet("可借：" + ubikeData.get(0).getCanBorrow() + "空位：" + ubikeData.get(0).getBemp()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker1, 14));
    }
}

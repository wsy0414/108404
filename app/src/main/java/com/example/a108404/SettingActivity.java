package com.example.a108404;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.model.TileOverlayOptions;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;

import java.util.ArrayList;
import java.util.Objects;

public class SettingActivity extends AppCompatActivity implements OnStartDragListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    SetAdapter setAdapter;
    //ItemTouchHelper mItemTouchHelper;
    public ItemTouchHelperExtension mItemTouchHelper;
    public ItemTouchHelperExtension.Callback mCallback;
    //IItemTouchHelperAdapter adpater;
    ArrayList<ToolList> usinglist = new ArrayList<ToolList>();
    ArrayList<ToolList> nonList = new ArrayList<ToolList>();
    ArrayList<ArrayList> toollist = new ArrayList<ArrayList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        initToolBar();

        initView();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        Log.d("startDrag", "ok");
        mItemTouchHelper.startDrag(viewHolder);
        Log.d("startDrag", "ok");
    }

    @Override
    public void onStartSwip(RecyclerView.ViewHolder viewHolder){
        Log.d("startSwip", "ok");
        mItemTouchHelper.startSwipe(viewHolder);
    }

    //資料初始化
    public void initData(){
        ToolList toolWeather = new ToolList(true, "天氣");
        ToolList toolAir = new ToolList(false, "空氣品質");
        ToolList toolOil = new ToolList(true, "油價");
        usinglist.add(toolWeather);
        nonList.add(toolAir);
        usinglist.add(toolOil);

        toollist.add(usinglist);
        toollist.add(nonList);
    }

    //取消toolbar並自定義
    public void initToolBar(){
        setContentView(R.layout.activity_setting);
        toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.lee);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //初始化畫面RecyclerView
    public void initView(){
        recyclerView = (RecyclerView)findViewById(R.id.setrecycler);
        setAdapter = new SetAdapter(this, this, toollist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("adapter", "ok");
        recyclerView.setAdapter(setAdapter);
        // mItemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(setAdapter));
        //mItemTouchHelper.attachToRecyclerView(recyclerView);
        mCallback = new MyItemTouchHelperCallback(setAdapter);
        mItemTouchHelper = new ItemTouchHelperExtension(mCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
}


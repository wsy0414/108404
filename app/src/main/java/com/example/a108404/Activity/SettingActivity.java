package com.example.a108404.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a108404.Adapter.SetAdapter;
import com.example.a108404.Service.MyItemTouchHelperCallback;
import com.example.a108404.OnStartDragListener;
import com.example.a108404.R;
import com.example.a108404.Module.ToolList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class SettingActivity extends AppCompatActivity implements ondeleteBtnClickListener {//implements OnStartDragListener {
    Toolbar toolbar;
    SwipeRecyclerView recyclerView;
    SetAdapter setAdapter;
    //ItemTouchHelper mItemTouchHelper;
    public ItemTouchHelperExtension mItemTouchHelper;
    public ItemTouchHelperExtension.Callback mCallback;
    //IItemTouchHelperAdapter adpater;
    ArrayList<ToolList> usinglist = new ArrayList<ToolList>();
    ArrayList<ToolList> nonList = new ArrayList<ToolList>();
    ArrayList<ArrayList> toollist = new ArrayList<ArrayList>();
    Gson gson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        initToolBar();

        initView();
    }

//    //資料初始化
    public void initData(){
        SharedPreferences spf = getSharedPreferences("tool", MODE_PRIVATE);
        Type collectionType = new TypeToken<ArrayList<ToolList>>() {}.getType();
        usinglist = gson.fromJson(spf.getString("use", ""), collectionType);
        if (spf.getString("non", "") != ""){
            nonList = gson.fromJson(spf.getString("non", ""), collectionType);
        }
        toollist.add(usinglist);
        toollist.add(nonList);
    }
//
//    //取消toolbar並自定義
    public void initToolBar(){
        setContentView(R.layout.activity_setting);
        toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("tool", MODE_PRIVATE);
                String usestr = gson.toJson(setAdapter.usingData);
                String nonstr = gson.toJson(setAdapter.nonData);
                preferences.edit().putString("use", usestr).putString("non", nonstr).commit();

                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
//
//    //初始化畫面RecyclerView
    public void initView(){

        recyclerView = (SwipeRecyclerView) findViewById(R.id.setrecycler);
        setAdapter = new SetAdapter(this, toollist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("setAdapter", "ok");
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setOnItemMenuClickListener(mMenuItemClickListener);
        setAdapter.setondeleteBrnClickListener(new ondeleteBtnClickListener() {
            @Override
            public void deleteBtnClick(int position) {
                recyclerView.smoothOpenRightMenu(position);
            }
        });
        recyclerView.setAdapter(setAdapter);
        // mItemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(setAdapter));
        //mItemTouchHelper.attachToRecyclerView(recyclerView);
        //mCallback = new MyItemTouchHelperCallback(setAdapter);
        recyclerView.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(this, R.color.cardview_light_background)));
        recyclerView.setOnItemMoveListener(getItemMoveListener());

        recyclerView.setLongPressDragEnabled(true);
        //mItemTouchHelper = new ItemTouchHelperExtension(mCallback);
        //mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    protected OnItemMoveListener getItemMoveListener() {
        // 监听拖拽和侧滑删除，更新UI和数据源。
        return new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                // 不同的ViewType不能拖拽换位置。
                if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) return false;
//
//                // 真实的Position：通过ViewHolder拿到的position都需要减掉HeadView的数量。
                int fromPosition = srcHolder.getAdapterPosition();
                int toPosition = targetHolder.getAdapterPosition();
//
                Collections.swap(setAdapter.usingData, fromPosition, toPosition);
                setAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;// 返回true表示处理了并可以换位置，返回false表示你没有处理并不能换位置。
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {

            }

        };
    }

    /**
     * 建立item的菜單
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;


            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(SettingActivity.this).setBackground(R.color.colorAccent)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
            }
        }
    };

    private OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();
            //int menuPosition = menuBridge.getPosition();
                //Toast.makeText(SettingActivity.this, "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        //.show();
            SharedPreferences spf = getSharedPreferences("tool", MODE_PRIVATE);
                //ArrayList list = new ArrayList();
            nonList.add(setAdapter.usingData.get(position));

            String jsonStr = gson.toJson(nonList);

            spf.edit().putString("non", jsonStr).commit();

            setAdapter.removeItem(position);
            //usinglist.remove(position);
            String useStr = gson.toJson(setAdapter.usingData);
            spf.edit().putString("use", useStr).commit();


        }
    };

    @Override
    public void deleteBtnClick(int position) {
        recyclerView.smoothOpenRightMenu(position);
    }
}


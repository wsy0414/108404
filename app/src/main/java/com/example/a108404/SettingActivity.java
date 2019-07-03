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

import java.util.Objects;

public class SettingActivity extends AppCompatActivity implements OnStartDragListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    SetAdapter setAdapter;
    ItemTouchHelper mItemTouchHelper;
    //IItemTouchHelperAdapter adpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        recyclerView = (RecyclerView)findViewById(R.id.setrecycler);
        setAdapter = new SetAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("adapter", "ok");
        recyclerView.setAdapter(setAdapter);
        mItemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(setAdapter));
        mItemTouchHelper.attachToRecyclerView(recyclerView);

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
//                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
//                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
//                //  上下拖移callback
//                final int fromPos = viewHolder.getAdapterPosition();
//                final int toPos = viewHolder1.getAdapterPosition(y);
//                // move item in `fromPos` to `toPos` in adapter.
//                setAdapter.notifyItemMoved(fromPos, toPos);
//                return true;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                // 左右滑動callback
//
//            }
//        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        Log.d("startDrag", "ok");
        mItemTouchHelper.startDrag(viewHolder);
        Log.d("startDrag", "ok");
    }
}


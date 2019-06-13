package com.example.a108404;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Objects;

public class EditActivity extends AppCompatActivity {
    private RecyclerView editRecycler;
    private String[] titleData = {"天氣", "垃圾車", "油價", "警報"};
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editRecycler = (RecyclerView)findViewById(R.id.editRecycler);
        toolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.setting);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        EditAdapter editAdapter = new EditAdapter(titleData, this);
        editRecycler.setLayoutManager(new LinearLayoutManager(this));
        editRecycler.setAdapter(editAdapter);
    }
}

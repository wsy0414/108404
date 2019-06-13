package com.example.a108404;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

class EditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String[] titleData;
    public EditAdapter(String[] Data, Context context){
        this.mContext = context;
        this.titleData = Data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.edititem, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(titleData[i] != null) {
            ((ViewHolder) viewHolder).tv1.setText(titleData[i]);
        }
    }

    @Override
    public int getItemCount() {
        return titleData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        Switch sw1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.textView2);
            sw1 = (Switch)itemView.findViewById(R.id.switch1);

        }
    }
}

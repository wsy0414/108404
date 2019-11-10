package com.example.a108404.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a108404.Module.Youbike;
import com.example.a108404.R;

import java.util.ArrayList;

public class YouBikeMapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Youbike> ubikeData;
    Context mContext;

    public YouBikeMapAdapter(Context context, ArrayList<Youbike> ubikeData){
        this.mContext = context;
        this.ubikeData = ubikeData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.youbike_map_card, viewGroup, false);
        ubikeViewHolder vh = new ubikeViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ubikeViewHolder){
            if (i <= ubikeData.size()) {
                if(ubikeData.get(i) != null) {
                    ((ubikeViewHolder) viewHolder).staNameText.setText(ubikeData.get(i).getStationName());
                    ((ubikeViewHolder) viewHolder).borText.setText(ubikeData.get(i).getCanBorrow());
                    ((ubikeViewHolder) viewHolder).bempText.setText(ubikeData.get(i).getBemp());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return ubikeData.size();
    }

    public class ubikeViewHolder extends RecyclerView.ViewHolder {
        public TextView staNameText;
        public TextView borText;
        public TextView bempText;
        //public Switch sw1;
        public ubikeViewHolder(@NonNull final View itemView) {
            super(itemView);
            staNameText = (TextView)itemView.findViewById(R.id.staNameText);
            borText = (TextView)itemView.findViewById(R.id.borText);
            bempText = (TextView)itemView.findViewById(R.id.bempText);
        }
    }
}

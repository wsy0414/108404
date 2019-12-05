package com.example.a108404.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a108404.Activity.SearchActivity;
import com.example.a108404.R;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String type;
    ArrayList<String> county = new ArrayList<String>();
    ArrayList<String> nowCounty = new ArrayList<String>();

    public SearchAdapter(Context context, String type, ArrayList<String> county, ArrayList<String> nowCounty){
        this.mContext = context;
        //this.mDragListener = mDragListener;
        this.type = type;
        this.county = county;
        this.nowCounty = nowCounty;
    }

    public ArrayList<String> getNowCounty(){return nowCounty;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(type.equals("0")) {
            if(i == 1) {
                View v = LayoutInflater.from(mContext)
                        .inflate(R.layout.county_item, viewGroup, false);
                ViewHolder vh = new ViewHolder(v);
                return vh;
            }else{
                View v = LayoutInflater.from(mContext)
                        .inflate(R.layout.now_county_item, viewGroup, false);
                nowViewHolder vh = new nowViewHolder(v);
                return vh;
            }
        }else{
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.add_coumty_item, viewGroup, false);
            aViewHolder vh = new aViewHolder(v);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ViewHolder) {
            if(county != null){
                ((ViewHolder) viewHolder).countyText.setText(county.get(i));
            }
        }

        if(viewHolder instanceof aViewHolder){
            if(county != null){
                ((aViewHolder) viewHolder).countyText.setText(county.get(i));
            }
        }

        if(viewHolder instanceof nowViewHolder){
            if(county != null){
                ((nowViewHolder) viewHolder).nowCountyText.setText(county.get(i));
            }
        }
    }

    @Override
    public int getItemCount() {
        return county.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else{
            return 1;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView countyText;
        public Button cancelBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countyText = itemView.findViewById(R.id.countyText);
            cancelBtn = itemView.findViewById(R.id.cancelBtn);

            cancelBtn.setOnClickListener(new View.OnClickListener(
            ) {
                @Override
                public void onClick(View v) {
                    county.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }

    public class aViewHolder extends RecyclerView.ViewHolder {
        public TextView countyText;
        public Button addBtn;
        public aViewHolder(@NonNull View itemView) {
            super(itemView);
            countyText = itemView.findViewById(R.id.countyText);
            addBtn = itemView.findViewById(R.id.addBtn);

            addBtn.setOnClickListener(new View.OnClickListener(
            ) {
                @Override
                public void onClick(View v) {
                    setType("0");
                    nowCounty.add(county.get(getAdapterPosition()));
                    county = nowCounty;
                    notifyDataSetChanged();
                }
            });
        }
    }

    public class nowViewHolder extends RecyclerView.ViewHolder {
        public TextView nowCountyText;
        public nowViewHolder(@NonNull View itemView) {
            super(itemView);
            nowCountyText = itemView.findViewById(R.id.nowCountyText);
        }
    }

    public void setType(String type){
        this.type = type;
    }
}

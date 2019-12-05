package com.example.a108404.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a108404.Activity.ToolActivity;
import com.example.a108404.Activity.ondeleteBtnClickListener;
import com.example.a108404.R;
import com.example.a108404.Module.ToolList;

import java.util.ArrayList;

public class SetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {//implements IItemTouchHelperAdapter {
    //String[] setData = {"天氣", "垃圾車", "病毒", "油價"};
    Context mContext;
    //OnStartDragListener mDragListener;
    public ArrayList<ToolList> usingData;
    public ArrayList<ToolList> nonData;
    public ondeleteBtnClickListener mListener;

    public SetAdapter(Context context,  ArrayList<ArrayList> toolData){
        this.mContext = context;
        //this.mDragListener = mDragListener;
        this.usingData = toolData.get(0);
        this.nonData = toolData.get(1);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 0) {
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.edititem, viewGroup, false);
            addViewHolder vh = new addViewHolder(v);
            return vh;
        }else{
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.item, viewGroup, false);
            textViewHolder vh = new textViewHolder(v);
            return vh;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {
        if(viewHolder instanceof addViewHolder) {
            if(i <= usingData.size()) {
                String tName = usingData.get(i).getToolName();
                ((addViewHolder) viewHolder).tv1.setText(usingData.get(i).getToolName());
                if(tName.equals("油價")){
                    ((addViewHolder) viewHolder).toolImg.setImageResource(R.drawable.oil_price);
                }else if(tName.equals("天氣")){
                    ((addViewHolder) viewHolder).toolImg.setImageResource(R.drawable.weather);
                }else if(tName.equals("YouBike")){
                    ((addViewHolder) viewHolder).toolImg.setImageResource(R.drawable.ubike);
                }else if(tName.equals("警報")){
                    ((addViewHolder) viewHolder).toolImg.setImageResource(R.drawable.warn);
                }
                //((addViewHolder) viewHolder).sw1.setChecked(usingData.get(i - 1).getUsing());
            }
        }

    }

    @Override
    public int getItemCount() {
        return usingData.size() + 1;
    }

    public void setondeleteBrnClickListener(ondeleteBtnClickListener listener){
        this.mListener = listener;
    }

    public void removeItem(int position){
        usingData.remove(position);

        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position){
        if (position == usingData.size()){
           return 1;
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public class textViewHolder extends RecyclerView.ViewHolder {
        public Button addBtn;
        public textViewHolder(@NonNull View itemView) {
            super(itemView);
            addBtn = (Button)itemView.findViewById(R.id.addBtn);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ToolActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class addViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1;
        public Button moveBtn;
        public Button deleteBtn;
        public View mcontentView;
        public ImageView toolImg;
        //public Switch sw1;
        public addViewHolder(@NonNull final View itemView) {
            super(itemView);
            mcontentView = itemView.findViewById(R.id.edititem);
            tv1 = (TextView)itemView.findViewById(R.id.textView2);
            //moveBtn = (Button)itemView.findViewById(R.id.moveBtn);
            //sw1 = (Switch)itemView.findViewById(R.id.switch1);
            deleteBtn = (Button)itemView.findViewById(R.id.addBtn);
            toolImg = (ImageView)itemView.findViewById(R.id.toolImg);

            //moveBtn.setOnTouchListener();

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 移除項目，getAdapterPosition為點擊的項目位置
                    //removeItem(getAdapterPos
                        mListener.deleteBtnClick(getAdapterPosition());

                }
            });
        }
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}


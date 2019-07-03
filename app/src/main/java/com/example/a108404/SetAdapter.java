package com.example.a108404;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

class SetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IItemTouchHelperAdapter {
    String[] setData = {"天氣", "垃圾車", "病毒", "油價"};
    Context mContext;
    OnStartDragListener mDragListener;

    public SetAdapter(Context context, OnStartDragListener mDragListener){
        this.mContext = context;
        this.mDragListener = mDragListener;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.edititem, viewGroup, false);
            addViewHolder vh = new addViewHolder(v);
            return vh;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {

        ((addViewHolder) viewHolder).tv1.setText(setData[i]);
        ((addViewHolder)viewHolder).moveBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mDragListener != null) {
                        Log.d("touch", "ok");
                        mDragListener.onStartDrag(viewHolder);
                        Log.d("touch", "ok");
                    }
                }
                return false;
            }
        });
        ((addViewHolder) viewHolder).dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("delete", "ok");
                onItemDismiss(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return setData.length;
    }

//    public void moveItem(int fromPos, int toPos) {
//        Collections.swap(Arrays.asList(setData), fromPos, toPos);
//        notifyItemMoved(fromPos, toPos);
//    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(Arrays.asList(setData), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        //setData.remove(position);
        notifyItemRemoved(position);
    }

//    public void remove(int position) {
//        //mTitle.remove(position);
//        notifyItemRemoved(position);
//    }
//
//    public void add(String text, int position) {
//        //mTitle.add(position, text);
//        notifyItemInserted(position);
//    }

//    @Override
//    public int getItemViewType(int position){
//        if (position != setData.length){
//           return 0;
//        }else{
//            return 1;
//        }
//    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public class textViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1;
        public textViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.textView);
        }
    }

    public class addViewHolder extends RecyclerView.ViewHolder implements SlideSwapAction {
        public TextView tv1;
        public Button btn1;
        public Button moveBtn;
        public Button dltBtn;
        private Boolean btnState = true;
        public addViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.textView2);
            btn1 = (Button)itemView.findViewById(R.id.editBtn);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 按下Button要做的事
                    if(btnState){
                        btnState = false;
                        btn1.setActivated(btnState);
                    }else{
                        btnState = true;
                        btn1.setActivated(btnState);
                    }
                }
            });

            moveBtn = (Button)itemView.findViewById(R.id.moveBtn);
            dltBtn = (Button)itemView.findViewById(R.id.dltBtn);


        }

        @Override
        public float getActionWidth() {
            return dip2px(mContext, 240);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}


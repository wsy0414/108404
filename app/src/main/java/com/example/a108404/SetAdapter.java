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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class SetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IItemTouchHelperAdapter {
    //String[] setData = {"天氣", "垃圾車", "病毒", "油價"};
    Context mContext;
    OnStartDragListener mDragListener;
    ArrayList<ToolList> usingData;
    ArrayList<ToolList> nonData;


    public SetAdapter(Context context, OnStartDragListener mDragListener, ArrayList<ArrayList> toolData){
        this.mContext = context;
        this.mDragListener = mDragListener;
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
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof addViewHolder) {
            if(i <= usingData.size()) {
                ((addViewHolder) viewHolder).tv1.setText(usingData.get(i - 1).getToolName());
            }else{
                Log.d("onBindPosition", String.valueOf(i));
                ((addViewHolder) viewHolder).tv1.setText((CharSequence) nonData.get(i - 2 - usingData.size()).getToolName());
            }
            try {
                ((addViewHolder) viewHolder).moveBtn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            if (mDragListener != null) {
                                Log.d("touch", "ok");
                                mDragListener.onStartDrag(viewHolder);
                                Log.d("touch", "ok");
                            }
                        }
                        return false;
                    }
                });
            }catch (Exception e){
                Log.d("aa", "aa");
            }
            ((addViewHolder) viewHolder).dltBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("delete", "ok");
                    onItemDismiss(viewHolder.getAdapterPosition());
                }
            });
        }else{
            ((textViewHolder) viewHolder).tv1.setText("工具");
        }

    }

    @Override
    public int getItemCount() {
        return usingData.size() + nonData.size()+2;
    }

//    public void moveItem(int fromPos, int toPos) {
//        Collections.swap(Arrays.asList(setData), fromPos, toPos);
//        notifyItemMoved(fromPos, toPos);
//    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //Collections.swap(usingData, fromPosition, toPosition);

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        Log.d("position", String.valueOf(position));
        nonData.add(usingData.get(position));
        usingData.remove(position);

        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position){
        if (position == 0 || position == usingData.size() + 1){
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
        public View mcontentView;
        private Boolean btnState = true;
        public addViewHolder(@NonNull final View itemView) {
            super(itemView);
            mcontentView = itemView.findViewById(R.id.edititem);
            tv1 = (TextView)itemView.findViewById(R.id.textView2);
            btn1 = (Button)itemView.findViewById(R.id.editBtn);


            moveBtn = (Button)itemView.findViewById(R.id.moveBtn);
            dltBtn = (Button)itemView.findViewById(R.id.dltBtn);

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


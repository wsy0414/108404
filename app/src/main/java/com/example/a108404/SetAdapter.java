package com.example.a108404;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

class SetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] setData = {"天氣", "垃圾車", "病毒", "油價"};
    Context mContext;

    public SetAdapter(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 1){
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.buttonitem, viewGroup, false);
            addViewHolder vh = new addViewHolder(v);
            return vh;
        }else {
            View v = LayoutInflater.from(mContext)
                  .inflate(R.layout.item, viewGroup, false);
            textViewHolder vh = new textViewHolder(v);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof textViewHolder) {
            ((textViewHolder) viewHolder).tv1.setText(setData[i]);
        }
    }

    @Override
    public int getItemCount() {
        return setData.length + 1;
    }

    @Override
    public int getItemViewType(int position){
        if (position != setData.length){
           return 0;
        }else{
            return 1;
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

    public class addViewHolder extends RecyclerView.ViewHolder {
        public Button btn1;
        public addViewHolder(@NonNull View itemView) {
            super(itemView);
            btn1 = (Button)itemView.findViewById(R.id.addbutton);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 按下Button要做的事
                    Intent intent = new Intent(view.getContext(), EditActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

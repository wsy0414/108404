package com.example.a108404;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

class MasterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    String[] data;
    public MasterAdapter(Context context, String[] data){
        this.mContext = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        if (i == 0){
//            View v = LayoutInflater.from(mContext)
//                    .inflate(R.layout.carditem, viewGroup, false);
//            imgViewHolder vh = new imgViewHolder(v);
//            return vh;
//        }else{
//            View v = LayoutInflater.from(mContext)
//                    .inflate(R.layout.item, viewGroup, false);
//            masterViewHolder vh = new masterViewHolder(v);
//            return vh;
//        }
        if (i == 1){
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.carditem, viewGroup, false);
            imgViewHolder vh = new imgViewHolder(v);
            return vh;
        }else{
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.carditem2, viewGroup, false);
            textViewHolder vh = new textViewHolder(v);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        if (viewHolder instanceof textViewHolder){
//            ((textViewHolder) viewHolder).tv1.setText(data[i]);
//        }
        if (i == 1){
            ((imgViewHolder) viewHolder).iv1.setImageResource(R.drawable.man);
        }else if (i == 0){
            ((textViewHolder) viewHolder).tv1.setText(data[1]);
            ((textViewHolder) viewHolder).tv2.setText(data[2]);
        }else{
            ((textViewHolder) viewHolder).tv1.setText(data[3]);
            ((textViewHolder) viewHolder).tv2.setText(data[4]);
        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position){
        if (position == 0){
            return 0;
        }else if (position == 1){
            return 1;
        }else{
            return 2;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class masterViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1;
        public masterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.textView);
        }
    }

    public class imgViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv1;
        public imgViewHolder(@NonNull View itemView) {
            super(itemView);
            iv1 = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }

    public class textViewHolder extends RecyclerView.ViewHolder{
        public TextView tv1;
        public TextView tv2;
        public textViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.topicTextView);
            tv2 = (TextView)itemView.findViewById(R.id.aaTextView);
        }
    }
}

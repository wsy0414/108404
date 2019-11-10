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

import com.example.a108404.Module.ToolList;
import com.example.a108404.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ToolAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    public ArrayList<ToolList> nonData;
    public ArrayList<ToolList> useData;
    public ToolAdapter(Context context, ArrayList<ToolList> toolData ,ArrayList<ToolList> useData){
        this.mContext = context;
        //this.mDragListener = mDragListener;
        //this.usingData = toolData.get(0);
        this.nonData = toolData;
        this.useData =useData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.tool_item, viewGroup, false);
        addViewHolder vh = new addViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof addViewHolder){
            if (i <= nonData.size()) {
                ((addViewHolder)viewHolder).tv1.setText(nonData.get(i).getToolName());
            }
        }
    }

    @Override
    public int getItemCount() {
        return nonData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public class addViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1;
        public Button addBtn;
        public View mcontentView;
        //public Switch sw1;
        public addViewHolder(@NonNull final View itemView) {
            super(itemView);
            mcontentView = itemView.findViewById(R.id.edititem);
            tv1 = (TextView)itemView.findViewById(R.id.textView2);
            addBtn = (Button)itemView.findViewById(R.id.addBtn);

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    useData.add(nonData.get(getAdapterPosition()));
                    nonData.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}

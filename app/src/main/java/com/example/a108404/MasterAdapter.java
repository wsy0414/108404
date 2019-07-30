package com.example.a108404;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MasterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements setData {
    Context mContext;
    MyAPIService myAPIService;
    Oil oilData;
    //public String[] data;
    ArrayList<ToolList> data = new ArrayList<ToolList>();
    public MasterAdapter(Context context, ArrayList<ToolList> data){
        this.mContext = context;
        this.data = data;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {        //i=viewType
        if (i == 0){
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.carditem2, viewGroup, false);
            textViewHolder vh = new textViewHolder(v);
            return vh;
        }else if (i == 2){
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.carditem, viewGroup, false);
            aqiViewHolder vh = new aqiViewHolder(v);
            return vh;
        }else{
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.weather_img_card, viewGroup, false);
            weatherViewHolder vh = new weatherViewHolder(v);
            return vh;
        }
//        if (i == 1){
//            View v = LayoutInflater.from(mContext)
//                    .inflate(R.layout.carditem, viewGroup, false);
//            imgViewHolder vh = new imgViewHolder(v);
//            return vh;
//        }else{
//            View v = LayoutInflater.from(mContext)
//                    .inflate(R.layout.carditem2, viewGroup, false);
//            textViewHolder vh = new textViewHolder(v);
//            return vh;
//        }

//        View v = LayoutInflater.from(mContext)
//                .inflate(R.layout.carditem2, viewGroup, false);
//        textViewHolder vh = new textViewHolder(v);
//        return vh;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {          //i=position
//        if (viewHolder instanceof textViewHolder){
//            ((textViewHolder) viewHolder).tv1.setText(data[i]);
//        }
        //----------------------------油價可以用的--------------------------------------------------
        if(oilData != null) {
            if (i == 3) {
                //getApiData();
                ((textViewHolder) viewHolder).titleText.setText("油價");
                if (oilData != null) {
                    ((textViewHolder) viewHolder).oil92.setText(oilData.getOil92() + "元/公升");
                    ((textViewHolder) viewHolder).oil95.setText(oilData.getOil95() + "元/公升");
                    ((textViewHolder) viewHolder).oil98.setText(oilData.getOil98() + "元/公升");
                    ((textViewHolder) viewHolder).supOil.setText(oilData.getSuperOil() + "元/公升");
                }
            }
        }


//        if (i == 1){
//            ((imgViewHolder) viewHolder).iv1.setImageResource(R.drawable.sun);
//        }else if (i == 0){
//            ((textViewHolder) viewHolder).tv1.setText(data[1]);
//            //((textViewHolder) viewHolder).tv2.setText(data[2]);
//        }else{
//            ((textViewHolder) viewHolder).tv1.setText(data[3]);
//            //((textViewHolder) viewHolder).tv2.setText(data[4]);
//        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void setOilData(Oil oilData) {
        this.oilData = oilData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position){
        if (position == 3){
            return 0;
        }else if(position == 2){
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

    public class weatherViewHolder extends RecyclerView.ViewHolder {
        public weatherViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class aqiViewHolder extends RecyclerView.ViewHolder{
        public aqiViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class textViewHolder extends RecyclerView.ViewHolder{
        public TextView titleText;
        public TextView oil92;
        public TextView oil95;
        public TextView oil98;
        public TextView supOil;
        public textViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = (TextView)itemView.findViewById(R.id.topicTextView);
            oil92 = (TextView)itemView.findViewById(R.id.oil92Txt);
            oil95 = (TextView)itemView.findViewById(R.id.oil95Txt);
            oil98 = (TextView)itemView.findViewById(R.id.oil98Txt);
            supOil = (TextView)itemView.findViewById(R.id.supOilTxt);
        }
    }

    private void getApiData(){
        // 2. 透過RetrofitManager取得連線基底
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        Call<Oil> call = myAPIService.postOil();

        // 4. 執行call
        call.enqueue(new Callback<Oil>() {
            @Override
            public void onResponse(Call<Oil> call, Response<Oil> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    setOilData(response.body());
                    String title = response.body().getOil92();
                    Log.d("title", title);
                }
            }

            @Override
            public void onFailure(Call<Oil> call, Throwable t) {
                // 連線失敗
            }
        });
    }
}


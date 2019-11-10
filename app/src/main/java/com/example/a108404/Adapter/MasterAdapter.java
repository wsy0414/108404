package com.example.a108404.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a108404.Activity.DetailAQIActivity;
import com.example.a108404.Activity.DetailWeatherActivity;
import com.example.a108404.Activity.YouBikeMapsActivity;
import com.example.a108404.Module.Aqi;
import com.example.a108404.Module.Park;
import com.example.a108404.Module.ParkNTPC;
import com.example.a108404.Module.PreWeather;
import com.example.a108404.Module.Warning;
import com.example.a108404.Module.Weather;
import com.example.a108404.Module.Youbike;
import com.example.a108404.MyAPIService;
import com.example.a108404.Module.Oil;
import com.example.a108404.OnItemClickListener;
import com.example.a108404.R;
import com.example.a108404.setData;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class MasterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements setData {
    Context mContext;
    MyAPIService myAPIService;
    public Oil oilData;
    public Aqi aqiData;
    public Weather weatherData;
    public Warning warnData;
    public PreWeather preWeatherData;
    //public String[] data;
    ArrayList<String> data;
    public ArrayList<Youbike> ubikeData;
    private OnItemClickListener mItemClickListener;
    public ParkNTPC parkData;
    public MasterAdapter(Context context, ArrayList<String> data){
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
        }else if(i == 3){
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.weather_card_item, viewGroup, false);
            weatherNowViewHolder vh = new weatherNowViewHolder(v);
            return vh;
        }else if(i == 1){
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.weather_img_card, viewGroup, false);
            weatherViewHolder vh = new weatherViewHolder(v);
            return vh;
        }else if(i == 5){
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.park_card, viewGroup, false);
            parkViewHolder vh = new parkViewHolder(v);
            return vh;
        }else if(i == 6){
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.warning_card, viewGroup, false);
            warnViewHolder vh = new warnViewHolder(v);
            return vh;
        }else{
            View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.youbike_card, viewGroup, false);
            ubikeViewHolder vh = new ubikeViewHolder(v);
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
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {          //i=position
//        if (viewHolder instanceof textViewHolder){
//            ((textViewHolder) viewHolder).tv1.setText(data[i]);
//        }
        //----------------------------油價可以用的--------------------------------------------------
        if(oilData != null) {
            if (data.get(i).equals("油價")) {
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

        if(aqiData != null) {
            if (data.get(i).equals("空氣品質")) {
                //getApiData();
                //((textViewHolder) viewHolder).titleText.setText("油價");
                int aqi = Integer.valueOf(aqiData.getAQI());
                int pm = Integer.valueOf(aqiData.getPM25());


                    ((aqiViewHolder) viewHolder).aqiText.setText(aqiData.getAQI());
                    ((aqiViewHolder) viewHolder).pmText.setText(aqiData.getPM25());
                    ((aqiViewHolder) viewHolder).aqiStateText.setText(aqiData.getStatus());
                    ((aqiViewHolder) viewHolder).pmStateText.setText(aqiData.getPmStatus());
                    if(aqi <= 50){
                        ((aqiViewHolder) viewHolder).aqiStateImg.setImageResource(R.drawable.greenline);
                    }else if(aqi <= 100){
                        ((aqiViewHolder) viewHolder).aqiStateImg.setImageResource(R.drawable.yellowline);
                    }else{
                        ((aqiViewHolder) viewHolder).aqiStateImg.setImageResource(R.drawable.redline);
                    }
                if(pm <= 50){
                    ((aqiViewHolder) viewHolder).pmStateImg.setImageResource(R.drawable.greenline);
                }else if(pm <= 100){
                    ((aqiViewHolder) viewHolder).pmStateImg.setImageResource(R.drawable.yellowline);
                }else{
                    ((aqiViewHolder) viewHolder).pmStateImg.setImageResource(R.drawable.redline);
                }
            }
        }

        if(weatherData != null) {
            if (data.get(i).equals("現在天氣")) {
                ((weatherNowViewHolder) viewHolder).tempNowText.setText(weatherData.getTempNow() + "°");
            }

            if (data.get(i).equals("今天白天")) {
                ((weatherViewHolder) viewHolder).rainText.setText(weatherData.getRainFall());
            }
        }

        if(preWeatherData != null) {
            if (data.get(i).equals("今天白天")) {
                ((weatherViewHolder) viewHolder).tempFlowText.setText(preWeatherData.getNowMax() + "°C~" + preWeatherData.getNowMin() + "°C");
                ((weatherViewHolder) viewHolder).wStatusText.setText(preWeatherData.getNowState());
            }
        }

        if(ubikeData != null) {
            if (data.get(i).equals("YouBike")) {
                //getApiData();
                //((textViewHolder) viewHolder).titleText.setText("油價");

                ((ubikeViewHolder) viewHolder).borText1.setText(ubikeData.get(0).getCanBorrow());
                ((ubikeViewHolder) viewHolder).borText2.setText(ubikeData.get(1).getCanBorrow());
                ((ubikeViewHolder) viewHolder).borText3.setText(ubikeData.get(2).getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(ubikeData.get(0).getBemp());
                ((ubikeViewHolder) viewHolder).bempText2.setText(ubikeData.get(1).getBemp());
                ((ubikeViewHolder) viewHolder).bempText3.setText(ubikeData.get(2).getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(ubikeData.get(0).getStationName());
                ((ubikeViewHolder) viewHolder).staText2.setText(ubikeData.get(1).getStationName());
                ((ubikeViewHolder) viewHolder).staText3.setText(ubikeData.get(2).getStationName());

            }
        }

        if(parkData != null) {
            if (data.get(i).equals("新北市汽車停車")) {
                //getApiData();
                //((textViewHolder) viewHolder).titleText.setText("油價")
                if(Integer.valueOf(parkData.getResult()) == 0){
                    ((parkViewHolder) viewHolder).addressText.setText("功能未提供");
                }else{
                    ((parkViewHolder) viewHolder).addressText.setText(parkData.getPark().get(0).getLatitude());
                    ((parkViewHolder) viewHolder).timeText.setText(parkData.getPark().get(0).getDay() + parkData.getPark().get(0).getHour());
                    ((parkViewHolder) viewHolder).payText.setText(parkData.getPark().get(0).getPayCash());
                    ((parkViewHolder) viewHolder).numText.setText(parkData.getPark().get(0).getCellStatus());
                }
            }
        }

        if(warnData != null) {
            if (data.get(i).equals("警報")) {
                if(Integer.valueOf(warnData.getResult()) == 1) {
                    ((warnViewHolder) viewHolder).warnText.setText(warnData.getWarning());
                }
            }
        }

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mItemClickListener.onItemClick(viewHolder.itemView, i);
//            }
//        });


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
        if(data != null){
            return data.size();
        }else{
            return 0;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


    @Override
    public void setOilData(Oil oilData) {
        this.oilData = oilData;
        this.notifyDataSetChanged();
    }

    @Override
    public void setPreWeather(PreWeather preWeatherData) {
        this.preWeatherData = preWeatherData;
        this.notifyDataSetChanged();
    }


    @Override
    public void setAqiData(Aqi aqiData) {
        this.aqiData = aqiData;
        this.notifyDataSetChanged();
    }

    @Override
    public void setWeatherData(Weather weatherData) {
        this.weatherData = weatherData;
        this.notifyDataSetChanged();
    }

    @Override
    public void setUbikeData(ArrayList<Youbike> ubikeData) {
        this.ubikeData = ubikeData;
        this.notifyDataSetChanged();
    }

    @Override
    public void setParkData(ParkNTPC parkData) {
        this.parkData = parkData;
        this.notifyDataSetChanged();
    }

    @Override
    public void setWarnData(Warning warnData) {
        this.warnData = warnData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position){
        if(data.get(position).equals("油價")){
            return 0;
        }else if(data.get(position).equals("今天白天")){
            return 1;
        }else if(data.get(position).equals("空氣品質")){
            return 2;
        }else if(data.get(position).equals("YouBike")){
            return 4;
        }else if(data.get(position).equals("新北市汽車停車")){
            return 5;
        }else if(data.get(position).equals("警報")){
            return 6;
        }else{
            return 3;
        }

        //        if (position == 3){
//            return 0;
//        }else if(position == 2){
//            return 1;
//        }else{
//            return 2;
//        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class weatherViewHolder extends RecyclerView.ViewHolder {
        public TextView tempFlowText;
        public TextView wStatusText;
        public TextView rainText;
        public weatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tempFlowText = (TextView)itemView.findViewById(R.id.tempFlowText);
            wStatusText = (TextView)itemView.findViewById(R.id.WStatusText);
            rainText = (TextView)itemView.findViewById(R.id.rainText);
        }
    }

    public class weatherNowViewHolder extends RecyclerView.ViewHolder {
        public TextView tempNowText;
        public TextView tempText;

        public weatherNowViewHolder(@NonNull View itemView) {
            super(itemView);
            tempNowText = (TextView) itemView.findViewById(R.id.aqiText);
            tempText = (TextView) itemView.findViewById(R.id.pmText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DetailWeatherActivity.class);
                    intent.putExtra("data", new Gson().toJson(weatherData));
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class aqiViewHolder extends RecyclerView.ViewHolder{
        public TextView aqiText;
        public TextView pmText;
        public TextView aqiStateText;
        public TextView pmStateText;
        public ImageView aqiStateImg;
        public ImageView pmStateImg;
        public aqiViewHolder(@NonNull View itemView) {
            super(itemView);
            aqiText = (TextView)itemView.findViewById(R.id.aqiText);
            pmText = (TextView)itemView.findViewById(R.id.pmText);
            aqiStateText = (TextView)itemView.findViewById(R.id.aqiStateText);
            pmStateText = (TextView)itemView.findViewById(R.id.pmStateText);
            aqiStateImg = (ImageView)itemView.findViewById(R.id.aqiStateImg);
            pmStateImg = (ImageView)itemView.findViewById(R.id.pmStateImg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DetailAQIActivity.class);
                    intent.putExtra("data", new Gson().toJson(aqiData));
                    mContext.startActivity(intent);
                }
            });
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

    public class ubikeViewHolder extends RecyclerView.ViewHolder{
        public TextView borText1;
        public TextView borText2;
        public TextView borText3;
        public TextView bempText1;
        public TextView bempText2;
        public TextView bempText3;
        public TextView staText1;
        public TextView staText2;
        public TextView staText3;
        public ubikeViewHolder(@NonNull View itemView) {
            super(itemView);
            borText1 = (TextView)itemView.findViewById(R.id.borText1);
            borText2 = (TextView)itemView.findViewById(R.id.borText2);
            borText3 = (TextView)itemView.findViewById(R.id.borText3);
            bempText1 = (TextView)itemView.findViewById(R.id.bempText1);
            bempText2 = (TextView)itemView.findViewById(R.id.bempText2);
            bempText3 = (TextView)itemView.findViewById(R.id.bempText3);
            staText1 = (TextView)itemView.findViewById(R.id.staText1);
            staText2 = (TextView)itemView.findViewById(R.id.staText2);
            staText3 = (TextView)itemView.findViewById(R.id.staText3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, YouBikeMapsActivity.class);
                    intent.putExtra("data", (Serializable)ubikeData);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class parkViewHolder extends RecyclerView.ViewHolder{
        public TextView addressText;
        public TextView timeText;
        public TextView payText;
        public TextView numText;
        public parkViewHolder(@NonNull View itemView) {
            super(itemView);
            addressText = (TextView)itemView.findViewById(R.id.addressText);
            timeText = (TextView)itemView.findViewById(R.id.timeText);
            payText = (TextView)itemView.findViewById(R.id.borText3);
            numText = (TextView)itemView.findViewById(R.id.numText);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, YouBikeMapsActivity.class);
//                    intent.putExtra("data", (Serializable)ubikeData);
//                    mContext.startActivity(intent);
//                }
//            });
        }
    }

    public class warnViewHolder extends RecyclerView.ViewHolder{
        public TextView warnText;
        public warnViewHolder(@NonNull View itemView) {
            super(itemView);
            warnText = (TextView)itemView.findViewById(R.id.warnText);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, YouBikeMapsActivity.class);
//                    intent.putExtra("data", (Serializable)ubikeData);
//                    mContext.startActivity(intent);
//                }
//            });
        }
    }

//    private void getApiData(){
//        // 2. 透過RetrofitManager取得連線基底
//        myAPIService = RetrofitManager.getInstance().getAPI();
//
//        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
//        Call<Oil> call = myAPIService.postOil();
//
//        // 4. 執行call
//        call.enqueue(new Callback<Oil>() {
//            @Override
//            public void onResponse(Call<Oil> call, Response<Oil> response) {
//                // 連線成功
//                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
//
//                if (response != null) {
//                    setOilData(response.body());
//                    String title = response.body().getOil92();
//                    Log.d("title", title);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Oil> call, Throwable t) {
//                // 連線失敗
//            }
//        });
//    }
}


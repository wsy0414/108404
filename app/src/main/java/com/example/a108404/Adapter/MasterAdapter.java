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
import com.example.a108404.Activity.ParkMapsActivity;
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
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Youbike> ubikeData = new HashMap<String, Youbike>();
    private OnItemClickListener mItemClickListener;
    public ParkNTPC parkData;
    public Youbike taipeiUBikeData;
    public Youbike newTaipeiUBikeData;
    public Youbike hsinchuUBikeData;
    public Youbike miaoliCountyUBikeData;
    public Youbike changhuaCountyUBikeData;
    public Youbike pingtungCountyUBikeData;
    public Youbike taoyuanUBikeData;
    public Youbike kaohsiungUBikeData;
    public Youbike tainanUBikeData;
    public Youbike taichungUBikeData;

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
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {
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

            if(data.get(i).equals("今天白天")){
                int temp = Integer.valueOf(weatherData.getTempNow());
                if(temp >= 26) {
                    ((weatherViewHolder) viewHolder).clothesImg.setImageResource(R.drawable.shirt);
                }else if(temp >= 20){
                    ((weatherViewHolder) viewHolder).clothesImg.setImageResource(R.drawable.sleeve);
                }else if(temp >= 16){
                    ((weatherViewHolder) viewHolder).clothesImg.setImageResource(R.drawable.coat);
                }else{
                    ((weatherViewHolder) viewHolder).clothesImg.setImageResource(R.drawable.scarf);
                }
            }
        }

        if(preWeatherData != null) {
            if (data.get(i).equals("今天白天")) {
                ((weatherViewHolder) viewHolder).tempFlowText.setText(preWeatherData.getNowMin() + "°C~" + preWeatherData.getNowMax() + "°C");
                ((weatherViewHolder) viewHolder).wStatusText.setText(preWeatherData.getNowState());
                ((weatherViewHolder) viewHolder).rainText.setText(preWeatherData.getNowRainFall() + "%");

                int rainFall = Integer.valueOf(preWeatherData.getNowRainFall());
                if(rainFall >= 50) {
                    ((weatherViewHolder) viewHolder).accesImg.setImageResource(R.drawable.umbrella);
                    ((weatherViewHolder) viewHolder).stateImg.setImageResource(R.drawable.movie);
                }else if(rainFall >= 10){
                    ((weatherViewHolder) viewHolder).accesImg.setImageResource(R.drawable.drink);
                    ((weatherViewHolder) viewHolder).stateImg.setImageResource(R.drawable.swim);
                }else{
                    ((weatherViewHolder) viewHolder).accesImg.setImageResource(R.drawable.hanger);
                    ((weatherViewHolder) viewHolder).stateImg.setImageResource(R.drawable.run);
                }

                if(preWeatherData.getNowState().equals("多雲") || preWeatherData.getNowState().equals("陰時多雲")){
                    ((weatherViewHolder) viewHolder).weatherImg.setImageResource(R.drawable.wind);
                }else if(preWeatherData.getNowState().equals("多雲短暫雨") || preWeatherData.getNowState().equals("多雲時陰短暫雨")){
                    ((weatherViewHolder) viewHolder).weatherImg.setImageResource(R.drawable.srain);
                }else{
                    ((weatherViewHolder) viewHolder).weatherImg.setImageResource(R.drawable.sunny);
                }
            }
        }

        if(taipeiUBikeData != null) {
            if (data.get(i).equals("台北市YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(taipeiUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(taipeiUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(taipeiUBikeData.getStationName());
            }
        }
        if(newTaipeiUBikeData != null) {
            if (data.get(i).equals("新北市YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(newTaipeiUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(newTaipeiUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(newTaipeiUBikeData.getStationName());
            }
        }
        if(taoyuanUBikeData != null) {
            if (data.get(i).equals("桃園YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(taoyuanUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(taoyuanUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(taoyuanUBikeData.getStationName());
            }
        }
        if(tainanUBikeData != null) {
            if (data.get(i).equals("台南YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(tainanUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(tainanUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(tainanUBikeData.getStationName());
            }
        }
        if(taichungUBikeData != null) {
            if (data.get(i).equals("台中YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(taichungUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(taichungUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(taichungUBikeData.getStationName());
            }
        }
        if(hsinchuUBikeData != null) {
            if (data.get(i).equals("新竹YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(hsinchuUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(hsinchuUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(hsinchuUBikeData.getStationName());
            }
        }
        if(miaoliCountyUBikeData != null) {
            if (data.get(i).equals("苗栗YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(miaoliCountyUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(miaoliCountyUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(miaoliCountyUBikeData.getStationName());
            }
        }
        if(pingtungCountyUBikeData != null) {
            if (data.get(i).equals("屏東YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(pingtungCountyUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(pingtungCountyUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(pingtungCountyUBikeData.getStationName());
            }
        }
        if(changhuaCountyUBikeData != null) {
            if (data.get(i).equals("彰化YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(changhuaCountyUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(changhuaCountyUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(changhuaCountyUBikeData.getStationName());
            }
        }
        if(kaohsiungUBikeData != null) {
            if (data.get(i).equals("高雄YouBike")) {
                ((ubikeViewHolder) viewHolder).ubikecityText.setText(data.get(i));
                ((ubikeViewHolder) viewHolder).borText1.setText(kaohsiungUBikeData.getCanBorrow());
                ((ubikeViewHolder) viewHolder).bempText1.setText(kaohsiungUBikeData.getBemp());
                ((ubikeViewHolder) viewHolder).staText1.setText(kaohsiungUBikeData.getStationName());
            }
        }

        if(parkData != null) {
            if (data.get(i).equals("新北市汽車停車")) {
                if(Integer.valueOf(parkData.getResult()) == 0){
                    ((parkViewHolder) viewHolder).addressText.setText("功能未提供");
                }else{
                    ((parkViewHolder) viewHolder).timeText.setText(parkData.getPark().get(0).getParkStatusZh());
                    ((parkViewHolder) viewHolder).payText.setText(parkData.getPark().get(0).getPayCash());
                    ((parkViewHolder) viewHolder).numText.setText(parkData.getPark().get(0).getMemo());
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
    public void setUbikeData(Youbike ubikeData, String city) {
        this.ubikeData.put(city, ubikeData);
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
        }else if(data.get(position).equals("台北市YouBike") || data.get(position).equals("新北市YouBike") || data.get(position).equals("台中YouBike") || data.get(position).equals("台南YouBike") || data.get(position).equals("高雄YouBike") || data.get(position).equals("桃園YouBike") || data.get(position).equals("新竹YouBike") || data.get(position).equals("屏東YouBike") || data.get(position).equals("彰化YouBike") || data.get(position).equals("苗栗YouBike")){
            return 4;
        }else if(data.get(position).equals("新北市汽車停車")){
            return 5;
        }else if(data.get(position).equals("警報")){
            return 6;
        }else{
            return 3;
        }
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
        public ImageView clothesImg;
        public ImageView accesImg;
        public ImageView stateImg;
        public ImageView weatherImg;
        public weatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tempFlowText = (TextView)itemView.findViewById(R.id.tempFlowText);
            wStatusText = (TextView)itemView.findViewById(R.id.WStatusText);
            rainText = (TextView)itemView.findViewById(R.id.rainText);
            clothesImg = itemView.findViewById(R.id.clothesImg);
            accesImg = itemView.findViewById(R.id.accesImg);
            stateImg = itemView.findViewById(R.id.stateImg);
            weatherImg = itemView.findViewById(R.id.weatherImg);
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
        public TextView ubikecityText;
        public ubikeViewHolder(@NonNull View itemView) {
            super(itemView);
            borText1 = (TextView)itemView.findViewById(R.id.borText1);
            bempText1 = (TextView)itemView.findViewById(R.id.bempText1);
            staText1 = (TextView)itemView.findViewById(R.id.staText1);
            ubikecityText = itemView.findViewById(R.id.ubikecityText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, YouBikeMapsActivity.class);
                    intent.putExtra("city", data.get(getAdapterPosition()));
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
            //addressText = (TextView)itemView.findViewById(R.id.addressText);
            timeText = (TextView)itemView.findViewById(R.id.timeText);
            payText = (TextView)itemView.findViewById(R.id.payText);
            numText = (TextView)itemView.findViewById(R.id.numText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ParkMapsActivity.class);
                    //intent.putExtra("data", (Serializable)ubikeData);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class warnViewHolder extends RecyclerView.ViewHolder{
        public TextView warnText;
        public warnViewHolder(@NonNull View itemView) {
            super(itemView);
            warnText = (TextView)itemView.findViewById(R.id.warnText);
        }
    }
}


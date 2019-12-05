package com.example.a108404;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a108404.Activity.DetailAQIActivity;
import com.example.a108404.Adapter.MasterAdapter;
import com.example.a108404.Module.Aqi;
import com.example.a108404.Module.Oil;
import com.example.a108404.Module.OilQuery;
import com.example.a108404.Module.ParkNTPC;
import com.example.a108404.Module.ParkQuery;
import com.example.a108404.Module.PreWeather;
import com.example.a108404.Module.Retval;
import com.example.a108404.Module.SetAddress;
import com.example.a108404.Module.Warning;
import com.example.a108404.Module.Weather;
import com.example.a108404.Module.YouBikeQuery;
import com.example.a108404.Module.Youbike;
import com.example.a108404.Service.LatLngMethod;
import com.example.a108404.Service.LocationMethod;
import com.example.a108404.Service.RetrofitManager;
import com.example.a108404.Service.YoubikeRetrofitManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MasterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MasterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MasterFragment extends Fragment implements setData{
    public TextView loctext;
    RecyclerView myRecyclerView;
    public MasterAdapter masterAdapter;
    String[] test = {"中正市", "天氣", "晴時多雲", "垃圾車", "再30分鐘即將到達"};
    ArrayList<String> title;
    static MyAPIService myAPIService;
    static YoubikeAPIService youbikeAPIService;
    static DistanceCalculation distanceCalculation = new DistanceCalculation();
    //public MasterAdapter masterAdapter = new MasterAdapter(getActivity(), test);
    // TODO: Rename parameter arguments, choose names that matc

    // TODO: Rename and change types of parameters
    private String[] mParam1;
    private String mParam2;
    String location;
    int id;
    Map<String, Youbike> ubikeCity = new HashMap<String, Youbike>();
    Map<String, String> cityTitle = new HashMap<String, String>();
    static String[] checkCity = {"台北市YouBike", "新北市YouBike", "台中YouBike", "台南YouBike", "高雄YouBike", "桃園YouBike", "新竹YouBike", "屏東YouBike", "彰化YouBike", "苗栗YouBike"};


    private OnFragmentInteractionListener mListener;

    public MasterFragment() {
        // Required empty public constructor
    }

    private void loadYouBikeCity(){
        //Map<String, String> cityTitle = null;
        cityTitle.put("台北市YouBike", "Taipei");
        cityTitle.put("新北市YouBike", "NewTaipei");
        cityTitle.put("台中YouBike", "Taichung");
        cityTitle.put("台南YouBike", "Tainan");
        cityTitle.put("高雄YouBike", "Kaohsiung");
        cityTitle.put("桃園YouBike", "Taoyuan");
        cityTitle.put("新竹YouBike", "Hsinchu");
        cityTitle.put("屏東YouBike", "PingtungCounty");
        cityTitle.put("彰化YouBike", "ChanghuaCounty");
        cityTitle.put("苗栗YouBike", "MiaoliCounty");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MasterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MasterFragment newInstance(ArrayList<String> param1, int i, ArrayList<String> toolData) {
        MasterFragment fragment = new MasterFragment();
        Bundle args = new Bundle();
        //args.p("location", toolData);
        args.putStringArrayList("title", toolData);
        args.putString("location", param1.get(i));

        args.putInt("id", i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);

        // Inflate the layout for this fragment
        loctext = (TextView)view.findViewById(R.id.locatText);
        myRecyclerView = (RecyclerView)view.findViewById(R.id.myRecycle);
        //locatText.setText(location[id]);
        loadYouBikeCity();
        //locationMethod = LocationMethod.getInstance(this);
        //MyAsyncTask myAsyncTask = new MyAsyncTask();
        //myAsyncTask.execute(this);

        if(location != null) {
            if (location.equals("現在地區")) {
                //locationMethod = LocationMethod.getInstance(this);
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(getActivity());
            } else {
                loctext.setText(location);
                LatLngAsyncTask latLngAsyncTask = new LatLngAsyncTask();
                latLngAsyncTask.execute(getActivity());
            }
        }

//        masterAdapter = new MasterAdapter(getActivity(), data);
//        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        myRecyclerView.setAdapter(masterAdapter);

        initRecyclerView();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
////        if (mListener != null) {
////            mListener.onFragmentInteraction(uri);
////        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle args = getArguments();
        if(args != null){
            location = args.getString("location");
            id = args.getInt("id");
            title = args.getStringArrayList("title");
        }
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    private void initRecyclerView(){
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == title.indexOf("油價") || position == title.indexOf("今天白天") || position == title.indexOf("台北市YouBike") || position == title.indexOf("新北市汽車停車") || position == title.indexOf("警報") || position == title.indexOf("苗栗YouBike") || position == title.indexOf("彰化YouBike") || position == title.indexOf("屏東YouBike") || position == title.indexOf("高雄YouBike") || position == title.indexOf("新竹YouBike") || position == title.indexOf("桃園YouBike") || position == title.indexOf("新北市YouBike") || position == title.indexOf("台南YouBike") || position == title.indexOf("台中YouBike") ){
                    return 2;
                }else{
                    return 1;
                }
            }
        });
        masterAdapter = new MasterAdapter(getActivity(), title);
        masterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailAQIActivity.class);
                startActivity(intent);
            }
        });
        myRecyclerView.setLayoutManager(manager);
        myRecyclerView.setAdapter(masterAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

        @Override
    public void setOilData(Oil oilData) {
        //this.oilData = oilData;
        masterAdapter.oilData = oilData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAqiData(Aqi aqiData) {
        //this.oilData = oilData;
        masterAdapter.aqiData = aqiData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setWeatherData(Weather weatherData) {
        //this.oilData = oilData;
        masterAdapter.weatherData = weatherData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUbikeData(Youbike ubikeData, String cityName) {
        //this.oilData = oilData;
        if(cityName.equals("台北市YouBike")) {
            masterAdapter.taipeiUBikeData = ubikeData;
        }else if(cityName.equals("新北市YouBike")){
            masterAdapter.newTaipeiUBikeData = ubikeData;
        }else if(cityName.equals("新竹YouBike")){
            masterAdapter.hsinchuUBikeData = ubikeData;
        }else if(cityName.equals("台中YouBike")){
            masterAdapter.taichungUBikeData = ubikeData;
        }else if(cityName.equals("台南YouBike")){
            masterAdapter.tainanUBikeData = ubikeData;
        }else if(cityName.equals("苗栗YouBike")){
            masterAdapter.miaoliCountyUBikeData = ubikeData;
        }else if(cityName.equals("高雄YouBike")){
            masterAdapter.kaohsiungUBikeData = ubikeData;
        }else if(cityName.equals("彰化YouBike")){
            masterAdapter.changhuaCountyUBikeData = ubikeData;
        }else if(cityName.equals("屏東YouBike")){
            masterAdapter.pingtungCountyUBikeData = ubikeData;
        }else{
            masterAdapter.taoyuanUBikeData = ubikeData;
        }
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setParkData(ParkNTPC parkData){
        masterAdapter.parkData = parkData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setWarnData(Warning warnData){
        masterAdapter.warnData = warnData;
        masterAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPreWeather(PreWeather preWeatherData){
        masterAdapter.preWeatherData = preWeatherData;
        masterAdapter.notifyDataSetChanged();
    }

    private void getUBikeData(final YouBikeQuery youBikeQuery, final String cityN){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //OilQuery oilQuery = new OilQuery(1);

        Call<Youbike> result = myAPIService.postUbike(youBikeQuery);

        // 4. 執行call
        result.enqueue(new Callback<Youbike>() {

            @Override
            public void onResponse(Call<Youbike> call, Response<Youbike> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    setUbikeData(response.body(), cityN);
                    //Log.d("ubike", ubikeData.toString());
                }
            }

            @Override
            public void onFailure(Call<Youbike> call, Throwable t) {
                // 連線失敗
                Log.d("ubike", "error");
            }
        });
    }

    private void getParkData(ParkQuery parkQuery){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //ParkQuery parkQuery = new ParkQuery("121.502569", "24.986205", "0", "1");

        Call<ParkNTPC> call = myAPIService.postParkNTPC(parkQuery);

        // 4. 執行call
        call.enqueue(new Callback<ParkNTPC>() {

            @Override
            public void onResponse(Call<ParkNTPC> call, Response<ParkNTPC> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    setParkData(response.body());
                    //String title = response.body().getOil92();
                    //if(response.body().getPark() != null){
                       // setParkData(response.body().getPark());
                    //}

                    //Log.d("park", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ParkNTPC> call, Throwable t) {
                // 連線失敗
            }
        });
    }

    private void getOilData(){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的postOil()連線
        OilQuery oilQuery = new OilQuery(1);

        Call<Oil> call = myAPIService.postOil(oilQuery);

            // 4. 執行call
            call.enqueue(new Callback<Oil>() {

                @Override
                public void onResponse(Call<Oil> call, Response<Oil> response) {
                    // 連線成功
                    // 回傳的資料已轉成Oil物件，可直接用get方法取得特定欄位

                    if (response != null) {
                        setOilData(response.body());
                        //String title = response.body().getOil92();
                        Log.d("oil", response.message());
                    }
                }

                @Override
                public void onFailure(Call<Oil> call, Throwable t) {
                    // 連線失敗
                }
            });
    }

    private void getPreWeatherData(SetAddress setAddress){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //OilQuery oilQuery = new OilQuery(1);

        Call<PreWeather> call = myAPIService.postPreWeather(setAddress);

        // 4. 執行call
        call.enqueue(new Callback<PreWeather>() {

            @Override
            public void onResponse(Call<PreWeather> call, Response<PreWeather> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    setPreWeather(response.body());
                    Log.d("preWeather", response.message());
                }
            }

            @Override
            public void onFailure(Call<PreWeather> call, Throwable t) {
                // 連線失敗
            }
        });
    }

    private void getWarnData(SetAddress setAddress){
        myAPIService = RetrofitManager.getInstance().getAPI();

        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //OilQuery oilQuery = new OilQuery(1);

        Call<Warning> call = myAPIService.postWarning(setAddress);

        // 4. 執行call
        call.enqueue(new Callback<Warning>() {

            @Override
            public void onResponse(Call<Warning> call, Response<Warning> response) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位

                if (response != null) {
                    setWarnData(response.body());
                    //setOilData(response.body());
                    //String title = response.body().getOil92();
                    Log.d("warning", response.message());
                }
            }

            @Override
            public void onFailure(Call<Warning> call, Throwable t) {
                // 連線失敗
            }
        });
    }

    private void getAqiData(SetAddress setAddress){
        myAPIService = RetrofitManager.getInstance().getAPI();
        //SetAddress location = new SetAddress(lon, lat);
        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //SetAddress setAddress = new SetAddress("121.565427", "25.789574");
        Call<Aqi> call = myAPIService.postAqi(setAddress);

        try {
            // 4. 執行call
            call.enqueue(new Callback<Aqi>() {
                @Override
                public void onResponse(Call<Aqi> call, Response<Aqi> response) {
                    // 連線成功
                    // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
                    Log.d("aqi", "success");
                    if (response != null) {
                        setAqiData(response.body());
                        //String title = response.body().getCountry();
                        //Log.d("title", title);
                    }
                }

                @Override
                public void onFailure(Call<Aqi> call, Throwable t) {
                    // 連線失敗
                    Log.d("aqi", "fail");
                }
            });
        }catch (Exception e){
            Log.d("AqiError", e.toString());
        }
    }

    private void getWeatherData(SetAddress setAddress){
        myAPIService = RetrofitManager.getInstance().getAPI();
        //SetAddress location = new SetAddress(lon, lat);
        // 3. 建立連線的Call，此處設置call為myAPIService中的getAlbums()連線
        //SetAddress setAddress = new SetAddress("121.565427", "25.789574");
        Call<Weather> call = myAPIService.postWeather(setAddress);

        try {
            // 4. 執行call
            call.enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {
                    // 連線成功
                    // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
                    Log.d("weather", "success");
                    if (response != null) {
                        setWeatherData(response.body());
                        //String title = response.body().getCountry();
                        //Log.d("title", title);
                    }
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {
                    // 連線失敗
                    Log.d("weather", "fail");
                }
            });
        }catch (Exception e){
            Log.d("WeatherError", e.toString());
        }
    }

        class MyAsyncTask extends AsyncTask<Context, Integer, String> {
            Location locccat;
            LocationMethod locationMethod = new LocationMethod();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //LocationMethod locationMethod = new LocationMethod();
                Log.d("preExcute", "ok");
                locccat = LocationMethod.getLocation(getContext());
            }

            @Override
            protected String doInBackground(Context... contexts) {
                return locationMethod.showLocation(this.locccat, contexts[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result != null) {
                    Log.d("result", result);
                    loctext.setText(result);
                    SetAddress setAddress = new SetAddress(String.valueOf(locccat.getLongitude()), String.valueOf(locccat.getLatitude()));
                    ParkQuery parkQuery = new ParkQuery(locccat.getLongitude(), locccat.getLatitude(), "1", "0");
                    for (int i = 0; i < title.size(); i++) {
                        String ubikeTitle = title.get(i);
                        for (int j = 0; j < checkCity.length; j++) {
                            if (ubikeTitle.equals(checkCity[j])) {
                                YouBikeQuery youBikeQuery = new YouBikeQuery(locccat.getLongitude(), locccat.getLatitude(), "1", cityTitle.get(checkCity[j]));
                                getUBikeData(youBikeQuery, checkCity[j]);
                            }
                        }
                    }

                    //連接API
                    getOilData();
                    getAqiData(setAddress);
                    getWeatherData(setAddress);
                    getParkData(parkQuery);
                    getWarnData(setAddress);
                    getPreWeatherData(setAddress);

                    //loc[0] = result;

                }
            }
    }

    class LatLngAsyncTask extends AsyncTask<Context, Integer, String> {
        //Location location;
        LatLngMethod latLngMethod = new LatLngMethod();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //LocationMethod locationMethod = new LocationMethod();
            //Log.d("preExcute", "ok");
            //location = LocationMethod.getLocation(getActivity());
        }

        @Override
        protected String doInBackground(Context... contexts) {
            return latLngMethod.addresses(contexts[0], location);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result != null) {
                Log.d("result", result);
                Gson gson = new GsonBuilder().create();
                Type collectionType = new TypeToken<List<Address>>() {
                }.getType();
                List<Address> addresses = gson.fromJson(result, collectionType);
                SetAddress setAddress = new SetAddress(String.valueOf(addresses.get(0).getLongitude()), String.valueOf(addresses.get(0).getLatitude()));
                ParkQuery parkQuery = new ParkQuery(addresses.get(0).getLongitude(), addresses.get(0).getLongitude(), "1", "0");
                for (int i = 0; i < title.size(); i++) {
                    String ubikeTitle = title.get(i);
                    for (int j = 0; j < checkCity.length; j++) {
                        if (ubikeTitle.equals(checkCity[j])) {
                            YouBikeQuery youBikeQuery = new YouBikeQuery(addresses.get(0).getLongitude(), addresses.get(0).getLatitude(), "1", cityTitle.get(checkCity[j]));
                            getUBikeData(youBikeQuery, checkCity[j]);
                        }
                    }
                }

                //連接API
                getOilData();
                getAqiData(setAddress);
                getWeatherData(setAddress);
                getParkData(parkQuery);
                getWarnData(setAddress);
                getPreWeatherData(setAddress);
            }

        }
    }
}

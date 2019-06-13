package com.example.a108404;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import static java.util.Locale.*;

public class LocationMethod{
    private volatile static LocationMethod uniqueInstance;
    //private static LocationManager locationManager;
    private static LocationManager locationManager;
    public static Context mContext;

//    public LocationMethod(Context context) {
//        mContext = context;
//        getLocation();
//    }
//
//    public static LocationMethod getInstance(Context context) {
//        if (uniqueInstance == null) {
//            synchronized (LocationMethod.class) {
//                if (uniqueInstance == null) {
//                    uniqueInstance = new LocationMethod(context);
//                }
//            }
//        }
//        return uniqueInstance;
//    }

    @SuppressLint("MissingPermission")
    public static Location getLocation(Context conext) {
        locationManager = (LocationManager) conext.getSystemService(Context.LOCATION_SERVICE);
        mContext = conext;
        //2.获取位置提供器，GPS或是NetWork
        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        String locationProvider;
        Log.d("getLocation", "ok");
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            //GPS 定位的精准度比较高，但是非常耗电。
            System.out.println("=====GPS_PROVIDER=====");
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {//Google服务被墙不可用
            //网络定位的精准度稍差，但耗电量比较少。
            System.out.println("=====NETWORK_PROVIDER=====");
            locationProvider = LocationManager.NETWORK_PROVIDER;

        } else {
            System.out.println("=====NO_PROVIDER=====");
            // 当没有可用的位置提供器时，弹出Toast提示用户
            Toast.makeText(conext, "No location provider to use", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            conext.startActivity(intent);
            return null;
        }
        locationManager.requestLocationUpdates(locationProvider, 5000, 10, locationListener);
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            // 显示当前设备的位置信息
            System.out.println("==显示当前设备的位置信息==");
            //showLocation(location);
            return location;
        } else {//当GPS信号弱没获取到位置的时候可从网络获取
            System.out.println("==Google服务被墙的解决办法==");
            //getLngAndLatWithNetwork();//Google服务被墙的解决办法
            LocationManager locationManager = (LocationManager) conext.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return location;
        }



        // 监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
        //LocationManager 每隔 5 秒钟会检测一下位置的变化情况，当移动距离超过 10 米的时候，
        // 就会调用 LocationListener 的 onLocationChanged() 方法，并把新的位置信息作为参数传入。
        //locationManager.requestLocationUpdates(locationProvider, 5000, 10, locationListener);
    }

//    @SuppressLint("MissingPermission")
//    private void getLngAndLatWithNetwork() {
//        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        showLocation(location);
//    }

    //获取经纬度
    public String showLocation(Location location, Context context) {
        double latitude;
        double longitude;
        if (location != null) {
            latitude = location.getLatitude();//纬度
            longitude = location.getLongitude();//经度
        }else{
            latitude = 25.789574;
            longitude = 121.565427;
        }
        String locality = "_ _";
       // if (context != null) {
            Log.d("showLocation", "ok");
            Geocoder gc = new Geocoder(context, TRADITIONAL_CHINESE);
            try {
                //Geocoder gc = new Geocoder(LocationMethod.mContext, TRADITIONAL_CHINESE);
                List<Address> locationList = gc.getFromLocation(latitude, longitude, 1);
                if (locationList != null) {
                    Address address = locationList.get(0);
                    Log.d("111", "111");
                    for (int i = 0; address.getAddressLine(i) != null; i++) {
                        String addressLine = address.getAddressLine(i);
                        Log.d("addressLine=====", addressLine);
                    }
                    locality = address.getLocality();
                    //subAdminArea = address.getAddressLine();
                    Log.d("address:", locality);
                }
            } catch (Exception e) {
                String eee = "error";
                Log.d("error", e.toString());
            }

        //}
        return locality;

        //getAddress(latitude, longitude);

    }

//    private void getAddress(double latitude, double longitude) {
//        //Geocoder通过经纬度获取具体信息
////        String locality = null;
////        Geocoder gc = new Geocoder(mContext, TRADITIONAL_CHINESE);
////        try {
////            List<Address> locationList = gc.getFromLocation(latitude, longitude, 1);
////
////            if (locationList != null) {
//
////                Log.d("error: ", "error");
//               // Address address = locationList.get(0);
////                Log.d("error: ", "error1");
////                String countryName = address.getCountryName();//国家
////                String countryCode = address.getCountryCode();
////                String adminArea = address.getAdminArea();//省
//               // String locality = address.getLocality();//市
//               // String subAdminArea = address.getSubAdminArea();//区
//                //String featureName = address.getFeatureName();//街道
//
////                for (int i = 0; address.getAddressLine(i) != null; i++) {
////                    String addressLine = address.getAddressLine(i);
////                    //街道名称:广东省深圳市罗湖区蔡屋围一街深圳瑞吉酒店
////                    System.out.println("addressLine=====" + addressLine);
////                }
//
////                String currentPosition = "latitude is " + latitude//22.545975
////                        + "\n" + "longitude is " + longitude//114.101232
////                        + "\n" + "countryName is " + countryName//null
////                        + "\n" + "countryCode is " + countryCode//CN
////                        + "\n" + "adminArea is " + adminArea//广东省
////                        + "\n" + "locality is " + locality//深圳市
////                        + "\n" + "subAdminArea is " + subAdminArea//null
////                        + "\n" + "featureName is " + featureName;//蔡屋围一街深圳瑞吉酒店
////
////                Log.d("currentPosition",currentPosition);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        MyAsyncTask myAsyncTask = new MyAsyncTask();
//        myAsyncTask.execute(latitude,longitude);
//        //return locality;
//    }

    public void removeLocationUpdatesListener() {
        if (locationManager != null) {
            uniqueInstance = null;
            locationManager.removeUpdates(locationListener);
        }

    }
    private static LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {
        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            System.out.println("==onLocationChanged==");

        }
    };



}


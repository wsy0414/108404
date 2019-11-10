package com.example.a108404;

import com.example.a108404.Module.SetAddress;
import com.example.a108404.Module.Youbike;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DistanceCalculation {

    public ArrayList<Youbike> calDistanceTopThree(Map<String, Youbike> data, SetAddress setAddress){
        Map<String, Double> distance = new HashMap<>();
        Sort sort = new Sort();
        ArrayList<Youbike> result = new ArrayList<>();
        Iterator iterator = data.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry mapData = (Map.Entry)iterator.next();
            Youbike ubikeData = (Youbike) mapData.getValue();
            double latDis = (Double.valueOf(ubikeData.getLat()) - Double.valueOf(setAddress.lat));
            double lonDis = (Double.valueOf(ubikeData.getLng()) - Double.valueOf(setAddress.lon));
            double dis = Math.pow(Math.pow(latDis, 2) + Math.pow(lonDis, 2), 0.5);
            distance.put(mapData.getKey().toString(), dis);
        }
        String[] top = sort.sortDistance(distance);

        for(int i = 0; i < top.length; i++){
            result.add(data.get(top[i]));
        }

        return result;
    }
}

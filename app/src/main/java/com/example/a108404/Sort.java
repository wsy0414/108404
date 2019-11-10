package com.example.a108404;

import com.example.a108404.Module.Youbike;

import java.util.Iterator;
import java.util.Map;

public class Sort {
    public String[] sortDistance(Map<String, Double> data){
        String[] result = new String[3];
        String key = "";
        int i = 0;
        //Iterator iterator = data.entrySet().iterator();
        while (i < 3) {
            double min = 1000;
            Iterator iterator = data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry mapData = (Map.Entry) iterator.next();
                if ((Double) mapData.getValue() < min) {
                    min = (Double) mapData.getValue();
                    key = mapData.getKey().toString();
                }
            }
            data.remove(key);
            result[i] = key;
            i ++;
        }
        return result;
    }
}

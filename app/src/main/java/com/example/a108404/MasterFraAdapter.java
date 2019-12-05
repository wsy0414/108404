package com.example.a108404;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a108404.Module.ToolList;

import java.util.ArrayList;

public class MasterFraAdapter extends FragmentPagerAdapter {
    private ArrayList<String> county;
    private ArrayList<String> toolData;
    public MasterFraAdapter(FragmentManager fm, ArrayList<String> county, ArrayList<String> toolData) {
        super(fm);
        this.county = county;
        this.toolData = toolData;
    }

    @Override
    public Fragment getItem(int i) {
        return MasterFragment.newInstance(county, i, toolData);
    }

    @Override
    public int getCount() {
        return county.size();
    }


}

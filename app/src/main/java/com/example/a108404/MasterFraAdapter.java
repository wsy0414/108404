package com.example.a108404;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class MasterFraAdapter extends FragmentPagerAdapter {
    private String[] aa;
    public MasterFraAdapter(FragmentManager fm, String[] aa) {
        super(fm);
        this.aa = aa;
    }

    @Override
    public Fragment getItem(int i) {
//        switch (i) {
//            case 0:
//                return MasterFragment.newInstance(aa);
//            case 1:
//                return MasterFragment.newInstance(aa);
//            case 2:
//                return MasterFragment.newInstance(aa);
//            default:
//                return null;
//        }
        return MasterFragment.newInstance(aa, i);
    }

    @Override
    public int getCount() {
        return aa.length;
    }
}

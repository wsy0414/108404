package com.example.a108404;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

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
        return 1;
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        ((ViewPager)container).removeView( (View) object);
//    }
//
//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view== (View) object;
//    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        container.addView(container) ;
//        return container;
//    }

}

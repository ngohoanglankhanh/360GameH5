package com.ngo.lap11877_khanh.a360_h5_2;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private static final int PAGE_NUM = 2;

    private final String tabTitles[] = {"KHO GAME H5", "TIN GAME"};

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return GameFragment.newInstance();
            case 1:
                return NewsFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
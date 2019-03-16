package com.example.adarsh.lovelyface.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/*
    CLASS THAT STORE FRAGMENTS FOR TABS
 */

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG="SectionP;agerAdapter";

    private final List<Fragment> mFragmentList=new ArrayList<>();
    public SectionPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment)
    {
        mFragmentList.add(fragment);
    }
}

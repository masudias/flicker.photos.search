package com.masudias.flickerdashboard.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.masudias.flickerdashboard.fragments.SportsFragment;

public class DashboardPagerAdapter extends FragmentPagerAdapter {

    public static final int TOTAL_PAGE_COUNT = 4;

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SportsFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return TOTAL_PAGE_COUNT;
    }
}
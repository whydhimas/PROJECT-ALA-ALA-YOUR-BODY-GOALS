package com.example.android.yourbodygoals_apps;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Yosafat Dhimas on 27/04/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNoOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabOverall tab1 = new TabOverall();
                return tab1;
            case 1:
                TabAnggota tab2 = new TabAnggota();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}

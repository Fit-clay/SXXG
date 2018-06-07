package com.eric.sxxg.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Eric on 2018/3/10.
 */

public class ViewPageFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragList;
    String[] mTittle;
    public ViewPageFragmentAdapter(FragmentManager fm , ArrayList<Fragment> fragList, String[] mTittle) {
        super(fm);
        this.fragList=fragList;
        this.mTittle=mTittle;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTittle[position];
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
    }
}

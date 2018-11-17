package com.example.administrator.huawei.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class FixPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> fragments = null;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public FixPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        return (Fragment)this.fragments.get(position);
    }

    public int getCount() {
        return this.fragments.size();
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;

        try {
            fragment = (Fragment)super.instantiateItem(container, position);
        } catch (Exception var5) {
            ;
        }

        return fragment;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }

}

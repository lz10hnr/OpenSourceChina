package com.example.xingge.opensourcechina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.xingge.opensourcechina.base.BaseFragment;

import java.util.List;

/**
 * Created by xingge on 2017/4/12.
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> titles;
    public NewsPagerAdapter(FragmentManager fm, List<BaseFragment> fragments,List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

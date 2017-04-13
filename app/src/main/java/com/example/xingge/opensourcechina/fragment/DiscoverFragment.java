package com.example.xingge.opensourcechina.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xingge on 2017/4/13.
 */

public class DiscoverFragment extends BaseFragment {
    @BindView(R.id.bannerViewPager)
    ViewPager bannerViewPager;

    @Override
    protected int layoutId() {
        return R.layout.discover_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

}

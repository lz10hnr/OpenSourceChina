package com.example.xingge.opensourcechina.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.activity.MainActivity;
import com.example.xingge.opensourcechina.adapter.NewsPagerAdapter;
import com.example.xingge.opensourcechina.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xingge on 2017/4/11.
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.newsTabLayout)
    TabLayout newsTabLayout;
    @BindView(R.id.newsViewPager)
    ViewPager newsViewPager;


    private NewsPagerAdapter pagerAdapter;

    private List<String> titles;
    private List<BaseFragment> fragments;

    private NewsContentFragment newsContentFragment;

    @Override
    protected int layoutId() {
        return R.layout.news_fragment;
    }

    @Override
    protected void initView(View view) {


    }

    @Override
    protected void initData() {

        titles = new ArrayList<>();
        titles.add("资讯");
        titles.add("热点");
        titles.add("博客");
        titles.add("推荐");

        fragments = new ArrayList<>();
        fragments.add(newsContentFragment = new NewsContentFragment());

        pagerAdapter = new NewsPagerAdapter(getFragmentManager(),fragments,titles);
        newsViewPager.setAdapter(pagerAdapter);
        newsTabLayout.setupWithViewPager(newsViewPager);
    }

    @Override
    protected void initListener() {



    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void updateTitleBar() {
        super.updateTitleBar();
        if(APP.activity instanceof MainActivity) {
            ((MainActivity) APP.activity).getTitleTv().setText("综合");
        }

    }
}

package com.example.xingge.opensourcechina.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.activity.MainActivity;
import com.example.xingge.opensourcechina.adapter.NewsPagerAdapter;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.config.Urls;
import com.example.xingge.opensourcechina.model.http.biz.INewsModel;
import com.example.xingge.opensourcechina.model.http.biz.NewsModelImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xingge on 2017/4/18.
 */

public class SearchFragment extends BaseFragment {
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.cancel_tv)
    TextView cancelTv;
    @BindView(R.id.searchResultTabLayout)
    TabLayout searchResultTabLayout;
    @BindView(R.id.searchResultViewPager)
    ViewPager searchResultViewPager;
    @BindView(R.id.searchResultGroup)
    LinearLayout searchResultGroup;

    private INewsModel newsModel;
    private int pageIndex = 0;
    private List<String> titles;
    private List<String> catalogs;
    private List<BaseFragment> fragments;
    private NewsPagerAdapter pagerAdapter;

    @Override
    protected int layoutId() {
        return R.layout.search_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        titles = new ArrayList<>();
        catalogs = new ArrayList<>();
        fragments = new ArrayList<>();
        titles.add("软件");
        titles.add("问答");
        titles.add("博客");
        titles.add("资讯");

        catalogs.add(Urls.SOFTWARE);
        catalogs.add(Urls.POST);
        catalogs.add(Urls.BLOG);
        catalogs.add(Urls.NEWS);

        newsModel = new NewsModelImpl();
    }

    @Override
    protected void initListener() {

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!TextUtils.isEmpty(s.toString())){
                    cancelTv.setText("搜索");
                }else
                    cancelTv.setText("取消");
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    @Override
    protected void loadData() {



    }


    @Override
    protected void updateTitleBar() {
        if(APP.activity instanceof MainActivity){
            ((MainActivity)APP.activity).getBottomGroup().setVisibility(View.GONE);
            ((MainActivity)APP.activity).getTopGroup().setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.cancel_tv)
    public void onViewClicked() {

        if("取消".equals(cancelTv.getText().toString().trim())){

        }else {
            String content = searchEt.getText().toString().trim();
            fragments.clear();
            for (int i = 0; i < titles.size(); i++) {
                SearchResultContentFragment fragment = new SearchResultContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("content",content);
                bundle.putString("catalog",catalogs.get(i));
                fragment.setParams(bundle);
                fragments.add(fragment);
            }
            pagerAdapter = new NewsPagerAdapter(getFragmentManager(),fragments,titles);
            searchResultViewPager.setAdapter(pagerAdapter);
            searchResultTabLayout.setupWithViewPager(searchResultViewPager);
        }

    }
}

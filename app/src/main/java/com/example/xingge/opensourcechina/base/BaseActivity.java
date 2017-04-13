package com.example.xingge.opensourcechina.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.config.FragmentBuilder;
import com.example.xingge.opensourcechina.fragment.NewsFragment;
import com.example.xingge.opensourcechina.util.Utils;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        APP.activity = this;
        ButterKnife.bind(this);
        init();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    //加载布局
    protected abstract int getLayoutId();

    //初始化
    protected abstract void init();

    //初始化监听
    protected abstract void initListener();

    //加载数据
    protected abstract void loadData();


}

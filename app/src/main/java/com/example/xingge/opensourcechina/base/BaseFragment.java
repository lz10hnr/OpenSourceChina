package com.example.xingge.opensourcechina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xingge.opensourcechina.APP;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xingge on 2017/4/11.
 */

public abstract class BaseFragment extends Fragment {

    protected Bundle bundle;
    protected Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initListener();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTitleBar();
        loadData();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden)
            onHidden();
        else
            onShow();
    }

    /**
     * 加载布局
     * @return
     */
    protected abstract int layoutId();

    /**
     * 初始化View控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据（对象）
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 页面切切换传递数据
     * @param bundle
     */
    public void setParams(Bundle bundle){
        this.bundle = bundle;
    }

    /**
     * 当Fragment可见时，在此刷setParams新页面显示
     */
    protected void onShow(){
        APP.lastFragment = this;
        String simpleName = APP.lastFragment.getClass().getSimpleName();
        updateTitleBar();
    };

    /**
     * 当Fragment不可见时
     */
    protected void onHidden(){

    };

    /**
     * 更改标题内容
     */
    protected void updateTitleBar(){};

    /**
     * Fragment销毁时
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}

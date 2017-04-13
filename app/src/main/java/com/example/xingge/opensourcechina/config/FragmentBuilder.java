package com.example.xingge.opensourcechina.config;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.base.BaseFragment;

/**
 * Created by xingge on 2017/4/12.
 */

public class FragmentBuilder {

    private static FragmentBuilder fragmentBuilder;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BaseFragment lastFragment;
    private BaseFragment fragment;
    private boolean isBack = false;
    private FragmentBuilder(){
        init();
    }

    public synchronized static FragmentBuilder getInstance(){

        if(fragmentBuilder == null)
            fragmentBuilder = new FragmentBuilder();
        return fragmentBuilder;
    }


    private void init(){
        fragmentManager = APP.activity.getSupportFragmentManager();
    }

    public FragmentBuilder start(Class<? extends BaseFragment> fragmentClass){
        this.isBack = false;
        transaction = fragmentManager.beginTransaction();
        //使用Fragment类名做Tag
        String simpleName = fragmentClass.getSimpleName();
        //根据tag查找fragment 如果能查找到就代表该Fragment已经实例化了，否则去动态实例化

        fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

        //判断Fragment是否为null，为null就自动创建Fragment对象
        if(fragment == null) {
            try {
                //Java的动态代理  动态创建Fragment对象
                fragment = fragmentClass.newInstance();

                transaction.add(R.id.contentGroup,fragment, simpleName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //隐藏上一个fragment
        if(lastFragment != null) {
            transaction.hide(lastFragment);
        }

        //已经添加就调用show方法显示
        transaction.show(fragment);

        return this;
    }

    public FragmentBuilder params(Bundle params){
        //传递参数
        if(params != null)
            fragment.setParams(params);
        return this;
    }

    public FragmentBuilder isBack(boolean isBack){
        this.isBack = isBack;
        if (isBack)
            transaction.addToBackStack(null);
        return this;
    }

    public BaseFragment build(){
        if (!isBack)
            lastFragment = fragment;
        transaction.commit();
        return fragment;
    }

    public void setLastFragment(BaseFragment lastFragment) {
        this.lastFragment = lastFragment;
    }
}

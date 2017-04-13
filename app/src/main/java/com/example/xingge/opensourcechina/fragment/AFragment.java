package com.example.xingge.opensourcechina.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.config.FragmentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by xingge on 2017/4/13.
 */

public class AFragment extends BaseFragment {
    @BindView(R.id.mBtn2)
    Button mBtn2;

    @Override
    protected int layoutId() {
        return R.layout.a_fragment;
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


    @OnClick(R.id.mBtn2)
    public void onViewClicked() {
        FragmentBuilder.getInstance().start(BFragment.class).build();
    }
}

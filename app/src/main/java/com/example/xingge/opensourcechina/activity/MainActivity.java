package com.example.xingge.opensourcechina.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.base.BaseActivity;
import com.example.xingge.opensourcechina.config.FragmentBuilder;
import com.example.xingge.opensourcechina.fragment.NewsFragment;
import com.example.xingge.opensourcechina.fragment.TweetFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.contentGroup)
    FrameLayout contentGroup;
    @BindView(R.id.zongHeBtn)
    RadioButton zongHeBtn;
    @BindView(R.id.dongTanBtn)
    RadioButton dongTanBtn;
    @BindView(R.id.addBtn)
    ImageView addBtn;
    @BindView(R.id.discoverBtn)
    RadioButton discoverBtn;
    @BindView(R.id.mineBtn)
    RadioButton mineBtn;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        FragmentBuilder.getInstance().start(NewsFragment.class).build();
    }

    public TextView getTitleTv() {
        return titleTv;
    }

    @OnClick({R.id.zongHeBtn, R.id.dongTanBtn, R.id.addBtn, R.id.discoverBtn, R.id.mineBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zongHeBtn:
                FragmentBuilder.getInstance().start(NewsFragment.class).build();
                break;
            case R.id.dongTanBtn:
                FragmentBuilder.getInstance().start(TweetFragment.class).build();
                break;
            case R.id.addBtn:
                break;
            case R.id.discoverBtn:
                break;
            case R.id.mineBtn:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());//获取PID
        System.exit(0);
    }
}

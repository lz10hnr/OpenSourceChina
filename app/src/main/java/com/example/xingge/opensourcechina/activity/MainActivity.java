package com.example.xingge.opensourcechina.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.base.BaseActivity;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.config.FragmentBuilder;
import com.example.xingge.opensourcechina.fragment.DiscoverFragment;
import com.example.xingge.opensourcechina.fragment.MineFragment;
import com.example.xingge.opensourcechina.fragment.NewsFragment;
import com.example.xingge.opensourcechina.fragment.SearchFragment;
import com.example.xingge.opensourcechina.fragment.TweetFragment;
import com.example.xingge.opensourcechina.util.ChannelUtil;
import com.example.xingge.opensourcechina.util.Utils;

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
    @BindView(R.id.searchImg)
    ImageView searchImg;
    @BindView(R.id.topGroup)
    RelativeLayout topGroup;
    @BindView(R.id.bottomGroup)
    RadioGroup bottomGroup;


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

    private String uploadType(){
        //上传渠道信息  友盟统计
        return ChannelUtil.getChannel(this);
    }

    @Override
    protected void loadData() {
        checkPermission();
        uploadType();
        FragmentBuilder.getInstance().start(NewsFragment.class).build();
    }

    private void checkPermission(){
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"授权成功",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"拒绝授权",Toast.LENGTH_LONG).show();
        }
    }


    public TextView getTitleTv() {
        return titleTv;
    }

    @OnClick({R.id.zongHeBtn, R.id.dongTanBtn, R.id.addBtn, R.id.discoverBtn, R.id.mineBtn, R.id.searchImg})
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
                FragmentBuilder.getInstance().start(DiscoverFragment.class).build();
                break;
            case R.id.mineBtn:
                FragmentBuilder.getInstance().start(MineFragment.class).build();
                break;
            case R.id.searchImg:
                FragmentBuilder.getInstance().start(SearchFragment.class).build();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.exit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(
                getSupportFragmentManager().getBackStackEntryCount() - 1);
        String simpleName = entry.getName();
        if ("NewsFragment".equals(simpleName) || "TweetFragment".equals(simpleName)
                || "DiscoverFragment".equals(simpleName)) {
            finish();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStackImmediate();
                String name = getSupportFragmentManager().getBackStackEntryAt(
                        getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
                APP.lastFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(name);

            }

        }
    }

    public View getTopGroup() {
        return topGroup;
    }

    public View getBottomGroup() {
        return bottomGroup;
    }
}

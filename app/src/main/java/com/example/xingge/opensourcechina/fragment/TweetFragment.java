package com.example.xingge.opensourcechina.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.activity.MainActivity;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.config.FragmentBuilder;
import com.example.xingge.opensourcechina.util.Utils;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by xingge on 2017/4/13.
 */

public class TweetFragment extends BaseFragment {
    @BindView(R.id.mBtn)
    Button mBtn;
    @BindView(R.id.mScircleImg)
    ImageView mScircleImg;

    @Override
    protected int layoutId() {
        return R.layout.tweet_fragment;
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

        try {
            InputStream is = getActivity().getAssets().open("a.png");
            mScircleImg.setImageBitmap(Utils.getCircleImg(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void updateTitleBar() {
        if (APP.activity instanceof MainActivity)
            ((MainActivity) APP.activity).getTitleTv().setText("动弹");
    }

    @OnClick(R.id.mBtn)
    public void onViewClicked() {
        FragmentBuilder.getInstance().start(AFragment.class).build();
    }
}

package com.example.xingge.opensourcechina.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.adapter.BannerPagerAdapter;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.model.enitity.PandaBean;
import com.example.xingge.opensourcechina.model.http.HttpFactory;
import com.example.xingge.opensourcechina.model.http.biz.DisconverModelImpl;
import com.example.xingge.opensourcechina.model.http.biz.IDiscoverModel;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xingge on 2017/4/13.
 */

public class DiscoverFragment extends BaseFragment {
    @BindView(R.id.bannerViewPager)
    ViewPager bannerViewPager;
    private IDiscoverModel discoverModel;
    private List<ImageView> imageViews;
    private BannerPagerAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.discover_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

        discoverModel = new DisconverModelImpl();
        imageViews = new ArrayList<>();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        discoverModel.loadBanner(new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {

                showBanner(xmlData);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }


    private void showBanner(String xmlData){
        Gson gson = new Gson();
        PandaBean pandaBean = gson.fromJson(xmlData, PandaBean.class);

        List<PandaBean.DataBean.BigImgBean> bigImg = pandaBean.getData().getBigImg();
        for (int i = 0; i < bigImg.size(); i++) {
            String imageUrl = bigImg.get(i).getImage();
            ImageView imageView = new ImageView(getActivity());
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            HttpFactory.create().loadImage(imageUrl,imageView);
            imageViews.add(imageView);
        }
        adapter = new BannerPagerAdapter(imageViews);
        bannerViewPager.setAdapter(adapter);
    }

}

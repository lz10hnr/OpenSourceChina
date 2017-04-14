package com.example.xingge.opensourcechina.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
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

/**
 * Created by xingge on 2017/4/13.
 */

public class DiscoverFragment extends BaseFragment {
    @BindView(R.id.bannerViewPager)
    ViewPager bannerViewPager;
    private IDiscoverModel discoverModel;
    private List<ImageView> imageViews;
    private BannerPagerAdapter adapter;
    private int currentNum = 1000000;
    private int updateWhat = 100;
    private int stopWhat = 200;
    private long updateTime = 3000;

    @Override
    protected int layoutId() {
        return R.layout.discover_fragment;
    }

    @Override
    protected void initView(View view) {

        bannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentNum = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onHidden() {
        super.onHidden();
        handler.sendEmptyMessage(stopWhat);
    }

    @Override
    protected void onShow() {
        super.onShow();
        handler.sendEmptyMessageDelayed(updateWhat,updateTime);
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

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == updateWhat) {
                bannerViewPager.setCurrentItem(currentNum++);
                handler.sendEmptyMessageDelayed(updateWhat,updateTime);
            }else if (msg.what == stopWhat) {
                //暂停
                handler.removeMessages(updateWhat);
            }
        }
    };


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
        bannerViewPager.setCurrentItem(currentNum++);

        handler.sendEmptyMessageDelayed(updateWhat,updateTime);
    }

}

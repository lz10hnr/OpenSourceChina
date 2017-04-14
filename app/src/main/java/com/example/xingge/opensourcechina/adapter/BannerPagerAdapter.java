package com.example.xingge.opensourcechina.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by xingge on 2017/4/13.
 */

public class BannerPagerAdapter extends PagerAdapter {

    private List<ImageView> imgs;
    public BannerPagerAdapter(List<ImageView> imgs){
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        if (imgs == null)
            return 0;
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 销毁view时调用
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//        container.removeView(imgs.get(position % imgs.size()));
    }

    /**
     * 装载view时调用
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imgs.get(position % imgs.size());
        if(imageView.getParent() != null)
            ((ViewGroup)imageView.getParent()).removeView(imageView);
        container.addView(imageView);
        return imageView;
    }
}

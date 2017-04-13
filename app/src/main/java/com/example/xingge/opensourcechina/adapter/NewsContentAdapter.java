package com.example.xingge.opensourcechina.adapter;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.model.enitity.NewsListBean;

import java.util.List;

/**
 * Created by xingge on 2017/4/12.
 */

public class NewsContentAdapter extends BaseAdapter<NewsListBean.NewsBean> {

    public NewsContentAdapter(Context context, List<NewsListBean.NewsBean> datas) {
        super(context, R.layout.newscontent_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, NewsListBean.NewsBean newsBean) {

        holder.setText(R.id.contentTitleTv,newsBean.getTitle());

    }
}

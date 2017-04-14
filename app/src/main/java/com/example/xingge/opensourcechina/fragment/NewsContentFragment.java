package com.example.xingge.opensourcechina.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.adapter.NewsContentAdapter;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.config.XmlParserBuilder;
import com.example.xingge.opensourcechina.model.enitity.NewsListBean;
import com.example.xingge.opensourcechina.model.http.biz.INewsModel;
import com.example.xingge.opensourcechina.model.http.biz.NewsModelImpl;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xingge on 2017/4/12.
 */

public class NewsContentFragment extends BaseFragment {
    @BindView(R.id.newsContentRecyclerView)
    PullToRefreshRecyclerView newsContentRecyclerView;

    private NewsContentAdapter adapter;
    private List<NewsListBean.NewsBean> newslist;
    private INewsModel newsModel;
    private NewsListBean newsListBean;

    @Override
    protected int layoutId() {
        return R.layout.newscontent_fragment;
    }

    @Override
    protected void initView(View view) {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        newsContentRecyclerView.setLayoutManager(llm);


    }

    @Override
    protected void initData() {
        newsModel = new NewsModelImpl();
        newslist = new ArrayList<>();
        adapter = new NewsContentAdapter(getActivity(),newslist);
        newsContentRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        newsModel.newsList("0", new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {
                newsListBean = (NewsListBean) XmlParserBuilder.getInstance()
                        .alias("oschina",NewsListBean.class)
                        .alias("news",NewsListBean.NewsBean.class)
                        .build(xmlData);
                newslist.addAll(newsListBean.getNewslist());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }

    @Override
    protected void onShow() {

    }

    @Override
    protected void onHidden() {

    }


}

package com.example.xingge.opensourcechina.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.adapter.SearchResultAdapter;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.config.Urls;
import com.example.xingge.opensourcechina.config.XmlParserBuilder;
import com.example.xingge.opensourcechina.model.enitity.SearchResultBean;
import com.example.xingge.opensourcechina.model.http.biz.INewsModel;
import com.example.xingge.opensourcechina.model.http.biz.NewsModelImpl;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xingge on 2017/4/18.
 */

public class SearchResultContentFragment extends BaseFragment {
    private INewsModel newsModel;
    private int pageIndex = 0;
    @BindView(R.id.searchResultRecyclerView)
    PullToRefreshRecyclerView searchResultRecyclerView;
    private List<SearchResultBean.ResultBean> datas;
    private SearchResultAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.search_result_content_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

        datas = new ArrayList<>();
        adapter = new SearchResultAdapter(APP.activity,datas);
        LinearLayoutManager manager = new LinearLayoutManager(APP.activity);
        searchResultRecyclerView.setLayoutManager(manager);
        searchResultRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("SearchResultContentFrag", "onResume is running....");
    }
    @Override
    protected void loadData() {

        search();
    }

    private void search(){


        String content = bundle.getString("content");
        String catalog = bundle.getString("catalog");

        Log.d("SearchResultContentFrag", "-------"+content+"------");
        Log.d("SearchResultContentFrag", "-------"+catalog+"------");
        if(newsModel == null)
            newsModel = new NewsModelImpl();
        newsModel.search(catalog, content, pageIndex, new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {
                Log.d("SearchResultContentFrag", xmlData);
                SearchResultBean searchResultBean = (SearchResultBean) XmlParserBuilder.getInstance()
                        .alias("oschina", SearchResultBean.class)
                        .alias("result",SearchResultBean.ResultBean.class).build(xmlData);

                datas.addAll(searchResultBean.getResults());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

}

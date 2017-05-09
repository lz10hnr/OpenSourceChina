package com.example.xingge.opensourcechina.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.activity.MainActivity;
import com.example.xingge.opensourcechina.adapter.TweenAdapter;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.config.XmlParserBuilder;
import com.example.xingge.opensourcechina.model.enitity.TweetList;
import com.example.xingge.opensourcechina.model.http.biz.ITweetModel;
import com.example.xingge.opensourcechina.model.http.biz.TweetModelImpl;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xingge on 2017/4/13.
 */

public class TweetFragment extends BaseFragment {

    @BindView(R.id.tweenRecyclerView)
    RecyclerView tweenRecyclerView;
    private List<TweetList.TweetBean> datas;
    private TweenAdapter adapter;

    private ITweetModel tweetModel;

    @Override
    protected int layoutId() {
        return R.layout.tweet_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

        tweetModel = new TweetModelImpl();
        datas = new ArrayList<>();

        adapter = new TweenAdapter(getActivity(),datas);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        tweenRecyclerView.setLayoutManager(manager);
        tweenRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        tweetModel.newTweentList("0", "0", new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {
                Log.d("TweetFragment", xmlData);
                TweetList tweetList = (TweetList) XmlParserBuilder.getInstance()
                        .alias("oschina",TweetList.class)
                        .alias("tweet",TweetList.TweetBean.class)
                        .alias("user",TweetList.TweetBean.UserBean.class)
                        .build(xmlData);
                datas.addAll(tweetList.getTweets());
                adapter.init(datas);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }

    @Override
    protected void updateTitleBar() {
        super.updateTitleBar();
        if (APP.activity instanceof MainActivity)
            ((MainActivity) APP.activity).getTitleTv().setText("动弹");
    }

}

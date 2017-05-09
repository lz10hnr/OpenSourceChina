package com.example.xingge.opensourcechina.model.http.biz;

import com.example.xingge.opensourcechina.config.Urls;
import com.example.xingge.opensourcechina.model.http.HttpFactory;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingge on 2017/4/24.
 */

public class TweetModelImpl implements ITweetModel {

    @Override
    public void newTweentList(String uid, String pageIndex, NetWorkCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.PAGESIZE);
        HttpFactory.create().post(Urls.TWEEN,params,callBack);
    }

    @Override
    public void addLike(String tweetid, String uid, String ownerOfTweet, NetWorkCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("tweetid",tweetid);
        params.put("ownerOfTweet", ownerOfTweet);
        HttpFactory.create().post(Urls.TWEEN_ADDLIKE,params,callBack);
    }
}

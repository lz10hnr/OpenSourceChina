package com.example.xingge.opensourcechina.model.http.biz;

import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

/**
 * Created by xingge on 2017/4/12.
 */

public interface ITweetModel {

    void newTweentList(String uid, String pageIndex, NetWorkCallBack callBack);
    void addLike(String tweetid,String uid,String ownerOfTweet,NetWorkCallBack callBack);
}

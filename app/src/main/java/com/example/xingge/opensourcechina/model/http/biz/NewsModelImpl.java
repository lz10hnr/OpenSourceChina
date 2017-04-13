package com.example.xingge.opensourcechina.model.http.biz;

import com.example.xingge.opensourcechina.config.Urls;
import com.example.xingge.opensourcechina.model.http.HttpFactory;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingge on 2017/4/12.
 */

public class NewsModelImpl implements INewsModel {

    @Override
    public void newsList(String pageIndex, NetWorkCallBack callBack) {

        Map<String,String> params = new HashMap<>();
        params.put("catalog", Urls.NEWSTYPE);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.PAGESIZE);

        HttpFactory.create().get(Urls.NEWSLIST,params,callBack);
    }
}

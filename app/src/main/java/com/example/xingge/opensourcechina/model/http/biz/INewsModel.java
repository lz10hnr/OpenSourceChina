package com.example.xingge.opensourcechina.model.http.biz;

import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by xingge on 2017/4/12.
 */

public interface INewsModel {

    void newsList(String pageIndex, NetWorkCallBack callBack);

}

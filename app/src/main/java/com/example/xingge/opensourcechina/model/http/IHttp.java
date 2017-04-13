package com.example.xingge.opensourcechina.model.http;


import android.widget.ImageView;

import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by xingge on 2017/4/12.
 */

public interface IHttp {


    void post(String url, Map<String,String> params, NetWorkCallBack callBack);

    void get(String url, Map<String,String> params, NetWorkCallBack callBack);

    void uploadFile();

    void download();

    void loadImage(String imgUrl, ImageView imageView);


}

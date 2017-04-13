package com.example.xingge.opensourcechina.model.http.biz;

import com.example.xingge.opensourcechina.config.Urls;
import com.example.xingge.opensourcechina.model.http.HttpFactory;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

/**
 * Created by xingge on 2017/4/13.
 */

public class DisconverModelImpl implements IDiscoverModel {

    @Override
    public void loadBanner(NetWorkCallBack callBack) {
        HttpFactory.create().get(Urls.PANDABANNER,null,callBack);
    }
}

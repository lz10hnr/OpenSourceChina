package com.example.xingge.opensourcechina.model.http.biz;

import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

/**
 * Created by xingge on 2017/4/12.
 */

public interface IMineModel {
    void login(String username, String pwd, String keep_login, NetWorkCallBack callBack);
}

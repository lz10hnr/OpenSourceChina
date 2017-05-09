package com.example.xingge.opensourcechina.model.http.biz;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.config.Urls;
import com.example.xingge.opensourcechina.model.http.HttpFactory;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xingge on 2017/4/21.
 */

public class MineModelImpl implements IMineModel {

    @Override
    public void login(String username, String pwd, String keep_login, final NetWorkCallBack callBack) {

        Map<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("pwd",pwd);
        params.put("keep_login",keep_login);

        HttpFactory.create().post(Urls.LOGIN,params,callBack);
    }

}

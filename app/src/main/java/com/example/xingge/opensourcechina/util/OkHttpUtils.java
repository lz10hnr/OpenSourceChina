package com.example.xingge.opensourcechina.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xingge.opensourcechina.APP;
import com.example.xingge.opensourcechina.model.http.IHttp;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xingge on 2017/4/12.
 */

public class OkHttpUtils implements IHttp{



    //请求成功的状态码
    private static final int SUCCESSCODE = 200;
    //请求失败的状态码
    private static final int ERRORCODE = 100;

    /**
     * 抽取单例的OKHttpClient对象
     * 第一步 构造函数私有化
     * 第二步 提供一个共有的、静态的方法 该方法的返回值BaseOkHttp
     * 第三步 提供私有的静态的BaseOkHttp的对象
     */


    //保证OkHttpClient对象是单例的
    private OkHttpClient okHttpClient;

    private static OkHttpUtils okHttpUtils = null;
    private OkHttpUtils(){
        okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = new ArrayList<Cookie>();
//                SharedPreferences preferences = APP.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
//                String cookie = preferences.getString("cookie", "");
//                if(!TextUtils.isEmpty(cookie)){
//                    Cookie c = new Cookie.Builder().name("Cookie").value(cookie).build();
//                    cookies.add(c);
//                }
                return cookies;
            }
        }).build();
    }

    public synchronized static OkHttpUtils getInstance(){
        if(okHttpUtils == null) {
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }


    @Override
    public  void post(String url, Map<String, String> params, final NetWorkCallBack callBack) {

        FormBody.Builder builder = null;
        if(params != null && params.size() > 0) {
            builder = new FormBody.Builder();

            Set<String> keys = params.keySet();

            for (String key : keys) {
                builder.add(key,params.get(key));
            }

        }

        Request request = new Request.Builder().addHeader("Cookie",getCooike()).url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            /**
             * 失败的监听
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call call, final IOException e) {

                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (e != null && e.getMessage() != null){
                            callBack.onError(e.getMessage());
                        }
                    }
                });

            }

            /**
             * 成功的监听
             * @param call
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();

                //当用户登录时才保存cookie
                if(response.request().url().toString().endsWith("login_validate")){
                    saveCookie(response);
                }

                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        callBack.onSuccess(result);

                    }
                });

            }
        });

    }


    @Override
    public void get(String url, Map<String, String> params, final NetWorkCallBack callBack) {


        if(params != null && params.size() > 0) {
            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            Set<String> keys = params.keySet();

            for (String key : keys) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }

            url = sb.toString().substring(0, sb.length() - 1);
        }

        Request request = new Request.Builder().addHeader("Cookie",getCooike()).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (e != null && e.getMessage() != null)
                            callBack.onError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                String cookie = "";
                Headers headers = response.headers();
                for (String key : headers.names()) {
                    List<String> values = headers.values(key);
                    if (key.contains("Set-Cookie"))
                        cookie += values + ";";
                }

                if (cookie.length() > 0) {
                    cookie = cookie.substring(0, cookie.length() - 1);
                }

                final String result = response.body().string();

                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(result);
                    }
                });

            }
        });


    }

    @Override
    public void uploadFile() {

    }

    @Override
    public void download() {

    }

    @Override
    public void loadImage(String imgUrl, ImageView imageView) {

        Glide.with(APP.activity).load(imgUrl).into(imageView);

    }


    /**
     * 1、获取服务器返回响应头信息
     * 2、遍历响应头集合
     * 3、取出请求头中携带的cookie信息
     * @param response
     */
    private void saveCookie(Response response){
        String cookie = "";
        Headers headers = response.headers();
        Set<String> names = headers.names();
        for (String name : names) {
            String value = headers.get(name);
            if (name.contains("Set-Cookie"))
                cookie += value + ";";
        }
        if (cookie.length() > 0) {
            cookie = cookie.substring(0, cookie.length() - 1);
        }

        Log.d("MainActivity", cookie);
        SharedPreferences preferences = APP.activity.getSharedPreferences("data", APP.activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("cookie",cookie);
        edit.commit();

    }

    /**
     * 从sharedPreferences中取出登陆成功后保存的cookie
     * @return
     */
    private String getCooike(){
        String cookie = "";
        SharedPreferences preferences = APP.activity.getSharedPreferences("data", APP.activity.MODE_PRIVATE);
        cookie = preferences.getString("cookie","");
        return cookie;
    }

}

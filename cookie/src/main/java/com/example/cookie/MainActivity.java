package com.example.cookie;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.util.HashMap;
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

public class MainActivity extends AppCompatActivity {


    private User user;
    private String type = "baidu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadType(type);
    }

    /**
     * 上传渠道信息
     * @param type
     */
    private void uploadType(String type) {

    }


    public void login(View view){
        String url = "http://www.oschina.net/action/api/login_validate";
        Map<String,String> params = new HashMap<>();
        params.put("username","18515410347");
        params.put("pwd","qwert12345++..");
        params.put("keep_login","1");
        post(url,params);
    }

    public void getUserInfo(View view){
        String url = "http://www.oschina.net/action/api/my_information?uid="+user.getUser().getUid();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //从sharedPreferences获取cookie信息
        String cookie = getCooike();
        Request request = new Request.Builder().addHeader("Cookie",cookie).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("MainActivity", response.body().string());
            }
        });
    }

    public void post(String url, Map<String, String> params) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody.Builder builder = null;
        if(params != null && params.size() > 0) {
            builder = new FormBody.Builder();

            Set<String> keys = params.keySet();

            for (String key : keys) {
                builder.add(key,params.get(key));
            }

        }

        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                XStream xStream = new XStream();
                xStream.alias("oschina",User.class);
                user = (User) xStream.fromXML(response.body().string());
                saveCookie(response);
            }


        });

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
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
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
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        cookie = preferences.getString("cookie","");
        return cookie;
    }


}

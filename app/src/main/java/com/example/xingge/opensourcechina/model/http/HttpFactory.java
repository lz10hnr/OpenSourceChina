package com.example.xingge.opensourcechina.model.http;

import com.example.xingge.opensourcechina.util.OkHttpUtils;

/**
 * Created by xingge on 2017/4/12.
 */

public class HttpFactory {

    private static final int OKHTTP = 0;
    private static final int VOLLEY = 1;
    private static final int RETROFIT = 2;
    private static final int TYPE = OKHTTP;

    public static IHttp create(){
        IHttp http = null;
        switch (TYPE){
            case OKHTTP:
                http = OkHttpUtils.getInstance();
                break;
            case VOLLEY:
                break;
            case RETROFIT:
                break;

        }
        return http;
    }


}

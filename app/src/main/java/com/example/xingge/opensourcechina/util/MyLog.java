package com.example.xingge.opensourcechina.util;

import android.util.Log;

import com.example.xingge.opensourcechina.BuildConfig;

/**
 * Created by xingge on 2017/4/22.
 */

public class MyLog {

    public static final boolean debug = false;
    public static void i(String tag,String msg){
        if(BuildConfig.DEBUG)
            Log.i(tag,msg);
    }
}

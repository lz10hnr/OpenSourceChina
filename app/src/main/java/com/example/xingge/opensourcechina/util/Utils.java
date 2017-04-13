package com.example.xingge.opensourcechina.util;

/**
 * Created by xingge on 2017/4/14.
 */

public class Utils {

    public static final void exit(){
        android.os.Process.killProcess(android.os.Process.myPid());//获取PID
        System.exit(0);
    }
}

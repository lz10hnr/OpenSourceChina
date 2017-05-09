package com.example.xingge.opensourcechina.test;

import android.os.AsyncTask;

/**
 * Created by xingge on 2017/4/18.
 */

public class MyTask extends AsyncTask<String,Integer,Long> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //初始化下载文件的保存目录
    }

    @Override
    protected Long doInBackground(String... params) {
        //执行下载操作
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //更新进度显示
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Long aLong) {
        //下载完成的结果  关闭进度条
        super.onPostExecute(aLong);
    }
}

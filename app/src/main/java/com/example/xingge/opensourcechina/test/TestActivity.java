package com.example.xingge.opensourcechina.test;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xingge.opensourcechina.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        MyTask task = new MyTask();
        task.execute("");
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");

    }
}

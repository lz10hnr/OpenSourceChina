package com.example.cookie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewActivity extends AppCompatActivity {


    private ListView mListView;
    private ScrollViewAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        init();
    }

    private void init(){
        mListView = (ListView) findViewById(R.id.mListView);
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            datas.add("09G"+i);
        }
        adapter = new ScrollViewAdapter(this,datas);
        mListView.setAdapter(adapter);
    }
}

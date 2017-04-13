package com.example.xingge.opensourcechina.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.adapter.GridViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabGridViewActivity extends AppCompatActivity {


    @BindView(R.id.topGridView)
    GridView topGridView;
    @BindView(R.id.bottomGridView)
    GridView bottomGridView;

    private List<String> topDatas;
    private List<String> bottomDatas;

    private GridViewAdapter topAdapter;
    private GridViewAdapter bottomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_grid_view);
        ButterKnife.bind(this);
        init();
        initListener();
    }

    private void init(){
        topDatas = new ArrayList<>();
        bottomDatas = new ArrayList<>();

        topDatas.add("直播");
        topDatas.add("熊猫文化");
        topDatas.add("熊猫观察");
        topDatas.add("精彩一刻");
        topDatas.add("百科全说");
        topDatas.add("瞧你那熊样");
        topDatas.add("熊猫那些事");

        bottomDatas.addAll(topDatas);
        bottomDatas.add("河南");
        bottomDatas.add("河北");
        bottomDatas.add("山西");
        bottomDatas.add("江苏");
        bottomDatas.add("上海");
        bottomDatas.add("北京");
        bottomDatas.add("新疆");

        topAdapter = new GridViewAdapter(this,topDatas,handler);
        bottomAdapter = new GridViewAdapter(this,bottomDatas,handler);

        topGridView.setAdapter(topAdapter);
        bottomGridView.setAdapter(bottomAdapter);

    }

    private void initListener(){
        topGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        bottomGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                topDatas.add(bottomDatas.get(position));
                topAdapter.notifyDataSetChanged();
                bottomDatas.remove(position);
                bottomAdapter.notifyDataSetChanged();


            }
        });
    }

    public void editItem(View view){

        topAdapter.setFlag(true);
        topAdapter.notifyDataSetChanged();
    }

    public List<String> getTopDatas() {
        return topDatas;
    }

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100){
                int index = (int) msg.obj;
                bottomDatas.add(topDatas.get(index));
                bottomAdapter.notifyDataSetChanged();
                topDatas.remove(index);
                topAdapter.notifyDataSetChanged();
            }
        }
    };
}

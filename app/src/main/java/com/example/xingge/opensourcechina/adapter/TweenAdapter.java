package com.example.xingge.opensourcechina.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.model.enitity.TweetList;
import com.example.xingge.opensourcechina.model.http.biz.ITweetModel;
import com.example.xingge.opensourcechina.model.http.biz.TweetModelImpl;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xingge on 2017/4/24.
 */

public class TweenAdapter extends BaseAdapter<TweetList.TweetBean> {

    private Map<Integer,Boolean> checkedStates;//记录每一行checkbox真实的状态
    private ITweetModel tweetModel;

    public TweenAdapter(Context context, List<TweetList.TweetBean> datas) {
        super(context, R.layout.newtweet_item, datas);
        tweetModel = new TweetModelImpl();
        this.checkedStates = new HashMap<>();

    }

    public void init(List<TweetList.TweetBean> datas){
        checkedStates.clear();
        for (int i = 0; i < datas.size(); i++) {
            if(datas.get(i).getIsLike().equals("1"))
                checkedStates.put(i,true);
            else
                checkedStates.put(i,false);
        }
    }

    @Override
    public void convert(final ViewHolder holder, final TweetList.TweetBean tweetBean) {

        holder.setText(R.id.Tweet_Item_Title,tweetBean.getAuthor());
        holder.setText(R.id.Tweet_Item_Count,tweetBean.getLikeCount());

        final CheckBox checkBox = holder.getView(R.id.Tweet_Item_Zan);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                boolean isChecked = checkedStates.get(position);
                checkedStates.put(position,!isChecked);
                checkBox.setChecked(!isChecked);

                if(isChecked) {
                    //取消赞

                }else {
                    //发送点赞的网络请求
                    tweetModel.addLike(tweetBean.getId(), "3415251", tweetBean.getAuthorid(), new NetWorkCallBack() {
                        @Override
                        public void onSuccess(String xmlData) {
                            Log.d("TweenAdapter", xmlData);
                        }

                        @Override
                        public void onError(String errorMsg) {

                        }
                    });
                }

            }
        });
        int position = holder.getAdapterPosition();
        if(checkedStates.size() > 0) {
            boolean isChecked = checkedStates.get(position);
            checkBox.setChecked(isChecked);
        }


    }
}

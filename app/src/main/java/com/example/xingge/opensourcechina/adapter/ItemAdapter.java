package com.example.xingge.opensourcechina.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.model.enitity.PandaBean;

import java.util.List;

/**
 * Created by xingge on 2017/4/17.
 */

public class ItemAdapter extends BaseAdapter {

    private static final int ITEMCOUNT = 3;
    private static final int ITEMTYPE1 = 0;//精彩推荐
    private static final int ITEMTYPE2 = 1;//长城致直播
    private static final int ITEMTYPE3 = 2;//熊猫观察


    private List<Object> datas;
    private LayoutInflater inflater;
    public ItemAdapter(Context context, List<Object> datas){

        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = datas.get(position);
        if(obj instanceof PandaBean.DataBean.AreaBean)
            return ITEMTYPE1;
        else if(obj instanceof PandaBean.DataBean.WallliveBean)
            return ITEMTYPE2;
        else if (obj instanceof PandaBean.DataBean.PandaeyeBean)
            return ITEMTYPE3;
        return 0;
    }

    //总共加载几种不同的item
    @Override
    public int getViewTypeCount() {
        return ITEMCOUNT;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);
        switch (type){
            case ITEMTYPE1:
                inflater.inflate(R.layout.news_fragment,null);
                break;
            case ITEMTYPE2:
                break;
            case ITEMTYPE3:
                break;
        }
        return null;
    }
}

package com.example.cookie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xingge on 2017/4/25.
 */

public class ScrollViewAdapter extends BaseAdapter {

    private List<String> datas;
    private LayoutInflater inflater;
    public ScrollViewAdapter(Context context, List<String> datas){
        this.inflater = LayoutInflater.from(context);
        this.datas = datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.scrollview_item,null);
        TextView t = (TextView) convertView.findViewById(R.id.titleTv);
        t.setText(datas.get(position));
        return convertView;
    }
}

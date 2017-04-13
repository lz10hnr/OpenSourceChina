package com.example.xingge.opensourcechina.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xingge.opensourcechina.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xingge on 2017/4/11.
 */

public class GridViewAdapter extends BaseAdapter {

    private List<String> datas;
    private LayoutInflater inflater;

    private boolean flag = false;
    private Context context;
    private Handler handler;

    public GridViewAdapter(Context context, List<String> datas,Handler handler) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
        this.handler = handler;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    @Override
    public int getCount() {
        if (datas == null)
            return 0;
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

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            viewHolder.detletBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (int) viewHolder.detletBtn.getTag();
                    handler.obtainMessage(100,index).sendToTarget();
                }
            });
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.detletBtn.setTag(position);
        viewHolder.itemContent.setText(datas.get(position));

        if (flag)
            viewHolder.detletBtn.setVisibility(View.VISIBLE);
        else
            viewHolder.detletBtn.setVisibility(View.GONE);


        return convertView;
    }


    public class ViewHolder {
        @BindView(R.id.itemContent)
        TextView itemContent;
        @BindView(R.id.deleteBtn)
        TextView detletBtn;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.example.xingge.opensourcechina.adapter;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.model.enitity.SearchResultBean;

import java.util.List;

/**
 * Created by xingge on 2017/4/18.
 */

public class SearchResultAdapter extends BaseAdapter<SearchResultBean.ResultBean> {

    public SearchResultAdapter(Context context, List<SearchResultBean.ResultBean> datas) {
        super(context, R.layout.searach_result_recycler_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, SearchResultBean.ResultBean resultBean) {

        holder.setText(R.id.searchResultTitle,resultBean.getTitle());
        holder.setText(R.id.searchResultContent,resultBean.getDescription());
    }
}

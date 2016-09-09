package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.viewholder.NewsViewHolder;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;


/**
 * Created by xushuzhan on 2016/7/26.
 */
public class NewsAdapter extends RecyclerArrayAdapter<NewsListBeen.ResultBean.DataBean> {
    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent, R.layout.item_news_list);
    }

}

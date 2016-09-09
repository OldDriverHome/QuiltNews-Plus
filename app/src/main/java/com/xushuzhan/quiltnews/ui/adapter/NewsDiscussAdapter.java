package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.viewholder.NewsDiscussViewHolder;
import com.xushuzhan.quiltnews.modle.been.NewsDiscussBeen;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class NewsDiscussAdapter extends RecyclerArrayAdapter<NewsDiscussBeen> {
    public NewsDiscussAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsDiscussViewHolder(parent, R.layout.item_news_discuss);
    }
}

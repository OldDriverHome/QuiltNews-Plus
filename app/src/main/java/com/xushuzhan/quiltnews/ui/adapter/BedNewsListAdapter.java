package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.viewholder.BedNewsListViewHodler;
import com.xushuzhan.quiltnews.modle.been.BedNewsListBeen;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class BedNewsListAdapter extends RecyclerArrayAdapter<BedNewsListBeen.ItemBean> {
    public BedNewsListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BedNewsListViewHodler(parent, R.layout.item_bed_news_list);
    }
}

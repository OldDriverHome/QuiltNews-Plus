package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.MyCollectionBeen;
import com.xushuzhan.quiltnews.modle.been.MyDiscussBeen;
import com.xushuzhan.quiltnews.modle.viewholder.MyCollectionViewHolder;
import com.xushuzhan.quiltnews.modle.viewholder.MyDiscussViewHolder;

/**
 * Created by xushuzhan on 2016/8/23.
 */
public class MyCollectionAdapter extends RecyclerArrayAdapter<MyCollectionBeen> {
    public MyCollectionAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyCollectionViewHolder(parent, R.layout.item_my_collection);
    }
}
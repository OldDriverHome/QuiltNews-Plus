package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.viewholder.MyDiscussViewHolder;
import com.xushuzhan.quiltnews.modle.been.MyDiscussBeen;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class MyDiscussAdapter extends RecyclerArrayAdapter<MyDiscussBeen> {
    public MyDiscussAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyDiscussViewHolder(parent, R.layout.item_my_discuss);
    }
}

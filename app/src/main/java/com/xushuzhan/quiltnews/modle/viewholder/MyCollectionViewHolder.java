package com.xushuzhan.quiltnews.modle.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.MyCollectionBeen;
import com.xushuzhan.quiltnews.modle.been.MyDiscussBeen;

/**
 * Created by xushuzhan on 2016/8/23.
 */
public class MyCollectionViewHolder extends BaseViewHolder<MyCollectionBeen> {
    TextView userName;
    TextView collectionTime;
    TextView newsTitle;
    ImageView newsPic;

    public MyCollectionViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        userName=$(R.id.tv_my_collection_user_name);
        collectionTime=$(R.id.tv_my_collection_collection_time);
        newsTitle = $(R.id.tv_my_collection_news_title);
        newsPic = $(R.id.iv_my_collection_news_pic);
    }

    @Override
    public void setData(MyCollectionBeen data) {
        super.setData(data);

        userName.setText(data.getNickName());
        collectionTime.setText(data.getCollectionTime());
        newsTitle.setText(data.getNewsTitle());
        Glide.with(getContext())
                .load(data.getPicUrl())
                .into(newsPic);
    }
}

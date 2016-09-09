package com.xushuzhan.quiltnews.modle.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.NewsDiscussBeen;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class NewsDiscussViewHolder extends BaseViewHolder<NewsDiscussBeen> {
    TextView userName;
    TextView discussTime;
    TextView discussContent;
    public NewsDiscussViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        userName = $(R.id.tv_discuss_item_user_name);
        discussTime = $(R.id.tv_discuss_item_discuss_time);
        discussContent = $(R.id.tv_discuss_item_discuss_content);
    }

    @Override
    public void setData(NewsDiscussBeen data) {
        super.setData(data);

        userName.setText(data.getUserName());
        discussTime.setText(data.getDiscussTime());
        discussContent.setText(data.getDiscussContent());
    }
}

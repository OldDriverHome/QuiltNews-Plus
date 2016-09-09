package com.xushuzhan.quiltnews.modle.viewholder;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.MyDiscussBeen;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class MyDiscussViewHolder extends BaseViewHolder<MyDiscussBeen> {
    TextView userName;
    TextView discussTime;
    TextView discussContent;
    TextView newsTitle;
    ImageView newsPic;

    public MyDiscussViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);

        userName=$(R.id.tv_my_discuss_user_name);
        discussTime=$(R.id.tv_my_discuss_discuss_time);
        discussContent = $(R.id.tv_my_discuss_discuss_content);
        newsTitle = $(R.id.tv_my_discuss_news_title);
        newsPic = $(R.id.iv_my_discuss_news_pic);
    }

    @Override
    public void setData(MyDiscussBeen data) {
        super.setData(data);

        userName.setText(data.getUserName());
        discussTime.setText(data.getDiscussTime());
        discussContent.setText(data.getContent());
        newsTitle.setText(data.getNewsTitle());
        Glide.with(getContext())
                .load(data.getPicURL())
                .into(newsPic);
        Log.d("147258", "setData: "+data.getPicURL());

    }
}

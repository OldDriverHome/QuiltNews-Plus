package com.xushuzhan.quiltnews.modle.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;

/**
 * Created by xushuzhan on 2016/7/26.
 */
public class NewsViewHolder extends BaseViewHolder<NewsListBeen.ResultBean.DataBean> {
    public static final String TAG = "NewsViewHolder";
    TextView Title;
    TextView Resource;
    TextView time;
    ImageView Disscuss;
    ImageView NewsListSmallPic;

    public NewsViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        Title = $(R.id.tv_news_list_item_title);
        Resource = $(R.id.tv_news_list_item_resource);
        time = $(R.id.tv_news_list_time);
        NewsListSmallPic = $(R.id.iv_news_small_pic);
    }

    @Override
    public void setData(NewsListBeen.ResultBean.DataBean data) {
        super.setData(data);

    Title.setText(data.getTitle());
    Resource.setText(data.getAuthor_name());
    time.setText(data.getDate());
        if (NewsInfo.isShowPic) {
            Glide.with(getContext())
                    .load(data.getThumbnail_pic_s())
                    .placeholder(R.drawable.loading_s)
                    .error(R.drawable.error)
                    .into(NewsListSmallPic);
        }
    }
}

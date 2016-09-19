package com.xushuzhan.quiltnews.modle.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.BedNewsListBeen;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class BedNewsListViewHodler extends BaseViewHolder<BedNewsListBeen.ItemBean> {
    ImageView picture;
    TextView title;
    TextView resource;
    TextView time;

    public BedNewsListViewHodler(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        picture = $(R.id.iv_bed_news_list_pic);
        title = $(R.id.tv_news_list_item_title);
        resource = $(R.id.tv_bed_news_list_source);
        time = $(R.id.tv_bed_news_list_time);
    }

    @Override
    public void setData(BedNewsListBeen.ItemBean data) {
        super.setData(data);

        if (NewsInfo.isShowPic) {
            Glide.with(getContext())
                    .load(data.getThumbnail())
                    .placeholder(R.drawable.loading_s)
                    .error(R.drawable.bed_news_error)
                    .into(picture);
        }else {
            picture.setImageResource(R.drawable.loading_s);
        }
        title.setText(data.getTitle());
        resource.setText(data.getSource());
        time.setText(data.getUpdateTime());
    }
}

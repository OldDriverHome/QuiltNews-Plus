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
public class BedNewsListViewHodler extends BaseViewHolder<BedNewsListBeen.RetDataBean> {
    ImageView picture;
    TextView title;
    TextView content_small;

    public BedNewsListViewHodler(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        picture = $(R.id.iv_bed_news_list_pic);
        title = $(R.id.tv_news_list_item_title);
        content_small = $(R.id.tv_bed_news_list_content);
    }

    @Override
    public void setData(BedNewsListBeen.RetDataBean data) {
        super.setData(data);

        if (NewsInfo.isShowPic) {
            Glide.with(getContext())
                    .load(data.getImage_url())
                    .placeholder(R.drawable.loading_s)
                    .error(R.drawable.bed_news_error)
                    .into(picture);
        }else {
            picture.setImageResource(R.drawable.loading_s);
        }
        title.setText(data.getTitle());
        content_small.setText(data.getAbstractX());
    }
}

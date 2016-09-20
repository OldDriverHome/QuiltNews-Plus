package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.utils.JUtils;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.cache.Cache;
import com.xushuzhan.quiltnews.modle.been.ViewPagerBeen;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerViewPager;
import com.xushuzhan.quiltnews.ui.view.BannerImageView;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/7/25.
 */
public class ViewPagerAdapter extends StaticPagerAdapter {
    public static ViewPagerBeen viewPagersContent;
    public static final String TAG = "ViewPagerAdapter";
    private Context ctx;
    public ViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        final BannerImageView view = new BannerImageView(container.getContext());
        final Cache<ViewPagerBeen> cache = new Cache<>(APP.getAppContext());
        if (JUtils.isNetWorkAvilable()) {
            Subscriber<ViewPagerBeen> subscriber = new Subscriber<ViewPagerBeen>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "onError: " + e.getMessage());
                }

                @Override
                public void onNext(ViewPagerBeen viewPagerBeen) {
                    viewPagersContent = viewPagerBeen;
                    cache.saveCache(viewPagerBeen);
                    if (NewsInfo.isShowPic) {
                        view.setText(viewPagerBeen.getShowapi_res_body().getNewslist().get(position).getTitle());
                        Glide.with(ctx)
                                .load(viewPagerBeen.getShowapi_res_body().getNewslist().get(position).getPicUrl())
                                .error(R.drawable.no_picture)
                                .into(view);
                    } else {
                        view.setImageResource(R.drawable.loading_s);
                    }
                }
            };
            RequestManagerViewPager.getInstance().getViewPager(subscriber);
        } else {
            if (NewsInfo.isShowPic) {
                if (cache.getCache(new ViewPagerBeen()) != null) {
                    view.setText(cache.getCache(new ViewPagerBeen()).getShowapi_res_body().getNewslist().get(position).getTitle());
                    Glide.with(ctx)
                            .load(cache.getCache(new ViewPagerBeen()).getShowapi_res_body().getNewslist().get(position).getPicUrl())
                            .error(R.drawable.no_picture)
                            .into(view);
                }
            } else {
                view.setImageResource(R.drawable.loading_s);
            }
        }

        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return 5;
    }
}

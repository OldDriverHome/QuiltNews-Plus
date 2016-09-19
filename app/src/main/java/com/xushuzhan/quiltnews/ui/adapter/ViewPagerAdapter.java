package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.ViewPagerBeen;
import com.xushuzhan.quiltnews.modle.network.config.API;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerBedNewsList;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerViewPager;
import com.xushuzhan.quiltnews.ui.view.BannerImageView;

import java.util.ArrayList;

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
        view.setScaleType(ImageView.ScaleType.FIT_XY);
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
//                for(int i = 0;i<viewPagersContent.getNewslist().size();i++){
//                    if(viewPagersContent.getNewslist().get(i).getPicUrl().equals("")){
//                        Log.d(TAG, "onNext: 没有图片哟");
//
//                    }
//                }
                if(NewsInfo.isShowPic) {
                    view.setText(viewPagerBeen.getShowapi_res_body().getNewslist().get(position).getTitle());
                    Glide.with(ctx)
                            .load(viewPagerBeen.getShowapi_res_body().getNewslist().get(position).getPicUrl())
                            .error(R.drawable.no_picture)
                            .into(view);
//                    Glide.with(ctx).load(viewPagerBeen.getShowapi_res_body().getNewslist().get(position).getUrl())
//                            .asBitmap().into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                            view.setBitmap(resource);
//                        }
//                    });
                }else {
                    view.setImageResource(R.drawable.loading_s);
                }
            }
        };
        RequestManagerViewPager.getInstance().getViewPager(subscriber);
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

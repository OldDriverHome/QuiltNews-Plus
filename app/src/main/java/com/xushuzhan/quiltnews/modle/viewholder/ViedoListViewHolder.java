package com.xushuzhan.quiltnews.modle.viewholder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.FinalVideoListBean;
import com.xushuzhan.quiltnews.ui.fragment.BaseFragment;
import com.xushuzhan.quiltnews.ui.fragment.video.VideoInfoPageFragment;
import com.youku.cloud.module.CustomInfo;
import com.youku.cloud.module.PlayerErrorInfo;
import com.youku.cloud.module.VideoInfo;
import com.youku.cloud.player.PlayerListener;
import com.youku.cloud.player.VideoDefinition;
import com.youku.cloud.player.YoukuPlayerView;
import com.youku.cloud.player.YoukuUIListener;


/**
 * Created by xushuzhan on 2016/8/17.
 */
public class ViedoListViewHolder extends BaseViewHolder<FinalVideoListBean> implements BaseFragment.ILifeCycleMonitor {
    private static final String TAG = "ViedoListViewHolder";
    TextView playCount;
    Button discuss;
    TextView publishTime;
    Button share;
    YoukuPlayerView mYoukuPlayerView;
    Activity mParentActivity;
    BaseFragment mFragment;

    TextView title;
    ImageView play;
    ImageView mCover;
    RelativeLayout mCoverContainer;
    String mCurVid;//当前正在播放的视频的vid
    public ViedoListViewHolder(final ViewGroup parent, @LayoutRes int res, Activity activity, BaseFragment fragment) {
        super(parent, res);

        playCount = $(R.id.tv_video_play_count);
//        discuss = $(R.id.bt_discuss_eyeshot_video);
        publishTime = $(R.id.tv_video_publish_time);
//        share = $(R.id.bt_video_share);

        title = $(R.id.video_title);
        play = $(R.id.video_play);
        mCover = $(R.id.video_cover);
        mCoverContainer = $(R.id.video_cover_container);

        mYoukuPlayerView = $(R.id.custom_videoplayer_standard);
        mParentActivity = activity;
        mFragment = fragment;
        mFragment.registLifeCycleMonitor(this);
    }

    @Override
    public void setData(final FinalVideoListBean data) {
        super.setData(data);
        mCoverContainer.setVisibility(View.VISIBLE);
        mCover.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d(TAG, "mCover - onFocusChange: " + b);

            }
        });
        playCount.setText(data.getPlayCount()+"次播放");
        publishTime.setText(data.getPublishTime());
        title.setText(data.getTitle());
        Glide.with(mFragment)
                .load(data.getThumbnail_pic_s())
                .into(mCover);


        mYoukuPlayerView.attachActivity(mParentActivity);
        mYoukuPlayerView.setPreferVideoDefinition(VideoDefinition.VIDEO_HD);
        mYoukuPlayerView.setShowBackBtn(false);
        mYoukuPlayerView.setShowFullBtn(false);

        mCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCoverContainer.setVisibility(View.INVISIBLE);
                mYoukuPlayerView.playYoukuVideo(data.getUrl());
                mCurVid = data.getUrl();
            }
        });

        mYoukuPlayerView.setPlayerListener(new PlayerListener() {
            @Override
            public void onAdBegin(int index) {
                super.onAdBegin(index);
            }

            @Override
            public void onAdCountUpdate(int second) {
                super.onAdCountUpdate(second);
            }

            @Override
            public void onAdEnd(int index) {
                super.onAdEnd(index);
            }

            @Override
            public void OnCurrentPositionChanged(int msec) {
                super.OnCurrentPositionChanged(msec);
            }

            @Override
            public void needPay(String vid) {
                super.needPay(vid);
            }

            @Override
            public void onBufferingUpdate(int percent) {
                super.onBufferingUpdate(percent);
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onError(int code, PlayerErrorInfo info) {
                super.onError(code, info);
            }

            @Override
            public void onLoaded() {
                super.onLoaded();
            }

            @Override
            public void onLoading() {
                super.onLoading();
            }

            @Override
            public void onNetSpeedChanged(int count) {
                super.onNetSpeedChanged(count);
            }

            @Override
            public void onPrepared() {
                super.onPrepared();
            }

            @Override
            public void onRealVideoStart() {
                super.onRealVideoStart();
            }

            @Override
            public void onSeekComplete() {
                super.onSeekComplete();
            }

            @Override
            public void onStartRenderVideo() {
                super.onStartRenderVideo();
            }

            @Override
            public void onTimeout() {
                super.onTimeout();
                Toast.makeText(mParentActivity, "哎呀～，播放出错了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoDefinitionChanged() {
                super.onVideoDefinitionChanged();
            }

            @Override
            public void onVideoInfoGetted(boolean arg0, VideoInfo videoInfo) {
                super.onVideoInfoGetted(arg0, videoInfo);
            }

            @Override
            public void onVideoNeedPassword(int code) {
                super.onVideoNeedPassword(code);
            }

            @Override
            public void onVideoSizeChanged(int width, int height) {
                super.onVideoSizeChanged(width, height);
            }

            @Override
            public void onCustomInfoGetted(CustomInfo customInfo) {
                super.onCustomInfoGetted(customInfo);
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        });
        
        
        

    }


    @Override
    public void onCreate() {
        
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {
        mYoukuPlayerView.onPause();
    }

    @Override
    public void onResume() {
        mYoukuPlayerView.onResume();
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
        mYoukuPlayerView.onDestroy();
        Log.d(TAG, "onDestroy: ViedoListViewHolder");

    }



}

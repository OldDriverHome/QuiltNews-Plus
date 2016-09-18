package com.xushuzhan.quiltnews.ui.fragment.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.xushuzhan.quiltnews.R;

import com.xushuzhan.quiltnews.modle.impl.VideoListModle;

import com.xushuzhan.quiltnews.ui.adapter.VideoAdapter;


import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class VideoInfoPageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String TAG = "OtherInfoPageFragment";
    private static final String PAGER_POSITION = "position";
    public int Page = 1;
    int time = 1;
    private View mView;
    private EasyRecyclerView recyclerView;
    private VideoAdapter adapter;
    VideoListModle videoListModle;


    public static VideoInfoPageFragment newInstance(String title) {
        //利用bundle传值
        Bundle bundle = new Bundle();
        bundle.putString(PAGER_POSITION, title);

        //实例化
        VideoInfoPageFragment fragment = new VideoInfoPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String title = null;
        //取出bundle中的值
        Bundle bundle = getArguments();
        if (bundle != null) {
            title =  bundle.getString(PAGER_POSITION);
        }
        if (mView == null) {

            mView = inflater.inflate(R.layout.fragment_video_view_pager, container, false);
        }
        setRecyclerView(title);
        return mView;
    }

    private void setRecyclerView(String title) {
        recyclerView= (EasyRecyclerView) mView.findViewById(R.id.recycler_view_fragment_video_view_pager);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapterWithProgress(adapter = new VideoAdapter(getContext()));
        Log.d(TAG, "setRecyclerView: "+title);
        videoListModle = new VideoListModle(adapter);
        videoListModle.getVedioList(title,"5",String.valueOf(Page));
        recyclerView.setRefreshListener(this);
        if(!recyclerView.hasFocus()){
            JCVideoPlayer.releaseAllVideos();
        }

    }
    @Override
    public void onRefresh() {
        adapter.clear();
        JCVideoPlayer.releaseAllVideos();
        videoListModle.getVedioList(getArguments().getString(PAGER_POSITION),"5",String.valueOf(++Page));
        recyclerView.setRefreshing(false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
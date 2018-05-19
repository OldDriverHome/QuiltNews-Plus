package com.xushuzhan.quiltnews.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.viewholder.ViedoListViewHolder;
import com.xushuzhan.quiltnews.modle.been.FinalVideoListBean;
import com.xushuzhan.quiltnews.ui.fragment.BaseFragment;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class VideoAdapter extends RecyclerArrayAdapter<FinalVideoListBean> {
    private static final String TAG = "VideoAdapter";
    Activity mContext;
    BaseFragment mFragment;
    String mCurVid;
    public VideoAdapter(Activity context,BaseFragment fragment) {
        super(context);
        mContext = context;
        mFragment = fragment;

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        ViedoListViewHolder viewHolder = new ViedoListViewHolder(parent, R.layout.item_eyeshot_video_cardview,mContext,mFragment);
        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d(TAG, "onFocusChange: " + b);
            }
        });

        return viewHolder;
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
        Log.d(TAG, "OnCreateViewHolder: " + mObjects.size());

    }
}

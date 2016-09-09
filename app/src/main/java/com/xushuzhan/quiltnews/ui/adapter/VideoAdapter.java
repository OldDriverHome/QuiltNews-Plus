package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.viewholder.ViedoListViewHolder;
import com.xushuzhan.quiltnews.modle.been.FinalVideoListBean;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class VideoAdapter extends RecyclerArrayAdapter<FinalVideoListBean> {
    public VideoAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViedoListViewHolder(parent, R.layout.item_eyeshot_video_cardview);
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);

        Log.d("45644264", "OnBindViewHolder: "+position+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}

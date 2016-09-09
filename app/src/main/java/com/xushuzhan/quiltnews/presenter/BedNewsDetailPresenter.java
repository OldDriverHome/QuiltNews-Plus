package com.xushuzhan.quiltnews.presenter;

import com.xushuzhan.quiltnews.ui.iview.IBedNewsDetailView;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class BedNewsDetailPresenter {
    IBedNewsDetailView iBedNewsDetailView;
    public BedNewsDetailPresenter(IBedNewsDetailView iBedNewsDetailView){
        this.iBedNewsDetailView = iBedNewsDetailView;
    }


    public void showNewsDetail(String url){
        iBedNewsDetailView.showNewsDtail(url);
    }
}

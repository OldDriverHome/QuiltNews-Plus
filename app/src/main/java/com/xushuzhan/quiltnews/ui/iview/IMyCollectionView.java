package com.xushuzhan.quiltnews.ui.iview;

/**
 * Created by xushuzhan on 2016/8/23.
 */
public interface IMyCollectionView {
    //显示Toast
    void showToast(String content);
    //跳转到新闻详情
    void intentToNewsDetail(String newsTitle,String newsUrl,String picUrl,String uniqueKey);
}

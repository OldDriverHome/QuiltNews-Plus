package com.xushuzhan.quiltnews.ui.iview;

import com.xushuzhan.quiltnews.modle.been.NewsListBeen;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public interface IOtherPageView {
    //给recyclerView添加数据
    void addDataToRecyclerView(NewsListBeen newsListBeen);

    //跳转到NewsDetailActivity
    void intentToNewsDetailActivity(String url,String title,String picUrl,String uniquekey);

    void showToast(String content);
}

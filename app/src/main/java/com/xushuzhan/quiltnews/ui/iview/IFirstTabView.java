package com.xushuzhan.quiltnews.ui.iview;

import com.xushuzhan.quiltnews.modle.been.NewsListBeen;

/**
 * Created by xushuzhan on 2016/8/16.
 */
public interface IFirstTabView {

    //给RecyclerView绑定数据
    void addDataToRecyclerView(NewsListBeen newsListBeen);

    //跳转到NewsDetailActivity
    void intentToNewsDetailActivity(String url,String title,String picUrl,String uniquekey);

    void showToast(String content);
}

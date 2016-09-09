package com.xushuzhan.quiltnews.ui.iview;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public interface IAllDiscussView {

    //获取news_uniquekey
    String getNewaUniqueKey();

    //获取新闻标题
    String getNewsTitle();

    //获取新闻图片的链接
    String getNewsPicUrl();
    //获取新闻的url
    String getNewsUrl();

    void showToast(String content);

    //显示沙发
    void setSofaPic(boolean isShow);
}

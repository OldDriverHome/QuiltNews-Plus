package com.xushuzhan.quiltnews.ui.iview;

import android.app.Activity;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public interface INewsDetailView {
    //webview设置url
    void showNewsDetail(String url);

    //跳转到评论详情的Activity

    void intentToAllDiscuss();

    //设置评论条数
    void setDiscussCount(String count);

    //获取新闻标题
    String getNewsTitle();

    //获取新闻图片的链接
    String getNewsPicUrl();

    //获取新闻唯一码
    String getNewsUniqueKey();

    //获取新闻的url
    String getNewsUrl();

    //获取activity
    Activity getActivity();

    //显示Toast
    void showToast(String content);

    //获取增加评论数量
    void addDiscussCount();

    //设置收藏按钮的样子
    void setColectButton();

}

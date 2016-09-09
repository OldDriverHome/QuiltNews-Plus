package com.xushuzhan.quiltnews.ui.iview;

import android.app.Activity;

/**
 * Created by xushuzhan on 2016/8/18.
 */
public interface IpersonalCenterView {
    //跳转到登录界面
    void intentToLogin();

    //显示Toast
    void showToast(String content);

    //设置头像
    void setHeadPicture();

    //隐藏头像
    void hintHeadPicture();

    //跳转到我的评论
    void intentToMyDiscuss();

    //获取当前界面的Activity
    Activity getMyActivity();


    //隐藏设置昵称的按钮
    void hintEditNickButton();

    //设置昵称
    void setNickName(String content);

}

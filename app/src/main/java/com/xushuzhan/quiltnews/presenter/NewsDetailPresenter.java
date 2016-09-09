package com.xushuzhan.quiltnews.presenter;


import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.been.NewsDiscussBeen;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.iview.INewsDetailView;
import com.xushuzhan.quiltnews.utils.DialogPopup;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xushuzhan on 2016/8/17.
 */
public class NewsDetailPresenter {
    public static final String TAG = "NewsDetailPresenter";
    INewsDetailView iNewsDetailView;
    DialogPopup dialogPopup;

    public NewsDetailPresenter(INewsDetailView iNewsDetailView) {
        this.iNewsDetailView = iNewsDetailView;
    }

    public void showNewsDetail(String url) {
        iNewsDetailView.showNewsDetail(url);
    }


    public void intentToAllDiscuss() {
        iNewsDetailView.intentToAllDiscuss();

    }

    public void showDiscussCount() {
        AVQuery<AVObject> query = new AVQuery<>("comment");
        query.whereEqualTo(NewsInfo.NEWS_UNIQUEKEY, iNewsDetailView.getNewsUniqueKey());   //查询某条新闻的所有评论
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                try {

                    iNewsDetailView.setDiscussCount(list.size() + "");


                } catch (Exception ee) {

                }


            }
        });

    }

    public void showPopupWindow() {
        dialogPopup = new DialogPopup(iNewsDetailView.getActivity(), "请输入评论的内容", "发送");
        dialogPopup.showPopupWindow();
        if (UserInfo.isQQLogin || UserInfo.isNormalLogin) {
            dialogPopup.setOnItemClickListener(new DialogPopup.OnSendButtonClickListener() {
                @Override
                public void onSendClick(View view, String content) {
                    Log.d(TAG, "onSendClick: " + content);
                    if (content != null) {
                        AVObject news = new AVObject("comment");// 构建对象
                        news.put(NewsInfo.USER_NAME, UserInfo.userName);
                        news.put(NewsInfo.NICK_NAME, UserInfo.nickName);
                        news.put(NewsInfo.NEWS_UNIQUEKEY, iNewsDetailView.getNewsUniqueKey());
                        news.put(NewsInfo.DISCUSS_CONTENT, content);
                        news.put(NewsInfo.NEWS_TITLE, iNewsDetailView.getNewsTitle());
                        news.put(NewsInfo.NEWS_URL, iNewsDetailView.getNewsUrl());
                        news.put(NewsInfo.NEWS_PIC_URL, iNewsDetailView.getNewsPicUrl());
                        news.saveInBackground();// 保存到服务端
                        iNewsDetailView.addDiscussCount();
                        dialogPopup.dismiss();

                    } else {
                        iNewsDetailView.showToast("评论内容不能为空");
                    }
                }
            });
        } else {
            iNewsDetailView.showToast("请先登录");
            dialogPopup.dismiss();
        }
    }

    public void collect() {
        if (UserInfo.isQQLogin || UserInfo.isNormalLogin) {
            final AVObject news = new AVObject("collection");// 构建对象
            news.put(NewsInfo.USER_NAME, UserInfo.userName);
            news.put(NewsInfo.NICK_NAME, UserInfo.nickName);
            news.put(NewsInfo.NEWS_UNIQUEKEY, iNewsDetailView.getNewsUniqueKey());
            news.put(NewsInfo.NEWS_TITLE, iNewsDetailView.getNewsTitle());
            news.put(NewsInfo.NEWS_URL, iNewsDetailView.getNewsUrl());
            news.put(NewsInfo.NEWS_PIC_URL, iNewsDetailView.getNewsPicUrl());
            news.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        SharedPreferenceUtils.putString(APP.getAppContext(), iNewsDetailView.getNewsUniqueKey(), news.getObjectId());

                    } else {
                        iNewsDetailView.showToast("糟糕，网络连接出了点问题");
                    }
                }
            });// 保存到服务端

        } else {
            iNewsDetailView.showToast("请先登录");
        }
    }

    public void unCollect() {
        AVObject news = AVObject.createWithoutData("collection", SharedPreferenceUtils.getString(APP.getAppContext(), iNewsDetailView.getNewsUniqueKey()));
        news.deleteInBackground();
    }

    public void setCollect() {
        AVQuery<AVObject> query = new AVQuery<>("collection");
        query.whereEqualTo(NewsInfo.NEWS_UNIQUEKEY, iNewsDetailView.getNewsUniqueKey());
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    if (list.size() != 0) {
                        NewsInfo.isCollect = true;
                        iNewsDetailView.setColectButton();
                        Log.d(TAG, "done: ");
                    } else if (list.size() == 0) {
                        NewsInfo.isCollect = false;
                    }
                }
            }
        });
    }


}


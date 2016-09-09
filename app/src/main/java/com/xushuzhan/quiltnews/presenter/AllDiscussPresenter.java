package com.xushuzhan.quiltnews.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.xushuzhan.quiltnews.modle.been.NewsDiscussBeen;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.adapter.NewsDiscussAdapter;
import com.xushuzhan.quiltnews.ui.iview.IAllDiscussView;
import com.xushuzhan.quiltnews.utils.DialogPopup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class AllDiscussPresenter {
    public static final String TAG = "AllDiscussPresenterTAG";
    DialogPopup dialogPopup;
    IAllDiscussView iAllDiscussView;
    NewsDiscussAdapter newsDiscussAdapter;
    Activity activity;
    ArrayList<NewsDiscussBeen> allNewsDiscuss;

    String url;
    String title;
    String picUrl;
    String uniqueKey;

    public AllDiscussPresenter(IAllDiscussView iAllDiscussView, NewsDiscussAdapter newsDiscussAdapter, Activity activity) {
        this.iAllDiscussView = iAllDiscussView;
        this.newsDiscussAdapter = newsDiscussAdapter;
        this.activity = activity;
    }

    //显示所有评论
    public void showAllDiscuss() {
        allNewsDiscuss = new ArrayList<>();
        AVQuery<AVObject> query = new AVQuery<>("comment");
        query.orderByDescending("createdAt");
        query.whereEqualTo("news_uniquekey", iAllDiscussView.getNewaUniqueKey());   //查询某条新闻的所有评论
        query.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.setMaxCacheAge(24 * 3600); //设置缓存有效期
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                try {

                    for (int i = 0; i < list.size(); i++) {
                        NewsDiscussBeen newsDiscussBeen = new NewsDiscussBeen();
                        newsDiscussBeen.setUserName(list.get(i).get("nick_name").toString());
                        newsDiscussBeen.setDiscussTime(list.get(i).get("createdAt").toString());
                        newsDiscussBeen.setDiscussContent(list.get(i).get("discuss_content").toString());
                        Log.d(TAG, "done: >>>>>" + list.get(i).get("discuss_content").toString());
                        allNewsDiscuss.add(newsDiscussBeen);
                        iAllDiscussView.setSofaPic(false);
                    }

                    if (list.size()==0){
                        iAllDiscussView.setSofaPic(true);
                        //iAllDiscussView.showToast("快来抢沙发");
                    }
                    newsDiscussAdapter.addAll(allNewsDiscuss);
                }catch (Exception ee){
                    iAllDiscussView.showToast("糟糕，网络不太顺畅");
                    Log.d(TAG, "done: ee>>"+ee.getMessage());
                }
                if (e != null)
                    Log.d(TAG, "done: error" + e.getMessage());

            }
        });

    }

    //发表评论
    public void sendDiscuss() {

        if (UserInfo.isNormalLogin||UserInfo.isQQLogin) {
            url = iAllDiscussView.getNewsUrl();
            title = iAllDiscussView.getNewsTitle();
            picUrl = iAllDiscussView.getNewsPicUrl();
            uniqueKey = iAllDiscussView.getNewaUniqueKey();
            dialogPopup = new DialogPopup(activity,"请输入评论内容","发送");
            dialogPopup.showPopupWindow();
            dialogPopup.setOnItemClickListener(new DialogPopup.OnSendButtonClickListener() {
                @Override
                public void onSendClick(View view, String content) {
                    Log.d(TAG, "onSendClick: " + content);
                    if (content != null) {
                        AVObject news = new AVObject("comment");// 构建对象
                        news.put("user_name", UserInfo.userName);
                        news.put("nick_name",UserInfo.nickName);
                        news.put("news_uniquekey", uniqueKey);
                        news.put("discuss_content", content);
                        news.put("news_title", title);
                        news.put("url", url);
                        news.put("pic_url", picUrl);
                        news.saveInBackground();// 保存到服务端
                        NewsDiscussBeen newsDiscussBeen = new NewsDiscussBeen();
                        newsDiscussBeen.setUserName(UserInfo.nickName);
                        newsDiscussBeen.setDiscussTime("刚刚");
                        newsDiscussBeen.setDiscussContent(content);
                        newsDiscussAdapter.add(newsDiscussBeen);
                        iAllDiscussView.setSofaPic(false);
                        dialogPopup.dismiss();
                        activity = null;
                        newsDiscussAdapter = null;
                    } else {
                        Toast.makeText(activity, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            iAllDiscussView.showToast("请先登录");
        }
    }
}

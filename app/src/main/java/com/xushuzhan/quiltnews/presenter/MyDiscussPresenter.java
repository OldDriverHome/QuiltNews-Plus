package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.been.MyDiscussBeen;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.adapter.MyDiscussAdapter;
import com.xushuzhan.quiltnews.ui.iview.IMyDiscussView;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class MyDiscussPresenter {
    public static final String TAG = "MyDiscussPresenterTAG";
    IMyDiscussView iMyDiscussView;
    MyDiscussAdapter myDiscussAdapter;
    ArrayList<MyDiscussBeen> newsList;

    public MyDiscussPresenter(MyDiscussAdapter myDiscussAdapter, IMyDiscussView iMyDiscussView) {
        this.myDiscussAdapter = myDiscussAdapter;
        this.iMyDiscussView = iMyDiscussView;
        newsList = new ArrayList<>();
    }


    //显示某个用户的新闻评论列表
    public void showNewsDiscussList() {
        if (AVUser.getCurrentUser()!=null) {
            //查询用户评论的所有新闻
            AVQuery<AVObject> query = new AVQuery<>("comment");
            query.orderByDescending("createdAt");
            query.whereEqualTo("user_name", AVUser.getCurrentUser().getUsername());   //查询小明在某条新闻的评论
            query.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
            query.setMaxCacheAge(24 * 3600); //设置缓存有效期
            Log.d(TAG, "showNewsDiscussList: "+UserInfo.userName);
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    try {
                        if (list.size() == 0) {
                            iMyDiscussView.showToast("你还没有对任何新闻发表看法哟");
                        }
                        for (int i = 0; i < list.size(); i++) {

                            MyDiscussBeen myDiscussBeen = new MyDiscussBeen();
                            myDiscussBeen.setUserName(SharedPreferenceUtils.getString(APP.getAppContext(),"nick_name"));
                            myDiscussBeen.setDiscussTime(list.get(i).get("createdAt").toString());
                            myDiscussBeen.setContent(list.get(i).get("discuss_content").toString());
                            myDiscussBeen.setNewsTitle(list.get(i).get("news_title").toString());
                            myDiscussBeen.setPicURL(list.get(i).get("pic_url").toString());
                            myDiscussBeen.setNewsUrl(list.get(i).get("url").toString());
                            myDiscussBeen.setUniqueKey(list.get(i).get("news_uniquekey").toString());
                            newsList.add(myDiscussBeen);
                        }
                        myDiscussAdapter.addAll(newsList);

                    } catch (Exception ee) {
                        Log.d(TAG, "done: "+ee.toString());
                        iMyDiscussView.showToast("糟糕，网络不太顺畅");
                    }

                }
            });
        } else {
            iMyDiscussView.showToast("请先登录！");
        }
    }

    public void intentToNewsDetail(int position){
        iMyDiscussView.intentToNewsDetail(
                newsList.get(position).getNewsTitle(),
                newsList.get(position).getNewsUrl(),
                newsList.get(position).getPicURL(),
                newsList.get(position).getUniqueKey()
        );
    }
}

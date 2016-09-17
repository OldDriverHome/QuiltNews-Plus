package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.xushuzhan.quiltnews.modle.been.MyCollectionBeen;
import com.xushuzhan.quiltnews.modle.been.MyDiscussBeen;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.adapter.MyCollectionAdapter;
import com.xushuzhan.quiltnews.ui.adapter.MyDiscussAdapter;
import com.xushuzhan.quiltnews.ui.iview.IMyCollectionView;
import com.xushuzhan.quiltnews.ui.iview.IMyDiscussView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/8/23.
 */
public class MyCollectionPresenter {
    public static final String TAG = "MyDiscussPresenterTAG";
    IMyCollectionView iMyCollectionView;
    MyCollectionAdapter myCollectionAdapter;
    ArrayList<MyCollectionBeen> newsList;

    public MyCollectionPresenter(IMyCollectionView iMyCollectionView,MyCollectionAdapter myCollectionAdapter) {
        this.iMyCollectionView = iMyCollectionView;
        this.myCollectionAdapter = myCollectionAdapter;
        newsList = new ArrayList<>();
    }

    //显示某个用户的新闻收藏列表
    public void showNewsCollectionList() {
        if (AVUser.getCurrentUser()!=null) {
            //查询用户评论的所有新闻
            AVQuery<AVObject> query = new AVQuery<>("collection");
            query.orderByDescending("createdAt");
            query.whereEqualTo("user_name", UserInfo.userName);
            Log.d(TAG, "showNewsDiscussList: "+UserInfo.userName);
            query.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
            query.setMaxCacheAge(24 * 3600); //设置缓存有效期
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    try {
                        if (list.size() == 0) {
                            iMyCollectionView.showToast("你还没有收藏新闻哟");
                        }
                        for (int i = 0; i < list.size(); i++) {
                            MyCollectionBeen myCollectionBeen = new MyCollectionBeen();
                            myCollectionBeen.setNickName(UserInfo.nickName);
                            myCollectionBeen.setCollectionTime(list.get(i).get("createdAt").toString());
                            myCollectionBeen.setNewsTitle(list.get(i).get("news_title").toString());
                            myCollectionBeen.setPicUrl(list.get(i).get("pic_url").toString());
                            myCollectionBeen.setNewsUrl(list.get(i).get("url").toString());
                            myCollectionBeen.setUniqueKey(list.get(i).get("news_uniquekey").toString());
                            newsList.add(myCollectionBeen);
                        }
                       myCollectionAdapter .addAll(newsList);

                    } catch (Exception ee) {
                        Log.d(TAG, "done: "+ee.toString());
                        iMyCollectionView.showToast("糟糕，网络不太顺畅");
                    }

                }
            });
        } else {
            iMyCollectionView.showToast("请先登录！");
        }
    }
    public void intentToNewsDetail(int position){
        iMyCollectionView.intentToNewsDetail(
                newsList.get(position).getNewsTitle(),
                newsList.get(position).getNewsUrl(),
                newsList.get(position).getPicUrl(),
                newsList.get(position).getUniqueKey()
        );
    }
}

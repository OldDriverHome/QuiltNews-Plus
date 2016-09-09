package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsList;
import com.xushuzhan.quiltnews.ui.iview.IFirstTabView;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/8/16.
 */
public class FirstTabFragmentPresenter {
    public static final String TAG = "FirstTabPresenter";
    IFirstTabView iFirstTabView;
    NewsListBeen ListBeen;
    public FirstTabFragmentPresenter(IFirstTabView iFirstTabView){
        this.iFirstTabView = iFirstTabView;
    }



    public void showNewsList(){
            Subscriber<NewsListBeen> subscriber = new Subscriber<NewsListBeen>() {

                @Override
                public void onCompleted() {
                    Log.d(TAG, "onCompleted: 请求完成了");
                }

                @Override
                public void onError(Throwable e) {
                    iFirstTabView.showToast("网络不太流畅哟");
                    Log.d(TAG, "onError: "+e.getMessage());
                }

                @Override
                public void onNext(NewsListBeen newsListBeen) {
                    //请求完成;
                    iFirstTabView.addDataToRecyclerView(newsListBeen);
                    ListBeen= newsListBeen;

                }
            };
            RequestManagerNewsList.getInstance().getNewsList(subscriber,"top");
    }

    public void showNewsDetail(int position){
        iFirstTabView.intentToNewsDetailActivity(
                ListBeen.getResult().getData().get(position).getUrl(),
                ListBeen.getResult().getData().get(position).getTitle(),
                ListBeen.getResult().getData().get(position).getThumbnail_pic_s(),
                ListBeen.getResult().getData().get(position).getUniquekey()
        );
    }
}

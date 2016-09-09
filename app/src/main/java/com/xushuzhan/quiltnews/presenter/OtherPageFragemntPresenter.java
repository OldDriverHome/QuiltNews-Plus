package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsList;
import com.xushuzhan.quiltnews.ui.iview.IOtherPageView;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class OtherPageFragemntPresenter {
    public static final String TAG = "OtherPagePresenter";
    IOtherPageView iOtherPageView;
    NewsListBeen ListBeen;
    public OtherPageFragemntPresenter(IOtherPageView iOtherPageView){
        this.iOtherPageView = iOtherPageView;
    }

    public void showNewsList(String title){
        Subscriber<NewsListBeen> subscriber = new Subscriber<NewsListBeen>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: 请求完成了");
            }

            @Override
            public void onError(Throwable e) {

                iOtherPageView.showToast("网络好像不太流畅");
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(NewsListBeen newsListBeen) {
                //请求完成;
                iOtherPageView.addDataToRecyclerView(newsListBeen);
                ListBeen= newsListBeen;
            }
        };
        RequestManagerNewsList.getInstance().getNewsList(subscriber,title);
    }

    public void showNewsDetail(int position){
        iOtherPageView.intentToNewsDetailActivity(
                ListBeen.getResult().getData().get(position).getUrl(),
                ListBeen.getResult().getData().get(position).getTitle(),
                ListBeen.getResult().getData().get(position).getThumbnail_pic_s(),
                ListBeen.getResult().getData().get(position).getDate()
        );
    }
}

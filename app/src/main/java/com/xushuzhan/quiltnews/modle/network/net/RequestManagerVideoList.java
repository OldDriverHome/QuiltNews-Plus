package com.xushuzhan.quiltnews.modle.network.net;

import com.xushuzhan.quiltnews.modle.been.VideoListBean;
import com.xushuzhan.quiltnews.modle.network.config.API;
import com.xushuzhan.quiltnews.modle.network.serverce.ApiServer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class RequestManagerVideoList {
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    private ApiServer mApiServer;
    //构造方法私有
    private RequestManagerVideoList() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.YOUKU_BASE_URL)
                .build();

        mApiServer = retrofit.create(ApiServer.class);

    }

    //在访问RequestManagerNewsList时创建单例
    private static class SingletonHolder{
        private static final RequestManagerVideoList INSTANCE = new RequestManagerVideoList();
    }

    //获取单例
    public static RequestManagerVideoList getInstance(){
        return SingletonHolder.INSTANCE;
    }


    public void getVideoList(Subscriber<VideoListBean> subscriber, String category, String count, String page){

        mApiServer.getVideoList(API.YOUKU_CLIENT_ID,category,count,page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

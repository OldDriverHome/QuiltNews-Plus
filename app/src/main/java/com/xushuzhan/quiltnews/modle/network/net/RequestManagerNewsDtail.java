package com.xushuzhan.quiltnews.modle.network.net;

import com.xushuzhan.quiltnews.modle.been.NewDetailBeen;
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
 * Created by xushuzhan on 2016/8/16.
 */
public class RequestManagerNewsDtail {
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    private ApiServer mApiServer;
    //构造方法私有
    private RequestManagerNewsDtail() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.NEWS_DETAIL_BASE_URL)
                .build();

        mApiServer = retrofit.create(ApiServer.class);

    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final RequestManagerNewsDtail INSTANCE = new RequestManagerNewsDtail();
    }

    //获取单例
    public static RequestManagerNewsDtail getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取新闻详情
     * @param subscriber
     * @param url
     */
    public void getNewsDetail(Subscriber<NewDetailBeen> subscriber, String url){

        mApiServer.getNewsDetail(API.ShowApiAppId,API.ShowApiApiSign,url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

package com.xushuzhan.quiltnews.modle.network.net;

import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.network.config.API;
import com.xushuzhan.quiltnews.modle.network.serverce.ApiServerce;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xushuzhan on 2016/8/10.
 */
public class RequestManagerNewsList {


    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private ApiServerce apiServerce;
    //构造方法私有
    private RequestManagerNewsList() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.NEWS_LIST_BASE_URL)
                .build();

        apiServerce = retrofit.create(ApiServerce.class);

    }

    //在访问RequestManagerNewsList时创建单例
    private static class SingletonHolder{
        private static final RequestManagerNewsList INSTANCE = new RequestManagerNewsList();
    }

    //获取单例
    public static RequestManagerNewsList getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取新闻列表
     * @param subscriber 调用时传过来的观察者对象
     * @param type 新闻的类型，如：头条，科技
     */

    public void getNewsList(Subscriber<NewsListBeen> subscriber,String type){

        apiServerce.getNewsList(type,API.JU_HE_API_KEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

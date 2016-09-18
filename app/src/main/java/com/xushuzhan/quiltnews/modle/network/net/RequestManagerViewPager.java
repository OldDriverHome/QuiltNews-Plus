package com.xushuzhan.quiltnews.modle.network.net;

import com.xushuzhan.quiltnews.modle.been.BedNewsListBeen;
import com.xushuzhan.quiltnews.modle.been.ViewPagerBeen;
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
 * Created by xushuzhan on 2016/9/18.
 */
public class RequestManagerViewPager {
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    private ApiServerce apiServerce;

    //构造方法私有
    private RequestManagerViewPager() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.BAI_DU_BASE_URL)
                .build();

        apiServerce = retrofit.create(ApiServerce.class);

    }

    //在访问RequestManagerNewsList时创建单例
    private static class SingletonHolder {
        private static final RequestManagerViewPager INSTANCE = new RequestManagerViewPager();
    }

    //获取单例
    public static RequestManagerViewPager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取新闻列表
     *
     * @param subscriber 调用时传过来的观察者对象
     */

    public void getViewPager(Subscriber<ViewPagerBeen> subscriber) {

        apiServerce.getViewPagerContent(API.BAIDU_API_APP_KEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}

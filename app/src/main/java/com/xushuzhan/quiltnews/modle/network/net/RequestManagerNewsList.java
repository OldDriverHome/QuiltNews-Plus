package com.xushuzhan.quiltnews.modle.network.net;

import android.util.Log;

import com.jude.utils.JUtils;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.network.config.API;
import com.xushuzhan.quiltnews.modle.network.serverce.ApiServer;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    public static final String TAG = "RequestManagerNewsList";

    private Retrofit retrofit;

    private ApiServer mApiServer;

    //构造方法私有
    private RequestManagerNewsList() {
        File httpCacheDir = new File(APP.getAppContext().getExternalCacheDir(), "NewsCache");
        long cacheSize = 50 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDir, cacheSize);

        Interceptor log = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                long t1 = System.nanoTime();
                Log.d(TAG, "intercept: " + String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Log.d(TAG, "intercept: " + String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
                return response;
            }
        };

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
/*                Request request = chain.request();
                if (!JUtils.isNetWorkAvilable()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (JUtils.isNetWorkAvilable()) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("EasyCache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("EasyCache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;*/
/*                Request request = chain.request();
                if(!JUtils.isNetWorkAvilable()){
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if(JUtils.isNetWorkAvilable()){
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("EasyCache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                }else{
                    return originalResponse.newBuilder()
                            .header("EasyCache-Control", "public, only-if-cached, max-stale=2419200")
                            .removeHeader("Pragma")
                            .build();
                }*/
                Request request = chain.request();
                if (!JUtils.isNetWorkAvilable()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);
                if (JUtils.isNetWorkAvilable()) {
                    int maxAge = 60 * 60; // read from cache for 1 minute
                    response.newBuilder()
                            .removeHeader("Pragma")
                            .header("EasyCache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    response.newBuilder()
                            .removeHeader("Pragma")
                            .header("EasyCache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };

        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient client = httpClientBuilder
                //.cache(cache)
                //.addNetworkInterceptor(interceptor)
                //.addInterceptor(log)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.NEWS_LIST_BASE_URL)
                .build();
        mApiServer = retrofit.create(ApiServer.class);
    }

    //在访问RequestManagerNewsList时创建单例
    private static class SingletonHolder {
        private static final RequestManagerNewsList INSTANCE = new RequestManagerNewsList();
    }

    //获取单例
    public static RequestManagerNewsList getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取新闻列表
     *
     * @param subscriber 调用时传过来的观察者对象
     * @param type       新闻的类型，如：头条，科技
     */

    public void getNewsList(Subscriber<NewsListBeen> subscriber, String type) {

        mApiServer.getNewsList(type, API.JU_HE_API_KEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

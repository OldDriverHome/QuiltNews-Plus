package com.xushuzhan.quiltnews.modle.network.serverce;

import com.xushuzhan.quiltnews.modle.been.BedNewsDetailBeen;
import com.xushuzhan.quiltnews.modle.been.BedNewsListBeen;
import com.xushuzhan.quiltnews.modle.been.BedNewsSlidesBeen;
import com.xushuzhan.quiltnews.modle.been.NewDetailBeen;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.been.VideoBean;
import com.xushuzhan.quiltnews.modle.been.VideoListBean;
import com.xushuzhan.quiltnews.modle.been.ViewPagerBeen;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xushuzhan on 2016/8/16.
 */
public interface ApiServer {
    /**
     * top(头条，默认) shehui(社会) guonei(国内) guoji(国际) yule(娱乐) tiyu(体育) junshi(军事) keji(科技) caijing(财经) shishang(时尚)
     * @param type
     * @param appKey
     * @return
     */
   // @Headers("EasyCache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST("index")
    Observable<NewsListBeen> getNewsList(@Field("type") String type, @Field("key") String appKey);

    /**
     * 抽取新闻页面的图片和内容
     * @param appId
     * @param apiSign
     * @param url
     * @return
     */
    @FormUrlEncoded
    @POST("644-1")
    Observable<NewDetailBeen> getNewsDetail(@Field("showapi_appid") String appId,@Field("showapi_sign") String apiSign,@Field("url") String url);


    @FormUrlEncoded
    @POST("1071-1")
    Observable<NewDetailBeen> getGoodNightNewsList(@Field("showapi_appid") String appId,@Field("showapi_sign") String sign);

    @FormUrlEncoded
    @POST("v2/videos/by_category.json")
    Observable<VideoListBean> getVideoList(@Field("client_id") String clientId,@Field("category") String category,
                                           @Field("count") String count,@Field("page") String page );


    @FormUrlEncoded
    @POST("dmxy/truevideourl/truevideourl")
    Observable<VideoBean> getVideaoURL(@Header("apikey") String videoApiKey, @Field("url") String url);


    @GET("songshuxiansheng/news/news")
    Observable<BedNewsListBeen> getBeforeBedNewsList(@Header("apikey") String apiKey);

    /**
     * 首页的ViewPager
     * @return
     */

    @FormUrlEncoded
    @POST("196-1")
    Observable<ViewPagerBeen> getViewPagerContent(@Field("showapi_appid") String appId,@Field("showapi_sign") String sign,@Field("num")String num,@Field("page")String page);


    //睡前新闻的幻灯片模式
    @GET("ipadtestdoc")
    Observable<BedNewsSlidesBeen> getBedNewsSlides(@Query("aid") String aid);

    @GET("irecommendList.php?userId=866048024885909&count=6&gv=5.2.6&av=5.2.6&uid=866048024885909&deviceid=866048024885909&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=720x1280&publishid=2024&nw=wifi&city=重庆市&province=重庆市")
    Observable<BedNewsListBeen> getBedNewsList();

    //睡前新闻详情
    @GET("ipadtestdoc")
    Observable<BedNewsDetailBeen> getBedNewsDtail(@Query("aid") String aid);
}

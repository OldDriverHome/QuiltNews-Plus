package com.xushuzhan.quiltnews.presenter;

import android.util.Log;
import android.widget.Toast;

import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.been.BedNewsListBeen;
import com.xushuzhan.quiltnews.modle.network.config.ReadInfo;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerBedNewsList;
import com.xushuzhan.quiltnews.ui.adapter.BedNewsListAdapter;
import com.xushuzhan.quiltnews.ui.iview.IBedNewsListView;
import com.xushuzhan.quiltnews.utils.TimerUtils;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class BedNewsListPresenter {
    public static final String TAG = "BedNewsListPresenter";
    IBedNewsListView iBedNewsListView;
    BedNewsListAdapter adapter;
    List<BedNewsListBeen.ItemBean> newsList;
    public BedNewsListPresenter(IBedNewsListView iBedNewsListView, BedNewsListAdapter adapter){
        this.iBedNewsListView = iBedNewsListView;
        this.adapter = adapter;
    }
    public void showBedNewsList(){
        Subscriber<BedNewsListBeen> subscriber = new Subscriber<BedNewsListBeen>() {

            @Override
            public void onCompleted() {
                //请求完成，换句话说，所有的newslistBean都仍到list里面去了
                //然后就可以执行把arrayList给recyclerView的adapter之类的操作了
                Log.d(TAG, "onCompleted: 请求完成了");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e);
            }

            @Override
            public void onNext(BedNewsListBeen bedNewsListBeen) {
                Log.d(TAG, "onNext: 请求成功"+bedNewsListBeen.getItem().get(0).getType());
                Log.d(TAG, "onNext: +"+bedNewsListBeen.getItem().get(0).getTitle());
                // showBedNewsList();
                adapter.addAll(getRightInfo(bedNewsListBeen));
                newsList = getRightInfo(bedNewsListBeen);

            }
        };

        RequestManagerBedNewsList.getInstance().getNewsList(subscriber);
    }
    public void guideUser(){
        if (SharedPreferenceUtils.getString(APP.getAppContext(),"useTime")==null){
            iBedNewsListView.showToast("每次下拉都可以更新新闻哟");
        }
        SharedPreferenceUtils.putString(APP.getAppContext(),"useTime","1");
    }

    public void intentToBedNewsDetail(int position){
        Log.d(TAG, "intentToBedNewsDetail: ->"+newsList.get(position).getType());
        //iBedNewsListView.intentToBenNewsDtail(newsList.getRetData().get(position).getUrl());
        if(newsList.get(position).getType().equals("doc")){
            iBedNewsListView.intentToBenNewsDtail(newsList.get(position).getLink().getUrl().replace("http://api.iclient.ifeng.com/ipadtestdoc?aid=",""));
        }else if(newsList.get(position).getType().equals("slide")){
            iBedNewsListView.intentToSlidesActivity(newsList.get(position).getLink().getUrl().replace("http://api.iclient.ifeng.com/ipadtestdoc?aid=",""));
            Log.d(TAG, "intentToBedNewsDetail: "+newsList.get(position).getLink().getUrl().replace("http://api.iclient.ifeng.com/ipadtestdoc?aid=",""));
        }

    }


    public void initTimer() {
        try {
            final TimerUtils timer = new TimerUtils();//实例化
            timer.setTotalTime(30*60* 1000);//设置毫秒数
            timer.setIntervalTime(30* 1000);//设置间隔数
            timer.setTimerLiener(new TimerUtils.TimeListener() {
                @Override
                public void onFinish() {
                    ReadInfo.isSleepTime = true;
                    Toast.makeText(APP.getAppContext(), "半小时飘走了，该休息了 (●'◡'●)", Toast.LENGTH_LONG).show();
                    timer.cancel();
                }

                @Override
                public void onInterval(long remainTime) {
                   // Toast.makeText(APP.getAppContext(), "亲，注意阅读时间哦", Toast.LENGTH_SHORT).show();
                }
            });
            timer.start();
        }catch (Exception e){
            Log.d("123", "initTimer: "+e.getMessage());
        }
    }

    //去除接口中的广告
    public List<BedNewsListBeen.ItemBean> getRightInfo(BedNewsListBeen orgData){
        List<BedNewsListBeen.ItemBean> newData = new ArrayList<>();
        for(int i = 0; i<orgData.getItem().size();i++){
            if(orgData.getItem().get(i).getType().equals("web")){
                continue;
            }else {
                newData.add(orgData.getItem().get(i));
            }
        }

        return newData;
    }
}

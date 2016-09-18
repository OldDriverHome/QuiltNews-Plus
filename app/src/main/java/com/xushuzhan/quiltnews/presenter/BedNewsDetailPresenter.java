package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.xushuzhan.quiltnews.modle.been.BedNewsDetailBeen;
import com.xushuzhan.quiltnews.modle.been.BedNewsSlidesBeen;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerBedNewSlides;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerBedNewsDetail;
import com.xushuzhan.quiltnews.ui.activity.SlidesActicity.InfoFixedPageAdapter;
import com.xushuzhan.quiltnews.ui.activity.SlidesActicity.InfoPageFragment;
import com.xushuzhan.quiltnews.ui.iview.IBedNewsDetailView;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class BedNewsDetailPresenter {

    public static final String TAG = "BedNewsDetailPresenter";
    IBedNewsDetailView iBedNewsDetailView;
    public BedNewsDetailPresenter(IBedNewsDetailView iBedNewsDetailView){
        this.iBedNewsDetailView = iBedNewsDetailView;
    }


    public void showNewsDetail(String aid){
        Subscriber<BedNewsDetailBeen> subscriber = new Subscriber<BedNewsDetailBeen>() {

            @Override
            public void onCompleted() {
                //请求完成，换句话说，所有的newslistBean都仍到list里面去了
                //然后就可以执行把arrayList给recyclerView的adapter之类的操作了
                Log.d(TAG, "onCompleted: 请求完成了");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(BedNewsDetailBeen bedNewsDetailBeen) {
                Log.d(TAG, "onNext:"+bedNewsDetailBeen.getBody().getTitle());
                iBedNewsDetailView.showNewsDtail(bedNewsDetailBeen.getBody().getText());
                iBedNewsDetailView.setTitleAndTime(
                        bedNewsDetailBeen.getBody().getTitle(),
                        bedNewsDetailBeen.getBody().getEditTime()

                );

            }
        };

        RequestManagerBedNewsDetail.getInstance().getBedNewsDetail(subscriber,aid);
    }
}

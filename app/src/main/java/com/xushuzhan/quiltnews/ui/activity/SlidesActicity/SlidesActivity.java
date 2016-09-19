package com.xushuzhan.quiltnews.ui.activity.SlidesActicity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.BedNewsSlidesBeen;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerBedNewSlides;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class SlidesActivity extends AppCompatActivity {
    public static final String TAG ="SlidesActivityTAG";
    public ViewPager mViewPager;
    private InfoFixedPageAdapter infoFixedPageAdapter;
    private List<Fragment> mFragments;
    private Intent intent;
    private String aid;//幻灯片接口的aid
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slides);
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent = getIntent();
        aid = intent.getStringExtra("aid");
        if(aid != null){
            showBedNewsList();
        }else {
            Toast.makeText(SlidesActivity.this, "网络状况不佳", Toast.LENGTH_SHORT).show();
        }
    }
    public void showBedNewsList(){
        Subscriber<BedNewsSlidesBeen> subscriber = new Subscriber<BedNewsSlidesBeen>() {

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
            public void onNext(BedNewsSlidesBeen bedNewsSlidesBeen) {
                Log.d(TAG, "onNext:"+bedNewsSlidesBeen.getBody().getTitle());
                infoFixedPageAdapter = new InfoFixedPageAdapter(getSupportFragmentManager());
                mFragments = new ArrayList<>();
                for (int i = 0; i < bedNewsSlidesBeen.getBody().getSlides().size(); i++) {
                    //传入标题和page的id
                    mFragments.add(InfoPageFragment.newInstance(
                            bedNewsSlidesBeen.getBody().getSlides().get(i).getImage(),
                            bedNewsSlidesBeen.getBody().getSlides().get(i).getDescription(),
                            bedNewsSlidesBeen.getBody().getSlides().get(i).getTitle(),
                            bedNewsSlidesBeen.getBody().getSlides().size(),
                            i+1
                    ));
                }
                //把要显示的fragment集合传给adapter
                infoFixedPageAdapter.setFragments(mFragments);

                //给viewPager设置适配器
                mViewPager.setAdapter(infoFixedPageAdapter);
            }
        };

        RequestManagerBedNewSlides.getInstance().getNewsSlide(subscriber,aid);
    }
}

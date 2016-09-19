package com.xushuzhan.quiltnews.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.NewsDiscussBeen;
import com.xushuzhan.quiltnews.presenter.AllDiscussPresenter;
import com.xushuzhan.quiltnews.ui.adapter.NewsAdapter;
import com.xushuzhan.quiltnews.ui.adapter.NewsDiscussAdapter;
import com.xushuzhan.quiltnews.ui.iview.IAllDiscussView;

import java.util.ArrayList;

public class AllDiscussActivity extends AppCompatActivity implements IAllDiscussView {
    EasyRecyclerView easyRecyclerView;
    NewsDiscussAdapter newsDiscussAdapter;

    String newsUniqueKey;
    String url;
    String title;
    String picUrl;

    ImageButton back;
    TextView titleToolbar;
    AllDiscussPresenter allDiscussPresenter;
    RelativeLayout sendDiscuss;

    ImageView firstDiscussPic;
    TextView firstDiscuss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_discuss);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        picUrl = intent.getStringExtra("pic_url");
        newsUniqueKey = intent.getStringExtra("news_uniquekey");
        initView();
        initData();

        allDiscussPresenter = new AllDiscussPresenter(this, newsDiscussAdapter, AllDiscussActivity.this);
        allDiscussPresenter.showAllDiscuss();


    }

    private void initView() {
        easyRecyclerView = (EasyRecyclerView) findViewById(R.id.recycler_view_all_discuss);
        sendDiscuss = (RelativeLayout) findViewById(R.id.rl_all_discuss);
        sendDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allDiscussPresenter.sendDiscuss();
            }
        });

        back = (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        titleToolbar.setVisibility(View.INVISIBLE);

        firstDiscuss = (TextView) findViewById(R.id.tv_first_discuss);
        firstDiscussPic = (ImageView) findViewById(R.id.iv_fist_discuss);
    }

    private void initData() {
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        easyRecyclerView.setAdapterWithProgress(newsDiscussAdapter = new NewsDiscussAdapter(this));

    }


    @Override
    public String getNewaUniqueKey() {
        Log.d("AllDiscussPresenterTAG", "getNewaUniqueKey: " + newsUniqueKey);
        return newsUniqueKey;
    }

    @Override
    public String getNewsTitle() {
        return title;
    }

    @Override
    public String getNewsPicUrl() {
        return picUrl;
    }


    @Override
    public String getNewsUrl() {
        return url;
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(AllDiscussActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSofaPic(boolean isShow) {
        if (isShow) {
            firstDiscuss.setVisibility(View.VISIBLE);
            firstDiscussPic.setVisibility(View.VISIBLE);
        }else {
            firstDiscuss.setVisibility(View.INVISIBLE);
            firstDiscussPic.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        allDiscussPresenter = null;
    }

}

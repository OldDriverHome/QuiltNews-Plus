package com.xushuzhan.quiltnews.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.MyDiscussPresenter;
import com.xushuzhan.quiltnews.ui.adapter.MyDiscussAdapter;
import com.xushuzhan.quiltnews.ui.iview.IMyDiscussView;
import com.xushuzhan.quiltnews.ui.view.LikeButtonView;

public class MyDiscussActivity extends AppCompatActivity implements IMyDiscussView {
    EasyRecyclerView easyRecyclerView;
    MyDiscussAdapter myDiscussAdapter;
    MyDiscussPresenter myDiscussPresenter;

    LikeButtonView likeButtonView;

    ImageButton back;
    TextView titleToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_discuss);
        initView();
        initData();
        myDiscussPresenter = new MyDiscussPresenter(myDiscussAdapter,this);
        myDiscussPresenter.showNewsDiscussList();
        likeButtonView = (LikeButtonView) findViewById(R.id.like_button);
        likeButtonView.setVisibility(View.INVISIBLE);
    }



    private void initView() {
    easyRecyclerView = (EasyRecyclerView) findViewById(R.id.recycler_view_my_discuss);

        back= (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        titleToolbar.setText("我的评论");
    }

    private void initData() {
        myDiscussAdapter = new MyDiscussAdapter(MyDiscussActivity.this);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(MyDiscussActivity.this));
        easyRecyclerView.setAdapter(myDiscussAdapter);

        myDiscussAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                myDiscussPresenter.intentToNewsDetail(position);
            }
        });
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(MyDiscussActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void intentToNewsDetail(String newsTitle, String newsUrl, String picUrl, String uniqueKey) {
        Intent intent = new Intent(MyDiscussActivity.this,NewsDtailActivity.class);
        intent.putExtra("uniquekey", uniqueKey);
        intent.putExtra("url", newsUrl);
        intent.putExtra("title", newsTitle);
        intent.putExtra("pic_url", picUrl);
        startActivity(intent);
    }
}

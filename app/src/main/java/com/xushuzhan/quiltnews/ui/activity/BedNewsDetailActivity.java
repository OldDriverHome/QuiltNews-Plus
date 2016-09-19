package com.xushuzhan.quiltnews.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.BedNewsDetailPresenter;
import com.xushuzhan.quiltnews.ui.iview.IBedNewsDetailView;
import com.xushuzhan.quiltnews.ui.view.LikeButtonView;

public class BedNewsDetailActivity extends AppCompatActivity implements IBedNewsDetailView {
    String aid;
    WebView webView;
    ImageButton back;
    String CSS_STYPE = "<head><style>img{max-width:340px !important;}</style></head>";
    TextView title;
    BedNewsDetailPresenter bedNewsDetailPresenter;
    TextView newsTitle;
    TextView newsTime;
    LikeButtonView likeButtonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_news_detail);
        newsTitle = (TextView) findViewById(R.id.tv_bed_news_detail_title);
        newsTime = (TextView) findViewById(R.id.tv_bed_news_detail_time);
        Intent intent = getIntent();
        aid = intent.getStringExtra("aid");
        bedNewsDetailPresenter = new BedNewsDetailPresenter(this);
        initView();
    }

    private void initView() {
        likeButtonView = (LikeButtonView) findViewById(R.id.like_button);
        likeButtonView.setVisibility(View.INVISIBLE);
        webView = (WebView) findViewById(R.id.bed_news_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String
                    url) {
                view.loadUrl(url); // 根据传入的参数再去加载新的网页
                return false;
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setLoadWithOverviewMode(true);

        bedNewsDetailPresenter.showNewsDetail(aid);

        back= (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (TextView) findViewById(R.id.tv_title_toolbar);
        title.setText("睡前精选");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bedNewsDetailPresenter = null;
        webView.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        webView.destroy();
    }

    @Override
    public void showNewsDtail(String content) {
        webView.loadDataWithBaseURL(null, CSS_STYPE+content , "text/html", "utf-8",null);
    }

    @Override
    public void setTitleAndTime(String title, String time) {
        newsTime.setText(time);
        newsTitle.setText(title);
    }
}

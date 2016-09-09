package com.xushuzhan.quiltnews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.presenter.NewsDetailPresenter;
import com.xushuzhan.quiltnews.ui.iview.INewsDetailView;
import com.xushuzhan.quiltnews.utils.DialogPopup;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

public class NewsDtailActivity extends AppCompatActivity implements INewsDetailView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public static final String TAG = "NewsDtailActivity";
    String url;
    String title;
    String picUrl;
    String uniqueKey;
    WebView webView;
    NewsDetailPresenter newsDetailPresenter;
    RelativeLayout rlNewsDetailDiscuss;
    Button allNews;
    TextView allNewsCount;
    ImageButton ReadMode;
    ImageButton back;
    TextView titleToolbar;
    TextView discussCount;
    CheckBox collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dtail);
        Intent intent = getIntent();
        Log.d(TAG, "onCreate: " + intent.getStringExtra("url"));


        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        picUrl = intent.getStringExtra("pic_url");
        uniqueKey = intent.getStringExtra("uniquekey");


        Log.d(TAG, "onCreate: " + title + ">>>" + picUrl + ">>>" + uniqueKey);
        newsDetailPresenter = new NewsDetailPresenter(this);
        newsDetailPresenter.setCollect();
        initView();
        initData();
    }

    private void initData() {
        newsDetailPresenter.showNewsDetail(url);
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.web_view_news_dtail);
        WebSettings settings = webView.getSettings();
        if(!NewsInfo.isShowPic){
            settings.setLoadsImagesAutomatically(false);  //不支持自动加载图片
        }
        if(NewsInfo.FROM_VIEW_PAGE) {
            settings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String
                        url) {
                    return false; // 不允许打开新的网页
                }
            });
            NewsInfo.FROM_VIEW_PAGE = false;
        }else {
            settings.setJavaScriptEnabled(false);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String
                        url) {
                    //view.loadUrl(url); // 根据传入的参数再去加载新的网页
                    return true; // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
                }
            });
            settings.setTextZoom(100);
        }
        rlNewsDetailDiscuss = (RelativeLayout) findViewById(R.id.rl_write_discuss);
        rlNewsDetailDiscuss.setOnClickListener(this);
        allNews = (Button) findViewById(R.id.bt_news_detail_discuss);
        allNews.setOnClickListener(this);
        allNewsCount = (TextView) findViewById(R.id.tv_news_detail_discuss_count);
        allNewsCount.setOnClickListener(this);
        ReadMode = (ImageButton) findViewById(R.id.ib_toobar_read_mode);

        ReadMode.setVisibility(View.INVISIBLE);

        back = (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        titleToolbar.setVisibility(View.INVISIBLE);

        discussCount = (TextView) findViewById(R.id.tv_news_detail_discuss_count);

        collect = (CheckBox) findViewById(R.id.cb_news_detail_collect);
        collect.setOnCheckedChangeListener(this);
        newsDetailPresenter.showDiscussCount();
    }

    @Override
    public void showNewsDetail(String url) {
        webView.loadUrl(url);
    }


    @Override
    public void intentToAllDiscuss() {
        Intent intent = new Intent(NewsDtailActivity.this, AllDiscussActivity.class);
        intent.putExtra("news_uniquekey", uniqueKey);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("pic_url", picUrl);
        startActivity(intent);
    }

    @Override
    public void setDiscussCount(String count) {
        allNewsCount.setText(count);

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
    public String getNewsUniqueKey() {
        return uniqueKey;
    }

    @Override
    public String getNewsUrl() {
        return url;
    }

    @Override
    public Activity getActivity() {
        return NewsDtailActivity.this;
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(NewsDtailActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addDiscussCount() {
        discussCount.setText((Integer.parseInt(discussCount.getText().toString()) + 1) + "");
    }

    @Override
    public void setColectButton() {
        collect.setChecked(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsDetailPresenter = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_write_discuss:
                newsDetailPresenter.showPopupWindow();

                break;
            case R.id.bt_news_detail_discuss:
            case R.id.tv_news_detail_discuss_count:
                newsDetailPresenter.intentToAllDiscuss();
                break;
            default:
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (!NewsInfo.isCollect) {
                newsDetailPresenter.collect();
            }
            NewsInfo.isCollect = false;
        } else if (!isChecked) {
            newsDetailPresenter.unCollect();
            NewsInfo.isCollect = false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        webView.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        newsDetailPresenter.showDiscussCount();
    }
}

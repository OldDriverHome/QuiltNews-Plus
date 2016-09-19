package com.xushuzhan.quiltnews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.guide.GuideActivity;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;


public class SplashActivity extends Activity {
    public static final String TAG = "SplashActivity";
    public static final int START_ACTIVITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        ImageView imageView = (ImageView) findViewById(R.id.iv_icon_splash_activity);
        Glide.with(this).load(R.drawable.icon).into(imageView);

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.rv_start);
        Glide.with(this).load(R.drawable.bg_splash).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layout.setBackground(drawable);
                }
            }
        });
        handler.sendEmptyMessageDelayed(START_ACTIVITY, 1000);
        NewsInfo.isShowPic = true;//默认设置可以显示图片
        try {
            UserInfo.tryLogin();
            UserInfo.tryQQlogin();
            if (SharedPreferenceUtils.getString(APP.getAppContext(), "version") == null) {
                AVQuery<AVObject> avQuery = new AVQuery<>("update");
                avQuery.getInBackground("57bcf2ae1532bc006582b9de", new GetCallback<AVObject>() {
                    @Override
                    public void done(AVObject avObject, AVException e) {
                        try {
                            SharedPreferenceUtils.putString(APP.getAppContext(), "version", avObject.get("version").toString());
                            Log.d(TAG, "done: " + avObject.get("version").toString());
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception ee) {
            Log.d(TAG, "onCreate: " + ee.getMessage());
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_ACTIVITY:
                    SharedPreferences sharedPreferences = getSharedPreferences("isFirst", MODE_PRIVATE);
                    boolean isFirst = sharedPreferences.getBoolean("isFirst", true);
                    if (isFirst) {
                        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    finish();
                    break;
            }
        }
    };
}
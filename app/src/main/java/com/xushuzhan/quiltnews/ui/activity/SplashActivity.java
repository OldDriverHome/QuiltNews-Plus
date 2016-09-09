package com.xushuzhan.quiltnews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
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
                        }

                    }
                });
            }
        } catch (Exception ee) {
            Log.d(TAG, "onCreate: " + ee.getMessage());
        }
        handler.sendEmptyMessageDelayed(START_ACTIVITY, 2000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_ACTIVITY:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };
}

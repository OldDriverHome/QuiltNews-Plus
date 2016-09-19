package com.xushuzhan.quiltnews;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.jude.utils.JUtils;

/**
 * Created by xushuzhan on 2016/8/10.
 */
public class APP extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this,"8nXKYmqCyuf6rwbSCDFajmIF-gzGzoHsz","guvH4KYjEViOviWmEWUilWXD");
        mContext = getApplicationContext();
        JUtils.initialize(this);
    }
    public static Context getAppContext(){
        return mContext;
    }
}

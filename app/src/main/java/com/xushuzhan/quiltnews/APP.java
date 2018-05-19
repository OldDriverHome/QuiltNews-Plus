package com.xushuzhan.quiltnews;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.avos.avoscloud.AVOSCloud;
import com.jude.utils.JUtils;
import com.youku.cloud.player.YoukuPlayerConfig;

/**
 * Created by xushuzhan on 2016/8/10.
 */
public class APP extends MultiDexApplication {
    public static Context mContext;
    //请在这里输入你的应用的clientId，clientSecret
    public static final String CLIENT_ID_WITH_AD = "bf2470fe3dfc9afc";
    public static final String CLIENT_SECRET_WITH_AD = "91af6595b1835ba8bf140f619d6436bc";
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this,"8nXKYmqCyuf6rwbSCDFajmIF-gzGzoHsz","guvH4KYjEViOviWmEWUilWXD");
        mContext = getApplicationContext();
        JUtils.initialize(this);
        YoukuPlayerConfig.setLog(true);
        /**设置client_id和client_secret*/
        YoukuPlayerConfig.setClientIdAndSecret(CLIENT_ID_WITH_AD, CLIENT_SECRET_WITH_AD);
        /**sdk初始化*/
        YoukuPlayerConfig.onInitial(this);
    }
    public static Context getAppContext(){
        return mContext;
    }
}

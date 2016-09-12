package com.xushuzhan.quiltnews.modle.network.config;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class UserInfo {
    //昵称
    public static String nickName = null;
    //用户名
    public static String userName = null;
    public static boolean isQQLogin = false;
    public static boolean isNormalLogin = false;
    public static boolean TAG;
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String NICKNAME = "nick_name";


    public static void tryLogin() {
        String account = SharedPreferenceUtils.getString(APP.getAppContext(), UserInfo.ACCOUNT);
        String password = SharedPreferenceUtils.getString(APP.getAppContext(), UserInfo.PASSWORD);
        try {
            if (account != null && password != null) {
                AVUser.logInInBackground(account, password, new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if (e == null) {
                            userName = SharedPreferenceUtils.getString(APP.getAppContext(),"user_name");
                            nickName = SharedPreferenceUtils.getString(APP.getAppContext(),"nick_name");
                            isNormalLogin = true;
                        } else {
                            Log.d("TestActivityTAG", "done: 自动登录失败");
                        }
                    }
                });
            }
        } catch (Exception ee) {
            Log.d("TestActivityTAG", "done: 自动登录失败");
        }

    }

    public static void tryQQlogin(){
        if(SharedPreferenceUtils.getString(APP.getAppContext(),"open_id")!=null&& isNormalLogin==false){
            isQQLogin = true;
            userName = SharedPreferenceUtils.getString(APP.getAppContext(),"open_id");
            nickName = SharedPreferenceUtils.getString(APP.getAppContext(),"nick_name");
        }
    }
}

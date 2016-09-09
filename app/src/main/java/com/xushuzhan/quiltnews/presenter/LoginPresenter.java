package com.xushuzhan.quiltnews.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.tencent.tauth.IUiListener;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.impl.UserModle;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.activity.MainActivity;
import com.xushuzhan.quiltnews.ui.iview.IloginView;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;
import com.xushuzhan.quiltnews.utils.TextUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xushuzhan on 2016/8/12.
 */
public class LoginPresenter {
    IloginView iloginView;
    UserModle userModle;
    public LoginPresenter(IloginView iloginView){
        this.iloginView = iloginView;
        userModle = new UserModle();
    }

    public void login(){
        final String account= iloginView.getAccount();
        final String password=  iloginView.getPassword();
        if(!TextUtil.isEmail(account)){
            iloginView.setError(iloginView.getEditAccount(),"请输入正确的邮箱");
        } else if (password.length() < 7) {
            iloginView.setError(iloginView.getEditPassword(),"密码不能小于7位");
        } else {
            AVUser.logInInBackground(account, password, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (e == null) {
                        iloginView.showToast("登陆成功");
                        SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.ACCOUNT,account);
                        SharedPreferenceUtils.putString(APP.getAppContext(),UserInfo.PASSWORD,password);
                        SharedPreferenceUtils.putString(APP.getAppContext(),"object_id",avUser.getObjectId());
                        SharedPreferenceUtils.putString(APP.getAppContext(),"nick_name","匿名用户");
                        UserInfo.userName = account;
                        UserInfo.nickName = "匿名用户";
                        UserInfo.isNormalLogin = true;
                        iloginView.toMainActivity();
                    } else {
                        iloginView.showToast("用户名或密码错误，请重试");
                    }
                }
            });
        }
    }

    public void signUp(){
        iloginView.toSignUpActivity();
    }

    public void loginByQQ(){
        userModle.loginByQQ(iloginView.getActivity());
    }

    public IUiListener getIUilistener(){
        return userModle.getIUiListener();
    }

    public void intentToMainActivity(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }
}

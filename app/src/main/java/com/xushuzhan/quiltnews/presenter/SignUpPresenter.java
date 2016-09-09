package com.xushuzhan.quiltnews.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.tencent.tauth.IUiListener;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.impl.UserModle;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.activity.MainActivity;
import com.xushuzhan.quiltnews.ui.iview.ISignUpView;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;
import com.xushuzhan.quiltnews.utils.TextUtil;

/**
 * Created by xushuzhan on 2016/8/12.
 */
public class SignUpPresenter {
    ISignUpView iSignUpView;
    UserModle userModle;
    public SignUpPresenter(ISignUpView iSignUpView) {
        this.iSignUpView = iSignUpView;
        userModle = new UserModle();
    }

    public void signUp() {
        ProgressDialog progressDialog = new ProgressDialog
                (iSignUpView.getActivity());
        progressDialog.setTitle("提示：");
        progressDialog.setMessage("正在注册，请稍等...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        final String account = iSignUpView.getAccount();
        final String password = iSignUpView.getPassword();
        if (!TextUtil.isEmail(account)) {
            iSignUpView.setError(iSignUpView.getEditTextAccount(), "请输入正确的邮箱");
        } else if (password == null || password.length() < 7) {
            iSignUpView.showError(iSignUpView.getEditTextPassword(), "密码不能小于了7位！");
        } else {
            final AVUser user = new AVUser();
            user.setUsername(account);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        iSignUpView.showToast("注册成功");
                        SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.ACCOUNT, account);
                        SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.PASSWORD, password);
                        SharedPreferenceUtils.putString(APP.getAppContext(),"object_id",user.getObjectId());
                        SharedPreferenceUtils.putString(APP.getAppContext(),"nick_name","匿名用户");
                        UserInfo.userName = account;
                        UserInfo.nickName = "匿名用户";
                        UserInfo.isNormalLogin = true;
                        iSignUpView.moveToMainActivity();

                    } else {
                        Log.d("789789", "done: " + e.getCode());
                        if (e.getCode() == 202) {
                            Toast.makeText(APP.getAppContext(), "用户已存在", Toast.LENGTH_SHORT).show();
                        } else {
                            iSignUpView.showToast("注册失败，请检查你的网络连接！");
                        }
                    }
                }
            });
        }
        progressDialog.dismiss();
    }

    public void loginByQQ(){
        userModle.loginByQQ(iSignUpView.getActivity());
    }
    public IUiListener getIUilistener(){
        return userModle.getIUiListener();
    }

    public void intentToMainActivity(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }
}

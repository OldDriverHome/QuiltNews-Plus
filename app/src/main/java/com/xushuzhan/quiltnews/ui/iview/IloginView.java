package com.xushuzhan.quiltnews.ui.iview;

import android.app.Activity;
import android.widget.EditText;

/**
 * Created by xushuzhan on 2016/8/12.
 */
public interface IloginView {
    /**登录按钮的操作*/

    //的到用户名与密码
    String getAccount();

    String getPassword();

    //显示与消失progressbar
    void showLoading();

    void hintLoading();

    //登录成功与失败的处理
    void toMainActivity();
    void showToast(String content);

    //获得EditText
    EditText getEditAccount();

    EditText getEditPassword();

    //设置错误信息

    void setError(EditText editText,String content);

    //跳转这注册页面

    void toSignUpActivity();


    //获取当前的Activity
    Activity getActivity();
}

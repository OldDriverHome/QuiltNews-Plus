package com.xushuzhan.quiltnews.ui.iview;

import android.app.Activity;
import android.widget.EditText;

/**
 * Created by xushuzhan on 2016/8/12.
 */
public interface ISignUpView {
    //获取用户名和密码
    String getAccount();
    String getPassword();

    //显示和隐藏dialog
    void showDialog(String content);
   // void hintDialog(String content);
    //成功和失败对应的操作
    void moveToMainActivity();
    void showToast(String content);

    //设置输入框的Error
    void showError(EditText editText,String content);

    //获得EditText的实例
    EditText getEditTextAccount();
    EditText getEditTextPassword();

    //设置错误信息

    void setError(EditText editText,String content);

    Activity getActivity();
}

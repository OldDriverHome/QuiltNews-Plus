package com.xushuzhan.quiltnews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tauth.Tencent;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.LoginPresenter;
import com.xushuzhan.quiltnews.ui.fragment.bottom.PersonalCenterFragment;
import com.xushuzhan.quiltnews.ui.iview.IloginView;
import com.xushuzhan.quiltnews.ui.view.LikeButtonView;

public class LoginActivity extends AppCompatActivity implements IloginView, View.OnClickListener {

    public static final String TAG = "LoginActivity";
    EditText account;
    EditText password;
    RelativeLayout login;
    TextView signUpNow;
    LoginPresenter loginPresenter;
    TextView title;
    ImageButton back;
    LikeButtonView likeButton;
    RelativeLayout loginByQQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.tv_title_toolbar);
        title.setText(getResources().getText(R.string.login));
        back = (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setOnClickListener(this);

        account = (EditText) findViewById(R.id.login_in_account);
        password = (EditText) findViewById(R.id.login_in_password);
        login = (RelativeLayout) findViewById(R.id.login_in_now);
        login.setOnClickListener(this);
        signUpNow = (TextView) findViewById(R.id.tv_login_sign_up_now);
        signUpNow.setOnClickListener(this);

        loginByQQ = (RelativeLayout) findViewById(R.id.qq_login);
        loginByQQ.setOnClickListener(this);

        likeButton = (LikeButtonView) findViewById(R.id.like_button);
        likeButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public String getAccount() {
        return account.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hintLoading() {

    }

    @Override
    public void toMainActivity() {
        //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        setResult(PersonalCenterFragment.RESULT_OK);
        finish();

    }

    @Override
    public void showToast(String content) {
        Toast.makeText(LoginActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public EditText getEditAccount() {
        return account;
    }

    @Override
    public EditText getEditPassword() {
        return password;
    }

    @Override
    public void setError(EditText editText, String content) {
        editText.setError(content);
    }

    @Override
    public void toSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivityForResult(intent,1);
    }
    @Override
    public Activity getActivity() {
        return LoginActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_toolbar_back:
                finish();
                break;
            case R.id.login_in_now:
                loginPresenter.login();
                break;
            case R.id.tv_login_sign_up_now:
                loginPresenter.signUp();
                break;
            case R.id.qq_login:
                loginPresenter.loginByQQ();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, loginPresenter.getIUilistener());

        switch (resultCode){
            case PersonalCenterFragment.RESULT_OK:
                setResult(PersonalCenterFragment.RESULT_OK);
                finish();
                break;
            case PersonalCenterFragment.RESULT_NO:
                break;
        }
    }

}

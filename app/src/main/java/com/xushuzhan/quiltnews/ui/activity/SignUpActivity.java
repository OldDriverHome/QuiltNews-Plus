package com.xushuzhan.quiltnews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tauth.Tencent;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.SignUpPresenter;
import com.xushuzhan.quiltnews.ui.iview.ISignUpView;

public class SignUpActivity extends AppCompatActivity implements ISignUpView, View.OnClickListener {
    public static final String TAG = "SignUpActivity";
    ImageView Back;
    TextView Title;
    EditText account;
    EditText password;
    SignUpPresenter signUpPresenter;
    RelativeLayout signUp;
    ImageButton ReadMode;
    RelativeLayout qqLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        signUpPresenter = new SignUpPresenter(this);
    }

    private void initView() {
        Back = (ImageView) findViewById(R.id.ib_toolbar_back);
        Back.setImageResource(R.drawable.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Title = (TextView) findViewById(R.id.tv_title_toolbar);
        Title.setText(getResources().getText(R.string.sign_up));

        account = (EditText) findViewById(R.id.sign_up_account);

        password = (EditText) findViewById(R.id.sign_up_password);

        signUp = (RelativeLayout) findViewById(R.id.sign_up_now);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpPresenter.signUp();
                Log.d("987654321", "onClick: ");
            }
        });

        ReadMode = (ImageButton) findViewById(R.id.ib_toobar_read_mode);
        ReadMode.setVisibility(View.INVISIBLE);

        qqLogin = (RelativeLayout) findViewById(R.id.rl_qq_login_sign_up);
        qqLogin.setOnClickListener(this);
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
    public void showDialog(String content) {

    }

    @Override
    public void moveToMainActivity() {
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(SignUpActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(EditText editText, String content) {
        editText.setError(content);
    }

    @Override
    public EditText getEditTextAccount() {
        return account;
    }

    @Override
    public EditText getEditTextPassword() {
        return password;
    }

    @Override
    public void setError(EditText editText, String content) {
        editText.setError(content);
    }

    @Override
    public Activity getActivity() {
        return SignUpActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signUpPresenter = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_toolbar_back:
                finish();
                break;
            case R.id.sign_up_now:
                signUpPresenter.signUp();
                break;
            case R.id.rl_qq_login_sign_up:
                signUpPresenter.loginByQQ();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data,signUpPresenter.getIUilistener());
        finish();
        signUpPresenter.intentToMainActivity(SignUpActivity.this);

    }
}

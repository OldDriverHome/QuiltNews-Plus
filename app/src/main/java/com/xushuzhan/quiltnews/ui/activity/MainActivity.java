package com.xushuzhan.quiltnews.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.ui.fragment.bottom.BeforeBedNewsFragment;
import com.xushuzhan.quiltnews.ui.fragment.bottom.DynamicFragment;
import com.xushuzhan.quiltnews.ui.fragment.bottom.EyeshotNewsFragment;
import com.xushuzhan.quiltnews.ui.fragment.bottom.HotNewsFragment;
import com.xushuzhan.quiltnews.ui.fragment.bottom.PersonalCenterFragment;
import com.xushuzhan.quiltnews.ui.view.LikeButtonView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    RadioGroup radioGroup;
    FragmentManager fragmentManager;
    TextView title;
    ImageButton back;
    LikeButtonView likeButtonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        SharedPreferences sharedPreferences = getSharedPreferences("isFirst", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirst", false);
        editor.apply();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction1 = fragmentManager.beginTransaction();
        transaction1.replace(R.id.content, new HotNewsFragment());
        transaction1.commit();
        radioGroup = (RadioGroup) findViewById(R.id.tab);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.bottom_hot_news:
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.content, new HotNewsFragment());
                        transaction.commit();
                        break;
                    case R.id.bottom_eyeshot_news:
                        FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                        transaction1.replace(R.id.content, new EyeshotNewsFragment());
                        transaction1.commit();
                        break;
                    case R.id.bottom_before_bed_news:
                        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                        transaction2.replace(R.id.content, new BeforeBedNewsFragment());
                        transaction2.commit();
                        break;
                    case R.id.bottom_personal_center:
                        FragmentTransaction transaction3 = fragmentManager.beginTransaction();
                        transaction3.replace(R.id.content, new PersonalCenterFragment());
                        transaction3.commit();
                        break;

                    case R.id.bottom_dynamic:
                        FragmentTransaction transaction4 = fragmentManager.beginTransaction();
                        transaction4.replace(R.id.content, new DynamicFragment());
                        transaction4.commit();
                        break;
                }
            }
        });


        back= (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setVisibility(View.INVISIBLE);

        title = (TextView) findViewById(R.id.tv_title_toolbar);
        title.setText("被窝资讯");

        likeButtonView = (LikeButtonView) findViewById(R.id.like_button);
        likeButtonView.setVisibility(View.INVISIBLE);
    }



    private long exitTime = 0;// 退出时间

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 判断间隔时间 大于2秒就退出应用
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                // 计算两次返回键按下的时间差
                exitTime = System.currentTimeMillis();
            } else {
                // 关闭应用程序
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

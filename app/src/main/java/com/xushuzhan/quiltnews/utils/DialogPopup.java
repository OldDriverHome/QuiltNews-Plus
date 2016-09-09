package com.xushuzhan.quiltnews.utils;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuzhan.quiltnews.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by xushuzhan on 2016/8/18.
 */
public class DialogPopup extends BasePopupWindow implements View.OnClickListener{
    private TextView sendDiscuss;
    private TextView titlePopup;
    EditText discussContent;
    //1.定义一个接口
    public static interface OnSendButtonClickListener {
        void onSendClick(View view,String content);
    }
    //2.申明一个接口类型的变量
    private OnSendButtonClickListener mOnItemClickListener = null;
    //3.创建一个setlisener的方法
    public void setOnItemClickListener(OnSendButtonClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public DialogPopup(Activity context,String title,String cofirmButton) {
        super(context);
        sendDiscuss= (TextView) findViewById(R.id.send_discuss);
        discussContent = (EditText) findViewById(R.id.content);
        titlePopup = (TextView) findViewById(R.id.popup_title);
        titlePopup.setText(title);
        sendDiscuss.setText(cofirmButton);

        setViewClickListener(this,sendDiscuss);
    }

    @Override
    protected Animation getShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return null;
    }

    @Override
    protected View getClickToDismissView() {
        return mPopupView;
    }

    @Override
    public View getPopupView() {
        return getPopupViewById(R.layout.popup_dialog);
    }

    @Override
    public View getAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_discuss:
                if(mOnItemClickListener!=null) {
                    mOnItemClickListener.onSendClick(v, discussContent.getText().toString());
                }
                break;
            default:
                break;
        }

    }
}

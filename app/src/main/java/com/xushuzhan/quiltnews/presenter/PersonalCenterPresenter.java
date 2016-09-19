package com.xushuzhan.quiltnews.presenter;

import android.app.ProgressDialog;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.iview.IpersonalCenterView;
import com.xushuzhan.quiltnews.utils.DialogPopup;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

/**
 * Created by xushuzhan on 2016/8/18.
 */
public class PersonalCenterPresenter {
    public static final String TAG = "PersonalCenterTAG";
    public static final int NO_UPDATE = 0;
    ProgressDialog progressDialog;
    DialogPopup dialogPopup;
    IpersonalCenterView iPersonalCenterView;
    public Uri imageUri;

    public PersonalCenterPresenter(IpersonalCenterView iPersonalCenterView) {
        this.iPersonalCenterView = iPersonalCenterView;
    }

    public void intentToLoginActivity() {
        iPersonalCenterView.intentToLogin();
    }

    public void setHeadPicture() {
        iPersonalCenterView.setHeadPicture();
    }

    public void intentToMyDiscuss() {
        iPersonalCenterView.intentToMyDiscuss();
    }

    public void hintHeadPicture() {
        iPersonalCenterView.hintHeadPicture();
    }

    public void signOut() {
        if (AVUser.getCurrentUser()!= null) {
            SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.ACCOUNT, null);
            SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.PASSWORD, null);
            SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.NICKNAME, null);
            SharedPreferenceUtils.putString(APP.getAppContext(), "open_id", null);
            SharedPreferenceUtils.putString(APP.getAppContext(), "token", null);
            SharedPreferenceUtils.putString(APP.getAppContext(), "object_id", null);
            UserInfo.nickName = null;
            UserInfo.userName = null;
            iPersonalCenterView.setNickName("点击登录");
        } else {
            iPersonalCenterView.showToast("请登录后再试");
        }

        AVUser.logOut();
    }

    public void editNickName() {
        dialogPopup = new DialogPopup(iPersonalCenterView.getMyActivity(), "请输入昵称", "确定");
        dialogPopup.showPopupWindow();

        dialogPopup.setOnItemClickListener(new DialogPopup.OnSendButtonClickListener() {
            @Override
            public void onSendClick(View view, String content) {
                dialogPopup.dismiss();
                iPersonalCenterView.hintEditNickButton();
                UserInfo.nickName = content;
                iPersonalCenterView.setNickName(content);
                Log.d(TAG, "onSendClick: "+SharedPreferenceUtils.getString(APP.getAppContext(), "object_id"));
                SharedPreferenceUtils.putString(APP.getAppContext(), "nick_name", content);
                AVObject user = AVObject.createWithoutData("_User", SharedPreferenceUtils.getString(APP.getAppContext(), "object_id"));
                // 修改 nick_name
                user.put("nick_name", content);
                // 保存到云端
                user.saveInBackground();

            }
        });
    }

    public void showIdead() {
        dialogPopup = new DialogPopup(iPersonalCenterView.getMyActivity(), "快把宝贵的意见告诉我们吧！", "发送");
        dialogPopup.showPopupWindow();

        dialogPopup.setOnItemClickListener(new DialogPopup.OnSendButtonClickListener() {
            @Override
            public void onSendClick(View view, String content) {
                iPersonalCenterView.showToast("感谢你的意见，我们一定会不断改进！");
                dialogPopup.dismiss();
            }
        });
    }

    public void checkUpdate() {
        progressDialog = new ProgressDialog
                (iPersonalCenterView.getMyActivity());
        progressDialog.setMessage("正在检查更新...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        AVQuery<AVObject> avQuery = new AVQuery<>("update");
        avQuery.getInBackground("57bcf2ae1532bc006582b9de", new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                try {
                    if (avObject.get("version").toString().equals(SharedPreferenceUtils.getString(APP.getAppContext(), "version"))) {
                        iPersonalCenterView.showToast("已经是最新版了");
                    } else {
                        iPersonalCenterView.showToast("发现新版本，快去市场更新吧");
                    }
                    progressDialog.dismiss();
                } catch (Exception ee) {
                    Log.d(TAG, "done: ee" + e.getMessage());
                }
            }
        });
    }

    public void setQQNickName() {
        if (SharedPreferenceUtils.getString(APP.getAppContext(), "nick_name")!=null&&!SharedPreferenceUtils.getString(APP.getAppContext(), "nick_name").equals("匿名用户")) {
            iPersonalCenterView.setNickName(SharedPreferenceUtils.getString(APP.getAppContext(), "nick_name"));
            UserInfo.nickName=SharedPreferenceUtils.getString(APP.getAppContext(), "nick_name");
            Log.d(TAG, "setQQNickName:  存在本地的nick_name");
        } else {Log.d(
                TAG, "setQQNickName:  尝试获取网络的NickName");
            String objectId = SharedPreferenceUtils.getString(APP.getAppContext(), "object_id");
            if (AVUser.getCurrentUser()!=null && objectId != null) {
                AVQuery<AVObject> avQuery = new AVQuery<>("_User");
                avQuery.getInBackground(objectId, new GetCallback<AVObject>() {
                    @Override
                    public void done(AVObject avObject, AVException e) {
                        try {
                            Log.d(TAG, "done: "+avObject.get("nick_name").toString());
                            if (avObject.get("nick_name").toString() != null) {
                                UserInfo.nickName = avObject.get("nick_name").toString();
                                iPersonalCenterView.setNickName(UserInfo.nickName);
                            } else {
                                UserInfo.nickName = "匿名用户";
                                iPersonalCenterView.setNickName("匿名用户");
                            }
                        } catch (Exception ee) {
                            Log.d(TAG, "setQQNickName: " + ee.getMessage());
                        }
                    }
                });
            }else {
                UserInfo.nickName = "匿名用户";
                iPersonalCenterView.setNickName("匿名用户");
            }
        }

    }


}

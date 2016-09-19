package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.bumptech.glide.Glide;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.presenter.PersonalCenterPresenter;
import com.xushuzhan.quiltnews.ui.activity.LoginActivity;
import com.xushuzhan.quiltnews.ui.activity.MyCollectionActivity;
import com.xushuzhan.quiltnews.ui.activity.MyDiscussActivity;
import com.xushuzhan.quiltnews.ui.iview.IpersonalCenterView;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

import java.io.FileNotFoundException;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

/**
 * Created by xushuzhan on 2016/8/15.
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener, IpersonalCenterView {
    public static final String TAG = "PersonalCenterTAG";
    public static final int RESULT_OK = 1;
    public static final int RESULT_NO = 0;
    View view;
    ImageView userLogin;
    ImageView ViewModeIV;
    RelativeLayout nightMode;
    RelativeLayout fontMode;
    RelativeLayout myDiscuss;
    RelativeLayout collect;
    RelativeLayout download;
    RelativeLayout idea;
    RelativeLayout update;
    RelativeLayout signOut;
    //    ImageView editNickName;
    TextView nickName;
    TextView ViewModeTV;

    ImageView ReadModeIV;
    TextView ReadModeTV;
    PersonalCenterPresenter personalCenterPresenter;
    boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_personal_certen, container, false);
        initView();
        personalCenterPresenter = new PersonalCenterPresenter(this);
        isLogin = AVUser.getCurrentUser() != null;
        setupNewHeadPicture();
        loadHeadPicture();
        checkInfo();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AVUser.getCurrentUser() != null && AVUser.getCurrentUser().get("nick_name") == null) {
            nickName.setText("设置昵称");
        }
    }

    private void initView() {
        ViewModeIV = (ImageView) view.findViewById(R.id.iv_font_mode);
        ViewModeTV = (TextView) view.findViewById(R.id.tv_font_mode);
        ReadModeIV = (ImageView) view.findViewById(R.id.iv_read_mode);
        ReadModeTV = (TextView) view.findViewById(R.id.tv_read_mode);
        nightMode = (RelativeLayout) view.findViewById(R.id.rl_personal_center_night_mode);
        nightMode.setOnClickListener(this);
        fontMode = (RelativeLayout) view.findViewById(R.id.rl_personal_center_font_mode);
        fontMode.setOnClickListener(this);
        myDiscuss = (RelativeLayout) view.findViewById(R.id.rl_personal_center_my_discuss);
        myDiscuss.setOnClickListener(this);
        userLogin = (ImageView) view.findViewById(R.id.iv_user_center_login);
        userLogin.setOnClickListener(this);
        collect = (RelativeLayout) view.findViewById(R.id.rl_pc_my_collect);
        collect.setOnClickListener(this);
        idea = (RelativeLayout) view.findViewById(R.id.rl_pc_idea);
        idea.setOnClickListener(this);
        update = (RelativeLayout) view.findViewById(R.id.rl_pc_check_update);
        update.setOnClickListener(this);
        nickName = (TextView) view.findViewById(R.id.personal_certen_login_now);
        signOut = (RelativeLayout) view.findViewById(R.id.rl_pc_sign_out);
        signOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_personal_center_night_mode:
                changeNightModel();
                break;
            case R.id.rl_personal_center_font_mode:
                if (!NewsInfo.isChecked) {
                    NewsInfo.isShowPic = false;
                    NewsInfo.isChecked = true;
                    ViewModeTV.setText(getResources().getText(R.string.picture_mode));
                    ViewModeIV.setImageResource(R.drawable.picture_mode);

                } else {
                    NewsInfo.isShowPic = true;
                    NewsInfo.isChecked = false;
                    ViewModeTV.setText(getResources().getText(R.string.font_mode));
                    ViewModeIV.setImageResource(R.drawable.font_mode);
                }
                break;
            case R.id.rl_personal_center_my_discuss:
                if (isLogin) {
                    personalCenterPresenter.intentToMyDiscuss();
                } else {
                    Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_user_center_login:
            case R.id.personal_certen_login_now:
                //personalCenterPresenter.intentToLoginActivity();
                break;
            case R.id.rl_pc_my_collect:
                if (isLogin) {
                    startActivity(new Intent(getContext(), MyCollectionActivity.class));
                } else {
                    Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
                }
                break;
//            case R.id.rl_pc_my_down:
//                Toast.makeText(getContext(), "抱歉-这个功能正在开发", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.rl_pc_idea:
                if (isLogin) {
                    personalCenterPresenter.showIdead();
                } else {
                    Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_pc_check_update:
                personalCenterPresenter.checkUpdate();
                break;
            case R.id.rl_pc_sign_out:
                if (isLogin) {
                    personalCenterPresenter.signOut();
                    Glide.with(getActivity()).load(R.drawable.ic_friend_1).into(userLogin);
                    isLogin = false;
                    setupNewHeadPicture();
                    nickName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            personalCenterPresenter.intentToLoginActivity();
                        }
                    });
                    Toast.makeText(getActivity(), "退出登录成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void intentToLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                isLogin = true;
                setupNewHeadPicture();
                loadHeadPicture();
                checkInfo();
                break;
            case RESULT_NO:
                break;

        }
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setHeadPicture() {
        if (AVUser.getCurrentUser() != null) {
            personalCenterPresenter.setQQNickName();

            Log.d(TAG, "setHeadPicture: 终于用到这个方法了");
        }
    }

    private void loadHeadPicture() {
        if (AVUser.getCurrentUser() == null) {
            Log.d(TAG, "loadHeadPicture: 用户未登录");
            Glide.with(getActivity()).load(R.drawable.ic_friend_1).into(userLogin);
        } else {
            if (AVUser.getCurrentUser().get("face_picture") == null) {
                Log.d(TAG, "loadHeadPicture: 用户头像为空");
                Glide.with(getActivity()).load(R.drawable.ic_friend_1).into(userLogin);
            } else {
                Log.d(TAG, "loadHeadPicture: 加载保存的头像");
                Glide.with(getActivity()).load(AVUser.getCurrentUser().getString("face_picture")).into(userLogin);
            }
        }
    }

    private void setupNewHeadPicture() {
        if (isLogin) {
            userLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxGalleryFinal
                            .with(getActivity())
                            .image()
                            .radio()
                            .crop()
                            .imageLoader(ImageLoaderType.GLIDE)
                            .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                                @Override
                                protected void onEvent(ImageRadioResultEvent imageRadioResultEvent)
                                        throws Exception {
                                    upLodeToLeanCloud(imageRadioResultEvent);
                                }
                            })
                            .openGallery();
                }
            });
        } else {
            userLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    personalCenterPresenter.intentToLoginActivity();
                }
            });
        }
    }

    private void upLodeToLeanCloud(ImageRadioResultEvent imageRadioResultEvent) throws FileNotFoundException {
        String fileName = AVUser.getCurrentUser().getObjectId();
        final String pictureUri = imageRadioResultEvent.getResult().getCropPath();
        final AVFile userPicture = AVFile.withAbsoluteLocalPath(fileName, pictureUri);
        userPicture.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Log.d(TAG, "done: 保存头像成功");
                    AVUser.getCurrentUser().put("face_picture", userPicture.getUrl());
                    AVUser.getCurrentUser().saveInBackground();
                    Log.d(TAG, "done: 头像地址" + userPicture.getUrl());
                    Glide.with(getActivity()).load(pictureUri).into(userLogin);
                } else {
                    Log.d(TAG, "done: " + e);
                }
                // TODO: 2016/9/9 取消进度条
            }
        }, new ProgressCallback() {
            @Override
            public void done(Integer integer) {
                showDialog(integer);
            }
        });
    }

    private void showDialog(Integer integer) {
        // TODO: 2016/9/9 头像上传进度条
    }

    @Override
    public void hintHeadPicture() {
        if (!isLogin) {
            userLogin.setImageResource(R.drawable.personl_certen_user);
            userLogin.setClickable(true);
            //nickName.setClickable(true);
            //nickName.setText(getResources().getText(R.string.login_now));
            //editNickName.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void intentToMyDiscuss() {
        startActivity(new Intent(getContext(), MyDiscussActivity.class));
    }

    @Override
    public Activity getMyActivity() {
        return PersonalCenterFragment.this.getActivity();
    }

    @Override
    public void hintEditNickButton() {
//        editNickName.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setNickName(String content) {
        nickName.setText(content);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        personalCenterPresenter = null;
    }

    private void checkInfo() {
        if (SharedPreferenceUtils.getString(APP.getAppContext(), "nick_name")!=null&&!SharedPreferenceUtils.getString(APP.getAppContext(), "nick_name").equals("匿名用户")) {
            nickName.setText(SharedPreferenceUtils.getString(APP.getAppContext(), "nick_name"));
        } else {
            if (AVUser.getCurrentUser() != null) {
                Log.d(TAG, "checkInfo: objectid=" + AVUser.getCurrentUser().getObjectId());
                AVQuery<AVObject> avQuery = new AVQuery<>("_User");
                avQuery.getInBackground(AVUser.getCurrentUser().getObjectId(), new GetCallback<AVObject>() {
                    @Override
                    public void done(AVObject avObject, AVException e) {
                        Log.d(TAG, "done: "+avObject);
                        if (avObject.get("nick_name") != null) {
                            if (!avObject.get("nick_name").toString().equals("")) {
                                nickName.setText(avObject.get("nick_name").toString());
                                SharedPreferenceUtils.putString(APP.getAppContext(), "nick_name", avObject.get("nick_name").toString());
                            } else {
                                nickName.setText("输入昵称");
                            }
                        }
                    }
                });
            }
        }

        nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    personalCenterPresenter.editNickName();
                } else {
                    personalCenterPresenter.intentToLoginActivity();
                }
            }
        });

        if (!NewsInfo.isChecked) {
            ViewModeTV.setText(getResources().getText(R.string.font_mode));
            ViewModeIV.setImageResource(R.drawable.font_mode);
        } else {
            ViewModeTV.setText(getResources().getText(R.string.picture_mode));
            ViewModeIV.setImageResource(R.drawable.picture_mode);
        }

        if (!NewsInfo.isNightMode) {
            ReadModeTV.setText(getResources().getText(R.string.nigt_mode));
            ReadModeIV.setImageResource(R.drawable.night_mode);
        } else {
            ReadModeTV.setText(getResources().getText(R.string.daytime_mode));
            ReadModeIV.setImageResource(R.drawable.daytime_mode);
        }
    }

    private void changeNightModel() {
        if (NewsInfo.isNightMode) {
            ReadModeIV.setImageResource(R.drawable.daytime_mode);
            ReadModeTV.setText(getResources().getText(R.string.nigt_mode));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            NewsInfo.isNightMode = false;
        } else {
            ReadModeIV.setImageResource(R.drawable.night_mode);
            ReadModeTV.setText(getResources().getText(R.string.daytime_mode));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            NewsInfo.isNightMode = true;

        }
        getActivity().recreate();
    }
}

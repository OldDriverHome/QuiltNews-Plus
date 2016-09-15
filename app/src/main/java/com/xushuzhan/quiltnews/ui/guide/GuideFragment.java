package com.xushuzhan.quiltnews.ui.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.ui.activity.MainActivity;

/**
 * Created by simonla on 2016/9/14.
 * Have a good day.
 */
public class GuideFragment extends Fragment {
    public static final String ARGUMENT_DRAWABLE = "TestFragment_drawable";
    public static final String ARGUMENT_STRING = "TestFragment_string";
    TextView mTvGuide;
    ImageView mIvGuide;
    Button mBtGuide;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_page, container, false);
        mBtGuide = (Button) view.findViewById(R.id.bt_guide);
        mIvGuide = (ImageView) view.findViewById(R.id.iv_guide);
        mTvGuide = (TextView) view.findViewById(R.id.tv_guide);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvGuide.setText(getArguments().getString(ARGUMENT_STRING));
        mIvGuide.setImageResource(getArguments().getInt(ARGUMENT_DRAWABLE));
        mBtGuide.setVisibility(View.GONE);
        mBtGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        if ("尽在被窝资讯".equals(getArguments().getString(ARGUMENT_STRING))) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setDuration(800);
            mBtGuide.setAnimation(alphaAnimation);
            mBtGuide.setVisibility(View.VISIBLE);
        }
    }
}

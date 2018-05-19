package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xushuzhan.quiltnews.R;

/**
 * Created by xushuzhan on 2018/5/19.
 * 动态的Fragment
 */

public class DynamicFragment extends Fragment{
    View mContentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_dynamic,container,false);
        return mContentView;
    }
}

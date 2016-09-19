package com.xushuzhan.quiltnews.ui.activity.SlidesActicity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xushuzhan on 2016/9/17.
 */
public class InfoFixedPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;


    /**
     * 接收传入的需要显示的Fragment
     *
     * @param fragments
     */
    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    /**
     * 这个是在继承FragmentStatePagerAdapter时写入的构造方法
     *
     * @param fm
     */
    public InfoFixedPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * 返回要显示的Fragment的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }

}
package com.xushuzhan.quiltnews.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xushuzhan on 2016/7/24.
 */
public class InfoFixedPageAdapter extends FragmentStatePagerAdapter {
    private String[] Titles;
    private List<Fragment> mFragment;

    /**
     * 接收传入的tab的标题
     * @param Titles
     * */
    public void setTitles(String[] Titles){
        this.Titles=Titles;
    }

    /***
     * 接收需要显示的fragment
     * @param fragment
     */
    public void setFragments(List<Fragment> fragment){
        mFragment=fragment;
    }

    public InfoFixedPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    /**
     * 返回选项卡的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
}

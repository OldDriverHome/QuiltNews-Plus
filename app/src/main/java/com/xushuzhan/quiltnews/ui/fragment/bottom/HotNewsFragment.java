package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.ui.adapter.InfoFixedPageAdapter;
import com.xushuzhan.quiltnews.ui.fragment.News.FirstTabFragment;
import com.xushuzhan.quiltnews.ui.fragment.News.OtherInfoPageFragment;

import java.util.ArrayList;
import java.util.List;

public class HotNewsFragment extends Fragment {
    public TabLayout mTabLayout;
    public ViewPager mViewPager;
    private InfoFixedPageAdapter infoFixedPageAdapter;
    String newsTitles[]  = new String[]{"top","keji","caijing","shehui","guonei","guoji","yule","tiyu","junshi","shishang"};

    //储存fragment的数组
    private List<Fragment> mFragments;
    //tab选项卡中的标题
    private String[] titles=new String[]{"头条","科技","财经","社会","国内","国际","娱乐","体育","军事","时尚"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_news_news, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout1);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager1);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoFixedPageAdapter = new InfoFixedPageAdapter(getChildFragmentManager());
        infoFixedPageAdapter.setTitles(titles);//标题
        mFragments = new ArrayList<>();
        mFragments.add(new FirstTabFragment());
        for (int i = 1; i < titles.length; i++) {
            //传入标题和page的id
            mFragments.add(OtherInfoPageFragment.newInstance(newsTitles[i]));
        }
        //把要显示的fragment集合传给adapter
        infoFixedPageAdapter.setFragments(mFragments);

        //设置tablayout的模式()
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //给viewPager设置适配器
        mViewPager.setAdapter(infoFixedPageAdapter);
        //TabLayout绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }
}

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
import com.xushuzhan.quiltnews.ui.fragment.video.VideoInfoPageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/8/15.
 */
public class EyeshotNewsFragment extends Fragment{
    public TabLayout mTabLayout;
    public ViewPager mViewPager;
    private InfoFixedPageAdapter infoFixedPageAdapter;
    String newsTitles[]  = new String[]{"搞笑","科技","娱乐","微电影","汽车","旅游","游戏"};

    //储存fragment的数组
    private List<Fragment> mFragments;
    //tab选项卡中的标题
    private String[] titles=new String[]{"搞笑","科技","娱乐","微电影","汽车","旅游","游戏"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eyeshot_news, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout3);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager_video);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoFixedPageAdapter = new InfoFixedPageAdapter(getChildFragmentManager());
        infoFixedPageAdapter.setTitles(titles);//标题
        mFragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            mFragments.add(VideoInfoPageFragment.newInstance(newsTitles[i]));
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

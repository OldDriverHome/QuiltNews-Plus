package com.xushuzhan.quiltnews.ui.fragment.News;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.presenter.FirstTabFragmentPresenter;
import com.xushuzhan.quiltnews.ui.activity.NewsDtailActivity;
import com.xushuzhan.quiltnews.ui.adapter.NewsAdapter;
import com.xushuzhan.quiltnews.ui.adapter.ViewPagerAdapter;
import com.xushuzhan.quiltnews.ui.iview.IFirstTabView;
import com.xushuzhan.quiltnews.utils.Utils;

/**
 * Created by xushuzhan on 2016/8/16.
 */
public class FirstTabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IFirstTabView {
    public static final int VIEW_PAGER = 0;
    View mView;
    private EasyRecyclerView recyclerView;
    private NewsAdapter adapter;
    private FirstTabFragmentPresenter firstTabFragmentPresenter;
    private Handler handler = new Handler();
    public static final String TAG = "FirstTabFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_first_viewpager_fragment
                , container, false);
        firstTabFragmentPresenter = new FirstTabFragmentPresenter(this);
        setRecyclerView();
        return mView;
    }

    private void setRecyclerView() {
        recyclerView = (EasyRecyclerView) mView.findViewById(R.id.recycler_view_view_pager_first);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapterWithProgress(adapter = new NewsAdapter(getContext()));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(final ViewGroup parent) {
                RollPagerView header = new RollPagerView(getContext());
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter((getContext()));
                header.setHintView(new ColorPointHintView(getContext(), getResources()
                        .getColor(R.color.material_color_yellow_300), Color.GRAY));
                header.setHintPadding(700, 0, 0, (int) Utils.convertDpToPixel(11, (getContext())));
                header.setPlayDelay(4000);
                header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , (int) Utils.convertDpToPixel(190, (getContext()))));
                header.setAdapter(viewPagerAdapter);
                header.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        try {
                            NewsInfo.FROM_VIEW_PAGE = true;
                            Intent intent = new Intent(getContext(), NewsDtailActivity.class);
                            intent.putExtra("url", ViewPagerAdapter.viewPagersContent.getNewslist()
                                    .get(position).getUrl());
                            intent.putExtra("title", ViewPagerAdapter.viewPagersContent.getNewslist()
                                    .get(position).getTitle());
                            intent.putExtra("pic_url", ViewPagerAdapter.viewPagersContent.getNewslist()
                                    .get(position).getPicUrl());
                            intent.putExtra("uniquekey", ViewPagerAdapter.viewPagersContent
                                    .getNewslist().get(position).getCtime());
                            startActivity(intent);
                        } catch (Exception e) {
                            Log.d(TAG, "onItemClick: " + e.getMessage());
                        }
                    }
                });

                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

        if (firstTabFragmentPresenter != null) {
            firstTabFragmentPresenter.showNewsList();
        }

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                firstTabFragmentPresenter.showNewsDetail(position);
            }
        });
        recyclerView.setRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "努力刷新中...", Toast.LENGTH_SHORT).show();
                firstTabFragmentPresenter.showNewsList();

                recyclerView.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void addDataToRecyclerView(NewsListBeen newsListBeen) {
        try {
            adapter.addAll(newsListBeen.getResult().getData());
        } catch (Exception e) {
        }
    }

    @Override
    public void intentToNewsDetailActivity(String url, String title, String picUrl, String uniquekey) {
        Intent intent = new Intent(getContext(), NewsDtailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("pic_url", picUrl);
        intent.putExtra("uniquekey", uniquekey);
        startActivity(intent);
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        firstTabFragmentPresenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        firstTabFragmentPresenter = null;
    }
}

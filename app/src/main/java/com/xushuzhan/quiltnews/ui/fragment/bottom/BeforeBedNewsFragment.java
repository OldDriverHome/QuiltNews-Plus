package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.network.config.ReadInfo;
import com.xushuzhan.quiltnews.presenter.BedNewsListPresenter;
import com.xushuzhan.quiltnews.ui.activity.BedNewsDetailActivity;
import com.xushuzhan.quiltnews.ui.adapter.BedNewsListAdapter;
import com.xushuzhan.quiltnews.ui.iview.IBedNewsListView;

public class BeforeBedNewsFragment extends Fragment implements IBedNewsListView, SwipeRefreshLayout.OnRefreshListener {
    BedNewsListPresenter bedNewsListPresenter;
    EasyRecyclerView easyRecyclerView;
    BedNewsListAdapter adapter;
    LinearLayout linearLayout;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_before_bed_news, container, false);
        initView();
        bedNewsListPresenter = new BedNewsListPresenter(this, adapter);
            if (!ReadInfo.isSleepTime) {
                bedNewsListPresenter.guideUser();
                bedNewsListPresenter.initTimer();
                bedNewsListPresenter.showBedNewsList();
            } else if (ReadInfo.isSleepTime) {
                linearLayout.setBackgroundResource(R.drawable.good_night_now);
            }
        return view;
    }


    private void initView() {
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_bed_news);
        easyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.recyclerview_bed_news);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        easyRecyclerView.setRefreshListener(this);
        adapter = new BedNewsListAdapter(getContext());
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                bedNewsListPresenter.intentToBedNewsDetail(position);
            }
        });
    }


    @Override
    public void onRefresh() {
        adapter.clear();
        if (!ReadInfo.isSleepTime) {
            bedNewsListPresenter.showBedNewsList();
            easyRecyclerView.setRefreshing(false);
        } else {
            easyRecyclerView.setRefreshing(false);
            linearLayout.setBackgroundResource(R.drawable.good_night_now);
            Toast.makeText(getContext(), "该睡觉了，亲", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showToast(String content) {

        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();
    }

    @Override
    public void intentToBenNewsDtail(String url) {
        Intent intent = new Intent(getContext(), BedNewsDetailActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bedNewsListPresenter = null;
    }
}

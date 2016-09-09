package com.xushuzhan.quiltnews.ui.fragment.News;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.presenter.OtherPageFragemntPresenter;
import com.xushuzhan.quiltnews.ui.activity.NewsDtailActivity;
import com.xushuzhan.quiltnews.ui.adapter.NewsAdapter;
import com.xushuzhan.quiltnews.ui.iview.IOtherPageView;

/**
 * Created by xushuzhan on 2016/7/24.
 */
public class OtherInfoPageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,IOtherPageView {
    public static final String TAG = "OtherInfoPageFragment";
    private static final String PAGER_POSITION = "position";
    private View mView;
    String title = null;
    private EasyRecyclerView recyclerView;
    private NewsAdapter adapter;
    OtherPageFragemntPresenter  otherPageFragemntPresenter;
    private Handler handler = new Handler();
    public static OtherInfoPageFragment newInstance(String title) {
        //利用bundle传值
        Bundle bundle = new Bundle();
        bundle.putString(PAGER_POSITION, title);

        //实例化
        OtherInfoPageFragment fragment = new OtherInfoPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        String title = null;
        //取出bundle中的值
        Bundle bundle = getArguments();
        if (bundle != null) {
            title =  bundle.getString(PAGER_POSITION);
        }
        if (mView == null) {

            mView = inflater.inflate(R.layout.fragment_info_viewpager_page, container, false);
        }
        otherPageFragemntPresenter = new OtherPageFragemntPresenter(this);
        setRecyclerView(title);
        return mView;
    }

    private void setRecyclerView(String title) {
        recyclerView= (EasyRecyclerView) mView.findViewById(R.id.recycler_view_view_pager);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapterWithProgress(adapter = new NewsAdapter(getContext()));
        Log.d(TAG, "setRecyclerView: "+title);
        otherPageFragemntPresenter.showNewsList(title);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                otherPageFragemntPresenter.showNewsDetail(position);
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
                otherPageFragemntPresenter.showNewsList(title);

                recyclerView.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void addDataToRecyclerView(NewsListBeen newsListBeen) {
        adapter.addAll(newsListBeen.getResult().getData());
    }

    @Override
    public void intentToNewsDetailActivity(String url, String title, String picUrl, String uniquekey) {
        Intent intent = new Intent(getContext(),NewsDtailActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        intent.putExtra("pic_url",picUrl);
        intent.putExtra("uniquekey",uniquekey);
        startActivity(intent);
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        otherPageFragemntPresenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        otherPageFragemntPresenter = null;
        Log.d(TAG, "onDestroyView: ");
    }
}

package com.xushuzhan.quiltnews.ui.activity.SlidesActicity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xushuzhan.quiltnews.R;

/**
 * Created by xushuzhan on 2016/9/17.
 */
public class InfoPageFragment extends Fragment {
    public static final String TAG = "InfoPageFragment";
    private static final String URL = "url";
    private static final String CONTENT = "content";
    private static final String TITLE = "title";
    private static final String TOTALPAGE = "total_page";
    private static final String NOWPAGE = "now_page";

    private View mView;
    ImageView slidesPic;
    TextView slidesTitle;
    TextView slidesContent;
    TextView slidesNowPage;
    TextView slidesTotalPage;

    private String url;
    private String content;
    private String title;
    private int nowPage;
    private int totalPage;

    /**
     * 在这里提供一个静态的方法来实例化PageFragment
     * 在这里我们传入一个参数，用来得到pageId
     * @return
     */
    public static InfoPageFragment newInstance(String url, String content, String title, int totalPage, int nowPage) {
        //利用bundle传值
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        bundle.putString(CONTENT, content);
        bundle.putString(TITLE, title);
        bundle.putInt(TOTALPAGE,totalPage);
        bundle.putInt(NOWPAGE,nowPage);
        //实例化
        InfoPageFragment fragment = new InfoPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //取出保存在Bundle中的值
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString(URL);
            content = bundle.getString(CONTENT);
            title = bundle.getString(TITLE);
            totalPage = bundle.getInt(TOTALPAGE);
            nowPage = bundle.getInt(NOWPAGE);
        }
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_slides, container, false);
        }
        slidesTitle = (TextView) mView.findViewById(R.id.tv_slide_title);
        slidesPic = (ImageView) mView.findViewById(R.id.iv_slide_pic);
        slidesContent = (TextView) mView.findViewById(R.id.tv_slide_content);
        slidesNowPage = (TextView) mView.findViewById(R.id.tv_now_page);
        slidesTotalPage = (TextView) mView.findViewById(R.id.tv_total_page);
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading)
                .into(slidesPic);
        Log.d(TAG, "onCreateView: "+url);
        slidesTitle.setText(title);
        slidesContent.setText(content);
        slidesNowPage.setText(String.valueOf(nowPage));
        slidesTotalPage.setText(String.valueOf("/"+totalPage));
        return mView;
    }

}
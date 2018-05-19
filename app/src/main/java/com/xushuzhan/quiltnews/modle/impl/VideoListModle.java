package com.xushuzhan.quiltnews.modle.impl;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.xushuzhan.quiltnews.modle.been.FinalVideoListBean;
import com.xushuzhan.quiltnews.modle.been.VideoBean;
import com.xushuzhan.quiltnews.modle.been.VideoListBean;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerVideo;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerVideoList;
import com.xushuzhan.quiltnews.ui.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class VideoListModle {
    List<VideoListBean.VideosBean> vides;
    public static final String TAG = "VideoListModle";
    VideoAdapter adapter;

    public VideoListModle(VideoAdapter adapter) {
        this.adapter = adapter;
        vides = new ArrayList<VideoListBean.VideosBean>();
    }

    public void getVedioList(final String category, String count, String page) {
        Subscriber<VideoListBean> subscriber = new Subscriber<VideoListBean>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "getVedioList: 请求完成了");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:  请求优酷的的时候： " + e.getMessage());
            }

            @Override
            public void onNext(VideoListBean videoListBean) {
                //请求完成;
                vides.add(videoListBean.getVideos().get(0));
                vides.add(videoListBean.getVideos().get(1));
                vides.add(videoListBean.getVideos().get(2));
                vides.add(videoListBean.getVideos().get(3));
                vides.add(videoListBean.getVideos().get(4));

                for (int i = 0; i < videoListBean.getVideos().size(); i++) {
                    VideoListBean.VideosBean videoBean = videoListBean.getVideos().get(i);
                    FinalVideoListBean fvl = new FinalVideoListBean();
                    fvl.setTitle(videoBean.getTitle());
                    fvl.setThumbnail_pic_s(videoBean.getBigThumbnail());
                    fvl.setUrl(videoBean.getId());
                    fvl.setPlayCount(videoBean.getView_count());
                    fvl.setPublishTime(videoBean.getPublished());
                    Log.d(TAG, "所有视频的真是链接》》来自接口: " + videoBean.getId());
                    adapter.add(fvl);
                }

            }
        };
        RequestManagerVideoList.getInstance().getVideoList(subscriber, category, count, page);
    }


    //从服务器上获取
//    public void findUsefulUrlById(final VideoListBean videoListBean) {
//        //根据ID查视频
//        final AVQuery<AVObject> query1 = new AVQuery<>("video");
//        query1.whereEqualTo("video_id", videoListBean.getVideos().get(0).getId());
//        final AVQuery<AVObject> query2 = new AVQuery<>("video");
//        query2.whereEqualTo("video_id", videoListBean.getVideos().get(1).getId());
//        final AVQuery<AVObject> query3 = new AVQuery<>("video");
//        query3.whereEqualTo("video_id", videoListBean.getVideos().get(2).getId());
//        final AVQuery<AVObject> query4 = new AVQuery<>("video");
//        query4.whereEqualTo("video_id", videoListBean.getVideos().get(3).getId());
//        final AVQuery<AVObject> query5 = new AVQuery<>("video");
//        query5.whereEqualTo("video_id", videoListBean.getVideos().get(4).getId());
//
//        AVQuery<AVObject> query = AVQuery.or(Arrays.asList(query1, query2, query3, query4, query5));
////        query.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
////        query.setMaxCacheAge(24 * 3600); //设置缓存有效期
//        query.orderByAscending("createdAt");
//        query.findInBackground(new FindCallback<AVObject>() {
//            @Override
//            public void done(List<AVObject> list, AVException e) {
//                if (e != null) {
//                    Log.d(TAG, "done: 请求服务器的时候出错了>" + e.getMessage());
//                }
//                try {
//                    Log.d(TAG, "请求服务器完成，返回的数据总大小是： " + list.size());
//                    //返回符合条件的视频信息
//                    if (list.size() > 0 && list.size() < 5) {
//                        Log.d(TAG, "尴尬，视频部分更新了 ：" + list.size());
//                       for(int j=0;j<list.size();j++) {
//                           FinalVideoListBean fvl = new FinalVideoListBean();
//                           fvl.setTitle(list.get(j).get("title").toString());
//                           fvl.setThumbnail_pic_s(list.get(j).get("pic_url").toString());
//                           fvl.setUrl(list.get(j).get("real_url").toString());
//                           fvl.setPlayCount(list.get(j).get("view_count").toString());
//                           fvl.setPublishTime(list.get(j).get("publish_time").toString());
//                           adapter.add(fvl);
//                           for (int i = 0; i < vides.size(); i++) {
//                               if (vides.get(i).getLink().equals(list.get(j).get("yk_url"))){
//                                   vides.remove(i);
//                               }
//
//                           }
//                       }
//
//                        for(int k=0;k<vides.size();k++){
//                            findUrlUsefulByAPI(
//                                    vides.get(k).getLink(),
//                                    vides.get(k).getTitle(),
//                                    vides.get(k).getThumbnail(),
//                                    vides.get(k).getId(),
//                                    vides.get(k).getView_count(),
//                                    vides.get(k).getPublished()
//                            );
//                            Log.d(TAG, "需要更新的视频是》》》》》》》"+vides.get(k).getTitle());
//                        }
//                    }
//
//                    if (list.size() >= 5) {
//                        try {
//                            for (int i = 0; i < 5; i++) {
//                                FinalVideoListBean fvl = new FinalVideoListBean();
//                                fvl.setTitle(list.get(i).get("title").toString());
//                                fvl.setThumbnail_pic_s(list.get(i).get("pic_url").toString());
//                                fvl.setUrl(list.get(i).get("real_url").toString());
//                                fvl.setPlayCount(list.get(i).get("view_count").toString());
//                                fvl.setPublishTime(list.get(i).get("publish_time").toString());
//                                Log.d(TAG, "所有视频的真实链接》》来自服务器: " + list.get(i).get("real_url").toString());
//                                videoList.add(fvl);
//                                adapter.add(fvl);
//                                Log.d(TAG, "添加到Recyclerview完成》》来自服务器 ");
//                            }
//                        } catch (Exception ee) {
//                            Log.d(TAG, "来服务器的错误" + ee.getMessage());
//                        }
//
//                    }
//                    if (list.size() == 0) {
//                        for (int j = 0; j < videoListBean.getVideos().size(); j++) {
//                            findUrlUsefulByAPI(videoListBean.getVideos().get(j).getLink(),
//                                    videoListBean.getVideos().get(j).getTitle(),
//                                    videoListBean.getVideos().get(j).getThumbnail(),
//                                    videoListBean.getVideos().get(j).getId(),
//                                    videoListBean.getVideos().get(j).getView_count(),
//                                    videoListBean.getVideos().get(j).getPublished()
//                            );
//                        }
//
//                        Log.d(TAG, "done: 没有查到任何数据");
//                    }
//                } catch (Exception ee) {
//                }
//            }
//
//
//        });
//    }

    //调用API转换
    public void findUrlUsefulByAPI(final String youkuURL, final String title, final String picUrl, final String videoId, final String viewCount, final String publishTime) {
        Subscriber<VideoBean> subscriber = new Subscriber<VideoBean>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "findUrlUsefulByAPI: 请求完成了");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: 转换优酷地址的时候（API） " + e.getMessage());
            }

            @Override
            public void onNext(VideoBean videoBean) {
                //请求完成;
                FinalVideoListBean fvl = new FinalVideoListBean();
                fvl.setTitle(title);
                fvl.setThumbnail_pic_s(picUrl);
                fvl.setUrl(videoBean.getMp4());
                fvl.setPlayCount(viewCount);
                fvl.setPublishTime(publishTime);
                Log.d(TAG, "所有视频的真是链接》》来自接口: " + videoBean.getMp4());
                adapter.add(fvl);

                Log.d(TAG, "添加到Recyclerview完成》》来自接口 ");
                AVObject video = new AVObject("video");// 构建对象
                video.put("video_id", videoId);
                video.put("yk_url", youkuURL);
                video.put("real_url", videoBean.getMp4());
                video.put("pic_url", picUrl);
                video.put("view_count", viewCount);
                video.put("publish_time", publishTime);
                video.put("title", title);
                video.put("publish_time", publishTime);
                video.saveInBackground();// 保存到服务端
            }
        };

        RequestManagerVideo.getInstance().getVideoUrl(subscriber, youkuURL);

    }
}

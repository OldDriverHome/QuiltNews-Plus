package com.xushuzhan.quiltnews.cache;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by simonla on 2016/9/18.
 * Have a good day.
 */
public class Cache<T> {

    public static final String TAG = "Cache";
    private CacheFileManger mCacheFileManger;
    private Context mContext;

    public Cache(Context context) {
        mContext = context;
        mCacheFileManger = new CacheFileManger(mContext);
    }

    public void saveCache(T o) {
        try {
            mCacheFileManger.writeToCache(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public T getCache(T name) {
        try {
            Gson gson = new Gson();
            return (T) gson.fromJson(mCacheFileManger.readFromCache(name.getClass().getSimpleName()), name.getClass());
        } catch (IOException e) {
            return null;
        }
    }
}

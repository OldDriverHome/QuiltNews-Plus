package com.xushuzhan.quiltnews.cache;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by simonla on 2016/9/18.
 * Have a good day.
 */
public class Cache {

    public static final String TAG = "Cache";

    public void saveCache(Object o) {
        try {
                CacheFileManger.writeToCache(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public Object getCache(String name) {
        try {
          return   CacheFileManger.readFromCache(name);
        } catch (IOException e) {
            return null;
        }
    }
}

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
            Log.d(TAG, "saveCache: "+CacheFileManger.add(o));
           // if (CacheFileManger.add(o)) {
                CacheFileManger.writeToCache(o);
           // }
        } catch (IOException e) {
            Log.d(TAG, "saveCache: "+e.getMessage());
        }
    }

    @Nullable
    public Object getCache(String name) {
        try {
          return   CacheFileManger.readFromCache(name);
        } catch (IOException e) {
            Log.d(TAG, "getCache: "+e.getMessage());
            return null;
        }
    }
}

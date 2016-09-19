package com.xushuzhan.quiltnews.cache;

import android.util.Log;

import com.google.gson.Gson;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 * Created by simonla on 2016/9/18.
 * Have a good day.
 */
public class CacheFileManger {

    public static final String TAG = "CacheFileManger";
    public static final String CACHE_DIR = APP.getAppContext().getCacheDir().getPath();

    public static boolean add(Object o) throws IOException {
        Log.d(TAG, "add: " + o.getClass().getSimpleName());
        File cacheFile = new File(CACHE_DIR + o.getClass().getSimpleName());
        Log.d(TAG, "add: " + cacheFile.exists());
        cacheFile.deleteOnExit();
        return cacheFile.createNewFile();
    }

    protected static void writeToCache(Object o) throws IOException {
        File file = new File(CACHE_DIR + o.getClass().getSimpleName());
        Gson gson = new Gson();
        String in = gson.toJson(o);
        Writer out = new FileWriter(file);
        out.write(in);
        out.close();
        Log.d(TAG, "writeToCache: " + in);
    }

    protected static NewsListBeen readFromCache(String name) throws IOException {
        File file = new File(CACHE_DIR + name);
        if (!file.exists()) {
            return null;
        } else {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bReader = new BufferedReader(read);
            String s = bReader.readLine();
            Log.d(TAG, "readFromCache: "+s);
            Gson gson = new Gson();
            //// TODO: 2016/9/18 泛型
            return gson.fromJson(s, NewsListBeen.class);
        }
    }
}


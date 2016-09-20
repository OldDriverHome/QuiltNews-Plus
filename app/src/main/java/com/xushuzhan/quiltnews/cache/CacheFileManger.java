package com.xushuzhan.quiltnews.cache;

import android.content.Context;

import com.google.gson.Gson;

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
    private String mCacheDir;

    public CacheFileManger(Context context) {
        mCacheDir = context.getCacheDir().getPath();
    }

    public boolean add(Object o) throws IOException {
        File cacheFile = new File(mCacheDir + o.getClass().getSimpleName());
        cacheFile.deleteOnExit();
        return cacheFile.createNewFile();
    }

    protected void writeToCache(Object o) throws IOException {
        File file = new File(mCacheDir + o.getClass().getSimpleName());
        Gson gson = new Gson();
        String in = gson.toJson(o);
        Writer out = new FileWriter(file);
        out.write(in);
        out.close();
    }

    protected String readFromCache(String name) throws IOException {
        File file = new File(mCacheDir + name);
        if (!file.exists()) {
            return null;
        } else {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bReader = new BufferedReader(read);
            //// TODO: 2016/9/18 泛型
            return bReader.readLine();
        }
    }
}


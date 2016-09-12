package com.xushuzhan.quiltnews.utils;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by simonla on 2016/9/11.
 * Have a good day.
 */
public class BitmapCount {

    private Bitmap mBitmap;
    public static final String TAG = "BitmapCount";

    public BitmapCount(Bitmap bitmap) {
        mBitmap = cutBitmap(bitmap);
    }

    private Bitmap cutBitmap(Bitmap bitmap) {
        bitmap = Bitmap.createBitmap(bitmap, 0, (getYPixel(bitmap) * 9) / 10,
                getXPixel(bitmap), getYPixel(bitmap) / 10);
        return bitmap;
    }

    private int getXPixel(Bitmap bitmap) {
        return bitmap.getWidth();
    }

    private int getYPixel(Bitmap bitmap) {
        return bitmap.getHeight();
    }

    private void count() {
        Log.d(TAG, "count: " + countRgbAverage());
        int rgbAverage = countRgbAverage();
    }

    private int countRgbAverage() {
        int[] rgbList = new int[100];
        final int count = 100;
        for (int i = 0; i < count / 2; i++) {
            rgbList[i] = getRgb((getXPixel(mBitmap) * i) / 50, getYPixel(mBitmap) / 3);
        }
        for (int i = count / 2; i < count; i++) {
            rgbList[i] = getRgb((getXPixel(mBitmap) * i) / 50, (getYPixel(mBitmap) * 2) / 3);
        }
        int total = 0;
        for (Integer i : rgbList) {
            total += i;
        }
        return total / count;
    }

    private int getRgb(int x, int y) {
        return mBitmap.getPixel(x, y);
    }
}

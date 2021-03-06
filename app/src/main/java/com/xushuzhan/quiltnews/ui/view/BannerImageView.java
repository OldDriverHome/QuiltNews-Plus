package com.xushuzhan.quiltnews.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.xushuzhan.quiltnews.R;

/**
 * Created by simonla on 2016/9/10.
 * Have a good day.
 */
public class BannerImageView extends ImageView {

    public static final String TAG = "BannerImageView";

    private Paint mPaint;
    private String mText;
    private BlurMaskFilter mBlurMaskFilter;
    private Bitmap mBitmap;
    //public static final int BLUR = 3;

    public void setText(String text) {
        mText = text;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public BannerImageView(Context context) {
        super(context);
        mPaint = new Paint();
        //mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    public BannerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    public BannerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        //mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BannerImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint();
        //mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.reset();
        mPaint.setColor(getResources().getColor(R.color.material_color_blue_gray_800));
        mPaint.setAlpha(100);
        mPaint.setAntiAlias(true);
        canvas.drawRect(0, (80 * getHeight()) / 100, getWidth(), getHeight(), mPaint);
        mPaint.reset();
        mPaint.setColor(getResources().getColor(R.color.material_color_white));
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(45);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        //mPaint.setTextAlign(Paint.Align.CENTER);
        //mPaint.setMaskFilter(mBlurMaskFilter);
        if (mText != null) {
            canvas.drawText(cutOutText(mText), 20, getHeight() * 19 / 20, mPaint);
        }
        mPaint.reset();
    }

    private String cutOutText(String text) {
        text = text.length() >= 16 ? text.substring(0, 16) + "..." : text;
        return text;
    }
}

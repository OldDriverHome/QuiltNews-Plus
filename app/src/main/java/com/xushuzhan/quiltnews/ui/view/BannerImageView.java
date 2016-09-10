package com.xushuzhan.quiltnews.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
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
    public static final int BLUR = 5;

    public void setText(String text) {
        mText = text;
    }

    public BannerImageView(Context context) {
        super(context);
        mPaint = new Paint();
        mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    public BannerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    public BannerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BannerImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint();
        mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        mPaint.reset();
        mPaint.setColor(getResources().getColor(R.color.material_color_blue_gray_400));
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(43);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setMaskFilter(mBlurMaskFilter);
        if (mText != null) {
            canvas.drawText(mText, getWidth() / 2, getHeight() * 17 / 20, mPaint);
        }
        mPaint.reset();
    }
}

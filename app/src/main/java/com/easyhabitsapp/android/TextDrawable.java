package com.easyhabitsapp.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.appcompat.content.res.AppCompatResources;


public class TextDrawable extends Drawable {
    private static final int DEFAULT_COLOR = Color.WHITE;
    private static final int DRAWABLE_SIZE = 24;
    private static final int DEFAULT_TEXT_SIZE = 8;
    private Paint mPaint;
    private CharSequence mText;
    private int width;
    private int height;
    private final Drawable mDrawable;

    public TextDrawable(Context context, CharSequence text,int width,int height,int sp) {
        mText = text;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        float textSize = spToPx(sp,context);
        mPaint.setTextSize(textSize);
        this.width = width;
        this.height = height;
        mDrawable = AppCompatResources.getDrawable(context, R.drawable.fav_rectangle);
        mDrawable.setBounds(0, 0, width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        mDrawable.draw(canvas);
        canvas.drawText(mText, 0, mText.length(),
                bounds.centerX(), bounds.centerY() + mPaint.getFontMetricsInt(null) / 3, mPaint); // this seems very wrong
    }

    @Override
    public int getOpacity() {
        return mPaint.getAlpha();
    }

    @Override
    public int getIntrinsicWidth() {
        return width;
    }

    @Override
    public int getIntrinsicHeight() {
        return height;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter filter) {
        mPaint.setColorFilter(filter);
    }

    public static int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

}
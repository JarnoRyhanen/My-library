package com.choicely.mylibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View {
    private static final String TAG = "DrawView";
    Paint paint = new Paint();

    private final List<Pair<Float, Float>> list = new ArrayList<>();

    public DrawView(Context context) {
        this(context, null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.d(TAG, "moving: (" + x + ", " + y + ")");
        postInvalidate();

        list.add(new Pair<Float, Float>(x, y));

        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Pair<Float, Float> p : list) {
            canvas.drawCircle(p.first,p.second, 20, paint);
        }

    }
}
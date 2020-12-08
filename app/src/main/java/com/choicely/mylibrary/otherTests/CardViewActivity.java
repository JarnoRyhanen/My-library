package com.choicely.mylibrary.otherTests;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.choicely.mylibrary.R;

public class CardViewActivity extends AppCompatActivity {
    private final static String TAG = "CardViewActivity";

    private CardView cardView;
    private CardView cardViewSecond;
    private SeekBar seekBar;
    private SeekBar seekBar2;
    private float endValueX = 0;
    private float endValueY = 0;
    private float newX, newY, startPosX, startPosY;
    private float endValueX2 = 0;
    private float endValueY2 = 0;
    private float newX2, newY2, startPosX2, startPosY2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view_activity);

        cardView = findViewById(R.id.card_view_activity_card_view);
        cardViewSecond = findViewById(R.id.card_view_activity_card_view_2);
        seekBar = findViewById(R.id.card_view_activity_seek_bar);
        seekBar2 = findViewById(R.id.card_view_activity_seek_bar_2);

        cardViewSecond.setOnTouchListener(onCardViewTouch2);
        cardView.setOnTouchListener(onCardViewTouch);

    }


    private boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "touched down");
                startPosX = event.getRawX();
                startPosY = event.getRawY();
                seekBar.setOnSeekBarChangeListener(seekBarListener);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "moving: ( X: " + newX + ", Y: " + newY + ")");
                newX = event.getRawX() - startPosX;
                newY = event.getRawY() - startPosY;
                cardView.setX(newX + endValueX);
                cardView.setY(newY + endValueY);

                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "touched up");
                endValueX += newX;
                endValueY += newY;
        }

        return true;
    }

    private boolean onTouch2(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "touched down");
                startPosX2 = event.getRawX();
                startPosY2 = event.getRawY();
                seekBar2.setOnSeekBarChangeListener(seekBarListener2);

                break;
            case MotionEvent.ACTION_MOVE:
                newX2 = event.getRawX() - startPosX2;
                newY2 = event.getRawY() - startPosY2;
                Log.d(TAG, "moving: ( X: " + newX2 + ", Y: " + newY2 + ")");
                cardViewSecond.setX(newX2 + endValueX2);
                cardViewSecond.setY(newY2 + endValueY2);

                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "touched up");
                endValueX2 += newX2;
                endValueY2 += newY2;
        }

        return true;
    }

    View.OnTouchListener onCardViewTouch = this::onTouch;
    @SuppressLint("ClickableViewAccessibility")
    View.OnTouchListener onCardViewTouch2 = this::onTouch2;

    SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.d(TAG, "Progress value: " + progress);
            cardView.setCardElevation(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    SeekBar.OnSeekBarChangeListener seekBarListener2 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
            Log.d(TAG, "Progress value: " + progress);
            cardViewSecond.setCardElevation(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
}

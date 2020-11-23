package com.choicely.mylibrary;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.CountDownLatch;

public class CountDownTimerActivity extends AppCompatActivity {
    private static final String TAG = "CountDownTimerActivity";

    private TextView textViewHours;
    private TextView textViewMinutes;
    private TextView textViewSeconds;
    private RelativeLayout relativeLayout;


    private Button startCountDown;
    private Button stopCountDown;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;


    TimeData timeData = new TimeData();
    final AnimationDrawable drawable = new AnimationDrawable();
    final Handler handler = new Handler();
    private int timeInMillis = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_down_timer_activity);

        textViewHours = findViewById(R.id.count_down_timer_hours);
        textViewMinutes = findViewById(R.id.count_down_timer_minutes);
        textViewSeconds = findViewById(R.id.count_down_timer_seconds);
        startCountDown = findViewById(R.id.count_down_timer_start);
        stopCountDown = findViewById(R.id.count_down_timer_stop_count_down);
        relativeLayout = findViewById(R.id.count_down_timer_relative_layout);


    }

    public void setTimeRemaining(int hours, int minutes, int seconds) {
        textViewSeconds.setText(String.format(":%02d", seconds));
        textViewMinutes.setText(String.format(":%02d", minutes));
        textViewHours.setText(String.format("%02d", hours));
    }

    CountDownTimer timer = null;

    protected void setTimer() {

        Log.d(TAG, "setTimer: time in milliseconds: " + timeInMillis);
        Log.d(TAG, "setTimer: hours " + timeData.getHours());
        Log.d(TAG, "setTimer: minutes " + timeData.getMinutes());
        Log.d(TAG, "setTimer: seconds " + timeData.getSeconds());


        timer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "onTick: millis until finished: " + millisUntilFinished);
                disableButtons();
                timeInMillis -= 1000;
                Log.d(TAG, "onTick: time in seconds " + timeInMillis / 1000);

                seconds -= 1;

                timeData.setSeconds(seconds);

                if (hours == 0 && minutes == 0 && seconds < 59 && seconds < 0) {
                    minutes -= 1;
                    seconds = 59;
                }
                if (hours == 0 && minutes <= 59 && seconds < 59 && seconds < 0) {
                    minutes -= 1;
                    seconds = 59;
                    timeData.setMinutes(minutes);
                    timeData.setSeconds(seconds);
                }
                if (hours <= 23 && minutes <= 59 && seconds <= 59) {
                    if (seconds < 0) {
                        seconds = 59;
                        minutes -= 1;
                        if (minutes < 0) {
                            hours -= 1;
                            minutes = 59;
                            seconds = 59;
                        }
                        timeData.setMinutes(minutes);
                        timeData.setSeconds(seconds);
                        timeData.setHours(hours);
                    }
                }

                setTimeRemaining(timeData.getHours(), timeData.getMinutes(), timeData.getSeconds());
            }

            @Override
            public void onFinish() {
                drawable.addFrame(new ColorDrawable(Color.RED), 1000);
                drawable.addFrame(new ColorDrawable(Color.GREEN), 1000);
                drawable.addFrame(new ColorDrawable(Color.BLUE), 1000);
                drawable.setOneShot(false);

                relativeLayout.setBackgroundDrawable(drawable);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawable.start();
                    }
                }, 100);

                setTimesToZero();
                enableButtons();
                setTimeRemaining(00, 00, 00);
            }
        }.start();

    }

    private void startTimer() {
        timeInMillis = (timeData.getSeconds() * 1000) + (60 * timeData.getMinutes() * 1000) + (3600 * timeData.getHours() * 1000);
        setTimer();
    }

    public void setTimesToZero() {
        hours = 0;
        minutes = 0;
        seconds = 0;
        timeData.setHours(hours);
        timeData.setMinutes(minutes);
        timeData.setSeconds(seconds);
        timeInMillis = 0;

    }

    public void enableButtons() {
        startCountDown.setEnabled(true);
        textViewSeconds.setEnabled(true);
        textViewMinutes.setEnabled(true);
        textViewHours.setEnabled(true);
    }

    public void disableButtons() {
        startCountDown.setEnabled(false);
        textViewSeconds.setEnabled(false);
        textViewMinutes.setEnabled(false);
        textViewHours.setEnabled(false);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.count_down_timer_hours:
                setHours();
                break;
            case R.id.count_down_timer_minutes:
                setMinutes();
                break;

            case R.id.count_down_timer_seconds:
                setSeconds();
                break;
            case R.id.count_down_timer_start:
                startTimer();
                Log.d(TAG, "clicked");
                break;
            case R.id.count_down_timer_stop_count_down:
                drawable.stop();
                enableButtons();
                setTimesToZero();
                setTimeRemaining(timeData.getHours(), timeData.getMinutes(), timeData.getSeconds());
                timer.cancel();
                break;
        }
    }

    private void setHours() {
        hours += 1;

        if (hours > 23) {
            hours = 0;
            Log.d(TAG, "SetHours: hours was 24, setting back to 0");
        }
        timeData.setHours(hours);
        textViewHours.setText(String.format("%02d", timeData.getHours()));

    }

    private void setMinutes() {
        minutes += 1;

        if (minutes > 59) {
            minutes = 0;
            Log.d(TAG, "SetMinutes: minutes was 60, setting back to 0");
        }
        timeData.setMinutes(minutes);
        textViewMinutes.setText(String.format(":%02d", timeData.getMinutes()));
    }


    private void setSeconds() {
        seconds += 1;

        if (seconds > 59) {
            seconds = 0;
            Log.d(TAG, "SetSeconds: seconds was 60, setting back to 0");
        }
        timeData.setSeconds(seconds);
        textViewSeconds.setText(String.format(":%02d", timeData.getSeconds()));
    }
}





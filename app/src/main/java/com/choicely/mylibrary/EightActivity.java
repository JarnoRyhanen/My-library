package com.choicely.mylibrary;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EightActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4, button5, button6;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eight_activity);

        progressBar = findViewById(R.id.eight_activity_progress_bar);
        button1 = findViewById(R.id.eight_activity_button1);
        button2 = findViewById(R.id.eight_activity_button2);
        button3 = findViewById(R.id.eight_activity_button3);
        button4 = findViewById(R.id.eight_activity_button4);
        button5 = findViewById(R.id.eight_activity_button5);
        button6 = findViewById(R.id.eight_activity_button6);


        button1.setOnClickListener(v -> {
            int color = getResources().getColor(R.color.red);
            progressBar.setBackgroundColor(color);
        });
        button2.setOnClickListener(v -> {
            progressBar.setBackgroundColor(getResources().getColor(R.color.teal_700));
        });
        button3.setOnClickListener(v -> {
            progressBar.setMax(50);
        });
        button4.setOnClickListener(v -> {
            progressBar.setMax(100);
        });
        button5.setOnClickListener(v -> playProgressBar());
        button6.setOnClickListener(v -> playProgressBarBackWards());


    }

    private void playProgressBarBackWards() {
        CountDownTimer timerBackwards = new CountDownTimer(10000, 100) {
            int progress = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) (millisUntilFinished / 100));
                button6.setEnabled(false);
                button5.setEnabled(false);
            }

            @Override
            public void onFinish() {
                button6.setEnabled(true);
                button5.setEnabled(true);
                progressBar.setProgress(0);
                Log.d("TAG", "finished");
            }
        }.start();
    }

    private void playProgressBar() {
        CountDownTimer timer = new CountDownTimer(10000, 100) {
            int progress = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                progress += 1;
                progressBar.setProgress(progress);
                button5.setEnabled(false);
                button6.setEnabled(false);
            }

            @Override
            public void onFinish() {
                button5.setEnabled(true);
                button6.setEnabled(true);
                progressBar.setProgress(0);
                Log.d("TAG", "finished");
            }
        }.start();

    }
}

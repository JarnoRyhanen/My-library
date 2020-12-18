package com.choicely.mylibrary.stringGenerator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class StringGeneratorActivity extends AppCompatActivity {

    private static final String TAG = "StringGeneratorActivity";
    private Button button;
    private TextView textView;
    private TextView progressBarText;
    private ProgressBar progressBar;

    private final ArrayList<String> stringList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.string_generator_activity);

        button = findViewById(R.id.string_generator_activity_button);
        textView = findViewById(R.id.string_generator_activity_text_view);
        progressBar = findViewById(R.id.string_generator_progress_bar);
        progressBarText = findViewById(R.id.string_generator_progress_bar_text);

        progressBar.setVisibility(View.GONE);
        progressBarText.setVisibility(View.GONE);

        button.setOnClickListener(v -> {
            stringList.clear();
            updateTextView();
            threadPool();
        });
    }

    Runnable mRunnable = () -> {

        progressBar.post(() -> {
            progressBar.setVisibility(View.VISIBLE);
            progressBarText.setVisibility(View.VISIBLE);
        });

        for (int i = 0; i < 250_000; i++) {
            String randomUUID = UUID.randomUUID().toString();
            stringList.add(randomUUID);
        }
        
        progressBar.post(() -> {
            progressBar.setVisibility(View.GONE);
            progressBarText.setVisibility(View.GONE);
        });
        updateTextView();

    };

    public void threadPool() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executor.execute(mRunnable);
        }
        Log.d(TAG, "threadPool: pressed");
    }

    private void updateTextView() {
        textView.setText("Total Strings: " + stringList.size());
    }

}

package com.choicely.mylibrary.otherTests;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

public class FourthActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity);

        textView = findViewById(R.id.fourth_activity_text);
        button = findViewById(R.id.fourth_activity_button);

        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.layout.anim);

        button.setOnClickListener(v -> textView.startAnimation(animation));
    }
}
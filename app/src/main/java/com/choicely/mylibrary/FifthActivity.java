package com.choicely.mylibrary;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {
    private final static String TAG = "FifthActivity";

    private DrawView drawView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth_activity);

        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);

        drawView = findViewById(R.id.fifth_activity_draw_view);
        //getSupportActionBar().hide();


    }

}

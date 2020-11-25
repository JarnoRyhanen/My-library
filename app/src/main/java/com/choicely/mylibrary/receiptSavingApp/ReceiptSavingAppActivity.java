package com.choicely.mylibrary.receiptSavingApp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

public class ReceiptSavingAppActivity extends AppCompatActivity {

    private static final String TAG = "ReceiptSavingApp";
    private Button openCamera;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_saving_app_activity);

        openCamera = findViewById(R.id.receipt_saving_app_activity_open_camera);

    }

    public void onClick(View view) {
        Log.d(TAG, "onClick: Camera opened");
    }







}

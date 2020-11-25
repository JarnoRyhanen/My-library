package com.choicely.mylibrary.receiptSavingApp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

public class PictureTakenActivity extends AppCompatActivity {
    private static final String TAG = "PictureTakenActivity";
    PictureData pictureData = new PictureData();

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_taken_activity);
        imageView = findViewById(R.id.picture_taken_activity_image_view);


        Uri photoUri = Uri.parse(getIntent().getStringExtra("resID"));
//        Uri photoUri = getIntent().getData("resID");
        imageView.setImageURI(photoUri);

        }

    }


package com.choicely.mylibrary.receiptSavingApp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

public class PictureTakenActivity extends AppCompatActivity {
    private static final String TAG = "PictureTakenActivity";
    PictureData pictureData = new PictureData();
    ReceiptSavingAppActivity receiptSavingAppActivity = new ReceiptSavingAppActivity();

    private EditText title;
    private EditText date;
    private Button saveButton;

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_taken_activity);
        imageView = findViewById(R.id.picture_taken_activity_image_view);
        title = findViewById(R.id.picture_taken_activity_edit_text_title);
        date = findViewById(R.id.picture_taken_activity_edit_text_date);
        saveButton = findViewById(R.id.picture_taken_activity_save_image_button);


        Uri photoUri = Uri.parse(getIntent().getStringExtra("resID"));
        imageView.setImageURI(photoUri);

        }

    public void onClick(View view) {
        pictureData.setPictureTitle(title.getText().toString());
        pictureData.setPictureDate(date.getText().toString());

        Log.d(TAG, "onClick: Title and date successfully saved");

    }
}


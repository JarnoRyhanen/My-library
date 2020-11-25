package com.choicely.mylibrary.receiptSavingApp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.choicely.mylibrary.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptSavingAppActivity extends AppCompatActivity {

    private static final String TAG = "ReceiptSavingApp";
    private Button openCameraBtn;
    private TextView textView;

    private Uri photoUri = null;

    PictureData pictureData = new PictureData();

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_saving_app_activity);

        openCameraBtn = findViewById(R.id.receipt_saving_app_activity_open_camera);
        textView = findViewById(R.id.receipt_saving_app_activity_text_view);

    }

    public void onClick(View view) {
        Log.d(TAG, "onClick: Camera opened");

        dispatchTakePictureIntent();

    }

    String currentPhotoPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Intent intent = new Intent(ReceiptSavingAppActivity.this, PictureTakenActivity.class);
            intent.putExtra("resID", photoUri.toString());
            startActivity(intent);

        }
    }

    private File createImageFile() {

        File image = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            pictureData.setPictureDir(storageDirectory.toString());
            image = File.createTempFile(imageFileName, ".jpg", storageDirectory);
            Log.d(TAG, "createImageFile: TIMESTAMP: " + timeStamp + ", IMAGE FILENAME: " + imageFileName + ", STORAGE DIRECTORY: " + storageDirectory);

            currentPhotoPath = image.getAbsolutePath();

            return image;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void dispatchTakePictureIntent() {

        File photoFile = createImageFile();

        if (photoFile != null) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            photoUri = FileProvider.getUriForFile(this, "com.choicely.mylibrary.receiptSavingApp", photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            Log.d(TAG, "dispatchTakePictureIntent: PHOTOURI: " + photoUri);
        }

    }

}

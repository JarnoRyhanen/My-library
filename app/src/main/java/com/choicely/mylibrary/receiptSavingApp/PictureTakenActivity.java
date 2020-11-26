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
import com.choicely.mylibrary.dp.RealmHelper;

import io.realm.Realm;
import io.realm.Sort;

public class PictureTakenActivity extends AppCompatActivity {
    private static final String TAG = "PictureTakenActivity";

    ReceiptSavingAppActivity receiptSavingAppActivity = new ReceiptSavingAppActivity();

    private EditText title;
    private EditText date;
    private Button saveButton;
    private int pictureId;
    private Uri photoUri;

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_taken_activity);
        imageView = findViewById(R.id.picture_taken_activity_image_view);
        title = findViewById(R.id.picture_taken_activity_edit_text_title);
        date = findViewById(R.id.picture_taken_activity_edit_text_date);
        saveButton = findViewById(R.id.picture_taken_activity_save_image_button);



        pictureId = (int) getIntent().getLongExtra(IntentKeys.PICTURE_ID, -1);

        if (pictureId == -1) {
            newPicture();
        } else {
            loadPicture();
        }

    }

    private void newPicture() {
        Realm realm = RealmHelper.getInstance().getRealm();
        PictureData lastPicture = realm.where(PictureData.class).sort("id", Sort.DESCENDING).findFirst();
        if (lastPicture != null) {
            pictureId = lastPicture.getId() + 1;
            photoUri = Uri.parse(getIntent().getStringExtra("resID"));
            imageView.setImageURI(photoUri);
            Log.d(TAG, "onCreate: photoUri: " + photoUri);
        } else {
            photoUri = Uri.parse(getIntent().getStringExtra("resID"));
            imageView.setImageURI(photoUri);
            Log.d(TAG, "onCreate: photoUri: " + photoUri);
            pictureId = 0;

        }

    }

    private void loadPicture() {
        Realm realm = RealmHelper.getInstance().getRealm();
        PictureData picture = realm.where(PictureData.class).equalTo("id", pictureId).findFirst();

        title.setText(picture.getPictureTitle());
        date.setText(picture.getPictureDate());
        imageView.setImageURI(Uri.parse(picture.getPictureUri()));

    }

    public void onClick(View view) {
        savePicture();
    }

    private void savePicture() {
        Realm realm = RealmHelper.getInstance().getRealm();
        PictureData pictureData = new PictureData();

        pictureData.setId(pictureId);
        pictureData.setPictureUri(photoUri.toString());
        pictureData.setPictureTitle(title.getText().toString());
        pictureData.setPictureDate(date.getText().toString());


        realm.executeTransaction(realm1 -> {
            realm.insertOrUpdate(pictureData);
        });

        Log.d(TAG, "onClick: Title " + '"' + pictureData.getPictureTitle() + '"' + " and date " + '"' + pictureData.getPictureDate() + '"' + " successfully saved");

    }


}


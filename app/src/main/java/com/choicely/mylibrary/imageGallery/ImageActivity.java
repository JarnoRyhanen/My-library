package com.choicely.mylibrary.imageGallery;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.choicely.mylibrary.R;
import com.choicely.mylibrary.dp.RealmHelper;

import io.realm.Realm;
import io.realm.Sort;

public class ImageActivity extends AppCompatActivity {

    private EditText urlField;
    private EditText title;
    private ImageView imageView;
    private Button addImage;
    private Button delete;

    private int imageID;


    private final static String TAG = "AddImageActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_image_activity);

        urlField = findViewById(R.id.add_image_activity_edit_text);
        imageView = findViewById(R.id.add_image_activity_image_view);
        addImage = findViewById(R.id.add_image_activity_add_image_button);
        title = findViewById(R.id.add_image_activity_title);
        delete = findViewById(R.id.add_image_activity_delete_button);

        imageID = getIntent().getIntExtra(IntentKeys.IMAGE_ID, -1);

        if (imageID == -1) {
            newImage();
        } else {
            loadImage();
        }
    }


    private void newImage() {

        Log.d(TAG, "newImage: @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@22");

        Realm realm = RealmHelper.getInstance().getRealm();
        ImageData lastImage = realm.where(ImageData.class).sort("id", Sort.DESCENDING).findFirst();
        delete.setVisibility(View.GONE);
        if (lastImage != null) {

            urlField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String urlFieldText = urlField.getText().toString();

                    Glide.with(ImageActivity.this)
                            .load(urlFieldText)
                            .into(imageView);
                }
            });


            imageID = lastImage.getId() + 1;
        } else {
            imageID = 0;
        }
    }

    private void loadImage() {
        Log.d(TAG, "loadImage: ##################################################");
        Realm realm = RealmHelper.getInstance().getRealm();
        ImageData image = realm.where(ImageData.class).equalTo("id", imageID).findFirst();

        String url = image.getUrl();
        urlField.setText(url);

        Log.d(TAG, "loadImage: ID:  " + image.getId());
        Log.d(TAG, "loadImage: imageID: " + imageID);

        Log.d(TAG, "loadImage: URL: " + url);
        title.setText(image.getTitle());

        Glide.with(this)
                .load(url)
                .into(imageView);

        urlField.setEnabled(false);
        addImage.setText("Update");
    }

    public void onClick(View view) {
        Realm realm = RealmHelper.getInstance().getRealm();
        ImageData imageData = new ImageData();

        imageData.setId(imageID);
        imageData.setUrl(String.valueOf(urlField.getText()));
        imageData.setTitle(title.getText().toString());
        realm.executeTransaction(realm1 -> {
            realm.insertOrUpdate(imageData);
        });

        Log.d(TAG, "onClick: Image saved with the ID: " + imageData.getId());
        Intent intent = new Intent(this, ImageGalleryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void deleteImage(View view) {

        Realm realm = RealmHelper.getInstance().getRealm();

        realm.executeTransaction(realm1 -> {
            ImageData results = realm1.where(ImageData.class).equalTo("id", imageID).findFirst();
            results.deleteFromRealm();
        });

        Toast.makeText(this, "Image deleted", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ImageGalleryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

package com.choicely.mylibrary.imageGallery;

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

public class EditImageActivity extends AppCompatActivity {

    private EditText urlField;
    private EditText title;
    private ImageView imageView;
    private Button addImage;
    private Button delete;

    private int imageID;

    private final static String TAG = "EditImageActivity";

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

    //Creates a new image
    private void newImage() {

        Realm realm = RealmHelper.getInstance().getRealm();
        ImageData lastImage = realm.where(ImageData.class).sort("id", Sort.DESCENDING).findFirst();
        delete.setVisibility(View.GONE);

        //checks if previous images already exists in Realm and gives the new image an ID
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

                    Glide.with(EditImageActivity.this)
                            .load(urlFieldText)
                            .into(imageView);
                }
            });


            imageID = lastImage.getId() + 1;
        }
        //if there were no previous images, create a new image with id 0
        else {
            imageID = 0;
        }
    }

    //Simply load an image that was clicked in the gallery
    private void loadImage() {
        Realm realm = RealmHelper.getInstance().getRealm();
        ImageData image = realm.where(ImageData.class).equalTo("id", imageID).findFirst();

        String url = image.getUrl();
        urlField.setText(url);

        Log.d(TAG, "loadImage: imageID: " + imageID);
        Log.d(TAG, "loadImage: URL: " + url);

        title.setText(image.getTitle());


        Glide.with(this)
                .load(url)
                .into(imageView);

        urlField.setEnabled(false);
        addImage.setText("Update");
    }

    //Save the image
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
        finish();
    }


    //Deletes the image from database and gallery
    public void deleteImage(View view) {

        Realm realm = RealmHelper.getInstance().getRealm();

        ImageData results = realm.where(ImageData.class).equalTo("id", imageID).findFirst();

        realm.executeTransaction(realm1 -> {
            results.deleteFromRealm();
        });

        Toast.makeText(this, "Image deleted", Toast.LENGTH_SHORT).show();

        finish();
    }
}

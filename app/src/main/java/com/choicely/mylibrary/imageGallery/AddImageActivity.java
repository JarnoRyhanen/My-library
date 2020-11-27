package com.choicely.mylibrary.imageGallery;

import android.app.Activity;
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

public class AddImageActivity extends AppCompatActivity {

    private EditText urlField;
    private ImageView imageView;
    private Button addImage;
    private Activity AddImageActivity;

    private int imageID;

    private final static String TAG = "AddImageaActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_image_activity);

        urlField = findViewById(R.id.add_image_activity_edit_text);
        imageView = findViewById(R.id.add_image_activity_image_view);
        addImage = findViewById(R.id.add_image_activity_add_image_button);

        imageID = getIntent().getIntExtra(IntentKeys.IMAGE_ID, -1);
//        urlField.addTextChangedListener(new TextWatcher() {
//            private static final String TAG = "AddImageActivity";
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                Glide.with(urlField.getContext())
//                        .load(urlField.getText())
//                        .into(imageView);
//
//
////                    Log.d(TAG, "onTextChanged: Images with this url were not found");
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

    }

    public void onClick(View view) {
        Realm realm = RealmHelper.getInstance().getRealm();
        ImageData imageData = new ImageData();

//        imageData.setId(imageID);
        imageData.setUrl(String.valueOf(urlField.getText()));

        realm.executeTransaction(realm1 -> {
            realm.insertOrUpdate(imageData);
        });
        Log.d(TAG, "onClick: Image added");

        realm.addChangeListener(realm1 -> {
            Log.d(TAG, "onClick: realm has received an item");
        });
        
    }
}

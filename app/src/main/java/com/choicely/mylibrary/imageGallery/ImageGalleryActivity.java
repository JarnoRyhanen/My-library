package com.choicely.mylibrary.imageGallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.choicely.mylibrary.R;
import com.choicely.mylibrary.dp.RealmHelper;

import io.realm.Realm;
import io.realm.RealmResults;

public class ImageGalleryActivity extends AppCompatActivity {

    private static final String TAG = "ImageGalleryActivity";

    private ViewPager2 viewPager2;
    private Button addImages;
    private ImageGalleryViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery_activity);
        viewPager2 = findViewById(R.id.image_gallery_activity_view_pager);
        addImages = findViewById(R.id.image_gallery_add_images);
        adapter = new ImageGalleryViewPagerAdapter(this);

        updateContent();

        viewPager2.setAdapter(adapter);
        Log.d(TAG, "Image Count: " + adapter.getItemCount());
    }

    void updateContent() {
        adapter.clear();
        Realm realm = RealmHelper.getInstance().getRealm();
        RealmResults<ImageData> images = realm.where(ImageData.class).findAll();
        for (ImageData image : images) {
            adapter.addImage(image.getUrl());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateContent();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, AddImageActivity.class);
        startActivity(intent);
    }


}

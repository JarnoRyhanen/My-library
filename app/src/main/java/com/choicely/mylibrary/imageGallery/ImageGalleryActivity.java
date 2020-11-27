package com.choicely.mylibrary.imageGallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.choicely.mylibrary.R;

public class ImageGalleryActivity extends AppCompatActivity {

    private static final String TAG = "ImageGalleryActivity";
    ImageFragment imageFragment = new ImageFragment();

    private ViewPager2 viewPager2;
    private Button addImages;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery_activity);
        viewPager2 = findViewById(R.id.image_gallery_activity_view_pager);
        addImages = findViewById(R.id.image_gallery_add_images);



        viewPager2.setAdapter(createAdapter());
    }

    private ImageGalleryViewPagerAdapter createAdapter() {
        ImageGalleryViewPagerAdapter adapter = new ImageGalleryViewPagerAdapter(this);
        return adapter;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, AddImageActivity.class);
        startActivity(intent);
    }
}

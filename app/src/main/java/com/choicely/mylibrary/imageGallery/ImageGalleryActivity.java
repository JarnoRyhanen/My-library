package com.choicely.mylibrary.imageGallery;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.choicely.mylibrary.R;

public class ImageGalleryActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery_activity);

        viewPager2 = findViewById(R.id.image_gallery_activity_view_pager);
        viewPager2.setAdapter(createAdapter());
    }

    private ImageGalleryViewPagerAdapter createAdapter() {
        ImageGalleryViewPagerAdapter adapter = new ImageGalleryViewPagerAdapter(this);
        return adapter;
    }
}

package com.choicely.mylibrary.imageGallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.choicely.mylibrary.imageGallery.ImageFragment.ARGUMENT_COUNT;
import static com.choicely.mylibrary.imageGallery.ImageFragment.COUNTER;
import static com.choicely.mylibrary.imageGallery.ImageFragment.TITLE;

class ImageGalleryViewPagerAdapter extends FragmentStateAdapter {

    private final static String TAG = "GalleryPagerAdapter";
    List<String> images = new ArrayList<>();
    ImageData imageData = new ImageData();

    public ImageGalleryViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addImage(String image) {
        images.add(image);
    }

    public void clear() {
        images.clear();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_COUNT, images.get(position));
        args.putInt(COUNTER, position);
        args.putString(TITLE, imageData.getTitle());
        imageFragment.setArguments(args);
        return imageFragment;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

}

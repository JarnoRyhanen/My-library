package com.choicely.mylibrary.imageGallery;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.choicely.mylibrary.imageGallery.ImageFragment.IMAGE_ID;
import static com.choicely.mylibrary.imageGallery.ImageFragment.TITLE;
import static com.choicely.mylibrary.imageGallery.ImageFragment.URL;

class ImageGalleryViewPagerAdapter extends FragmentStateAdapter {

    private final static String TAG = "GalleryPagerAdapter";

    //List of the images
    List<ImageData> images = new ArrayList<>();

    //Constructor
    public ImageGalleryViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //Adds the image in the list
    public void addImage(ImageData image) {
        images.add(image);
    }

    //Empties the list of all images
    public void clear() {
        images.clear();
    }

    //Creates the fragment
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        ImageFragment imageFragment = new ImageFragment();

        //Bundles up the arguments that will be passed to the fragment
        Bundle args = new Bundle();

        ImageData image = images.get(position);
        Log.d(TAG, "image: fragment position: position: "+position+" url: "+image.getUrl());

        args.putString(URL, image.getUrl());
        args.putInt(IMAGE_ID, image.getId());
        args.putString(TITLE, image.getTitle());

        imageFragment.setArguments(args);
        return imageFragment;
    }

    //Returns the of the image in the fragment
    @Override
    public long getItemId(int position) {
        return images.get(position).getId();
    }

    //Returns the size of the list
    @Override
    public int getItemCount() {
        return images.size();
    }

}

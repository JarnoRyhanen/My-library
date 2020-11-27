package com.choicely.mylibrary.imageGallery;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class ImageGalleryViewPagerAdapter extends FragmentStateAdapter {
    private static final int ITEM_SIZE = 5;

    public ImageGalleryViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ImageFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return ITEM_SIZE;
    }
}

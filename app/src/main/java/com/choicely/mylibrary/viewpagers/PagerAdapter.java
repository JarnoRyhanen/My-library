package com.choicely.mylibrary.viewpagers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


/**
 * This is the adapter class. It creates an adapter for the fragments.
 */

class PagerAdapter extends FragmentStateAdapter{
    private final static int CARD_SIZE = 5;



    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return CardFragmentTest.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return CARD_SIZE;
    }
}

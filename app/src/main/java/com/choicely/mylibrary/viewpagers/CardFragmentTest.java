package com.choicely.mylibrary.viewpagers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.choicely.mylibrary.R;

/**
 * This class creates the fragments and gives them their contents.
 */

public class CardFragmentTest extends Fragment {
    private final static String ARGUMENT_COUNT = "param1";
    public Integer counter;


    // Creates an array and puts a number of images in it. This array will be used later.
    private final int[] IMAGE_MAP = {
            R.drawable.button, R.drawable.donut, R.drawable.lataa, R.drawable.puuhapete, R.drawable.ic_launcher_background
    };


    //This method creates the new fragment and returns it.
    public static CardFragmentTest newInstance(Integer counter) {
        CardFragmentTest fragment = new CardFragmentTest();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_COUNT, counter);
        fragment.setArguments(arguments);
        return fragment;
    }

    //The onCreate method check
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARGUMENT_COUNT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_fragments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.new_fragment_images);
        Log.d("TAG", "Counter is: " + counter);
        imageView.setImageResource(IMAGE_MAP[counter]);
    }
}

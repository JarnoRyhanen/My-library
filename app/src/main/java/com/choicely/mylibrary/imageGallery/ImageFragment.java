package com.choicely.mylibrary.imageGallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.choicely.mylibrary.R;

public class ImageFragment extends Fragment {
    public final static String ARGUMENT_COUNT = "param1";
    public final static String COUNTER = "param2";
    public final static String TITLE = "param3";
    private static final String TAG = "ImageFragment";
    private int imageID;
    private String url;
    private int counter;
    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARGUMENT_COUNT);
            counter = getArguments().getInt(COUNTER);
            title = getArguments().getString(TITLE);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.image_gallery_fragments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.image_gallery_fragments_image_view);
//        TextView textView = view.findViewById(R.id.image_gallery_fragments_title);
//
//        Log.d(TAG, "onViewCreated: TITLE:" + title);
//        textView.setText("sdfghgrarstdygfh");

        Glide.with(this)
                .load(url)
                .into(imageView);

        Log.d(TAG, "onViewCreated: counter: " + counter);
        imageID = counter;

        imageView.setOnClickListener(v -> {
            Context context = view.getContext();
            Intent intent = new Intent(context, AddImageActivity.class);
            intent.putExtra(IntentKeys.IMAGE_ID, imageID);
            context.startActivity(intent);
        });

    }
}



package com.choicely.mylibrary.imageGallery;

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
import com.choicely.mylibrary.dp.RealmHelper;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ImageFragment extends Fragment {
    private final static String ARGUMENT_COUNT = "param1";
    private static final String TAG = "ImageFragment";
    public Integer counter;

    private int imageID;

    ArrayList<String> images = new ArrayList<String>();

    public void addImage(String image) {
        images.add(image);
    }

    public int getImageCount() {

        return images.size();
    }

    public static ImageFragment newInstance(Integer counter) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_COUNT, counter);
        imageFragment.setArguments(args);
        return imageFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            counter = getArguments().getInt(ARGUMENT_COUNT);
        }


//        images.add("https://lh3.googleusercontent.com/proxy/SUHuIVvmBUPbcUmRJtEkYaarw7XFy3JjRXwR6NIku5keAp4RgjY65YIf6wJAtY4QZIvj9Q9umkB6dQyEKe-pkTDhO2yeXts");
//        images.add("https://d26hhearhq0yio.cloudfront.net/content/misterspex/produkte/grafiken/6664017_a2.jpg");
//        images.add("https://www.compasshrg.com/wp-content/uploads/wartsila-logo-small.jpg");
//        images.add("https://compote.slate.com/images/697b023b-64a5-49a0-8059-27b963453fb1.gif");
        updateContent();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateContent();
        Log.d(TAG, "onResume: number of images in the list: " + images.size());
    }

    private void updateContent() {
        images.clear();
        Realm realm = RealmHelper.getInstance().getRealm();
//        images.add("https://image.flaticon.com/icons/png/512/3/3901.png");
        RealmResults<ImageData> images = realm.where(ImageData.class).findAll();
        for (ImageData image : images) {
            addImage(image.getUrl());
        }

        Log.d(TAG, "updateContent: " + images);

        realm.addChangeListener(realm1 -> {

        });
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
//
        if (images == null) {
            Log.d(TAG, "onViewCreated:  Images is null");
        } else {
            Glide.with(this)
                    .load(images.get(counter))
                    .into(imageView);
            Log.d(TAG, "onViewCreated: counter: " + counter);
        }
    }
}

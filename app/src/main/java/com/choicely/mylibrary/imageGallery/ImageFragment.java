package com.choicely.mylibrary.imageGallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.choicely.mylibrary.R;
import com.choicely.mylibrary.dp.RealmHelper;

import io.realm.Realm;

public class ImageFragment extends Fragment {
    public final static String IMAGE_ID = "param1";
    private static final String TAG = "ImageFragment";
    private int imageID;

    private View openView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageID = getArguments().getInt(IMAGE_ID);
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

        Realm realm = RealmHelper.getInstance().getRealm();

        ImageData image = realm.where(ImageData.class).equalTo("id", imageID).findFirst();
        String title = image.getTitle();
        String url = image.getUrl();


        ImageView imageView = view.findViewById(R.id.image_gallery_fragments_image_view);

        TextView textView = view.findViewById(R.id.image_gallery_fragments_title);
        textView.setText(title);

        Glide.with(this)
                .load(url)
                .into(imageView);

        Log.d(TAG, "onViewCreated: image ID:  " + imageID);
        imageView.setOnClickListener(v -> {
            openView = view;
            openImage();
        });
    }

    private void openImage() {
        Log.d(TAG, "opened");
        Context context = openView.getContext();
        Intent intent = new Intent(context, EditImageActivity.class);
        intent.putExtra(IntentKeys.IMAGE_ID, imageID);
        context.startActivity(intent);
    }
}



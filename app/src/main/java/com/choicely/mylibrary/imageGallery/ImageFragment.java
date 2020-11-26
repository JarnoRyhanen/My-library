package com.choicely.mylibrary.imageGallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.choicely.mylibrary.R;

import java.util.ArrayList;

public class ImageFragment extends Fragment {
    private final static String ARGUMENT_COUNT = "param1";
    public Integer counter;

    private int IMAGE_MAP[] = {
            R.drawable.button, R.drawable.donut, R.drawable.lataa, R.drawable.puuhapete, R.drawable.ic_launcher_background
    };
    ArrayList<Integer> images = new ArrayList<Integer>();

    public void addImage(Integer image){
        images.add(R.drawable.puuhapete);
        images.add(R.drawable.donut);
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
        if(getArguments() != null){
            counter = getArguments().getInt(ARGUMENT_COUNT);
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
        imageView.setImageResource(images.get(counter));
    }
}

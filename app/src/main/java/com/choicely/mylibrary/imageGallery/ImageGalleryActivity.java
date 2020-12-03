package com.choicely.mylibrary.imageGallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.choicely.mylibrary.R;
import com.choicely.mylibrary.dp.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ImageGalleryActivity extends AppCompatActivity {

    private static final String TAG = "ImageGalleryActivity";

    private ViewPager2 viewPager2;
    private TextView addImages;
    private ImageGalleryViewPagerAdapter adapter;
    private Spinner spinner;
    private String suffix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery_activity);
        viewPager2 = findViewById(R.id.image_gallery_activity_view_pager);
        addImages = findViewById(R.id.image_gallery_add_images);
        spinner = findViewById(R.id.image_gallery_activity_spinner);


        //creates and sets the an adapter for the viewpager
        adapter = new ImageGalleryViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);


        createSpinnerAndListen();
        updateContent();
        Log.d(TAG, "Image Count: " + adapter.getItemCount());
    }

    //Creates a drop down menu
    private void createSpinnerAndListen() {

        //List of items in the drop down menu
        List<String> imageTypes = new ArrayList<String>();
        imageTypes.add("No filter");
        imageTypes.add(".png");
        imageTypes.add(".jpg");
        imageTypes.add(".gif");
        imageTypes.add(".webP");

        //Adapter for the menu
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, imageTypes);
        spinner.setAdapter(arrayAdapter);


        //Listener for items in the menu
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                suffix = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemSelected: " + suffix);

                performFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "onNothingSelected: Nothing is selected");
            }
        };
        //Sets the listener for the menu
        spinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    private void performFilter() {
        if (suffix == null || suffix.equals("No filter")) {
            updateContent();
        } else {
            filterImages();
        }
    }

    //Filter images
    private void filterImages() {
        adapter.clear();

        //Instance of realm and a search query for data in the database
        Realm realm = RealmHelper.getInstance().getRealm();
        RealmResults<ImageData> images = realm.where(ImageData.class).contains("url", suffix.toLowerCase()).findAll();

        //Iterates every image found with the query and adds them to the adapter
        for (ImageData image : images) {
            adapter.addImage(image);
            Log.d(TAG, "image: " + image.getUrl());
        }
        //Notifies the adapter that data has changed
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        performFilter();
    }

    //Iterates every image from the database and adds them in the adapter
    private void updateContent() {
        adapter.clear();
        Realm realm = RealmHelper.getInstance().getRealm();
        RealmResults<ImageData> images = realm.where(ImageData.class).findAll();
        for (ImageData image : images) {
            adapter.addImage(image);
        }
        adapter.notifyDataSetChanged();
    }

    //Opens a new window
    public void onClick(View view) {
        Intent intent = new Intent(this, EditImageActivity.class);
        startActivity(intent);
    }


}

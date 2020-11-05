package com.choicely.mylibrary.viewpagers;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.choicely.mylibrary.R;

/**
 * This is the main activity for the view pager test.
 */

public class ViewPagerTest2 extends AppCompatActivity {

    ViewPager2 viewPagerTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_activity_test);

        viewPagerTest = findViewById(R.id.view_pager_activity_test_view_pager);
        viewPagerTest.setAdapter(createAdapter());

    }

    //A method that creates the adapter
    private PagerAdapter createAdapter() {
        PagerAdapter adapter = new PagerAdapter(this);
        return adapter;
    }

}

package com.choicely.mylibrary.otherTests;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

public class SixthActivity extends AppCompatActivity {

    private ImageButton button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sixth_activity);

        button = findViewById(R.id.sixth_activity_image_button);

        button.setOnClickListener(v -> {

            String url = "https://www.youtube.com/watch?v=d0tGBCCE0lc";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);

        });

    }
}

package com.choicely.mylibrary;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CitySearchActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_search_activity);

        editText = findViewById(R.id.city_search_activity_edit_text);
    }
}

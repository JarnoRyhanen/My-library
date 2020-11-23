package com.choicely.mylibrary;

import android.os.Bundle;
import android.text.Editable;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class CitySearchActivity extends AppCompatActivity {

    private static final String TAG = "CitySearchActivity";
    OkHttpClient client = new OkHttpClient();



//    private String[] fruits = {"Apple", "Appy", "Banana", "Cherry", "Grape", "Kiwi", "Mango", "Pear", "date"};


    private AutoCompleteTextView autoCompleteTextView;
    private Editable textViewInput = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_search_activity);

        autoCompleteTextView = findViewById(R.id.city_search_activity_auto_complete_text_view);

        textViewInput = autoCompleteTextView.getText();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://geo-test.choicely.com/search/cities/<city>?limit=10").newBuilder();
        urlBuilder.addQueryParameter("<city>", String.valueOf(textViewInput));
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (this, android.R.layout.select_dialog_item, fruits);
//        autoCompleteTextView.setThreshold(1); //will start working from first character
//        autoCompleteTextView.setAdapter(adapter);


    }
}

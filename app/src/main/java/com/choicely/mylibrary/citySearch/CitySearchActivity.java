package com.choicely.mylibrary.citySearch;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CitySearchActivity extends AppCompatActivity {

    private static final String TAG = "CitySearchActivity";
    OkHttpClient client = new OkHttpClient();

    private AutoCompleteTextView autoCompleteTextView;
    private ProgressBar progressBar;
    ArrayList<String> cities = new ArrayList<String>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_search_activity);

        autoCompleteTextView = findViewById(R.id.city_search_activity_auto_complete_text_view);
        progressBar = findViewById(R.id.city_search_activity_spinner);

        progressBar.setVisibility(View.GONE);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parseJson();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void parseJson() {
        Editable textViewInput = autoCompleteTextView.getText();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("geo-test.choicely.com")
                .addPathSegment("search")
                .addPathSegment("cities")
                .addPathSegment(String.valueOf(textViewInput))
                .build();
        Log.d(TAG, "onCreate: " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    Log.d(TAG, "onResponse: this is the json" + myResponse);

                    try {
                        JSONObject jsonObject = new JSONObject(myResponse);
                        JSONArray dataArray = jsonObject.getJSONArray("data");

                        progressBar.post(() -> {
                            progressBar.setVisibility(View.VISIBLE);
                        });
                        cities.clear();
                        for (int i = 0; i < dataArray.length(); i++) {

                            JSONObject obj = dataArray.getJSONObject(i);
                            String country = obj.getString("country_code_3");
                            String city = obj.getString("full_name");
                            String countryAndCity = country + ", " + city;
                            cities.add(countryAndCity);
                            Log.d(TAG, "onResponse: city: " + (obj.getString("full_name")));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                progressBar.post(() -> {
                    progressBar.setVisibility(View.GONE);
                });
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.auto_complete_item, R.id.auto_complete_item_text_view, cities);

        autoCompleteTextView.setAdapter(adapter);
    }
}

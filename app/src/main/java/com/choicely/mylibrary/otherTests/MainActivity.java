package com.choicely.mylibrary.otherTests;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;
import com.choicely.mylibrary.ViewPagerActivity;
import com.choicely.mylibrary.blackJack.BlackJackActivity;
import com.choicely.mylibrary.citySearch.CitySearchActivity;
import com.choicely.mylibrary.countcowntimer.CountDownTimerActivity;
import com.choicely.mylibrary.imageGallery.ImageGalleryActivity;
import com.choicely.mylibrary.receiptSavingApp.ReceiptSavingAppActivity;
import com.choicely.mylibrary.stringGenerator.StringGeneratorActivity;
import com.choicely.mylibrary.viewpagers.ViewPagerTest2;

public class MainActivity extends AppCompatActivity {

    private Button rotatePicBtn;
    private Button rotatePicWithGyroBtn;
    private Button rotateTextBtn;
    private Button drawOnScreen;
    private Button imageButton;
    private Button cardViewButton;
    private Button eigthButton;
    private Button viewPagerBtn;
    private Button viewPagerTestBtn;
    private Button notificationBtn;
    private Button countDownTimerBtn;
    private Button citySearchBtn;
    private Button generateStrings;
    private Button saveReceipts;
    private Button imageGallery;
    private Button blackJack;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotatePicBtn = findViewById(R.id.main_activity_rotate_picture_button);
        rotatePicWithGyroBtn = findViewById(R.id.main_activity_gyro_rotate_picture_button);
        rotateTextBtn = findViewById(R.id.main_activity_rotate_text_button);
        drawOnScreen = findViewById(R.id.main_activity_draw_on_screen_button);
        imageButton = findViewById(R.id.main_activity_image_button);
        cardViewButton = findViewById(R.id.main_activity_card_view_button);
        eigthButton = findViewById(R.id.main_activity_some_button_button);

        viewPagerBtn = findViewById(R.id.main_activity_view_pager);
        viewPagerTestBtn = findViewById(R.id.main_activity_view_pager_test);

        notificationBtn = findViewById(R.id.notification_test);
        countDownTimerBtn = findViewById(R.id.count_down_timer);
        citySearchBtn = findViewById(R.id.city_search_button);
        generateStrings = findViewById(R.id.generate_strings);
        saveReceipts = findViewById(R.id.receipt_saving_app);
        imageGallery = findViewById(R.id.image_gallery);
        blackJack = findViewById(R.id.black_jack);


        rotatePicBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondaryActivity.class);
            startActivity(intent);
        });
        rotatePicWithGyroBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        });
        rotateTextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, FourthActivity.class);
            startActivity(intent);
        });
        drawOnScreen.setOnClickListener(v -> {
            Intent intent = new Intent(this, FifthActivity.class);
            startActivity(intent);
        });
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SixthActivity.class);
            startActivity(intent);
        });
        cardViewButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, CardViewActivity.class);
            startActivity(intent);
        });
        eigthButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, EightActivity.class);
            startActivity(intent);
        });
        viewPagerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewPagerActivity.class);
            startActivity(intent);
        });

        viewPagerTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewPagerTest2.class);
            startActivity(intent);
        });
        notificationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationTestActivity.class);
            startActivity(intent);
        });
        countDownTimerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CountDownTimerActivity.class);
            startActivity(intent);
        });
        citySearchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CitySearchActivity.class);
            startActivity(intent);
        });
        generateStrings.setOnClickListener(v -> {
            Intent intent = new Intent(this, StringGeneratorActivity.class);
            startActivity(intent);
        });
        saveReceipts.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReceiptSavingAppActivity.class);
            startActivity(intent);
        });

        imageGallery.setOnClickListener(v -> {
            Intent intent = new Intent(this, ImageGalleryActivity.class);
            startActivity(intent);
        });
        blackJack.setOnClickListener(v -> {
            Intent intent = new Intent(this, BlackJackActivity.class);
            startActivity(intent);
        });
    }
}
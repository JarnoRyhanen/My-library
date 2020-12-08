package com.choicely.mylibrary.blackJack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

public class BlackJackActivity extends AppCompatActivity {

    private static final String TAG = "BlackJackActivity";

    private Button startGame;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_activity);

        startGame = findViewById(R.id.black_jack_start_game);

    }

    public void onStartGameClicked(View view) {
        startGame.setVisibility(View.GONE);


    }

    public void onHitClicked(View view) {
    }

    public void onSurrenderClicked(View view) {
    }

    public void onStandClicked(View view) {
    }

    public void onSplitClicked(View view) {
    }

    public void onDoubleClicked(View view) {
    }
}

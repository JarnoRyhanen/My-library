package com.choicely.mylibrary.blackJack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;
import com.choicely.mylibrary.blackJack.hand.DealerHand;
import com.choicely.mylibrary.blackJack.hand.Hand;
import com.choicely.mylibrary.blackJack.hand.PlayerHand;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BlackJackActivity extends AppCompatActivity {

    private static final String TAG = "BlackJackActivity";

    private Shoe shoe;

    private Stack<Hand> handStack = new Stack<>();
    private List<PlayerHand> currentAndCompletedPlayerHands = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_activity);

        shoe = new Shoe(4);
    }

    public void onStartGameClicked(View view) {

        currentAndCompletedPlayerHands.clear();
        handStack.clear();

        Log.d(TAG, "onNewGameClick");

        DealerHand dealerHand = new DealerHand(shoe);
        dealerHand.findHandSpecificViewsFromView(findViewById(R.id.bj_dealers_hand_layout));
        dealerHand.setOnHandPlayerListener(onHandPlayedListener);
        dealerHand.onDataChanged();
        handStack.add(dealerHand);

        PlayerHand playerMainHand = new PlayerHand(shoe);
        playerMainHand.findButtonsFromView(findViewById(R.id.player));
        playerMainHand.findHandSpecificViewsFromView(findViewById(R.id.player_player_hand_1_values));
        playerMainHand.setSplitLayout(findViewById(R.id.player_player_hand_2_values));
        playerMainHand.setOnHandPlayerListener(onHandPlayedListener);
        playerMainHand.onDataChanged();
        handStack.add(playerMainHand);

        activateNextHand();
    }

    private Hand.OnHandPlayedListener onHandPlayedListener = hand -> {
        if (hand instanceof DealerHand) {
            // game is over
            Log.d(TAG, "Game over");
            handleResult((DealerHand) hand);
        }

        else {
            Log.d(TAG, "you pressed stand: ");
            activateNextHand();
        }
    };

    private void handleResult(DealerHand dealerHand) {
        for (PlayerHand playerHand : currentAndCompletedPlayerHands) {
            playerHand.compareToDealerHand(dealerHand);
        }
    }

    private void activateNextHand() {

        Hand nextHand = handStack.pop();
        if (nextHand instanceof PlayerHand) {
            currentAndCompletedPlayerHands.add((PlayerHand) nextHand);
        }
        nextHand.setActive(true);
    }

    private void updateContent() {

    }


}

package com.choicely.mylibrary.blackJack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private Button startGameButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_activity);
        startGameButton = findViewById(R.id.black_jack_start_game);

        shoe = new Shoe(2);
    }

    public void onStartGameClicked(View view) {

        startGameButton.setVisibility(View.GONE);
        Log.d(TAG, "onNewGameClick");

        DealerHand dealerHand = new DealerHand(shoe);
        dealerHand.findHandSpecificViewsFromView(findViewById(R.id.bj_dealers_hand_layout));
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
            handleResult((DealerHand) hand);
        } else {
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

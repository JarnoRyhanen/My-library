package com.choicely.mylibrary.blackJack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private TextView betTextView;
    private TextView balanceTextView;

    private final BetAndBalance betAndBalance = new BetAndBalance();

    private final Stack<Hand> handStack = new Stack<>();
    private final List<PlayerHand> currentAndCompletedPlayerHands = new ArrayList<>();

    private View alertView;

    private final PopUpAlert popUpAlert = new PopUpAlert();

    private final PopUpAlert.BetAddedListener listener = bet -> addBet(bet);

    private final BetAndBalance.BalanceUpdatedListener balanceUpdatedListener = betWon -> updateBalance(betWon);

    private int balance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_activity);

        betTextView = findViewById(R.id.black_jack_activity_bet);
        balanceTextView = findViewById(R.id.black_jack_activity_balance);

        shoe = new Shoe(4);

        alertView = getLayoutInflater().inflate(R.layout.custom_alert, null);
    }

    public void addBet(int bet) {
        betTextView.setText(String.format("%d €", bet));
        updateBalance(-bet);
        betAndBalance.setYourBet(bet);
        removePopUpView();
        Log.d(TAG, "addBet: getbet: " + betAndBalance.getYourBet());
    }

    public void updateBalance(int bet) {

        betAndBalance.setOnBalanceUpdatedListener(balanceUpdatedListener);

        balance = betAndBalance.getBalance() + bet;
        betAndBalance.setBalance(balance);
        balanceTextView.setText(String.format("%d €", balance));
    }

    public void doubleBet(int bet) {
        betTextView.setText(String.valueOf(bet));
        betAndBalance.setYourBet(bet);

        balance = betAndBalance.getBalance() - bet/2;
        betAndBalance.setBalance(balance);
        balanceTextView.setText(String.format("%d €", balance));
    }

    public void onStartGameClicked(View view) {

        popUpAlert.setBedAddedListener(listener);
        popUpAlert.popUpBetting(alertView);

        currentAndCompletedPlayerHands.clear();
        handStack.clear();

        Log.d(TAG, "onNewGameClick");

        DealerHand dealerHand = new DealerHand(shoe);
        dealerHand.findHandSpecificViewsFromView(findViewById(R.id.bj_dealers_hand_layout));
        dealerHand.setOnHandPlayerListener(onHandPlayedListener);
        dealerHand.onDataChanged();
        dealerHand.setBetAndBalance(betAndBalance);
        handStack.add(dealerHand);

        PlayerHand playerMainHand = new PlayerHand(shoe);
        playerMainHand.findButtonsFromView(findViewById(R.id.player));
        playerMainHand.findHandSpecificViewsFromView(findViewById(R.id.player_player_hand_1_values));
        playerMainHand.setSplitLayout(findViewById(R.id.player_player_hand_2_values));
        playerMainHand.setSplitLayoutInvisible();
        playerMainHand.setOnHandPlayerListener(onHandPlayedListener);
        playerMainHand.onDataChanged();
        playerMainHand.setBlackJackActivity(BlackJackActivity.this);
        playerMainHand.setBetAndBalance(betAndBalance);

        handStack.add(playerMainHand);

        activateNextHand();

    }

    private final Hand.OnHandPlayedListener onHandPlayedListener = hand -> {
        if (hand instanceof DealerHand) {
            // game is over
            Log.d(TAG, "Game over");
            handleResult((DealerHand) hand);
        } else {
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


    public void onHandValueClick(View view) {

    }

    private void removePopUpView() {
        ViewGroup parent = (ViewGroup) alertView.getParent();
        parent.removeView(alertView);
    }


}

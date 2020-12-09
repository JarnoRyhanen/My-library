package com.choicely.mylibrary.originalBlackJack;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OriginalBlackJackActivity extends AppCompatActivity {

    private static final String TAG = "BlackJackActivity";

    private Random random = new Random();
    private List<String> list = new ArrayList<>();

    private TextView dealerCardValue;
    private TextView playerCardValue;

    private TextView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5;
    private TextView playerCard1, playerCard2, playerCard3, playerCard4, playerCard5;

    private List<TextView> dealersCards = new ArrayList<>();

    private Button placeBet;
    private Button hit;
    private Button surrender;
    private Button stand;
    private Button playAgain;

    private int firstCardDealer = 0;
    private int getPlayerCardScore;
    private int getDealerCardScore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.original_black_jack);


        dealerCard1 = findViewById(R.id.black_jack_dealer_card_1);
        dealerCard2 = findViewById(R.id.black_jack_dealer_card_2);
        dealerCard3 = findViewById(R.id.black_jack_dealer_card_3);
        dealerCard4 = findViewById(R.id.black_jack_dealer_card_4);
        dealerCard5 = findViewById(R.id.black_jack_dealer_card_5);

        dealersCards.add(dealerCard3);
        dealersCards.add(dealerCard4);
        dealersCards.add(dealerCard5);

        playerCard1 = findViewById(R.id.black_jack_player_card_1);
        playerCard2 = findViewById(R.id.black_jack_player_card_2);
        playerCard3 = findViewById(R.id.black_jack_player_card_3);
        playerCard4 = findViewById(R.id.black_jack_player_card_4);
        playerCard5 = findViewById(R.id.black_jack_player_card_5);

        dealerCardValue = findViewById(R.id.black_jack_dealer_card_value);
        playerCardValue = findViewById(R.id.black_jack_player_card_value);

        placeBet = findViewById(R.id.black_jack_start_game);
        hit = findViewById(R.id.black_jack_hit);
        surrender = findViewById(R.id.black_jack_surrender);
        stand = findViewById(R.id.black_jack_stand);
        playAgain = findViewById(R.id.black_jack_play_again);


        setButtonsInvisible();
        getDealerCardScore = Integer.parseInt(dealerCardValue.getText().toString());
        getPlayerCardScore = Integer.parseInt(playerCardValue.getText().toString());
    }

    private void setButtonsInvisible() {
        hit.setVisibility(View.GONE);
        surrender.setVisibility(View.GONE);
        stand.setVisibility(View.GONE);
        playAgain.setVisibility(View.GONE);
    }

    //The game will begin once onPlaceBetClicked is called
    public void onStartGameClicked(View view) {

        placeBet.setVisibility(View.GONE);
        hit.setVisibility(View.VISIBLE);
        surrender.setVisibility(View.VISIBLE);
        stand.setVisibility(View.VISIBLE);

        getDealerCardScore = 0;
        firstCardDealer = checkIfNumberIsOver10(random.nextInt(13) + 1);
        int checkedDealerFirstCard = checkIfDealerNumberIs1(firstCardDealer);

        getDealerCardScore = checkedDealerFirstCard;

        Log.d(TAG, "onPlaceBetCLicked: Dealers first card" + firstCardDealer);

        dealerCard1.setText(String.valueOf(checkedDealerFirstCard));
        dealerCard2.setText("??");
        dealerCardValue.setText(String.valueOf(checkedDealerFirstCard));


        int firstPlayerCardValue = checkIfNumberIsOver10(random.nextInt(13) + 1);
        int secondPlayerCardValue = checkIfNumberIsOver10(random.nextInt(13) + 1);

        getPlayerCardScore = 0;
        int checkedPlayerCard1 = checkIfPlayerNumberIs1(firstPlayerCardValue);
        getPlayerCardScore += checkedPlayerCard1;
        int checkedPlayerCard2 = checkIfPlayerNumberIs1(secondPlayerCardValue);
        getPlayerCardScore += checkedPlayerCard2;

        Log.d(TAG, "onPlaceBetCLicked: Total card value: " + getPlayerCardScore);

        playerCard1.setText(String.valueOf(checkedPlayerCard1));
        playerCard2.setText(String.valueOf(checkedPlayerCard2));
        playerCardValue.setText(String.valueOf(getPlayerCardScore));

        if (getPlayerCardScore == 21) {
            Toast.makeText(this, "BLACKJACK, Player won", Toast.LENGTH_SHORT).show();

            setButtonsInvisible();
            playAgain.setVisibility(View.VISIBLE);
        }
    }


    private int onHitClickedCounter = 0;

    public void onHitClicked(View view) {

        onHitClickedCounter += 1;

        if (onHitClickedCounter == 1) {
            int randomNumberPlayer3 = checkIfNumberIsOver10(random.nextInt(13) + 1);
            int checkedPlayerCard3 = checkIfPlayerNumberIs1(randomNumberPlayer3);

            playerCard3.setText(String.valueOf(checkedPlayerCard3));
            getPlayerCardScore = getPlayerCardScore + checkedPlayerCard3;

            Log.d(TAG, "onHitClicked: " + getPlayerCardScore);
            playerCardValue.setText(String.valueOf(getPlayerCardScore));


        } else if (onHitClickedCounter == 2) {
            int randomNumberPlayer4 = checkIfNumberIsOver10(random.nextInt(13) + 1);
            int checkedPlayerCard4 = checkIfPlayerNumberIs1(randomNumberPlayer4);

            playerCard4.setText(String.valueOf(checkedPlayerCard4));
            getPlayerCardScore = getPlayerCardScore + checkedPlayerCard4;

            Log.d(TAG, "onHitClicked: " + getPlayerCardScore);
            playerCardValue.setText(String.valueOf(getPlayerCardScore));

        } else if (onHitClickedCounter == 3) {

            int randomNumberPlayer5 = checkIfNumberIsOver10(random.nextInt(13) + 1);
            int checkedPlayerCard5 = checkIfPlayerNumberIs1(randomNumberPlayer5);


            playerCard5.setText(String.valueOf(checkedPlayerCard5));
            getPlayerCardScore = getPlayerCardScore + checkedPlayerCard5;

            Log.d(TAG, "onHitClicked: " + getPlayerCardScore);
            playerCardValue.setText(String.valueOf(getPlayerCardScore));
        }
        if (getPlayerCardScore > 21) {
            Toast.makeText(this, "Game over, dealer won", Toast.LENGTH_SHORT).show();

            setButtonsInvisible();
            playAgain.setVisibility(View.VISIBLE);

        } else if (getPlayerCardScore == 21) {
            Toast.makeText(this, "Game over, Player won", Toast.LENGTH_SHORT).show();

            setButtonsInvisible();
            playAgain.setVisibility(View.VISIBLE);

        }
    }


    @SuppressLint("SetTextI18n")
    private void startOver() {
        playerCardValue.setText("00");
        playerCard1.setText("00");
        playerCard2.setText("00");
        playerCard3.setText("00");
        playerCard4.setText("00");
        playerCard5.setText("00");


        dealerCardValue.setText("00");
        dealerCard1.setText("00");
        dealerCard2.setText("00");
        dealerCard3.setText("00");
        dealerCard4.setText("00");
        dealerCard5.setText("00");

    }

    public void onSurrenderClicked(View view) {


        Toast.makeText(this, "Game over,  You surrendered", Toast.LENGTH_SHORT).show();

        setButtonsInvisible();
        startOver();
        playAgain.setVisibility(View.VISIBLE);


    }

    public void onStandClicked(View view) {

        int randomNumberDealer2 = checkIfNumberIsOver10(random.nextInt(13) + 1);
        int checkedDealerCard2 = checkIfPlayerNumberIs1(randomNumberDealer2);

        int newDealerValue = getDealerCardScore + checkedDealerCard2;

        Log.d(TAG, "onHitClicked: " + newDealerValue);


        dealerCard2.setText(String.valueOf(checkedDealerCard2));
        dealerCardValue.setText(String.valueOf(newDealerValue));

        if (Integer.parseInt(dealerCardValue.getText().toString()) == 21) {
            Toast.makeText(this, "BLACKJACK, Dealer won", Toast.LENGTH_SHORT).show();
            setButtonsInvisible();
            playAgain.setVisibility(View.VISIBLE);
        }

        int i = 0;
        while (Integer.parseInt((String) dealerCardValue.getText()) < 17) {

            int randomNumberDealer = random.nextInt(14) + 1;

            if (randomNumberDealer > 10) {
                randomNumberDealer = 10;
            }

            dealersCards.get(i).setText(String.valueOf(randomNumberDealer));
            int newDealerValue2 = Integer.parseInt(String.valueOf(dealerCardValue.getText())) + randomNumberDealer;
            dealerCardValue.setText(String.valueOf(newDealerValue2));
            i++;
        }

        if (Integer.parseInt(String.valueOf(dealerCardValue.getText())) > Integer.parseInt(String.valueOf(playerCardValue.getText())) &&
                Integer.parseInt(String.valueOf(dealerCardValue.getText())) <= 21) {
            Toast.makeText(this, "Game over, Dealer won", Toast.LENGTH_SHORT).show();

        } else if (Integer.parseInt(String.valueOf(dealerCardValue.getText())) == Integer.parseInt(String.valueOf(playerCardValue.getText()))) {
            Toast.makeText(this, "Game over, Draw", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Game over, player won", Toast.LENGTH_SHORT).show();
        }
        setButtonsInvisible();
        playAgain.setVisibility(View.VISIBLE);


    }

    public void onPlayAgainClicked(View view) {
        setButtonsInvisible();
        startOver();
        onHitClickedCounter = 0;
        placeBet.setVisibility(View.VISIBLE);
    }

    private int checkIfNumberIsOver10(int number) {
        if (number > 10) {
            number = 10;
            return number;
        }
        return number;
    }

    private int checkIfPlayerNumberIs1(int number) {

        if (number == 1 && getPlayerCardScore <= 10) {
            number = 11;
            return number;
        }
        return number;
    }

    private int checkIfDealerNumberIs1(int number) {

        if (number == 1 && getDealerCardScore <= 10) {
            number = 11;
            return number;
        }
        return number;
    }

}

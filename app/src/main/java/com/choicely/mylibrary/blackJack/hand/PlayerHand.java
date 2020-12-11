package com.choicely.mylibrary.blackJack.hand;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.choicely.mylibrary.blackJack.Card;
import com.choicely.mylibrary.blackJack.PopUpAlert;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

public class PlayerHand extends Hand {


    private final static String TAG = "PlayerHand";

    private AlertDialog.Builder builder;

    public PlayerHand(Shoe shoe) {
        super(shoe);
    }

    @Override
    protected void setStartingHand() {
        addCard();
        addCard();
    }

    private void setStatus(DealerHand dealerHand) {
        if (getHandValue() < dealerHand.getHandValue()
                && dealerHand.getHandValue() < 21) {
            setStatus(HandStatus.LOSS);
        } else if (getHandValue() > dealerHand.getHandValue()) {
            setStatus(HandStatus.WIN);
        } else if (getHandValue() == dealerHand.getHandValue()) {
            setStatus(HandStatus.DRAW);
        }
    }

    public void compareToDealerHand(DealerHand dealerHand) {
        setStatus(dealerHand);

        switch (getStatus()) {
            case WIN:
                popUpAlert.alertPopUp(handValueText, "Congratulations", "You won");
                break;
            case LOSS:
                popUpAlert.alertPopUp(handValueText, "Dealer took this one", "Dealer won, You lost");
                break;
            case DRAW:
                popUpAlert.alertPopUp(handValueText, "Draw", "You drew");
                break;
            case NULL:
                break;
        }
    }


    @Override
    public void setActive(boolean active) {
        super.setActive(active);

        if (active) {
            setButtonListeners();
        }
    }

    private void setButtonListeners() {
        if (hitButton == null || doubleButton == null || splitButton == null ||
                insuranceButton == null || standButton == null) {
            return;
        }

        hitButton.setOnClickListener(this::onHitClicked);
        doubleButton.setOnClickListener(this::onDoubleClicked);
        splitButton.setOnClickListener(this::onSplitClicked);
        insuranceButton.setOnClickListener(this::onInsuranceClicked);
        standButton.setOnClickListener(this::onStandClicked);
    }

    private void getCardValues() {
        for (Card c : cards) {
            Log.d(TAG, "Player's Cards: " + c.getBlackJackCardValue());
        }
    }

    private PopUpAlert popUpAlert = new PopUpAlert();

    private void onHitClicked(View view) {

        if (getHandValue() <= 21) {
            addCard();
        }

        getCardValues();

        switch (getStatus()) {
            case WIN:
                popUpAlert.alertPopUp(handValueText, "Congratulations", "You won");
                break;
            case LOSS:
                popUpAlert.alertPopUp(handValueText, "Better luck next time", "You lost");
                break;
            case NULL:
                break;
        }

    }


    private void onStandClicked(View view) {

        onHandFinished();
    }

    private void onSplitClicked(View view) {
        Log.d(TAG, "onSplitClicked: ");
    }

    private void onDoubleClicked(View view) {
        Log.d(TAG, "onDoubleClicked: ");
    }


    private void onInsuranceClicked(View view) {
        Log.d(TAG, "onInsuranceClicked: ");
    }

}

package com.choicely.mylibrary.blackJack.hand;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;

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

    public void compareToDealerHand(DealerHand dealerHand) {

        if (getHandValue() < dealerHand.getHandValue()) {
            popUpAlert.alertPopUp(handValueText, "Dealer took this one", "Dealer won, You lost");
        }
        else if (getHandValue() > dealerHand.getHandValue()) {
            popUpAlert.alertPopUp(handValueText, "Congratulations", "You won");
        }
        else  {
            popUpAlert.alertPopUp(handValueText, "Draw", "You drew");
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

    private PopUpAlert popUpAlert = new PopUpAlert();

    private void onHitClicked(View view) {

        if (getHandValue() <= 21) {
            addCard();
        }
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

        Log.d(TAG, "onHitClicked: hand value:" + getHandValue());
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

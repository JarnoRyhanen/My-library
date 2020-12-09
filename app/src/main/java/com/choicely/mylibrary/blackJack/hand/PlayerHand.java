package com.choicely.mylibrary.blackJack.hand;

import android.util.Log;
import android.view.View;

import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

public class PlayerHand extends Hand {

    private final static String TAG ="PlayerHand";

    public PlayerHand(Shoe shoe) {
        super(shoe);
    }

    protected void setStartingHand() {
        addCard();
        addCard();
    }

    public void compareToDealerHand(Hand hand) {

    }

    public void setActive(Boolean active) {
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


    private void onHitClicked(View view) {
        if(getHandValue() <= 21){

        }
        addCard();
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

    public void onInsuranceClicked(View view) {
        Log.d(TAG, "onInsuranceClicked: ");
    }

}

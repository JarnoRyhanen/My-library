package com.choicely.mylibrary.blackJack.hand;

import android.util.Log;
import android.view.View;

import com.choicely.mylibrary.blackJack.BetAndBalance;
import com.choicely.mylibrary.blackJack.BlackJackActivity;
import com.choicely.mylibrary.blackJack.PopUpAlert;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

public class PlayerHand extends Hand {


    private final static String TAG = "PlayerHand";
    private BetAndBalance betAndBalance = new BetAndBalance();
    private BlackJackActivity blackJackActivity = new BlackJackActivity();

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
                betAndBalance.setVictorySum(betAndBalance.getYourBet() * 2);
                Log.d(TAG, "compareToDealerHand: victory sum: " + betAndBalance.getVictorySum());
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
            getCardValues(this);
        }
    }

    private void setButtonListeners() {
        if (hitButton == null || doubleButton == null || splitButton == null ||
                insuranceButton == null || standButton == null) {
            return;
        }
        enableAllButtons();

        splitButton.setEnabled(false);
        doubleButton.setEnabled(false);
        insuranceButton.setEnabled(false);

        if (getHandValue() <= 11 && getHandValue() >= 9) {
            doubleButton.setEnabled(true);
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
        checkGameStatus();
    }


    private void onStandClicked(View view) {
        onHandFinished();
    }

    private void onSplitClicked(View view) {
        Log.d(TAG, "onSplitClicked: ");
        splitLayout.setVisibility(View.VISIBLE);
    }

    private void onDoubleClicked(View view) {
        Log.d(TAG, "onDoubleClicked: ");

        blackJackActivity.doubleBet(betAndBalance.getYourBet() * 2);
        addCard();
        if (checkGameStatus() == false) {
            onHandFinished();
        }
    }

    private void onInsuranceClicked(View view) {
        Log.d(TAG, "onInsuranceClicked: ");

    }

    public void setSplitAvailable() {
        splitButton.setEnabled(true);
    }

    public void setSplitLayoutInvisible() {
        splitLayout.setVisibility(View.GONE);
    }

    public void setBetAndBalance(BetAndBalance betAndBalance) {
        this.betAndBalance = betAndBalance;
    }

    public void setBlackJackActivity(BlackJackActivity blackJackActivity) {
        this.blackJackActivity = blackJackActivity;
    }

    private boolean checkGameStatus() {
        switch (getStatus()) {
            case WIN:
                popUpAlert.alertPopUp(handValueText, "Congratulations", "You won");
                betAndBalance.setVictorySum(betAndBalance.getYourBet() * 2);
                Log.d(TAG, "compareToDealerHand: victory sum: " + betAndBalance.getVictorySum());
                return true;
            case LOSS:
                popUpAlert.alertPopUp(handValueText, "Better luck next time", "You lost");
                return true;
            case NULL:
                break;
        }
        return false;
    }

//    public void setDealerHand(DealerHand dealerHand) {
//        this.dealerHand = dealerHand.getDealerHand();
//    }

    private void enableAllButtons() {
        doubleButton.setEnabled(true);
        hitButton.setEnabled(true);
        insuranceButton.setEnabled(true);
        splitButton.setEnabled(true);
        standButton.setEnabled(true);
    }

    private void disableAllButtons() {
        standButton.setEnabled(false);
        doubleButton.setEnabled(false);
        hitButton.setEnabled(false);
        insuranceButton.setEnabled(false);
        splitButton.setEnabled(false);
    }

}

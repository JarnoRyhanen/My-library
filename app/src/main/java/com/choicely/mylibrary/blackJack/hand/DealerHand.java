package com.choicely.mylibrary.blackJack.hand;

import android.app.AlertDialog;
import android.util.Log;

import com.choicely.mylibrary.blackJack.BetAndBalance;
import com.choicely.mylibrary.blackJack.BlackJackActivity;
import com.choicely.mylibrary.blackJack.Card;
import com.choicely.mylibrary.blackJack.PopUpAlert;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

public class DealerHand extends Hand {

    private static final String TAG = "DealerHand";
    private AlertDialog.Builder builder;

    private BetAndBalance betAndBalance = new BetAndBalance();
    protected boolean isInsuranceActive = false;

    public DealerHand(Shoe shoe) {
        super(shoe);
    }

    protected void setStartingHand() {
        addCard();
    }

    public void setActive(boolean active) {
        super.setActive(active);
        if (active) {
            play();
        }

    }

    private PopUpAlert popUpAlert = new PopUpAlert();

    @Override
    protected void onHandFinished() {
        super.onHandFinished();
        switch (getStatus()) {
            case WIN:
                Log.d(TAG, "checkForDealerBlackJack: Dealer has BlackJack");
                betAndBalance.setBalance((betAndBalance.getYourBet() + betAndBalance.getInsurance()) + betAndBalance.getBalance());
                popUpAlert.alertPopUp(handValueText, "Dealer took this one", "Dealer won, You lost");
                break;
            case BLACKJACK:
                checkIfInsuranceWasClicked();
                break;

            case LOSS:
                popUpAlert.alertPopUp(handValueText, "Dealer got over 21", "Dealer lost, You Win");
                betAndBalance.setVictorySum(betAndBalance.getYourBet() * 2);
                Log.d(TAG, "compareToDealerHand: victory sum: " + betAndBalance.getVictorySum());

                break;
        }
    }

    protected void checkIfInsuranceWasClicked() {
        if (isInsuranceActive) {
            int sum = betAndBalance.getYourBet() + betAndBalance.getInsurance();
            blackJackActivity.updateBalance(sum);
            popUpAlert.alertPopUp(handValueText, "Dealer got Black Jack, but you insured", "Insurance vs Black Jack");
        } else {
            popUpAlert.alertPopUp(handValueText, "Dealer got Black Jack", "Dealer won, You lost");
        }
    }


    private void getCardValues() {
        for (Card c : cards) {
            Log.d(TAG, "Dealer's Cards: " + c.getBlackJackCardValue());
        }
    }

    private void play() {
        if (getHandValue() < 17) {
            addCard();
            getCardValues();
            handValueText.postDelayed(this::play, 1000);
        } else {
            onHandFinished();
        }

    }

    public void setBetAndBalance(BetAndBalance betAndBalance) {
        this.betAndBalance = betAndBalance;
    }

    BlackJackActivity blackJackActivity = new BlackJackActivity();

    public void setBlackJackActivity(BlackJackActivity blackJackActivity) {
        this.blackJackActivity = blackJackActivity;
    }
}

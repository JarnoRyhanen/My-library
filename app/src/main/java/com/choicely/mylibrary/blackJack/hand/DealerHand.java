package com.choicely.mylibrary.blackJack.hand;

import android.app.AlertDialog;
import android.util.Log;

import com.choicely.mylibrary.blackJack.Card;
import com.choicely.mylibrary.blackJack.PopUpAlert;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

public class DealerHand extends Hand {

    private static final String TAG = "DealerHand";
    private AlertDialog.Builder builder;


    public DealerHand(Shoe shoe) {
        super(shoe);
    }

    protected void setStartingHand(){
        addCard();
    }

    public void setActive(boolean active){
        super.setActive(active);
        if(active){
            play();
        }

    }
    private PopUpAlert popUpAlert = new PopUpAlert();

    @Override
    protected void onHandFinished() {
        super.onHandFinished();
        switch (getStatus()) {
            case WIN:
                popUpAlert.alertPopUp(handValueText, "Dealer took this one", "Dealer won, You lost");
                break;
            case LOSS:
                popUpAlert.alertPopUp(handValueText, "Dealer got over 21", "Dealer lost, You Win");
                break;
        }
    }

    private void getCardValues(){
        for (Card c : cards) {
            Log.d(TAG, "Dealer's Cards: " + c.getBlackJackCardValue());
        }
    }

    private void play() {
        if(getHandValue() < 17){
            addCard();
            getCardValues();
            handValueText.postDelayed(this::play, 1000);
        }else {
            onHandFinished();
        }

    }
}

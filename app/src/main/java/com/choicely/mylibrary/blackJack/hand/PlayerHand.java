package com.choicely.mylibrary.blackJack.hand;

import android.util.Log;
import android.view.View;

import com.choicely.mylibrary.R;
import com.choicely.mylibrary.blackJack.BetAndBalance;
import com.choicely.mylibrary.blackJack.BlackJackActivity;
import com.choicely.mylibrary.blackJack.Card;
import com.choicely.mylibrary.blackJack.PopUpAlert;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

public class PlayerHand extends Hand {

    private final static String TAG = "PlayerHand";
    private BetAndBalance betAndBalance = new BetAndBalance();
    private BlackJackActivity blackJackActivity = new BlackJackActivity();
    private DealerHand dealerHand;


    public PlayerHand(Shoe shoe) {
        super(shoe);
    }

    private Card cardFromSplitPressed;

    public PlayerHand(Card splitCard, Shoe shoe) {
        super(splitCard, shoe);
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
            Log.d(TAG, "setActive: active");
        }
    }

    private void setButtonListeners() {

        if (hitButton == null || doubleButton == null || splitButton == null ||
                insuranceButton == null || standButton == null) {
            return;
        }
//        splitButton.setEnabled(false);
        doubleButton.setEnabled(false);
        insuranceButton.setEnabled(false);

        checkDoubleAvailability();
//        checkInsuranceAvailability();

        hitButton.setOnClickListener(this::onHitClicked);
        doubleButton.setOnClickListener(this::onDoubleClicked);
        splitButton.setOnClickListener(this::onSplitClicked);
        insuranceButton.setOnClickListener(this::onInsuranceClicked);
        standButton.setOnClickListener(this::onStandClicked);
    }


    private final PopUpAlert popUpAlert = new PopUpAlert();

    private void onHitClicked(View view) {

        if (getHandValue() <= 21) {
            addCard();
        }
        checkGameStatus();
    }

    private void onStandClicked(View view) {
//        if(has sub hands) {
//
//        }
        if (playerSplitHand instanceof PlayerHand) {
    
        }
        onHandFinished();
    }

    private PlayerHand playerSplitHand;

    private void onSplitClicked(View view) {
        splitLayout.setVisibility(View.VISIBLE);

        cardFromSplitPressed = getCards().get(0);
        getCards().remove(0);
        Log.d(TAG, "onSplitClicked: card from split pressed: " + cardFromSplitPressed.getSuite() + ", " + cardFromSplitPressed.getNumberValue());

        playerSplitHand = new PlayerHand(cardFromSplitPressed, shoe);
        playerSplitHand.findButtonsFromView(buttonLayout);
        playerSplitHand.findHandSpecificViewsFromView(splitLayout.findViewById(R.id.player_player_hand_2_values));
        playerSplitHand.addSplitCard(cardFromSplitPressed);


        Log.d(TAG, "onSplitClicked: " + getSplitHandValue());
        PlayerHand.this.setActive(false);
        playerSplitHand.setActive(true);
        playerSplitHand.onDataChangedSplitHand();

    }

    private void onDoubleClicked(View view) {
        Log.d(TAG, "onDoubleClicked: ");

        blackJackActivity.doubleBet(betAndBalance.getYourBet() * 2);
        addCard();
        if (!checkGameStatus()) {
            onHandFinished();
        }
    }

    private void onInsuranceClicked(View view) {
        Log.d(TAG, "onInsuranceClicked: ");
        blackJackActivity.insurance();
        dealerHand.isInsuranceActive = true;
        if (insuranceButton != null) {
            insuranceButton.setEnabled(false);
        }
    }

    public void setSplitAvailable() {
        if (splitButton != null) {
            splitButton.setEnabled(true);
        }
    }

    public void setSplitLayoutInvisible() {
        splitLayout.setVisibility(View.GONE);
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

    private void checkDoubleAvailability() {
        if (getHandValue() <= 11 && getHandValue() >= 9) {
            if (doubleButton != null) {
                doubleButton.setEnabled(true);
            }
        }
    }
//
//    private void checkInsuranceAvailability() {
//        if (dealerHand.getHandValue() == 11) {
//            insuranceButton.setEnabled(true);
//        }
//    }

    public void setBetAndBalance(BetAndBalance betAndBalance) {
        this.betAndBalance = betAndBalance;
    }

    public void setBlackJackActivity(BlackJackActivity blackJackActivity) {
        this.blackJackActivity = blackJackActivity;
    }

    public void setDealerHand(DealerHand dealerHand) {
        this.dealerHand = dealerHand;
    }

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

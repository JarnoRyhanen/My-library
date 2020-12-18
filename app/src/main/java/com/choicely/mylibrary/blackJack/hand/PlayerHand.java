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
//        checkInsuranceAvailability();

        if (hitButton == null || doubleButton == null || splitButton == null ||
                insuranceButton == null || standButton == null) {
            return;
        }
        splitButton.setEnabled(false);
        doubleButton.setEnabled(false);
        insuranceButton.setEnabled(false);

        checkDoubleAvailability();

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
        onSplitPressedListener.onSplitPressed();
        onHandFinished();
    }

    private SplitPressedListener onSplitPressedListener = new SplitPressedListener() {
        @Override
        public void onSplitPressed() {
            if (playerSplitHand instanceof PlayerHand) {
                Log.d(TAG, "onSplitPressed: Split was pressed");
                playerSplitHand.setActive(false);
                PlayerHand.this.setActive(true);
                splitLayout.setActivated(false);

            } else {
                Log.d(TAG, "onSplitPressed: split was not pressed");
            }
        }
    };
    private PlayerHand playerSplitHand;

    private void onSplitClicked(View view) {
        splitLayout.setVisibility(View.VISIBLE);

        Card cardFromSplitPressed = getCards().get(0);
        getCards().remove(0);
        Log.d(TAG, "onSplitClicked: card from split pressed: " + cardFromSplitPressed.getSuite() + ", " + cardFromSplitPressed.getNumberValue());

        playerSplitHand = new PlayerHand(cardFromSplitPressed, shoe);
        playerSplitHand.findButtonsFromView(buttonLayout);
        playerSplitHand.findHandSpecificViewsFromView(splitLayout.findViewById(R.id.player_player_hand_2_values));
        playerSplitHand.cards.add(cardFromSplitPressed);
        playerSplitHand.setSplitPressedListener(onSplitPressedListener);

        playerSplitHand.splitHandBet.setVisibility(View.VISIBLE);
        PlayerHand.this.splitHandBet.setVisibility(View.VISIBLE);
        PlayerHand.this.splitHandBet.setText("sdfg");
        playerSplitHand.splitHandBet.setText(String.format("wredsf"));

        Log.d(TAG, "onSplitClicked: " + getHandValue());
        playerSplitHand.setActive(true);
        PlayerHand.this.setActive(false);
        splitLayout.setActivated(true);
        playerSplitHand.onDataChanged();
    }


    public void setSplitPressedListener(SplitPressedListener listener) {
        this.onSplitPressedListener = listener;
    }

    public interface SplitPressedListener {
        void onSplitPressed();
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
            doubleButton.setEnabled(true);
        }
    }

    private void checkInsuranceAvailability() {
        if (dealerHand.getHandValue() == 11 && insuranceButton != null && dealerHand != null) {
            insuranceButton.setEnabled(true);
        }
    }

    public void setBetAndBalance(BetAndBalance betAndBalance) {
        this.betAndBalance = betAndBalance;
    }

    public void setBlackJackActivity(BlackJackActivity blackJackActivity) {
        this.blackJackActivity = blackJackActivity;
    }

    public void setDealerHand(DealerHand dealerHand) {
        this.dealerHand = dealerHand;
    }

}

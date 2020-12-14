package com.choicely.mylibrary.blackJack.hand;

import android.util.Log;

import androidx.annotation.Nullable;

import com.choicely.mylibrary.blackJack.Card;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand extends HandUI {

    private static final String TAG = "Hand";
    private OnHandPlayedListener onHandPlayedListener;

    private final Shoe shoe;

    final List<Card> cards = new ArrayList<>();

    protected boolean isActive;

    @Nullable
    private HandStatus status = null;


    public enum HandStatus {
        WIN, LOSS, DRAW, NULL
    }


    public Hand(Shoe shoe) {
        this.shoe = shoe;

        setStartingHand();
        onDataChanged();
    }

    protected abstract void setStartingHand();

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;

        onDataChanged();
    }

    public void addCard() {
        Card card = shoe.getNextCard();
        cards.add(card);

        onDataChanged();

    }

    @Nullable
    public HandStatus getStatus() {
        return status;
    }

    protected void setStatus(HandStatus status) {
        this.status = status;
    }

    protected void onHandFinished() {
        if (onHandPlayedListener != null) {
            onHandPlayedListener.onHandPlayed(this);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void getCardValues(PlayerHand playerHand){
        if(playerHand.getCards().get(0).getBlackJackCardValue() == playerHand.getCards().get(1).getBlackJackCardValue()){
            Log.d(TAG, "getCardValues: card 1: " + playerHand.getCards().get(0).getBlackJackCardValue());
            Log.d(TAG, "getCardValues: card 2: " + playerHand.getCards().get(1).getBlackJackCardValue());

            playerHand.setSplitAvailable();
        }

    }

    public int getHandValue() {
        int totalValue = 0;
        for (Card c : cards) {
            totalValue += c.getBlackJackCardValue();
        }
        updateContent();
        return totalValue;
    }

    public void setResult() {
        if (getHandValue() == 21) {
            setStatus(HandStatus.WIN);
        } else if (getHandValue() > 21) {
            setStatus(HandStatus.LOSS);
        } else {
            setStatus(HandStatus.NULL);
        }
    }

    public void onDataChanged() {
        if (handValueText != null) {
            setResult();
            Log.d(TAG, "STATUS: " + getStatus());
            Log.d(TAG, "handValue: " + getHandValue());
            handValueText.setText(String.valueOf(getHandValue()));
        }

    }

    public void setOnHandPlayerListener(OnHandPlayedListener listener) {
        this.onHandPlayedListener = listener;
    }

    public interface OnHandPlayedListener {

        void onHandPlayed(Hand hand);

    }

    public void updateContent() {
        adapter.clearList();

        for (int i = 0; i < cards.size(); i++) {
            adapter.add(cards.get(i));
        }

        adapter.notifyDataSetChanged();
    }
}

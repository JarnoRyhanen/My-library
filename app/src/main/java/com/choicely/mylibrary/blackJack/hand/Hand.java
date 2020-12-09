package com.choicely.mylibrary.blackJack.hand;

import android.util.Log;

import androidx.annotation.Nullable;

import com.choicely.mylibrary.blackJack.Card;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand extends HandUI {

    private static final String TAG = "Hand";
    private final Shoe shoe;
    private final List<Card> cardList = new ArrayList<>();
    private OnHandPlayedListener onHandPlayedListener;
    protected boolean isActive;

    @Nullable
    private HandStatus status = null;

    public enum HandStatus {
        LOSS, WIN, DRAW
    }

    public Hand(Shoe shoe) {
        this.shoe = shoe;

        setStartingHand();
        onDataChanged();
    }

    protected abstract void setStartingHand();

    public int getHandValue() {
        int value = 0;
        for (Card card : cardList) {
            value += card.getBlackJackCardValue();
        }
        return value;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;

        onDataChanged();
    }

    public void addCard() {
        cardList.add(shoe.getNextCard());

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

    public void onDataChanged() {
        if (handValueText != null) {
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


}

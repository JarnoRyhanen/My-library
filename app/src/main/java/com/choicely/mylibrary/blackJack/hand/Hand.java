package com.choicely.mylibrary.blackJack.hand;

import android.util.Log;

import androidx.annotation.Nullable;

import com.choicely.mylibrary.blackJack.Card;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand extends HandUI {

    private static final String TAG = "Hand";

    protected Shoe shoe;
    protected Card splitCard;
    final List<Card> cards = new ArrayList<>();
    final List<Card> splitHandCards = new ArrayList<>();
    protected boolean isActive;

    @Nullable
    private HandStatus status = null;

    public enum HandStatus {
        WIN, LOSS, DRAW, NULL, BLACKJACK
    }

    public Hand(Card splitCard, Shoe shoe) {
        this.splitCard = splitCard;
        this.shoe = shoe;
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

    public void addSplitCard(Card card) {
        splitHandCards.add(card);
        onDataChangedSplitHand();
    }


    @Nullable
    public HandStatus getStatus() {
        return status;
    }

    protected void setStatus(HandStatus status) {
        this.status = status;
    }


    public List<Card> getCards() {
        return cards;
    }

    public void getCardValues(PlayerHand playerHand) {
        if (playerHand.getCards().size() == 2) {
            if (playerHand.getCards().get(0).getBlackJackCardValue() == playerHand.getCards().get(1).getBlackJackCardValue()) {
                Log.d(TAG, "getCardValues: card 1: " + playerHand.getCards().get(0).getBlackJackCardValue());
                Log.d(TAG, "getCardValues: card 2: " + playerHand.getCards().get(1).getBlackJackCardValue());

                playerHand.setSplitAvailable();
            }
        }
    }

    public int getSplitHandValue() {
        int totalValue = 0;
        for (Card c : splitHandCards) {

            if (totalValue < 11 && c.getBlackJackCardValue() == 1) {
                totalValue += 11;
            } else {
                totalValue += c.getBlackJackCardValue();
            }
        }
        return totalValue;
    }


    public int getHandValue() {
        int totalValue = 0;
        for (Card c : cards) {

            if (totalValue < 11 && c.getBlackJackCardValue() == 1) {
                totalValue += 11;
            } else {
                totalValue += c.getBlackJackCardValue();
            }
        }
        return totalValue;
    }

    public void setResult() {
        if (getHandValue() == 21 && getCards().size() == 2) {
            setStatus(HandStatus.BLACKJACK);
        } else if (getHandValue() == 21) {
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
            updateContent();

//            Log.d(TAG, "STATUS: " + getStatus());
//            Log.d(TAG, "handValue: " + getHandValue());
            handValueText.setText(String.valueOf(getHandValue()));
        }
    }

    public void onDataChangedSplitHand() {
        if (handValueText != null) {
            setResult();
            updateContentSplitHand();

//            Log.d(TAG, "STATUS: " + getStatus());
            Log.d(TAG, "handValue: " + getSplitHandValue());
            handValueText.setText(String.valueOf(getSplitHandValue()));
        }
    }

    private OnHandPlayedListener onHandPlayedListener;

    public void setOnHandPlayerListener(OnHandPlayedListener listener) {
        this.onHandPlayedListener = listener;
    }

    public interface OnHandPlayedListener {
        void onHandPlayed(Hand hand);
    }

    protected void onHandFinished() {
        if (onHandPlayedListener != null) {
            onHandPlayedListener.onHandPlayed(this);
        }
    }

    public void updateContent() {
        adapter.clearList();
        for (int i = 0; i < cards.size(); i++) {
            adapter.add(cards.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    public void updateContentSplitHand() {
        adapter.clearList();
        for (int i = 0; i < splitHandCards.size(); i++) {
            adapter.add(splitHandCards.get(i));
            Log.d(TAG, "onSplitClicked: cards: " +
                    splitHandCards.get(i).getBlackJackCardValue() + ", Suite: " +
                    splitHandCards.get(i).getSuite() + " real value: " +
                    splitHandCards.get(i).getNumberValue());
        }
        adapter.notifyDataSetChanged();
    }
}
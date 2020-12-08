package com.choicely.mylibrary.blackJack;

import androidx.annotation.IntRange;

import com.choicely.mylibrary.blackJack.cardDataInterfaces.BlackJackCardValue;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.CardNumber;
import com.choicely.mylibrary.blackJack.cardDataInterfaces.CardSuite;

public class Card implements CardNumber, CardSuite, BlackJackCardValue, Comparable<Card> {

    @IntRange(from = 1, to = 14)
    private int cardValue;
    private Suite suite;

    public Card(int cardValue, Suite suite) {
        this.cardValue = cardValue;
        this.suite = suite;
    }


    @Override
    public int getBlackJackCardValue() {
        int valueInGame;
        switch (cardValue) {
            case 14:
                //card was an ace
                valueInGame = 1;
                break;
            case 13:
            case 12:
            case 11:
                //card was a jack, a queen or a king
                valueInGame = 10;
                break;
            default:
                valueInGame = cardValue;
        }

        return valueInGame;
    }

    @Override
    public int getNumber() {
        return cardValue;
    }

    @Override
    public String getNumberValue() {
        String valueInGame;
        switch (cardValue) {
            default:
                valueInGame = String.valueOf(cardValue);
                break;
            case 1:
            case 14:
                valueInGame = "A";
                break;
            case 11:
                valueInGame = "J";
                break;
            case 12:
                valueInGame = "Q";
                break;
            case 13:
                valueInGame = "K";
                break;
        }
        return valueInGame;
    }

    @Override
    public Suite getSuite() {
        return suite;
    }

    private int getCompareValue() {
        int compareValue = cardValue;

        switch (suite) {
            case SPADES:
                compareValue += 400;
                break;
            case HEARTS:
                compareValue += 300;
                break;
            case CLUBS:
                compareValue += 200;
                break;
            case DIAMONDS:
                compareValue += 100;
                break;
        }
        return compareValue;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(getCompareValue(), o.getCompareValue());
    }
}

package com.choicely.mylibrary.blackJack;

import com.choicely.mylibrary.blackJack.cardDataInterfaces.CardSuite;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> deck = new Stack<>();


    public Deck() {
        for (CardSuite.Suite suite : CardSuite.Suite.values()) {
            for (int i = 2; i <= 14; i++) {
                deck.add(new Card(i, suite));
            }
        }
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Card getCard() {
        return deck.pop();
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
}

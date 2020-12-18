package com.choicely.mylibrary.blackJack.cardDataInterfaces;

import com.choicely.mylibrary.blackJack.Card;
import com.choicely.mylibrary.blackJack.Deck;

import java.util.Collections;
import java.util.Stack;

public class Shoe {

    private final int decks;
    private final Stack<Card> deckStack = new Stack<>();

    public Shoe(int decks) {
        this.decks = decks;
        shuffleAllCards();
    }

    private void shuffleAllCards() {
        for (int i = 0; i < decks; i++) {
            deckStack.addAll(new Deck().getDeck());
        }
        Collections.shuffle(deckStack);
    }

    public Card getNextCard() {
        return deckStack.pop();
    }

    public int getCardsSize() {
        return deckStack.size();
    }

}

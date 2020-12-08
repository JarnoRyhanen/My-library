package com.choicely.mylibrary.blackJack.cardDataInterfaces;

public interface CardSuite {

    enum Suite {
        SPADES,
        HEARTS,
        CLUBS,
        DIAMONDS;

    }

    Suite getSuite();

}

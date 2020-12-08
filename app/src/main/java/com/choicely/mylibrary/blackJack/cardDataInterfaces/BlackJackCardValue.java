package com.choicely.mylibrary.blackJack.cardDataInterfaces;

import androidx.annotation.IntRange;

public interface BlackJackCardValue {

    @IntRange(from = 1, to = 10)
    int getBlackJackCardValue();

}

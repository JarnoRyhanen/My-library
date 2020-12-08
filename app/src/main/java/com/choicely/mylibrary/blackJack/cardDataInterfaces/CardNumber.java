package com.choicely.mylibrary.blackJack.cardDataInterfaces;

import androidx.annotation.StringDef;

public interface CardNumber {

    int getNumber();
    @StringDef(value = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"})
    @interface CardNumberValue{
    }

    @CardNumberValue
    String getNumberValue();

}

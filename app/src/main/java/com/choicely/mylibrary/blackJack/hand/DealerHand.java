package com.choicely.mylibrary.blackJack.hand;

import com.choicely.mylibrary.blackJack.cardDataInterfaces.Shoe;

public class DealerHand extends Hand{

    public DealerHand(Shoe shoe) {
        super(shoe);
    }

    protected void setStartingHand(){
        addCard();
    }

    public void setActive(Boolean active){
        super.setActive(active);
        if(active){
            play();
        }

    }

    private void play() {
        if(getHandValue() < 17){
            addCard();
            handValueText.postDelayed(this::play, 1000);
        }else {
            onHandFinished();
        }

    }


}

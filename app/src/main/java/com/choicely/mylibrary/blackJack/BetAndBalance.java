package com.choicely.mylibrary.blackJack;

public class BetAndBalance {
    private static final String TAG = "BetAndBalance";
    private int balance = 1000;
    private int yourBet;
    private int victorySum;
    private int insurance;

    public int getVictorySum() {
        return victorySum;
    }

    public void setVictorySum(int victorySum) {
        this.victorySum = victorySum;
        onBalanceUpdatedListener.onBalanceUpdated(getVictorySum());
    }


    public int getYourBet() {
        return yourBet;
    }

    public void setYourBet(int yourBet) {
        this.yourBet = yourBet;

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    private BalanceUpdatedListener onBalanceUpdatedListener;

    public void setOnBalanceUpdatedListener(BalanceUpdatedListener listener) {
        this.onBalanceUpdatedListener = listener;
    }


    public interface BalanceUpdatedListener {
        void onBalanceUpdated(int betWon);
    }
}

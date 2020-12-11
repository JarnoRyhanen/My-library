package com.choicely.mylibrary.sulkeiset;

public class Lamppu {

    //onko päällä vai ei

    private boolean isActive;
    private int color;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

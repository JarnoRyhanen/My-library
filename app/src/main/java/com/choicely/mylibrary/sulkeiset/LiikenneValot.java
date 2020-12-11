package com.choicely.mylibrary.sulkeiset;

import java.util.ArrayList;
import java.util.List;

public class LiikenneValot {

    private final static String TAG = "LiikenneValot";
    List<Lamppu> lights = new ArrayList<>();

    private StatusChangeListener statusChangeListener;

    private int pos;

    public interface StatusChangeListener {
        void onStatusChanged();
    }

    public void updateStatus() {



        for (int i = 0; i < lights.size(); i++) {
            if (lights.get(i).isActive()) {
                lights.get(i).setActive(false);
                if (i + 1 >= lights.size()) {
                    lights.get(0).setActive(true);
                } else {
                    lights.get(i + 1).setActive(true);
                }
                pos = i;
                break;
            }
        }
        statusChangeListener.onStatusChanged();
    }

    public void setOnStatusChangeListener(StatusChangeListener listener) {
        this.statusChangeListener = listener;
    }

    public void addLights(Lamppu valo) {
        lights.add(valo);
    }

    public List<Lamppu> getLights() {
        return lights;
    }

    public int getPos() {
        return pos;
    }


}


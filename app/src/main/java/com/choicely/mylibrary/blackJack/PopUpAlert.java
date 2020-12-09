package com.choicely.mylibrary.blackJack;

import android.app.AlertDialog;
import android.view.View;

public class PopUpAlert {


    public void alertPopUp(View view, String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message);

        AlertDialog alert = builder.create();
        alert.setTitle(title);
        alert.show();
    }
}

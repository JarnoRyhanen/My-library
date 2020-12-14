package com.choicely.mylibrary.blackJack;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.choicely.mylibrary.R;

public class PopUpAlert {

    private final static String TAG = "PopUpAlert";

    public void alertPopUp(View view, String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message);

        AlertDialog alert = builder.create();
        alert.setTitle(title);
        alert.show();
    }

    public void popUpBetting(View view) {
        SeekBar seekBar = view.findViewById(R.id.black_jack_betting_seek_bar);
        TextView textView = view.findViewById(R.id.black_jack_text_view);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setView(view);
        builder.setTitle("Bet between 0 - 1000");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.format("%d €", progress * 25));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        builder.setPositiveButton("Bet", (dialogInterface, i) -> {

            Log.d(TAG, "popUpBetting: " + seekBar.getProgress() * 25);
            onBetAddedListener.onBetAdded(seekBar.getProgress() * 25);
        });
        final AlertDialog dialog = builder.show();

    }

    private BetAddedListener onBetAddedListener;

    public void setBedAddedListener(BetAddedListener listener) {
        this.onBetAddedListener = listener;
    }

    public interface BetAddedListener {
        void onBetAdded(int bet);
    }
}

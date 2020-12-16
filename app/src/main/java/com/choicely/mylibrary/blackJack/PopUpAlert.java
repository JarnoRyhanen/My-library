package com.choicely.mylibrary.blackJack;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.choicely.mylibrary.R;

public class PopUpAlert {

    private final static String TAG = "PopUpAlert";
    private BlackJackActivity blackJackActivity = new BlackJackActivity();

    public void alertPopUp(View view, String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message);

        AlertDialog alert = builder.create();
        alert.setTitle(title);
        alert.show();
    }

    public void rulesPopUp(View view) {

//        TextView title = view.findViewById(R.id.black_jack_rules_title);
        TextView body = view.findViewById(R.id.black_jack_rules_body);
//        String string = view.getResources().getString(R.string.rules_body);
        CharSequence s2 = view.getResources().getText(R.string.rules_body);
        body.setText(s2);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setView(view);

        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        });
        final AlertDialog dialog = builder.show();
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
            blackJackActivity.beginGame();
            onBetAddedListener.onBetAdded(seekBar.getProgress() * 25);
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        });
        final AlertDialog dialog = builder.show();
    }


    private BetAddedListener onBetAddedListener;

    public void setBedAddedListener(BetAddedListener listener) {
        this.onBetAddedListener = listener;
    }

    public void setBlackJackActivity(BlackJackActivity blackJackActivity) {
        this.blackJackActivity = blackJackActivity;
    }

    public interface BetAddedListener {
        void onBetAdded(int bet);
    }
}

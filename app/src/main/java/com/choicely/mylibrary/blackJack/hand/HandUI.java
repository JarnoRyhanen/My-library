package com.choicely.mylibrary.blackJack.hand;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.choicely.mylibrary.R;

public class HandUI {

    @Nullable
    protected Button startGameBtn;
    @Nullable
    protected Button hitButton;
    @Nullable
    protected Button insuranceButton;
    @Nullable
    protected Button splitButton;
    @Nullable
    protected Button standButton;
    @Nullable
    protected Button doubleButton;

    protected TextView handValueText;
    protected RecyclerView cardRecycler;
    private View splitLayout;


    public void findButtonsFromView(View v) {

        startGameBtn = v.findViewById(R.id.black_jack_start_game);
        hitButton = v.findViewById(R.id.bj_hit_button);
        standButton = v.findViewById(R.id.bj_stand_button);
        insuranceButton = v.findViewById(R.id.bj_insurance_button);
        doubleButton = v.findViewById(R.id.bj_double_button);
        splitButton = v.findViewById(R.id.bj_split_button);
    }

    public void findHandSpecificViewsFromView(View v) {
        handValueText = v.findViewById(R.id.hand_size_text_view);
        cardRecycler = v.findViewById(R.id.hand_card_recycler_view);
    }

    public void setSplitLayout(View v) {
        splitLayout = v;
    }

}

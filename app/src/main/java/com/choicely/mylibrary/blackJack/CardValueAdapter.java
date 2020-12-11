package com.choicely.mylibrary.blackJack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.choicely.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

public class CardValueAdapter extends RecyclerView.Adapter<CardValueAdapter.CardValueViewHolder> {

    private final Context context;
    private final List<Card> cardValues = new ArrayList<>();
    private final static String TAG = "CardAdapter";

    public CardValueAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardValueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CardValueViewHolder(LayoutInflater.from(context).inflate(R.layout.black_jack_card_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardValueViewHolder holder, int position) {

        Card cardPos = cardValues.get(position);
//        Log.d(TAG, String.format("%s %d", cardPos.getSuite(), cardPos.getBlackJackCardValue()));
//        getItemCount();
        holder.card.setText(String.format("%s %d", cardPos.getSuite(), cardPos.getBlackJackCardValue()));

    }

    @Override
    public int getItemCount() {
        return cardValues.size();
    }

    public void add(Card card) {
        cardValues.add(card);
    }

    public void clearList() {
        cardValues.clear();
    }


    static class CardValueViewHolder extends RecyclerView.ViewHolder {

        TextView card;

        public CardValueViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.black_jack_recycler_view_card_value);
        }

    }

}

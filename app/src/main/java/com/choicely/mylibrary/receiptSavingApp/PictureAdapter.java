package com.choicely.mylibrary.receiptSavingApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.choicely.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    private final Context context;
    private final List<PictureData> list = new ArrayList<>();

    public PictureAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PictureViewHolder(LayoutInflater.from(context).inflate(R.layout.picture_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {

        //TODO put image titles and aates here

    }

    public void add(PictureData picture) {
        list.add(picture);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    static class PictureViewHolder extends RecyclerView.ViewHolder {

        long pictureID;
        ImageView imageView;
        TextView title;
        TextView date;


        public PictureViewHolder(View view) {
            super(view);
            view.setOnClickListener(onRowClick);

            title = view.findViewById(R.id.picture_list_row_title);
            date = view.findViewById(R.id.picture_list_row_date);
        }

        private View.OnClickListener onRowClick = view -> {

            Context ctx = itemView.getContext();
            Intent intent = new Intent(ctx, ReceiptSavingAppActivity.class);
            intent.putExtra(IntentKeys.PICTURE_ID, pictureID);
            ctx.startActivity(intent);
        };

    }

}

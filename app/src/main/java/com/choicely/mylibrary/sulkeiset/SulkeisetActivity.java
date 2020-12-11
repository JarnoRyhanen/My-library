package com.choicely.mylibrary.sulkeiset;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

public class SulkeisetActivity extends AppCompatActivity {

    private LiikenneValot liikennevalo = new LiikenneValot();

    private final static String TAG = "sulkeiset";

    private LinearLayout linearLayout;
    private Button loopButton;

    private List<Integer> colors = new ArrayList<>();
    private List<View> views = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sulkeiset_activity);

        loopButton = findViewById(R.id.loop);
        linearLayout = findViewById(R.id.linearLayout_sulkeiset);

        addColorsToList();

        liikennevalo.setOnStatusChangeListener(() -> Toast.makeText(SulkeisetActivity.this, "status changed", Toast.LENGTH_SHORT).show());

        addNewLamp();
        addNewViews();

    }

    private void addNewViews() {
        for (Lamppu lamppu : liikennevalo.lights) {
            View view = new View(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(1000, 100));
            linearLayout.addView(view);
            views.add(view);
        }
    }

    private void addNewLamp() {
        for (int i = 0; i < colors.size(); i++) {
            Lamppu lamppu = new Lamppu();
            lamppu.setColor(colors.get(i));
            lamppu.setActive(true);
            liikennevalo.addLights(lamppu);
        }
    }

    private void addColorsToList() {
        colors.add(getResources().getColor(R.color.red));
        colors.add(getResources().getColor(R.color.yellow));
        colors.add(getResources().getColor(R.color.green));
        colors.add(getResources().getColor(R.color.blue));
        colors.add(getResources().getColor(R.color.black));
        colors.add(getResources().getColor(R.color.teal_700));
        colors.add(getResources().getColor(R.color.material_on_surface_disabled));
        colors.add(getResources().getColor(R.color.orange));
        colors.add(getResources().getColor(R.color.purple_500));
        colors.add(getResources().getColor(R.color.teal_200));
        colors.add(getResources().getColor(R.color.material_on_surface_emphasis_medium));
        colors.add(getResources().getColor(R.color.design_default_color_primary_dark));
        colors.add(getResources().getColor(R.color.fire));
        colors.add(getResources().getColor(R.color.turquoise));
        colors.add(getResources().getColor(R.color.hulk));
        colors.add(getResources().getColor(R.color.velvet_green));
        colors.add(getResources().getColor(R.color.magenta));
    }


    public void onClickLayout(View view) {
        liikennevalo.updateStatus();

        for (int i = liikennevalo.getPos(); i < liikennevalo.lights.size(); i++) {
            views.get(i).setBackgroundColor(colors.get(i));
            break;
        }
    }

    private void setColorsToWhite() {
        for (int i = 0; i < views.size(); i++) {
            views.get(i).setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    public void onButtonClicked(View view) {
        loop();
        loopButton.setClickable(false);
    }

    private void loop() {
        liikennevalo.updateStatus();

        Handler handler = new Handler();

        for (int i = liikennevalo.getPos(); i < liikennevalo.lights.size(); i++) {
            setColorsToWhite();
            views.get(i).setBackgroundColor(colors.get(i));
            handler.postDelayed(this::loop, 500);
            break;
        }
    }
}




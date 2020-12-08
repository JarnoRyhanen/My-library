package com.choicely.mylibrary.otherTests;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mylibrary.R;

public class SecondaryActivity extends AppCompatActivity {

    private ImageView image;
    private final static String TAG = "SecondaryActivity";

    private float pivotX = 0;
    private float pivotY = 0;
    private float newX, newY, startPosX, startPosY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity);


        image = findViewById(R.id.secondary_activity_image);
        image.setOnTouchListener(onImageTouch);

    }

    View.OnTouchListener onImageTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d(TAG, "touched down");
                    startPosX = (int) event.getX();
                    startPosY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    newX = (int) (event.getX() - startPosX);
                    newY = (int) (event.getY() - startPosY);
                    Log.d(TAG, "moving: ( X: " + newX + ", Y: " + newY + ")");
                    image.setRotationY((pivotX +newX)/50);
                    image.setRotationX((pivotY + newY)/50);

                    break;
                case MotionEvent.ACTION_UP:
                    Log.d(TAG, "touched up");
                    pivotX += newX;
                    pivotY += newY;
                    break;
            }
            return true;
        }

    };

}
